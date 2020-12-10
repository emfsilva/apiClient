package io.github.emfsilva.clienteApi.controller;

import io.github.emfsilva.clienteApi.dto.ClientDto;
import io.github.emfsilva.clienteApi.entity.Client;
import io.github.emfsilva.clienteApi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


// Informando que Essa classe é um controller que segue o padrão Rest
@RestController
//Mapeando a rota /client
@RequestMapping(value = "/client")
public class ClientController {

    // @Autowired é responsavel pela injeção de Dependencia
    @Autowired
    ClientService clientService; // referennciando a camada "Service"

    //Mapeando a rota que será responsavel por retornar um único cliente através do parâm
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDto> get(@PathVariable Integer id) {
        var obj = clientService.find(id).clientDto();
        return ResponseEntity.ok(obj);
    }

    //Mapeando a rota que será responsavel por retornar todos os clientes cadastrados.
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> getAll() {
        var obj = clientService
                .findAll()
                .stream()
                .map(Client::clientDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(obj);
    }

    //Mapeando a rota que sera responsavel por adicionar um novo Client
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> post(@Valid @RequestBody ClientDto objDTO) {
        clientService.insert(objDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Mapeando a rota que será responsável por Editar um Client
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody ClientDto objDtTO, @PathVariable Integer id) {
        var obj = clientService.update(id, objDtTO);
        return ResponseEntity.ok(obj.clientDto());
    }

    //Mapeando a rota que será responsável por Deletar um Cliente de acordo com o seu Id.
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
