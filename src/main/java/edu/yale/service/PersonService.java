package edu.yale.service;

import edu.yale.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PersonService {

    Page<Person> findAll(Specification<Person> var1, Pageable pageable);

    Page<Person> findByPersonId(long id, Pageable pageable);

    Iterable<Person> save(Iterable<Person> persons);

}
