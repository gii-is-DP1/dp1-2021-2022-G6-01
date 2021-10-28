package org.springframework.samples.petclinic.game;



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
	
	public Iterable<Game> findAll(){
		return gameRepo.findAll();
	}
	
	
}