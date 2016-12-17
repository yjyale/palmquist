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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Controller
public class QuickSearchResultsController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 25;
    private static final int[] PAGE_SIZES = {25, 50, 100};

    private PersonService personService;

    @Autowired
    public QuickSearchResultsController(PersonService studentService) {
        this.personService = studentService;
    }

    /**
     * Handles all requests
     *
     * @param pageSize
     * @param page
     * @return model and view
     */
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public ModelAndView showPersonsPage(@ModelAttribute Greeting greeting,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value= "keywords", required=false) String keywords,
                                        @RequestParam(value = "keywordsOption", required = false) String keywordsOption) {

        ModelAndView modelAndView = new ModelAndView("results");

        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;

        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        String keywordOption = greeting.getKeywordOption();
        System.out.println("Keywords option" + keywordOption);

        String keywordsStr = greeting.getContent().toLowerCase();
        System.out.println("Keywords:" + keywordsStr);

        if (keywordsStr == null) {
            System.out.println("Error with page");
            throw new FormException();
        }


        List<String> keywordsList = Arrays.asList(keywordsStr.split("\\s+"));

        Specifications spec = null;


        // add each Specification object in a loop:
        for (final String s : keywordsList) {

            PersonSpecification personSpecification = new PersonSpecification(
                    new SpecSearchCriteria("index", SearchOperation.CONTAINS, s));

            if (spec == null) {
                spec = Specifications.where(personSpecification);
            } else {

                if (keywordOption.contains("ALL")) {
                    spec = spec.and(personSpecification);
                } else if (keywordOption.contains("ANY")) {
                    spec = spec.or(personSpecification);
                } else if (keywordOption.contains("entire phrase")) {
                    spec = Specifications.where(new PersonSpecification(
                            new SpecSearchCriteria("index", SearchOperation.CONTAINS, keywordsStr))); //forget the previous set
                    break;
                } else {
                    System.out.println("Unknown option");
                }
            }

        }

        if (spec == null) {
            System.out.println("spec null");
            throw new FormException();
        }

        final Page<Person> results = personService.findAll(spec, new PageRequest(evalPage, evalPageSize,
                Sort.Direction.ASC, "fullName"));
        final Pager pager = new Pager(results.getTotalPages(), results.getNumber(), BUTTONS_TO_SHOW);

        // Don't show pagination if no results found
        if (results.getTotalElements() != 0) {
            System.out.println("No results found");
            System.out.println("Total number of elements:" + results.getTotalElements());
        } else {
            System.out.println("No results found");
        }

        modelAndView.addObject("persons", results);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("keywords", keywords);
        modelAndView.addObject("keywordsOption", keywordOption);
        modelAndView.addObject("numberResults", results.getTotalElements());
        return modelAndView;
    }



}
