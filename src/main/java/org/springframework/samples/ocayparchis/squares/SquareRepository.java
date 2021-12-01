package org.springframework.samples.ocayparchis.squares;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ocayparchis.model.ParchisTurn;

public interface SquareRepository extends CrudRepository<Square, Integer>{

	@Query("SELECT DISTINCT square FROM Square square WHERE square.position = :position")
	Square findByPosition(Integer position);

}
