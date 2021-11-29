package org.springframework.samples.ocayparchis.pieces;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParchisPieceRepository extends CrudRepository<ParchisPiece, Integer>{
	@Query("SELECT DISTINCT piece FROM ParchisPiece piece, IN (piece.player) AS player WHERE player.id LIKE :playerId")
    public Collection<ParchisPiece> findByPlayerId(Integer playerId);
}
