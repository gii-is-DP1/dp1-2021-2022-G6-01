package org.springframework.samples.ocayparchis.ocagame;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.stereotype.Service;

@Service

public class OcaGameService {

	private  OcaGameRepository ocaGameRepo;

	@Autowired
	public OcaGameService(OcaGameRepository ocaGameRepo) {
		super();
		this.ocaGameRepo = ocaGameRepo;
	}
	
	@Transactional
	public Iterable<OcaGame> findAll(){
		return ocaGameRepo.findAll();
	}
	
	@Transactional
	public OcaGame findGameById(int id){
		return ocaGameRepo.findById(id).get();
	}
	@Transactional
	public void save(OcaGame game){
		ocaGameRepo.save(game);
	}
	
	@Transactional
	public void delete(OcaGame game){
		ocaGameRepo.delete(game);
	}


}