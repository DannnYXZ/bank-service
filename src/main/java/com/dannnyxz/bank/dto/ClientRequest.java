package com.dannnyxz.bank.dto;

import com.dannnyxz.bank.entity.Gender;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

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
    private Integer passportId;
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
    @NotEmpty
    private String residenceAddress;
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
