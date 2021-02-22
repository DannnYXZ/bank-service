package com.dannnyxz.bank.repository;

import com.dannnyxz.bank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByFirstNameAndSecondNameAndPatronymic(String firstName, String secondName, String patronymic);
}
