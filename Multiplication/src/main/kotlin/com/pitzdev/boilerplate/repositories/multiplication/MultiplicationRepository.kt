package com.pitzdev.boilerplate.repositories.multiplication

import com.pitzdev.boilerplate.models.multiplication.Multiplication
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MultiplicationRepository : CrudRepository<Multiplication, Long>