package com.api_clientes.controller;

import com.api_clientes.dto.ApiResponse;
import com.api_clientes.model.Clientes;
import com.api_clientes.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    // Obtener todos los clientes
    @GetMapping
    public List<Clientes> getAllClientes() {
        return clientesService.getAllClientes();
    }

    // Obtener cliente por ID
    @GetMapping("/{id_cliente}")
    public ResponseEntity<Clientes> getClienteById(@PathVariable(value = "id_cliente") int id_cliente) {
        Clientes cliente = clientesService.getClienteById(id_cliente);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cliente);
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ApiResponse> createCliente(@RequestBody Clientes cliente) {
        return clientesService.createCliente(cliente); // Cambiado de ClientesService a clientesService
    }

    // Actualizar un cliente
    @PutMapping("/{id_cliente}")  // Añadido @PutMapping para mapear el endpoint
    public ResponseEntity<ApiResponse> updateCliente(@PathVariable int id_cliente, @RequestBody Clientes clienteDetalles) {
        return clientesService.updateCliente(id_cliente, clienteDetalles); // Usar servicio para la lógica
    }

    // Eliminar un cliente
    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<ApiResponse> deleteCliente(@PathVariable(value = "id_cliente") int id_cliente) {
        return clientesService.deleteCliente(id_cliente);
    }
}
