package io.github.emfsilva.clienteApi.repository;

import io.github.emfsilva.clienteApi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Assinatura do método que retoena um cliente pelo e-mail informado
    Optional<Client> findOptionalByEmail(String email);
}
