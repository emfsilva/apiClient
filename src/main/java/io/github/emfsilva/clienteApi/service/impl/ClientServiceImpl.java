package io.github.emfsilva.clienteApi.service.impl;

import io.github.emfsilva.clienteApi.dto.ClientDto;
import io.github.emfsilva.clienteApi.entity.Client;
import io.github.emfsilva.clienteApi.exceptions.type.DataIntegrationException;
import io.github.emfsilva.clienteApi.repository.ClientRepository;
import io.github.emfsilva.clienteApi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Informando que a classe é um service
@Service
public class ClientServiceImpl implements ClientService {

    // Instanciando atraves da injeção de dpeencia um objeto do tipo
    // ClientRepository que nos permite manipular nosso banco de dados


    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client find(int id) {

        // Realiza a busca de um cliente atravéz do Id
        // O metodo findById retorna um objeto do tipo Optional
        var cli = clientRepository.findById(id);

        // Verifica se o usuário existe
        if (!cli.isPresent()) {

            // verifica uma exceção informndo que o cliente não existe
            throw new DataIntegrationException("Cliente não existe");
        }

        return cli.get();
    }

    @Override
    // Busca todos os clientes da base
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    // Realiza o update do Cliente
    public Client update(int idClient, ClientDto client) {

        // Busca o cliente que será alterado
        var cli = this.find(idClient);

        // sobrescreve os valores antigos pelos novos
        cli.setName(client.getName());
        cli.setEmail(client.getEmail());

        // Salva a alteração no banco de dados
        return clientRepository.save(cli);
    }

    @Override
    // Insere um novo cliente no banco de dados
    public Client insert(ClientDto client) {

        //Busca um cliente atravéz do email informado
        var cli = clientRepository.findOptionalByEmail(client.getEmail());

        //Verifica se já existe um cliente com o email informado
        if (cli.isPresent()) {
            //Caso já exista um cliente cadastrado com o mesmo email informado
            //é retornado uma exceção informando que esse email ja foi cadastrado para outro cliente

            throw new DataIntegrationException("Email já Cadastrado");
        }

        // Salva um novo Cliente
        // Não estamos informando um id pois estamos salvando um novo cliente
        return clientRepository.save(Client.builder()
                .name(client.getName())
                .email(client.getEmail())
                .build());
    }

    @Override
    public void delete(int idClient) {
        // Busca o c cliente que será deletado
        var cli = this.find(idClient);
        // deleta o cliente
        clientRepository.delete(cli);
    }
}
