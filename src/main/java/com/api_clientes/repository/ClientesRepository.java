package com.api_clientes.repository;

import com.api_clientes.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    Optional<Clientes> findByDni(String dni);  // Puedes agregar más métodos si es necesario
}
