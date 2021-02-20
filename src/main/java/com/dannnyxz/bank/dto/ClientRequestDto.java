package com.dannnyxz.bank.dto;

import com.dannnyxz.bank.entity.Gender;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    private Integer id;
    @NotBlank
    private String idNumber;
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
    @NotBlank
    private String patronymic;
    @NotBlank
    private LocalDate birthDate;
    @NotBlank
    private String birthPlace;
    @NotNull
    private Gender gender;
    @NotBlank
    private String passportSeries;
    @NotBlank
    private String passportNumber;
    @NotBlank
    private String passportIssuer;
    @NotNull
    private LocalDate passportIssueDate;
    @NotNull
    private Integer liveCityId;
    @NotBlank
    private String liveAddress;
    @NotBlank
    private String phoneHome;
    @NotBlank
    private String phoneMobile;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private Integer residenceCityId;
    @NotNull
    private Integer maritalStatusId;
    @NotNull
    private Integer citizenshipId;
    @NotNull
    private Integer disabilityId;
    private String monthlyIncomeCurrency;
    private Integer monthlyIncomeValue;
    private boolean retiree;
}
