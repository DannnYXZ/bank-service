package com.dannnyxz.bank.controller;

import com.dannnyxz.bank.dto.ClientRequest;
import com.dannnyxz.bank.dto.ClientFormSuggestions;
import com.dannnyxz.bank.entity.Client;
import com.dannnyxz.bank.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("clients")
    public Client saveClient(@RequestBody ClientRequest client) {
        return clientService.saveClient(client);
    }

    @GetMapping("clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("clients/{clientId}")
    public Client getClient(@PathVariable Integer clientId) {
        return clientService.getClient(clientId);
    }

    @DeleteMapping("clients/{clientId}")
    public void deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
    }

    @GetMapping("clients/{clientId}/shallow")
    public ClientRequest getClientShallow(@PathVariable Integer clientId) {
        return clientService.getClientShallow(clientId);
    }

    @GetMapping("clients/form/suggestions")
    public ClientFormSuggestions getClientFormSuggestions() {
        return clientService.getFormSuggestions();
    }
}
