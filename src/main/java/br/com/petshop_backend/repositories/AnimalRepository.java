package br.com.petshop_backend.repositories;

import br.com.petshop_backend.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
