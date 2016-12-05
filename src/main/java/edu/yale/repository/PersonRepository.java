package edu.yale.repository;

import edu.yale.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Page<Person> findByLastname(String lastName, Pageable pageable);
    Page<Person> findByTitle(String str, Pageable pageable);
    Page<Person> findByAlias(String str, Pageable pageable);
    Page<Person> findByNations(String str, Pageable pageable);
    Page<Person> findByStates(String str, Pageable pageable);
    Page<Person> findByCities(String str, Pageable pageable);
}
