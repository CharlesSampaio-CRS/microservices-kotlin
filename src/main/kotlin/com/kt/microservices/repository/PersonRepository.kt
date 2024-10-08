package com.kt.microservices.repository

import com.kt.microservices.model.Person
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface PersonRepository : JpaRepository<Person, Long?> {
}