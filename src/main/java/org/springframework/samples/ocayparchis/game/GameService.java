package org.springframework.samples.ocayparchis.game;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.samples.ocayparchis.model.Game;
import org.springframework.samples.ocayparchis.owner.Owner;
import org.springframework.stereotype.Service;

@Service

public class GameService {

	@Autowired
	private  GameRepository gameRepo;
	
	

	
	@Transactional
	public Iterable<Game> findAll(){
		return gameRepo.findAll();
	}
	
	@Transactional
	public Game findGameById(int id){
		return gameRepo.findById(id);
	}
	@Transactional
	public void save(Game game){
		gameRepo.save(game);
	}



}