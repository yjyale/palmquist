package edu.yale.controller;

import edu.yale.domain.Pager;
import edu.yale.domain.Person;
import edu.yale.service.PersonService;
import edu.yale.spec.PersonSpecification;
import edu.yale.spec.SearchOperation;
import edu.yale.spec.SpecSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Advanced Search
 */
@Controller
public class MultipleItemController {

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
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    public MultipleItemController(PersonService studentService) {
        this.personService = studentService;
    }

    /*
        The request looks like:
        http://localhost:8080/advanced_search?title=&fullName=Bard&alias=&nations=&states=&cities=
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


        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        final int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        final int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;


        Specifications spec = null;

        // Create maps

        // Populate with PrepareSpecification for each form param
        final Map<String, PersonSpecification> specificationsMap = new HashMap<>();

        if (title != null && !title.isEmpty()) {
            specificationsMap.put(TITLE,
                    new PersonSpecification(new SpecSearchCriteria(TITLE, SearchOperation.CONTAINS, title)));
        }

        if (fullName != null && !fullName.isEmpty()) {
            specificationsMap.put(FULL_NAME,
                    new PersonSpecification(new SpecSearchCriteria(FULL_NAME, SearchOperation.CONTAINS, fullName)));
        }

        if (alias != null && !alias.isEmpty()) {
            specificationsMap.put(ALIAS,
                    new PersonSpecification(new SpecSearchCriteria(ALIAS, SearchOperation.CONTAINS, alias)));
        }

        if (cities != null && !cities.isEmpty()) {
            specificationsMap.put(CITIES,
                    new PersonSpecification((new SpecSearchCriteria(CITIES, SearchOperation.CONTAINS, cities))));
        }

        if (nations != null && !nations.isEmpty()) {
            specificationsMap.put(NATIONS,
                    new PersonSpecification((new SpecSearchCriteria(NATIONS, SearchOperation.CONTAINS, nations))));
        }

        if (states != null && !states.isEmpty()) {
            specificationsMap.put(STATES,
                    new PersonSpecification((new SpecSearchCriteria(STATES, SearchOperation.CONTAINS, states))));
        }

        // Create a LogicOperator map, one entry for each form logic param

        final Map<String, LogicOperator> logicalOpMap = new HashMap<>();
        logicalOpMap.put(TITLE, LogicOperator.valueOf(titleOption));
        logicalOpMap.put(FULL_NAME, LogicOperator.valueOf(fullNameOption));
        logicalOpMap.put(ALIAS, LogicOperator.valueOf(aliasOption));
        logicalOpMap.put(CITIES, LogicOperator.valueOf(citiesOption));
        logicalOpMap.put(NATIONS, LogicOperator.valueOf(nationsOption));
        logicalOpMap.put(STATES, LogicOperator.valueOf(statesOption));


        final Set<String> keys = specificationsMap.keySet();

        // Create a Specification object

        for (final String k : keys) {
            final PersonSpecification fieldSpec = specificationsMap.get(k);
            final LogicOperator op = logicalOpMap.get(k);
            spec = addSpec(op, spec, fieldSpec);
        }


        if (spec == null) {
            // todo
        }

        // Finally search

        final Page<Person> results = personService.findAll(spec, new PageRequest(evalPage, evalPageSize));
        final Pager pager = new Pager(results.getTotalPages(), results.getNumber(), BUTTONS_TO_SHOW);

        // Populate the model for the form. Add request parameters so pagination can work:

        model.addAttribute("persons", results);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

        model.addAttribute(TITLE, title);
        model.addAttribute(FULL_NAME, fullName);
        model.addAttribute(ALIAS, alias);
        model.addAttribute(CITIES, cities);
        model.addAttribute(NATIONS, nations);
        model.addAttribute(STATES, states);

        model.addAttribute(TITLE_OPTION, titleOption);
        model.addAttribute(FULL_NAME_OPTION, fullNameOption);
        model.addAttribute(ALIAS_OPTION, aliasOption);
        model.addAttribute(CITIES_OPTION, citiesOption);
        model.addAttribute(NATIONS_OPTION, nationsOption);
        model.addAttribute(STATES_OPTION, statesOption);
        return model;
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
                return Specifications.not(add);
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
}

