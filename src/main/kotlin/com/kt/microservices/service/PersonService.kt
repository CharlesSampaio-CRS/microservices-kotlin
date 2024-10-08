package com.kt.microservices.service

import com.kt.microservices.exception.ResourceNotFoundException
import com.kt.microservices.model.Person
import com.kt.microservices.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id:Long):Person {
        logger.info("Find person")
        return repository.findById(id).orElseThrow({ResourceNotFoundException("not found id.")})
    }

    fun create(person: Person) : Person{
        logger.info("Create person ${person.firstName}")
        return repository.save(person)
    }

    fun update(person: Person): Person {
        logger.info("Update person ${person.firstName}")
        var entity = repository.findById(person.id)
            .orElseThrow({ResourceNotFoundException("not found id.")})
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

       return repository.save(entity)

    }

    fun delete(id: Long) {
        logger.info("delete person by id $id")
       var entity = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("not found id.")})
        repository.delete(entity)
    }

    fun findAll(): List<Person> {
        logger.info("Find all peaple")
        return repository.findAll()
    }




}