package org.springframework.samples.ocayparchis.game;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.stereotype.Service;
@Service
public class OcaTurnService {
	@Autowired
	private OcaTurnRepository ocaTurnRepo;
	
	
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
	
	

}
