package com.nttdata.microservice.client.domain.repository;

import com.nttdata.microservice.client.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}