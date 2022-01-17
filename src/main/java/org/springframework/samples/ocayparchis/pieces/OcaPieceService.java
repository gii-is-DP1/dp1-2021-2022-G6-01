package org.springframework.samples.ocayparchis.pieces;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.ocagame.OcaTurnRepository;
import org.springframework.stereotype.Service;

@Service
public class OcaPieceService {
	


	private OcaPieceRepository ocaPieceRepo;
	
	@Autowired
	public OcaPieceService(OcaPieceRepository ocaPieceRepo) {
		super();
		this.ocaPieceRepo = ocaPieceRepo;
	}

	@Transactional
	public Iterable<OcaPiece> findAll(){
		return ocaPieceRepo.findAll();
	}
	
	@Transactional
	public OcaPiece findPieceById(int id){
		return ocaPieceRepo.findById(id).get();
	}
	@Transactional
	public OcaPiece save(OcaPiece piece){
		return ocaPieceRepo.save(piece);
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