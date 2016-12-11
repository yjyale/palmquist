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
import java.util.List;


@Controller
public class QuickSearchResultsController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

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
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ModelAndView showPersonsPage(@ModelAttribute Greeting greeting,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value= "keywords", required=false) String keywords,
                                        @RequestParam(value = "keywordsOption", required = false) String keywordsOption) {

        ModelAndView modelAndView = new ModelAndView("persons");

        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;

        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        String keywordOption = greeting.getKeywordOption();
        System.out.println("Keywords option" + keywordOption);

        String keywordsStr = greeting.getContent();
        System.out.println("Keywords:" + keywordsStr);

        if (keywordsStr == null) {
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
                    //personService.findByIndexLike("madame").and("bard")
                }

                if (keywordOption.contains("ANY")) {
                    spec = spec.or(personSpecification);
                }

            }

        }

        if (spec == null) {
            System.out.println("spec null");
            throw new FormException();
        }

        final Page<Person> results = personService.findAll(spec, new PageRequest(evalPage, evalPageSize));
        final Pager pager = new Pager(results.getTotalPages(), results.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("persons", results);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }



}
