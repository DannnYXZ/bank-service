package com.dannnyxz.bank.controller;

import com.dannnyxz.bank.dto.ClientRequestDto;
import com.dannnyxz.bank.dto.ClientFormSuggestions;
import com.dannnyxz.bank.entity.Client;
import com.dannnyxz.bank.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("clients")
    public Client createClient(@RequestBody ClientRequestDto client) {
        return clientService.createClient(client);
    }

    @GetMapping("clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("clients/form/suggestions")
    public ClientFormSuggestions getClientFormSuggestions() {
        return clientService.getFormSuggestions();
    }
}
