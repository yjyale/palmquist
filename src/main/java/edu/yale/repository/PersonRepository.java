package edu.yale.repository;

import edu.yale.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository //extends PagingAndSortingRepository<Person, Long>
        extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person>

{

    Page<Person> findByTitleOrFullNameOrAliasOrNationsOrCitiesOrStates(List<String> keywords, Pageable pageable);

    Page<Person> findByTitleInOrFullNameInOrAliasInOrNationsInOrCitiesInOrStatesIn(List<String> keywords, Pageable pageable);

    Page<Person> findByFullNameIn(List<String> keywords, Pageable pageable);


    // Page<Person> findAll(Specification<Person> var1);
    Page<Person> findByPersonId(long id, Pageable pageable);

    Page<Person> findByFullName(String lastName, Pageable pageable);

    Page<Person> findByFullNameContaining(String lastName, Pageable pageable);

    Page<Person> findByTitle(String str, Pageable pageable);

    Page<Person> findByAlias(String str, Pageable pageable);

    Page<Person> findByAliasContaining(String str, Pageable pageable);

    Page<Person> findByNations(String str, Pageable pageable);

    Page<Person> findByNationsContaining(String str, Pageable pageable);

    Page<Person> findByStates(String str, Pageable pageable);

    Page<Person> findByStatesContaining(String str, Pageable pageable);

    Page<Person> findByCities(String str, Pageable pageable);

    Page<Person> findByCitiesContaining(String str, Pageable pageable);

    Page<Person> findByTitleAndFullNameAndAliasAndNationsAndStates(String str, String str1, String str2, String str3, String str4, Pageable pageable);

    Page<Person> findByTitleAndAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);

    Page<Person> findByFullNameAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);

    Page<Person> findByTitleAndAndFullNameAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);

    Page<Person> findByTitleAndAndFullNameAndAliasAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);

    Page<Person> findByTitleAndAndFullNameAndAliasAndNationsAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);


    // 6 columnns
    Page<Person> findByTitleAndFullNameAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, String str5, Pageable pageable);

}
