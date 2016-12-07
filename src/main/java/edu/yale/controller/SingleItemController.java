package edu.yale.controller;

import edu.yale.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SingleItemController {

    @RequestMapping(value = "/singleitem", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        // FIXME: remove the stub
        Person person = new Person();
        person.setFullName("Nancy Ajram");
        person.setAlbumbox("10");
        person.setCities("Dubai");
        person.setNations("UAE");
        model.addAttribute("person", person);
        return "singleitem";
    }
}

