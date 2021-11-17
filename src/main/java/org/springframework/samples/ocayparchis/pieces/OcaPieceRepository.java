package org.springframework.samples.ocayparchis.pieces;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.player.Player;

public interface OcaPieceRepository extends CrudRepository<OcaPiece, Integer>{
	
	@Query("SELECT DISTINCT piece FROM OcaPiece piece, IN (piece.player) AS player WHERE player.id LIKE :playerId")
	public Collection<OcaPiece> findByPlayerId(@Param("playerId") Integer playerId);
}
