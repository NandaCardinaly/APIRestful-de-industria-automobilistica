package gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.entities.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

}
