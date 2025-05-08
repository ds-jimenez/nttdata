package com.nttdata.microservice.account.domain.repository;

import com.nttdata.microservice.account.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}