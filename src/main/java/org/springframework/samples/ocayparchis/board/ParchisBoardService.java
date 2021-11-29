package org.springframework.samples.ocayparchis.board;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParchisBoardService {
	@Autowired 
	ParchisBoardRepository boardRepo;
	
	public Optional<ParchisBoard> findById(Integer id){
		return boardRepo.findById(id);
}

	@Transactional
	public void save(ParchisBoard game){
		boardRepo.save(game);
	}

	public void delete(ParchisBoard board) {
		boardRepo.delete(board);
	}

}
