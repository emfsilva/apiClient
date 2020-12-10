package io.github.emfsilva.clienteApi.service;

import io.github.emfsilva.clienteApi.dto.ClientDto;
import io.github.emfsilva.clienteApi.entity.Client;

import java.util.List;

public interface ClientService {

    Client find(int id);

    List<Client> findAll();

    Client update(int idClient, ClientDto client);

    Client insert(ClientDto client);

    void delete(int idClient);

}
