package edu.yale.repository;

import edu.yale.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    Page<Person> findByPersonId(long id, Pageable pageable);
}
