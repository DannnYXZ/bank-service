package com.dannnyxz.bank.repository;

import com.dannnyxz.bank.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Integer> {

    boolean existsByPassportSeriesAndPassportNumber(String passportSeries, String passportNumber);

    boolean existsByIdNumber(String idNumber);
}
