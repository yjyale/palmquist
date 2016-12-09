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
    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    public Page<Person> findByTitleAndFullnameAndAliasAndNationsAndStates(String str, String str1, String str2, String str3, String str4, Pageable pageable) {
        return personRepository.findByTitleAndFullNameAndAliasAndNationsAndStates(str, str1, str2, str3, str4, pageable);
    }

    @Override
    public Page<Person> findByTitleAndAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable) {
        return personRepository.findByTitleAndAndAliasAndNationsAndStatesAndCities(str, str1, str2, str3, str4, pageable);
    }

    @Override
    public Page<Person> findByFullnameAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable) {
        return personRepository.findByFullNameAndAliasAndNationsAndStatesAndCities(str, str1, str2, str3, str4, pageable);
    }

    @Override
    public Page<Person> findByTitleAndAndFullnameAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable) {
        return personRepository.findByTitleAndAndFullNameAndNationsAndStatesAndCities(str, str1, str2, str3, str4, pageable);
    }

    @Override
    public Page<Person> findByTitleAndAndFullnameAndAliasAndStatesAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable) {
        return personRepository.findByTitleAndAndFullNameAndAliasAndStatesAndCities(str, str1, str2, str3, str4, pageable);
    }

    @Override
    public Page<Person> findByTitleAndAndFullnameAndAliasAndNationsAndCities(String str, String str1, String str2, String str3, String str4, Pageable pageable) {
        return personRepository.findByTitleAndAndFullNameAndAliasAndNationsAndCities(str, str1, str2, str3, str4, pageable);
    }

    @Override
    public Page<Person> findByAliasContaining(String str, Pageable pageable) {
        return personRepository.findByAliasContaining(str, pageable);
    }

    @Override
    public Page<Person> findByNationsContaining(String str, Pageable pageable) {
        return personRepository.findByNationsContaining(str, pageable);
    }

    @Override
    public Page<Person> findByStatesContaining(String str, Pageable pageable) {
        return personRepository.findByStatesContaining(str, pageable);
    }

    @Override
    public Page<Person> findByCitiesContaining(String str, Pageable pageable) {
        return personRepository.findByCitiesContaining(str, pageable);
    }

    @Override
    public Page<Person> findByFullnameContaining(String fullName, Pageable pageable) {
        return personRepository.findByFullNameContaining(fullName, pageable);
    }

    @Override
    public Page<Person> findByTitleAndFullnameAndAliasAndNationsAndStatesAndCities(String str, String str1, String str2, String str3, String str4, String str5, Pageable pageable) {
        return personRepository.findByTitleAndFullNameAndAliasAndNationsAndStatesAndCities(str, str1, str2, str3, str4, str5, pageable);
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
