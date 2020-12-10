package service;
// Coloque a anotação InjectMocks na classe onde está o código que sera testado.
// Aqui estamos instanciando a classe que será testada ClientServiceImpl e informando que
// as dependências simuladas (Mocks) serão injetadas dentro dela


import io.github.emfsilva.clienteApi.entity.Client;
import io.github.emfsilva.clienteApi.exceptions.type.DataIntegrationException;
import io.github.emfsilva.clienteApi.repository.ClientRepository;
import io.github.emfsilva.clienteApi.service.impl.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    //Criando um objeto simulado da classe ClientRepository, ou seja ele é um objeto "Falso" que vamos utilizar no nosso teste.
    @Mock
    private ClientRepository clientRepository;

    /*
    Aqui estamos iniciando os Mocks, ou seja estamos injetando o Mock que acabamos de criar da classe ClientRepository
    e estamos injetando dentro da classe ClientServiceImpl
    */
    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindSuccess() {
        try {
            Mockito.when(clientRepository.findById(any(Integer.class)))
                    .thenReturn(Optional.of(Client.builder()
                            .email("teste@teste.com")
                            .name("teste teste")
                            .id(1)
                            .build()));

            Client client = clientService.find(any(Integer.class));
            Assert.assertEquals("teste teste", client.getName());
        } catch (Exception ex) {
            Assert.fail();
        }
    }

    @Test
    public void testClientNotFound() {
        try {
            Mockito.when(clientRepository.findById(any(Integer.class)))
                    .thenReturn(Optional.empty());

            clientService.find(any(Integer.class));
            Assert.fail();
        } catch (DataIntegrationException ex) {
            Assert.assertEquals(ex.getMessage(), "Cliente não existe");
        }
    }

}
