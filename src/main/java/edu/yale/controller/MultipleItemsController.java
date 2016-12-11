package edu.yale.controller;

import edu.yale.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MultipleItemsController {

    @RequestMapping(value = "/multipleitems1", method = RequestMethod.GET)
    public ModelAndView greetingForm(@ModelAttribute Person person) {
        ModelAndView modelAndView = new ModelAndView("multipleitems");
        return modelAndView;
    }
}

