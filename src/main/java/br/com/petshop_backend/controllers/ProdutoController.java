package br.com.petshop_backend.controllers;


import br.com.petshop_backend.entities.Produto;
import br.com.petshop_backend.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> ListarTodosOsProdutos() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto novo) {
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
    public ResponseEntity<Produto> alterarProdutoPorId (@PathVariable Long id, @RequestBody Produto novosDados) {
        try {
            Produto produtoAntigo = repository.findById(id).get();
            produtoAntigo.setNome(novosDados.getNome());
            produtoAntigo.setPreco(novosDados.getPreco());

            return new ResponseEntity<>(repository.save(produtoAntigo), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
