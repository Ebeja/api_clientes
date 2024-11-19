package com.api_clientes.service;

import com.api_clientes.dto.ApiResponse;
import com.api_clientes.model.Clientes;
import com.api_clientes.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    // Obtener todos los clientes
    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    // Obtener un cliente por ID
    public Clientes getClienteById(int id_cliente) {
        return clientesRepository.findById(id_cliente).orElse(null);  // Retorna null si no lo encuentra
    }

    // Crear un nuevo cliente
    public ResponseEntity<ApiResponse> createCliente(Clientes cliente) {
        Clientes nuevoCliente = clientesRepository.save(cliente); // Guardar cliente
        ApiResponse response = new ApiResponse(true, "Cliente creado exitosamente con ID: " + nuevoCliente.getId_cliente());
        return ResponseEntity.ok(response);
    }

    // Actualizar un cliente
    public ResponseEntity<ApiResponse> updateCliente(int id_cliente, Clientes clienteDetalles) {
        Optional<Clientes> clienteExistente = clientesRepository.findById(id_cliente);

        if (clienteExistente.isPresent()) {
            Clientes cliente = clienteExistente.get();
            if (clienteDetalles.getNombre() != null) cliente.setNombre(clienteDetalles.getNombre());
            if (clienteDetalles.getApellido() != null) cliente.setApellido(clienteDetalles.getApellido());
            if (clienteDetalles.getDni() != null) cliente.setDni(clienteDetalles.getDni());
            if (clienteDetalles.getEmail() != null) cliente.setEmail(clienteDetalles.getEmail());
            if (clienteDetalles.getTelefono() != null) cliente.setTelefono(clienteDetalles.getTelefono());
            if (clienteDetalles.getDireccion() != null) cliente.setDireccion(clienteDetalles.getDireccion());
            if (clienteDetalles.getEstado() != null) cliente.setEstado(clienteDetalles.getEstado());
            if (clienteDetalles.getTipo_cliente() != null) cliente.setTipo_cliente(clienteDetalles.getTipo_cliente());

            clientesRepository.save(cliente);
            return ResponseEntity.ok(new ApiResponse(true, "Cliente actualizado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Cliente no encontrado"));
        }
    }

    // Eliminar un cliente
    public ResponseEntity<ApiResponse> deleteCliente(int id_cliente) {
        Optional<Clientes> cliente = clientesRepository.findById(id_cliente);

        if (cliente.isPresent()) {
            clientesRepository.delete(cliente.get());
            return ResponseEntity.ok(new ApiResponse(true, "Cliente eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Cliente no encontrado"));
        }
    }
}
