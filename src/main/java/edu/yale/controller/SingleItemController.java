package edu.yale.controller;

import edu.yale.domain.Person;
import edu.yale.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SingleItemController {

    private PersonService personService;

    @Autowired
    public SingleItemController(PersonService studentService) {
        this.personService = studentService;
    }

    @RequestMapping(value = "/singleitem", method = RequestMethod.GET)
    public String greetingForm(Model model,  @RequestParam(value = "id", required = false) Long id, @RequestParam(value="fullName", required = false)  String fullName) {

        Person person = new Person();

        if (id != null) {
            Page<Person> persons = personService.findByPersonId(id, new PageRequest(0, 1));
            person = persons.iterator().next();
        } else if (fullName != null) {
            Page<Person> persons = personService.findByFullname(fullName, new PageRequest(0, 1));
            person = persons.iterator().next();
        }

        model.addAttribute("person", person);
        return "singleitem";
    }
}

