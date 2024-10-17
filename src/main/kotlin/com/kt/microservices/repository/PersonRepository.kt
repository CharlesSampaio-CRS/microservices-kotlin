package com.kt.microservices.repository

import com.kt.microservices.model.entity.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long?> {
}