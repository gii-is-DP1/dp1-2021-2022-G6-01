package org.springframework.samples.petclinic.game;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;
	
	@Transactional
	public int gameCount() {
		return (int) gameRepo.count();
	}
	@Transactional
	public Iterable<Game> findAll(){
		return gameRepo.findAll();
	}
	public void save(@Valid Game game) {
		gameRepo.save(game);
		
	}
	public void delete(Game gameId) {
		gameRepo.delete(gameId);
		
	}
	public Optional<Game> findGameById(int gameId) {
		return gameRepo.findById(gameId);
	}
	
	
	
	
}