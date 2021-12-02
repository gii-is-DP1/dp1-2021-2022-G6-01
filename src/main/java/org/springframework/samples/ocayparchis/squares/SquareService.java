package org.springframework.samples.ocayparchis.squares;

import java.util.List;

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
	
	@Transactional
	public void generateSquares() {
		for(int i=-3;i<=100;i++) {
			if(69<=i&&i<=76){
				Square s = new Square(null,i,Color.YELLOW,null);
				squareRepo.save(s);
			}else if(77<=i&&i<=84){
				Square s = new Square(null,i,Color.BLUE,null);
				squareRepo.save(s);
			}else if(85<=i&&i<=92){
				Square s = new Square(null,i,Color.RED,null);
				squareRepo.save(s);
			}else if(93<=i&&i<=100){
				Square s = new Square(null,i,Color.GREEN,null);
				squareRepo.save(s);
			}else {
				Square s = new Square(null,i,null,null);
				squareRepo.save(s);
			}
		}
	}
}
