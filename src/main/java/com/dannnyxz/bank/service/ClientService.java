package com.dannnyxz.bank.service;

import com.dannnyxz.bank.dto.ClientFormSuggestions;
import com.dannnyxz.bank.dto.ClientRequest;
import com.dannnyxz.bank.entity.Citizenship;
import com.dannnyxz.bank.entity.City;
import com.dannnyxz.bank.entity.Client;
import com.dannnyxz.bank.entity.Disability;
import com.dannnyxz.bank.entity.MaritalStatus;
import com.dannnyxz.bank.entity.Passport;
import com.dannnyxz.bank.exception.ApiException;
import com.dannnyxz.bank.repository.CitizenshipRepository;
import com.dannnyxz.bank.repository.CityRepository;
import com.dannnyxz.bank.repository.ClientRepository;
import com.dannnyxz.bank.repository.DisabilityRepository;
import com.dannnyxz.bank.repository.MaritalStatusRepository;
import com.dannnyxz.bank.repository.PassportRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final CityRepository cityRepository;
    private final CitizenshipRepository citizenshipRepository;
    private final DisabilityRepository disabilityRepository;
    private final PassportRepository passportRepository;

    @Transactional
    public Client saveClient(ClientRequest createRequest) {
        Client clientToSave = Client.builder()
                .id(createRequest.getId())
                .passport(Passport.builder()
                        .id(createRequest.getPassportId())
                        .idNumber(createRequest.getIdNumber().strip())
                        .passportSeries(createRequest.getPassportSeries().strip())
                        .passportNumber(createRequest.getPassportNumber().strip())
                        .passportIssuer(createRequest.getPassportIssuer())
                        .passportIssueDate(createRequest.getPassportIssueDate())
                        .build()
                )
                .firstName(createRequest.getFirstName().strip())
                .secondName(createRequest.getSecondName().strip())
                .patronymic(createRequest.getPatronymic().strip())
                .birthDate(createRequest.getBirthDate())
                .birthPlace(createRequest.getBirthPlace())
                .gender(createRequest.getGender())
                .liveCity(City.builder().id(createRequest.getLiveCityId()).build())
                .liveAddress(createRequest.getLiveAddress())
                .phoneHome(createRequest.getPhoneHome())
                .phoneMobile(createRequest.getPhoneMobile())
                .email(createRequest.getEmail())
                .residenceCity(City.builder().id(createRequest.getResidenceCityId()).build())
                .residenceAddress(createRequest.getResidenceAddress())
                .maritalStatus(MaritalStatus.builder().id(createRequest.getMaritalStatusId()).build())
                .citizenship(Citizenship.builder().id(createRequest.getCitizenshipId()).build())
                .disability(Disability.builder().id(createRequest.getDisabilityId()).build())
                .monthlyIncomeCurrency(createRequest.getMonthlyIncomeCurrency())
                .monthlyIncomeValue(createRequest.getMonthlyIncomeValue())
                .retiree(createRequest.isRetiree())
                .build();
        if (clientToSave.getId() == null) {
            if (clientRepository.existsByFirstNameAndSecondNameAndPatronymic(
                    clientToSave.getFirstName(), clientToSave.getSecondName(), clientToSave.getPatronymic())
            ) {
                throw new ApiException("Клиент с таким именем уже существует", HttpStatus.BAD_REQUEST);
            }
            if (passportRepository.existsByPassportSeriesAndPassportNumber(
                    clientToSave.getPassport().getPassportSeries(), clientToSave.getPassport().getPassportNumber())
            ) {
                throw new ApiException("Клиент с таким паспортом уже существует", HttpStatus.BAD_REQUEST);
            }
            if (passportRepository.existsByIdNumber(clientToSave.getPassport().getIdNumber())
            ) {
                throw new ApiException("Клиент с таким идентификационным номером уже существует", HttpStatus.BAD_REQUEST);
            }
        }
        return clientRepository.save(
                clientToSave
        );
    }

    public ClientRequest getClientShallow(Integer clientId) {
        Client client = clientRepository.findById(clientId).get();
        return ClientRequest.builder()
                .id(client.getId())
                .passportId(client.getPassport().getId())
                .idNumber(client.getPassport().getIdNumber().strip())
                .firstName(client.getFirstName().strip())
                .secondName(client.getSecondName().strip())
                .patronymic(client.getPatronymic().strip())
                .birthDate(client.getBirthDate())
                .birthPlace(client.getBirthPlace())
                .gender(client.getGender())
                .passportSeries(client.getPassport().getPassportSeries().strip())
                .passportNumber(client.getPassport().getPassportNumber().strip())
                .passportIssuer(client.getPassport().getPassportIssuer())
                .passportIssueDate(client.getPassport().getPassportIssueDate())
                .liveCityId(client.getLiveCity().getId())
                .liveAddress(client.getLiveAddress())
                .phoneHome(client.getPhoneHome())
                .phoneMobile(client.getPhoneMobile())
                .email(client.getEmail())
                .residenceCityId(client.getResidenceCity().getId())
                .residenceAddress(client.getResidenceAddress())
                .maritalStatusId(client.getMaritalStatus().getId())
                .citizenshipId(client.getCitizenship().getId())
                .disabilityId(client.getDisability().getId())
                .monthlyIncomeCurrency(client.getMonthlyIncomeCurrency())
                .monthlyIncomeValue(client.getMonthlyIncomeValue())
                .retiree(client.isRetiree())
                .build();
    }

    public Client getClient(Integer clientId) {
        return clientRepository.findById(clientId).get();
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

    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
