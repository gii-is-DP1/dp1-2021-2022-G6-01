package org.springframework.samples.petclinic.game;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Game;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.vet.Vet;

public interface GameRepository extends Repository<Game, Integer>{
	/**
	 * Retrieve all <code>Game</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Game</code>s
	 */
	Collection<Game> findAll() throws DataAccessException;
	
	
	
	/**
	 * Save a <code>Game</code> to the data store, either inserting or updating it.
	 * @param game the <code>Game</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(Game game) throws DataAccessException;
	
	
	/**
	 * Retrieve an <code>Game</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Game</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */	
	@Query("SELECT game FROM Game game  WHERE game.id =:id")
	public Game findById(@Param("id") int id);
}