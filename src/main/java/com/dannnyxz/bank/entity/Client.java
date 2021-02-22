package com.dannnyxz.bank.entity;


import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "live_city_id", referencedColumnName = "id")
    private City liveCity;

    @Column(name = "live_address")
    private String liveAddress;

    @Column(name = "phone_home")
    private String phoneHome;

    @Column(name = "phone_mobile")
    private String phoneMobile;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "residence_city_id", referencedColumnName = "id")
    private City residenceCity;

    @Column(name = "residence_address")
    private String residenceAddress;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marital_status_id", referencedColumnName = "id")
    private MaritalStatus maritalStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citizenship_id", referencedColumnName = "id")
    private Citizenship citizenship;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disability_id", referencedColumnName = "id")
    private Disability disability;

    @Column(name = "monthly_income_currency")
    private String monthlyIncomeCurrency;

    @Column(name = "monthly_income_value")
    private Integer monthlyIncomeValue;

    @Column(name = "retiree")
    private boolean retiree;
}
