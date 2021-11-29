package org.springframework.samples.ocayparchis.pieces;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParchisPieceService {
	@Autowired
	private OcaPieceRepository ocaPieceRepo;
	
	
	@Transactional
	public Iterable<OcaPiece> findAll(){
		return ocaPieceRepo.findAll();
	}
	
	@Transactional
	public OcaPiece findPieceById(int id){
		return ocaPieceRepo.findById(id).get();
	}
	@Transactional
	public void save(OcaPiece piece){
		ocaPieceRepo.save(piece);
	}
	
	@Transactional
	public void delete(OcaPiece piece){
		ocaPieceRepo.delete(piece);
	}
	

	@Transactional
	public OcaPiece findByPlayerId(Integer playerId) {
		return ocaPieceRepo.findByPlayerId(playerId).iterator().next();
	}
}
