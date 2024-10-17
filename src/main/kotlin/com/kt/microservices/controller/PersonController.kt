package com.kt.microservices.controller

import com.kt.microservices.model.vo.v1.PersonVO
import com.kt.microservices.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person/v1")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @GetMapping("/{id}")
    fun getPerson(@PathVariable(value = "id")id: Long): PersonVO {
        return service.findById(id)
    }

    @PostMapping("/create")
    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person)
    }

    @PutMapping("/update")
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping("/delete/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
         service.delete(id)
        return ResponseEntity.noContent().build()

    }

    @RequestMapping("/all")
    fun findAll(): List<PersonVO>{
        return service.findAll()
    }
}