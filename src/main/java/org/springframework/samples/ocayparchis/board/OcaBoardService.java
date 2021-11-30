package org.springframework.samples.ocayparchis.board;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OcaBoardService {

	OcaBoardRepository boardRepo;

	@Autowired
	public OcaBoardService(OcaBoardRepository boardRepo) {
		super();
		this.boardRepo = boardRepo;
	}

	@Transactional
	public Optional<OcaBoard> findById(Integer id){
		return boardRepo.findById(id);
	}

	@Transactional
	public void save(OcaBoard game){
		boardRepo.save(game);
	}

	@Transactional
	public void delete(OcaBoard board) {
		boardRepo.delete(board);

	}
}
