package org.springframework.samples.ocayparchis.squares;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.stereotype.Service;

@Service
public class SquareService {

	private SquareRepository squareRepo;

	@Autowired
	public SquareService(SquareRepository squareRepo) {
		super();
		this.squareRepo = squareRepo;
	}
	
	@Transactional
	public void save(Square square){
		squareRepo.save(square);
	}
	
	@Transactional
	public void delete(Square square){
		squareRepo.delete(square);
	}
	
	@Transactional
	public Square findByPosition(Integer position) {
		return squareRepo.findByPosition(position);
	}
	
}
