package org.springframework.samples.ocayparchis.parchisgame;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.ParchisGame;
import org.springframework.stereotype.Service;

@Service
public class ParchisGameService {
	
	@Autowired
	private  ParchisGameRepository parchisGameRepo;
	
	@Transactional
	public Iterable<ParchisGame> findAll(){
		return parchisGameRepo.findAll();
	}
	
	@Transactional
	public ParchisGame findGameById(int id){
		return parchisGameRepo.findById(id).get();
	}
	@Transactional
	public void save(ParchisGame game){
		parchisGameRepo.save(game);
	}
	
	@Transactional
	public void delete(ParchisGame game){
		parchisGameRepo.delete(game);
	}

}
