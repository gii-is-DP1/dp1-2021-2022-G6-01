package org.springframework.samples.ocayparchis.game;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.stereotype.Service;

@Service

public class OcaGameService {

	@Autowired
	private  OcaGameRepository gameRepo;
	
	

	
	@Transactional
	public Iterable<OcaGame> findAll(){
		return gameRepo.findAll();
	}
	
	@Transactional
	public OcaGame findGameById(int id){
		return gameRepo.findById(id).get();
	}
	@Transactional
	public void save(OcaGame game){
		gameRepo.save(game);
	}



}