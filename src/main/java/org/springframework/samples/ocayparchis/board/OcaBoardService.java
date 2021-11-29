package org.springframework.samples.ocayparchis.board;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OcaBoardService {


		@Autowired 
		OcaBoardRepository boardRepo;
		
		public Optional<OcaBoard> findById(Integer id){
			return boardRepo.findById(id);
	}

		@Transactional
		public void save(OcaBoard game){
			boardRepo.save(game);
		}

		public void delete(OcaBoard board) {
			boardRepo.delete(board);
			
		}
}
