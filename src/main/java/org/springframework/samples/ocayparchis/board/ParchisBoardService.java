package org.springframework.samples.ocayparchis.board;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParchisBoardService {

	ParchisBoardRepository boardRepo;
	
	@Autowired
	public ParchisBoardService(ParchisBoardRepository boardRepo) {
		super();
		this.boardRepo = boardRepo;
	}
	
	@Transactional
	public Optional<ParchisBoard> findById(Integer id){
		return boardRepo.findById(id);
	}

	@Transactional
	public void save(ParchisBoard game){
		boardRepo.save(game);
	}

	@Transactional
	public void delete(ParchisBoard board) {
		boardRepo.delete(board);
	}

}
