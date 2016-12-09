package edu.yale.controller;

import edu.yale.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdvancedController {

    @RequestMapping(value = "/advanced_search", method = RequestMethod.GET)
    public ModelAndView greetingForm(@ModelAttribute Person person) {
        ModelAndView modelAndView = new ModelAndView("advanced_search");
        return modelAndView;
    }
}

