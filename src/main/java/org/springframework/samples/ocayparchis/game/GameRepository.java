package org.springframework.samples.ocayparchis.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ocayparchis.model.Game;

public interface GameRepository extends CrudRepository<Game, Integer>{}