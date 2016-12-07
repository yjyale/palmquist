package edu.yale.service;

import edu.yale.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

    /**
     * Finds a "page" of persons
     *
     * @param pageable
     * @return {@link Page} instance
     */
    Page<Person> findByFullname(String fullName, Pageable pageable);
    Page<Person> findByPersonId(long id, Pageable pageable);

    Page<Person> findByTitle(String str, Pageable pageable);
    Page<Person> findByAlias(String str, Pageable pageable);
    Page<Person> findByNations(String str, Pageable pageable);
    Page<Person> findByStates(String str, Pageable pageable);
    Page<Person> findByCities(String str, Pageable pageable);

    /**
     * Saves collection of persons
     *
     * @param persons
     * @return collection of persons
     */
    Iterable<Person> save(Iterable<Person> persons);

}
