package edu.yale.service.impl;

import edu.yale.domain.Person;
import edu.yale.service.PersonService;
import edu.yale.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Page<Person> findByFullname(String fullName, Pageable pageable) {
        return personRepository.findByFullName(fullName, pageable);
    }

    @Override
    public Page<Person> findByPersonId(long id, Pageable pageable) {
        return personRepository.findByPersonId(id, pageable);
    }

    @Override
    public Page<Person> findByAlias(String str, Pageable pageable) {
        return personRepository.findByAlias(str, pageable);
    }

    @Override
    public Page<Person> findByTitle(String str, Pageable pageable) {
        return personRepository.findByTitle(str, pageable);
    }

    @Override
    public Page<Person> findByNations(String str, Pageable pageable) {
        return personRepository.findByNations(str, pageable);
    }

    @Override
    public Page<Person> findByStates(String str, Pageable pageable) {
        return personRepository.findByStates(str, pageable);
    }

    @Override
    public Page<Person> findByCities(String str, Pageable pageable) {
        return personRepository.findByCities(str, pageable);
    }

    @Transactional
    public Page<Person> findAllPageable(Pageable pageable) {
        return personRepository.findAll(pageable);
    }



    @Transactional
    @Override
    public Iterable<Person> save(Iterable<Person> persons) {
        return personRepository.save(persons);
    }

}
