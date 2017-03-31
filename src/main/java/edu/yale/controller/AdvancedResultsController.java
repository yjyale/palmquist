package edu.yale.controller;

import edu.yale.domain.Pager;
import edu.yale.domain.Person;
import edu.yale.service.PersonService;
import edu.yale.spec.PersonSpecification;
import edu.yale.spec.SearchOperation;
import edu.yale.spec.SpecSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Advanced Search
 */
@Controller
public class AdvancedResultsController {

    private PersonService personService;

    // Form constants
    private static final String TITLE = "title";
    private static final String FULL_NAME = "fullName";
    private static final String ALIAS = "alias";
    private static final String CITIES = "cities";
    private static final String NATIONS = "nations";
    private static final String STATES = "states";
    private static final String TITLE_OPTION = "titleOption";
    private static final String FULL_NAME_OPTION = "fullNameOption";
    private static final String ALIAS_OPTION = "aliasOption";
    private static final String CITIES_OPTION = "citiesOption";
    private static final String NATIONS_OPTION = "nationsOption";
    private static final String STATES_OPTION = "statesOption";

    // Pagination options
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 25;
    private static final int[] PAGE_SIZES = {25, 50, 100};

    @Autowired
    public AdvancedResultsController(PersonService studentService) {
        this.personService = studentService;
    }

    /*
        The request looks like:
        http://localhost:8080/advanced_search?title=&fullName=Bard&alias=&nations=&states=&cities=

        Not using a Map in method signature to keep the method shorter

        // todo simplify

     */


