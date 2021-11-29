package org.springframework.samples.ocayparchis.pieces;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParchisPieceService {
	@Autowired
	private ParchisPieceRepository parchisPieceRepo;
	
	
	@Transactional
	public Iterable<ParchisPiece> findAll(){
		return parchisPieceRepo.findAll();
	}
	
	@Transactional
	public ParchisPiece findPieceById(int id){
		return parchisPieceRepo.findById(id).get();
	}
	@Transactional
	public void save(ParchisPiece piece){
		parchisPieceRepo.save(piece);
	}
	
	@Transactional
	public void delete(ParchisPiece piece){
		parchisPieceRepo.delete(piece);
	}
	

	@Transactional
	public Collection<ParchisPiece> findByPlayerId(Integer playerId) {
		return parchisPieceRepo.findByPlayerId(playerId);
	}
}
