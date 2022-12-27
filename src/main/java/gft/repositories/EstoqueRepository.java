package gft.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.entities.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	void save(double total);
}