    @RequestMapping(value = "/multipleitems", method = RequestMethod.GET)
    public Model greetingForm(final Model model,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "fullName", required = false) String fullName,
                              @RequestParam(value = TITLE, required = false) String title,
                              @RequestParam(value = ALIAS, required = false) String alias,
                              @RequestParam(value = CITIES, required = false) String cities,
                              @RequestParam(value = STATES, required = false) String states,
                              @RequestParam(value = NATIONS, required = false) String nations,
                              @RequestParam(value = "fullNameOption", required = false) String fullNameOption,
                              @RequestParam(value = "titleOption", required = false) String titleOption,
                              @RequestParam(value = "aliasOption", required = false) String aliasOption,
                              @RequestParam(value = "cityOption", required = false) String citiesOption,
                              @RequestParam(value = "stateOption", required = false) String statesOption,
                              @RequestParam(value = "nationOption", required = false) String nationsOption) {


        final int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        final int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        if (title != null && !title.matches(".*[a-zA-Z]+.*")) {
            title = "";
        }

        if (fullName != null && !fullName.matches(".*[a-zA-Z]+.*")) {
            fullName = "";
        }

        if (alias != null && !alias.matches(".*[a-zA-Z]+.*")) {
            alias = "";
        }

        if (nations != null && !nations.matches(".*[a-zA-Z]+.*")) {
            nations = "";
        }

        if (states != null && !states.matches(".*[a-zA-Z]+.*")) {
            states = "";
        }


        if (cities != null && !cities.matches(".*[a-zA-Z]+.*")) {
            cities = "";
        }

        // debug:

        System.out.println(titleOption);

        Specifications spec = null;

        // Create maps:

        // 1. A map to represent all form values that should be passed back (like Spring's requestMap)

        fullName = fullName.toLowerCase();
        title = title.toLowerCase();
        alias = alias.toLowerCase();
        nations = nations.toLowerCase();
        cities = cities.toLowerCase();
        states = states.toLowerCase();

        final Map<String, String> formParams = populateFormMap(fullName, title, alias, cities, states, nations, fullNameOption,
                titleOption, aliasOption, citiesOption, statesOption, nationsOption);

        // 2. Populate with PrepareSpecification for each form param

        final Map<String, String> requestParams = populateFormSansOptions(fullName, title, alias, cities, states, nations);
        final Set<String> reqParamSet = requestParams.keySet();

        final Map<String, PersonSpecification> specificationsMap = new HashMap<>();

        for (String s : reqParamSet) {
            if (isValid(requestParams.get(s))) {
                if (formParams.get(s.concat("Option")).contains("NOT")) {
                    // System.out.println("Found a NOT");
                    specificationsMap.put(s, new PersonSpecification(new SpecSearchCriteria(s, SearchOperation.NEGATION, requestParams.get(s))));
                } else {
                    // System.out.println("Form param:" + s.concat("Option"));
                    specificationsMap.put(s, new PersonSpecification(new SpecSearchCriteria(s, SearchOperation.CONTAINS, requestParams.get(s))));
                }
            }
        }

        // re organize the order:


        // 3. Create a LogicOperator map, one entry for each form logic param

        final Map<String, LogicOperator> logicalOpMap = populateLogicOperatorMap(fullNameOption, titleOption,
                aliasOption, citiesOption, statesOption, nationsOption);


        final Set<String> keys = specificationsMap.keySet();

        // Create a Specification object

        // 1. order so that not works with and
        // 2. if precursor is AND and a successor is OR, set the precusor to OR. This is taken care of foundOR

        for (final String k : keys) {
            final PersonSpecification fieldSpec = specificationsMap.get(k);
            final LogicOperator op = logicalOpMap.get(k);

            if (op == LogicOperator.NOT) {
                spec = addSpec(LogicOperator.NOT, spec, fieldSpec);
            }
        }


        int foundOR = 0;

        for (final String k : keys) {
            final PersonSpecification fieldSpec = specificationsMap.get(k);
            final LogicOperator op = logicalOpMap.get(k);

            if (op == LogicOperator.OR) {
                spec = addSpec(op, spec, fieldSpec);
                foundOR++; // to make AND/OR work
            } 
        }


        for (final String k : keys) {
            final PersonSpecification fieldSpec = specificationsMap.get(k);
            final LogicOperator op = logicalOpMap.get(k);

            if (op == LogicOperator.AND && foundOR == 0) {
                spec = addSpec(op, spec, fieldSpec);
            } else if (op == LogicOperator.AND && foundOR > 0) {
                spec = addSpec(LogicOperator.OR, spec, fieldSpec);  // this is to make AND/OR searches work
            }
        }

        // Finally search

        final Page<Person> results = personService.findAll(spec, new PageRequest(evalPage, evalPageSize,
                Sort.Direction.ASC, "fullName", "title"));
        final Pager pager = new Pager(results.getTotalPages(), results.getNumber(), BUTTONS_TO_SHOW);

        // Populate the model for the form. Add request parameters so pagination can work:

        model.addAttribute("persons", results);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

        // add form parameters for paginated search
        // adds model.addAttribute(TITLE, title);

        final Set<String> formKeys = formParams.keySet();

        for (final String f : formKeys) {
            model.addAttribute(f, formParams.get(f));
        }

        model.addAttribute("numberResults", results.getTotalElements());
        return model;
    }

    private boolean isValid(String title) {
        return title != null && title.length() > 0;
    }

    private Map<String, LogicOperator> populateLogicOperatorMap(@RequestParam(value = "fullNameOption", required = false) String fullNameOption, @RequestParam(value = "titleOption", required = false) String titleOption, @RequestParam(value = "aliasOption", required = false) String aliasOption, @RequestParam(value = "cityOption", required = false) String citiesOption, @RequestParam(value = "stateOption", required = false) String statesOption, @RequestParam(value = "nationOption", required = false) String nationsOption) {
        final Map<String, LogicOperator> logicalOpMap = new HashMap<>();
        logicalOpMap.put(TITLE, LogicOperator.valueOf(titleOption));
        logicalOpMap.put(FULL_NAME, LogicOperator.valueOf(fullNameOption));
        logicalOpMap.put(ALIAS, LogicOperator.valueOf(aliasOption));
        logicalOpMap.put(NATIONS, LogicOperator.valueOf(nationsOption));
        logicalOpMap.put(STATES, LogicOperator.valueOf(statesOption));
        logicalOpMap.put(CITIES, LogicOperator.valueOf(citiesOption));
        return logicalOpMap;
    }


    private Map<String, String> populateFormMap(@RequestParam(value = "fullName", required = false) String fullName,
                                                @RequestParam(value = TITLE, required = false) String title,
                                                @RequestParam(value = ALIAS, required = false) String alias,
                                                @RequestParam(value = CITIES, required = false) String cities,
                                                @RequestParam(value = STATES, required = false) String states,
                                                @RequestParam(value = NATIONS, required = false) String nations,
                                                @RequestParam(value = "fullNameOption", required = false) String fullNameOption,
                                                @RequestParam(value = "titleOption", required = false) String titleOption,
                                                @RequestParam(value = "aliasOption", required = false) String aliasOption,

                                                @RequestParam(value = "cityOption", required = false) String citiesOption,
                                                @RequestParam(value = "stateOption", required = false) String statesOption,
                                                @RequestParam(value = "nationOption", required = false) String nationsOption) {
        final Map<String, String> formParams = new HashMap<>();
        formParams.put(TITLE, title);
        formParams.put(FULL_NAME, fullName);
        formParams.put(ALIAS, alias);
        formParams.put(CITIES, cities);
        formParams.put(STATES, states);
        formParams.put(NATIONS, nations);
        formParams.put(TITLE_OPTION, titleOption);
        formParams.put(FULL_NAME_OPTION, fullNameOption);
        formParams.put(ALIAS_OPTION, aliasOption);
        formParams.put(CITIES_OPTION, citiesOption);
        formParams.put(STATES_OPTION, statesOption);
        formParams.put(NATIONS_OPTION, nationsOption);
        return formParams;
    }

    private Map<String, String> populateFormSansOptions(@RequestParam(value = "fullName", required = false) String fullName,
                                                        @RequestParam(value = TITLE, required = false) String title,
                                                        @RequestParam(value = ALIAS, required = false) String alias,
                                                        @RequestParam(value = CITIES, required = false) String cities,
                                                        @RequestParam(value = STATES, required = false) String states,
                                                        @RequestParam(value = NATIONS, required = false) String nations) {
        final Map<String, String> formParams = new HashMap<>(); // This should be replaced with Spring Map
        formParams.put(TITLE, title);
        formParams.put(FULL_NAME, fullName);
        formParams.put(ALIAS, alias);
        formParams.put(CITIES, cities);
        formParams.put(STATES, states);
        formParams.put(NATIONS, nations);
        return formParams;
    }


    private static Specifications addSpec(final LogicOperator logicOperation,
                                          final Specifications specification, final PersonSpecification add) {

        if (specification == null) {
            return Specifications.where(add);
        }

        switch (logicOperation) {
            case AND:
                return specification.and(add);
            case OR:
                return specification.or(add);
            case NOT:
                return Specifications.where(add);
            default:
                return null;
        }

    }

    private enum LogicOperator {
        AND("AND"),
        OR("OR"),
        NOT("NOT");

        String name;

        LogicOperator(String name) {
            this.name = name;
        }
    }

    boolean oneOrExists(
            @RequestParam(value = "fullNameOption", required = false) String fullNameOption,
            @RequestParam(value = "titleOption", required = false) String titleOption,
            @RequestParam(value = "aliasOption", required = false) String aliasOption,
            @RequestParam(value = "cityOption", required = false) String citiesOption,
            @RequestParam(value = "stateOption", required = false) String statesOption,
            @RequestParam(value = "nationOption", required = false) String nationsOption
    ) {

        if (titleOption.equals("OR") || fullNameOption.equals("OR")
                || aliasOption.equals("OR") || citiesOption.equals("OR") || statesOption.equals("OR")
                || nationsOption.equals("OR")) {
            return true;
        }
        return false;
    }


}
