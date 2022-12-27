package gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
