package edu.yale.service.impl;

import edu.yale.domain.Person;
import edu.yale.repository.PersonRepository;
import edu.yale.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Page<Person> findAll(Specification<Person> var1, Pageable pageable) {
        return personRepository.findAll(var1, pageable);
    }

    @Override
    public Page<Person> findByPersonId(long id, Pageable pageable) {
        return personRepository.findByPersonId(id, pageable);
    }


    @Transactional
    @Override
    public Iterable<Person> save(Iterable<Person> persons) {
        return personRepository.save(persons);
    }

}
