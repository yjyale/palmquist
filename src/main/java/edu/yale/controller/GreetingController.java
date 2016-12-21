package edu.yale.controller;

import edu.yale.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GreetingController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greetingForm(final Model model) {
        model.addAttribute("person", new Person());
        return "greeting";
    }
}

