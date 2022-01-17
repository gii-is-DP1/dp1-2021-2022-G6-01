package org.springframework.samples.ocayparchis.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.ocayparchis.board.ParchisBoardService;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.Assert;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class ParchisBoardServiceTest {

	 @Autowired
		protected ParchisBoardService parchisBoardService;
	    
	 @Autowired
		protected ParchisPieceService parchisPieceService;

		@Test
		@Transactional
		void shouldFindBoardAttributes() {
			ParchisBoard board = this.parchisBoardService.findById(1).get();
			assertEquals(1, board.getId());
			assertEquals("", board.getBackground());
			assertEquals(800,board.getWidth());
			assertEquals(800,board.getHeight());
		}

		
		@Test
		@Transactional
		public void shouldInsertBoard() {
			ParchisBoard board = new ParchisBoard();
			board.setId(10);
	        board.setHeight(50);
	        board.setBackground("");
	        board.setWidth(100);
	        board.setPieces(List.of());
			this.parchisBoardService.save(board);
			assertThat(board.getId().longValue()).isNotEqualTo(0);
		
		}
		
		@Test
		@Transactional
		public void shouldDeleteBoard() {
			ParchisBoard board = new ParchisBoard();
			board.setId(10);
	        board.setHeight(50);
	        board.setBackground("");
	        board.setWidth(100);
			this.parchisBoardService.save(board);
			this.parchisBoardService.delete(board);
			assertThat(!board.getId().equals(10));
		}

	
	
}

