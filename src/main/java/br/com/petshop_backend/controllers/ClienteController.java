package br.com.petshop_backend.controllers;

import br.com.petshop_backend.entities.Cliente;
import br.com.petshop_backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/clientes")

public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> ListarTodosOsAnimais() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientePorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente novo) {
        return repository.save(novo);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId (@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alterarClientePorId (@PathVariable Long id, @RequestBody Cliente novosDados) {
        try {
            Cliente clienteAntigo = repository.findById(id).get();
            clienteAntigo.setNome(novosDados.getNome());
            clienteAntigo.setCpf(novosDados.getCpf());

            return new ResponseEntity<>(repository.save(clienteAntigo), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
