package br.com.petshop_backend.controllers;

import br.com.petshop_backend.entities.Animal;
import br.com.petshop_backend.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/animais")
public class AnimalController {
@Autowired
private AnimalRepository repository;

@GetMapping
    public List<Animal> ListarTodosOsAnimais() {
        return repository.findAll();
    }
@GetMapping("/{id}")
    public ResponseEntity<Animal>  getAnimalPorId(@PathVariable Long id) {
        try {
        return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
@PostMapping
    public Animal adicionarAnimal(@RequestBody Animal novo) {
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
public ResponseEntity<Animal> alterarAnimalPorId (@PathVariable Long id, @RequestBody Animal novosDados) {
    try {
        Animal animalAntigo = repository.findById(id).get();
        animalAntigo.setNome(novosDados.getNome());
        animalAntigo.setRaca(novosDados.getRaca());
        animalAntigo.setPeso(novosDados.getPeso());

        return new ResponseEntity<>(repository.save(animalAntigo), HttpStatus.OK);
    } catch (NoSuchElementException ex) {
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
}

