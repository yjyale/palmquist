package edu.yale.controller;

import edu.yale.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdvancedController {

    @RequestMapping(value = "/advanced", method = RequestMethod.GET)
    public String greetingForm(@ModelAttribute Person person) {
        ModelAndView modelAndView = new ModelAndView("persons");

        // FIXME: remove the stub

        Person person1 = new Person();
        person1.setFullName("Nancy Ajram");
        person1.setAlbumbox("10");
        person1.setCities("Dubai");
        person1.setNations("UAE");
        modelAndView.addObject(person);

        return "advanced";
    }
}

