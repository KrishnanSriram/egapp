package com.sb.egapp.service;

import com.sb.egapp.model.Person;
import com.sb.egapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public List<Person> getPeople() {
        return repository.findAll();
    }

    public Optional<Person> findPersonbyId(String personId) {
        return repository.findById(personId);
    }

    public Person savePerson(Person person) {
        return repository.save(person);
    }

    public int getPeopleCount() {
        return 140;
    }

    public int getMalePopulationCount() {
        return 96;
    }

    public int getFemalePopulationCount() {
        return 44;
    }
}
