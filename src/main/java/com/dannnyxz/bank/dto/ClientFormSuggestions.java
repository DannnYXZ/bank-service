package com.dannnyxz.bank.dto;

import com.dannnyxz.bank.entity.Citizenship;
import com.dannnyxz.bank.entity.City;
import com.dannnyxz.bank.entity.Disability;
import com.dannnyxz.bank.entity.MaritalStatus;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientFormSuggestions {

    List<MaritalStatus> maritalStatuses;
    List<City> cities;
    List<Citizenship> citizenship;
    List<Disability> disabilities;
}
