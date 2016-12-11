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


@Controller
public class MultipleItemController {

    private PersonService personService;

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    public MultipleItemController(PersonService studentService) {
        this.personService = studentService;
    }

    /*
        The request looks something like:
        http://localhost:8080/singleitem?title=&fullName=Bard&alias=&nations=&states=&cities=
     */
    @RequestMapping(value = "/multipleitems", method = RequestMethod.GET)
    public Model greetingForm(final Model model,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "fullName", required = false) String fullName,
                               @RequestParam(value = "title", required = false) String title,
                               @RequestParam(value = "alias", required = false) String alias,
                               @RequestParam(value = "cities", required = false) String cities,
                               @RequestParam(value = "states", required = false) String states,
                               @RequestParam(value = "nations", required = false) String nations,
                               @RequestParam(value = "fullNameOption", required = false) String fullNameOption,
                               @RequestParam(value = "titleOption", required = false) String titleOption,
                               @RequestParam(value = "aliasOption", required = false) String aliasOption,
                               @RequestParam(value = "cityOption", required = false) String citiesOption,
                               @RequestParam(value = "stateOption", required = false) String statesOption,
                               @RequestParam(value = "nationOption", required = false) String nationsOption) {


        // return results
        System.out.println("Custom search");

        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        System.out.println("Eval page size:" + evalPageSize);

        System.out.println("Eval page:" + evalPage);

        System.out.println("Title:" + title);

        if (title == null && fullName == null) {
            //title = "Madame";
            //fullName = "Bard";
        } else {
            System.out.println("Not null params title, fullname");
        }

        System.out.println("Full Name" + fullName);

        final PersonSpecification spec1 =
                new PersonSpecification(new SpecSearchCriteria("title", SearchOperation.CONTAINS, title));
        final PersonSpecification spec2 =
                new PersonSpecification(new SpecSearchCriteria("fullName", SearchOperation.CONTAINS, fullName));

        final Page<Person> results1 = personService.findAll(Specifications.where(spec1).and(spec2),
                new PageRequest(evalPage, evalPageSize));

        System.out.println("Results size:" + results1.getNumberOfElements());
        //final Pager pager = new Pager(results.getTotalPages(), results.getNumber(), 5);
        Pager pager = new Pager(results1.getTotalPages(), results1.getNumber(), BUTTONS_TO_SHOW);


        model.addAttribute("persons", results1);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        model.addAttribute("title", title);
        model.addAttribute("fullName", fullName);
        return model;


    }

    private boolean allAndSearch(String titleOption, String fullNameOption, String aliasOption, String cityOption, String stateOption, String nationOption) {
        if (isAnd(titleOption) && isAnd(fullNameOption)
                && isAnd(aliasOption) && isAnd(cityOption) && isAnd(stateOption) && isAnd(nationOption)) {
            return true;
        }
        return false;
    }

    private boolean allAndSearch(String... options) {

        boolean result = true;

        for (String s : options) {
            if (!isAnd(s)) {
                result = false;
                break;
            }
        }


        return result;
    }


    private boolean allOrSearch(String... options) {

        boolean result = true;

        for (String s : options) {
            if (!isOr(s)) {
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean allOrSearch(String titleOption, String fullNameOption, String aliasOption, String cityOption, String stateOption, String nationOption) {
        if (isOr(titleOption) && isOr(fullNameOption)
                && isOr(aliasOption) && isOr(cityOption) && isOr(stateOption) && isOr(nationOption)) {
            return true;
        }
        return false;
    }

    private boolean isAnd(String string) {
        return string != null && string.equals("AND");
    }

    private boolean isOr(String string) {
        return string.equals("OR");
    }

    private boolean isNot(String string) {
        return string.equals("NOT");
    }


    private boolean containsField(final Map<SearchFields, String> fields, final SearchFields request) {
        return fields.containsKey(request);
    }

    private boolean singleField(final Map<SearchFields, String> fields) {
        System.out.println("Field size" + fields.size());
        return fields.size() == 1;
    }

    // populate form objects
    // sigh
    private Map<SearchFields, String> populateMap(String title, String fullName, String alias, String cities, String states, String nations) {
        final Map<SearchFields, String> fields = new HashMap<>();

        if (!fullName.isEmpty()) {
            fields.put(SearchFields.FullName, fullName);
        }
        if (!title.isEmpty()) {
            fields.put(SearchFields.Title, title);
            System.out.println(title);

        }

        if (!alias.isEmpty()) {
            fields.put(SearchFields.Alias, alias);

        }

        if (!cities.isEmpty()) {
            fields.put(SearchFields.City, cities);

        }

        if (!states.isEmpty()) {
            fields.put(SearchFields.State, states);

        }

        if (!nations.isEmpty()) {
            fields.put(SearchFields.Nation, nations);

        }
        return fields;
    }

    private enum SearchFields {
        FullName,
        Title,
        Alias,
        City,
        State,
        Nation
    }

    private enum LogicOperator {
        AND,
        OR,
        NOT
    }
}

