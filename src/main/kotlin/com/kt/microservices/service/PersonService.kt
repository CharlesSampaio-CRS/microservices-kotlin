package com.kt.microservices.service

import com.kt.microservices.exception.ResourceNotFoundException
import com.kt.microservices.mapper.DozerMapper
import com.kt.microservices.model.entity.Person
import com.kt.microservices.model.vo.v1.PersonVO
import com.kt.microservices.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id:Long): PersonVO {
        logger.info("Find person")
        var person =  repository.findById(id).orElseThrow({ResourceNotFoundException("not found id.")})
        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun create(person: PersonVO) : PersonVO {
        logger.info("Create person ${person.firstName}")
        var entity: Person = DozerMapper.parseObject(person,Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("Update person ${person.firstName}")
        var entity = repository.findById(person.id)
            .orElseThrow({ResourceNotFoundException("not found id.")})
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

       return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("delete person by id $id")
       var entity = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("not found id.")})
        repository.delete(entity)
    }

    fun findAll(): List<PersonVO> {
        logger.info("Find all peaple")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

}