package org.springframework.samples.ocayparchis.ocagame;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.stereotype.Service;
@Service
public class OcaTurnService {
	
	private OcaTurnRepository ocaTurnRepo;
	
	@Autowired
	public OcaTurnService(OcaTurnRepository ocaTurnRepo) {
		super();
		this.ocaTurnRepo = ocaTurnRepo;
	}

	@Transactional
	public Iterable<OcaTurn> findAll(){
		return ocaTurnRepo.findAll();
	}
	
	@Transactional
	public OcaTurn findTurnById(int id){
		return ocaTurnRepo.findById(id).get();
	}
	@Transactional
	public void save(OcaTurn turn){
		ocaTurnRepo.save(turn);
	}
	@Transactional
	public void delete(OcaTurn turn) {
		ocaTurnRepo.delete(turn);
		
	}
	
	

}
