package com.sb.egapp.controller;

import com.sb.egapp.model.Person;
import com.sb.egapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping()
    public ResponseEntity<List<Person>> getPeople() {
        return new ResponseEntity(personService.getPeople(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        try {
            return new ResponseEntity(personService.savePerson(person), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findPersonById(@PathVariable("personId") String personId) {
        Optional<Person> person = personService.findPersonbyId(personId);
        if(person.isPresent()) {
            return new ResponseEntity(person.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Invalid person ID. Please check and try again", HttpStatus.NOT_FOUND);
    }
}
