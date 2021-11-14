package org.springframework.samples.ocayparchis.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ocayparchis.model.OcaGame;


public interface OcaGameRepository extends CrudRepository<OcaGame, Integer>{}