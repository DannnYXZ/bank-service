package com.dannnyxz.bank.service;

import com.dannnyxz.bank.dto.ClientRequestDto;
import com.dannnyxz.bank.dto.ClientFormSuggestions;
import com.dannnyxz.bank.entity.Citizenship;
import com.dannnyxz.bank.entity.City;
import com.dannnyxz.bank.entity.Client;
import com.dannnyxz.bank.entity.Disability;
import com.dannnyxz.bank.entity.MaritalStatus;
import com.dannnyxz.bank.repository.CitizenshipRepository;
import com.dannnyxz.bank.repository.CityRepository;
import com.dannnyxz.bank.repository.ClientRepository;
import com.dannnyxz.bank.repository.DisabilityRepository;
import com.dannnyxz.bank.repository.MaritalStatusRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final CityRepository cityRepository;
    private final CitizenshipRepository citizenshipRepository;
    private final DisabilityRepository disabilityRepository;

    @Transactional
    public Client createClient(ClientRequestDto createRequest) {
        return clientRepository.save(
                Client.builder()
                        .id(createRequest.getId())
                        .idNumber(createRequest.getIdNumber())
                        .firstName(createRequest.getFirstName())
                        .secondName(createRequest.getSecondName())
                        .patronymic(createRequest.getPatronymic())
                        .birthDate(createRequest.getBirthDate())
                        .birthPlace(createRequest.getBirthPlace())
                        .gender(createRequest.getGender())
                        .passportSeries(createRequest.getPassportSeries())
                        .passportNumber(createRequest.getPassportNumber())
                        .passportIssuer(createRequest.getPassportIssuer())
                        .passportIssueDate(createRequest.getPassportIssueDate())
                        .liveCity(City.builder().id(createRequest.getLiveCityId()).build())
                        .liveAddress(createRequest.getLiveAddress())
                        .phoneHome(createRequest.getPhoneHome())
                        .phoneMobile(createRequest.getPhoneMobile())
                        .email(createRequest.getEmail())
                        .residenceCity(City.builder().id(createRequest.getResidenceCityId()).build())
                        .maritalStatus(MaritalStatus.builder().id(createRequest.getMaritalStatusId()).build())
                        .citizenship(Citizenship.builder().id(createRequest.getCitizenshipId()).build())
                        .disability(Disability.builder().id(createRequest.getDisabilityId()).build())
                        .monthlyIncomeCurrency(createRequest.getMonthlyIncomeCurrency())
                        .monthlyIncomeValue(createRequest.getMonthlyIncomeValue())
                        .retiree(createRequest.isRetiree())
                        .build()
        );
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientFormSuggestions getFormSuggestions() {
        List<MaritalStatus> allMaritalStatuses = maritalStatusRepository.findAll();
        List<City> allCities = cityRepository.findAll();
        List<Citizenship> allCitizenship = citizenshipRepository.findAll();
        List<Disability> allDisabilities = disabilityRepository.findAll();
        return ClientFormSuggestions
                .builder()
                .maritalStatuses(allMaritalStatuses)
                .cities(allCities)
                .citizenship(allCitizenship)
                .disabilities(allDisabilities)
                .build();
    }
}
