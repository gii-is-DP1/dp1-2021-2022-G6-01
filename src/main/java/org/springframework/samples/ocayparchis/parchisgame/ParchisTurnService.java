package org.springframework.samples.ocayparchis.parchisgame;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.ParchisTurn;
import org.springframework.stereotype.Service;

@Service
public class ParchisTurnService {
	@Autowired
	private ParchisTurnRepository parchisTurnRepo;
	
	
	@Transactional
	public Iterable<ParchisTurn> findAll(){
		return parchisTurnRepo.findAll();
	}
	
	@Transactional
	public ParchisTurn findTurnById(int id){
		return parchisTurnRepo.findById(id).get();
	}
	@Transactional
	public void save(ParchisTurn turn){
		parchisTurnRepo.save(turn);
	}
	@Transactional
	public void delete(ParchisTurn turn) {
		parchisTurnRepo.delete(turn);
	}

}
