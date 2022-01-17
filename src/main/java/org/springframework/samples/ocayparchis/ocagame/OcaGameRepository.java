package org.springframework.samples.ocayparchis.ocagame;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ocayparchis.model.OcaGame;


public interface OcaGameRepository extends CrudRepository<OcaGame, Integer>{
	OcaGame save(OcaGame game);
}