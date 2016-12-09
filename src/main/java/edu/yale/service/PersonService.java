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
     * <p>
     * <p>
     * Title
     * Full Name
     * Bard
     * Also Known As
     * Nations
     * States
     * France
     * Cities
     */

    Page<Person> findAll(Pageable pageable);
    Page<Person> findByPersonId(long id, Pageable pageable);

    Page<Person> findByTitle(String str, Pageable pageable);
    Page<Person> findByFullname(String fullName, Pageable pageable);
    Page<Person> findByAlias(String str, Pageable pageable);
    Page<Person> findByNations(String str, Pageable pageable);
    Page<Person> findByStates(String str, Pageable pageable);
    Page<Person> findByCities(String str, Pageable pageable);

    /*

    // 2 columns

    Page<Person> findByTitleAndFullname(String str1, String str2, Pageable pageable);
    Page<Person> findByTitleAndAlias(String str, String str2, Pageable pageable);
    Page<Person> findByTitleAndNations(String str, String str2, Pageable pageable);
    Page<Person> findByTitleAndStates(String str, String str2,Pageable pageable);
    Page<Person> findByTitleAndCities(String str, String str2,Pageable pageable);
    Page<Person> findByFullnameAndAlias(String str, String str2,Pageable pageable);
    Page<Person> findByFullnameAndNations(String str, String str2,Pageable pageable);
    Page<Person> findByFullnameAndStates(String str, String str2,Pageable pageable);
    Page<Person> findByFullnameAndCities(String str, String str2,Pageable pageable);
    Page<Person> findByAliasAndNations(String str, String str2,Pageable pageable);
    Page<Person> findByAliasAndStates(String str, String str2,Pageable pageable);
    Page<Person> findByAliasAndCities(String str, String str2,Pageable pageable);
    Page<Person> findByNationsAndStates(String str,String str2, Pageable pageable);
    Page<Person> findByNationsAndCities(String str, String str2,Pageable pageable);
    Page<Person> findByStatesAndCities(String str, String str2,Pageable pageable);


    // 3 columns


    Page<Person> findByTitleAndFullnameAndAlias(String str, String str1, String str2, Pageable pageable);
    Page<Person> findByTitleAndFullnameAndNations(String str, String str1, String str2, Pageable pageable);
    Page<Person> findByTitleAndFullnameAndStates(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByTitleAndFullnameAndCities(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByTitleAndAliasAndNations(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByTitleAndAliasAndStates(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByTitleAndAliasAndCities(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByTitleAndNationsAndStates(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByTitleAndStatesAndCities(String str, String str1, String str2,Pageable pageable);

    Page<Person> findByFullnameAndAliasAndNations(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByFullNameAndNationsAndCities(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByFullNameAndCitiesAndStates(String str, String str1, String str2,Pageable pageable);


    Page<Person> findByAliasAndNationsAndStates(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByAliasAndStatesAndCities(String str, String str1, String str2,Pageable pageable);
    Page<Person> findByNationsAndStatesAndCities(String str, String str1, String str2,Pageable pageable);



    // 4+ columns

    Page<Person> findByTitleAndFullnameAndAliasAndNations(String str, String str1, String str2, String str3, Pageable pageable);
    Page<Person> findByTitleAndAndAliasAndNationsAndStates(String str, String str1, String str2, String str3, Pageable pageable);
    Page<Person> findByTitleAndAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, Pageable pageable);

    Page<Person> findByFullnameAndAliasAndNationsAndStates(String str, String str1, String str2, String str3,Pageable pageable);
    Page<Person> findByFullnameAndNationsAndStatesAndCites(String str,String str1, String str2, String str3,Pageable pageable);


    Page<Person> findByAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3,Pageable pageable);
*/
    // 5 columns

    Page<Person> findByTitleAndFullnameAndAliasAndNationsAndStates(String str,String str1, String str2, String str3, String str4,  Pageable pageable);
    Page<Person> findByTitleAndAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);
    Page<Person> findByFullnameAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);
    Page<Person> findByTitleAndAndFullnameAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);
    Page<Person> findByTitleAndAndFullnameAndAliasAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);
    Page<Person> findByTitleAndAndFullnameAndAliasAndNationsAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable);


    // 6 columnns
    Page<Person> findByTitleAndFullnameAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, String str5, Pageable pageable);




    /**
     * Saves collection of persons
     *
     * @param persons
     * @return collection of persons
     */
    Iterable<Person> save(Iterable<Person> persons);

  }
