package org.springframework.samples.ocayparchis.game;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.samples.ocayparchis.model.Game;
import org.springframework.stereotype.Service;

@Service

public class GameService {

	@Autowired
	private  GameRepository gameRepo;
	
	
	@Transactional
	public int gameCount() {
		return (int) gameRepo.count();
	}
	
	@Transactional
	public Iterable<Game> findAll(){
		return gameRepo.findAll();
	}
	
	@Transactional
	public Optional<Game> findGameById(int id){
		return gameRepo.findById(id);
	}
	@Transactional
	public void save(Game game){
		gameRepo.save(game);
	}
	@Transactional
	public void delete(Game game){
		gameRepo.delete(game);
	}
}