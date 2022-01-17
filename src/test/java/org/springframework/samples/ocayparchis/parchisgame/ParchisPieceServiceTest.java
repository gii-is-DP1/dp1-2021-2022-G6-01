package org.springframework.samples.ocayparchis.parchisgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.ParchisBoard;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.squares.Color;
import org.springframework.samples.ocayparchis.squares.Square;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class ParchisPieceServiceTest {

	 @Autowired
		protected ParchisPieceService parchisPieceService;
	    

		@Test
		void shouldFindPieceAttributes() {
			ParchisPiece piece = this.parchisPieceService.findPieceById(1);
			assertThat(piece.getBoard().getId().equals(1));
			assertThat(piece.getPlayer().getId().equals(1));
			assertThat(piece.getCanMove().equals(true));
			assertThat(piece.getColor().equals(Color.RED));
		
			
		}
		
		@Test
		void shouldFindPieceByPlayerId() {
			ParchisPiece piece = this.parchisPieceService.findPieceById(1);
			assertThat(piece.getInStart().equals(true));
			assertThat(piece.getSquare().getPosition().equals(104));	
		}
		
		@Test
		void shouldFindAll() {
			Iterable<ParchisPiece> pieces = this.parchisPieceService.findAll();
			assertEquals(1,((Collection<ParchisPiece>) pieces).size());
		}

		
		@Test
		@Transactional
		public void shouldInsertPiece() {
			ParchisPiece piece= new ParchisPiece();
			ParchisBoard board = new ParchisBoard();
			Player player = new Player();
			piece.setId(2);
	        piece.setCanMove(true);
	        piece.setName("prueba");
	        piece.setBoard(board);
	        piece.setPlayer(player);
			this.parchisPieceService.save(piece);
			assertThat(piece.getId().longValue()).isNotEqualTo(0);
		
		}
		
		@Test
		@Transactional
		public void shouldDeletePiece() {
			ParchisPiece piece= new ParchisPiece();
			piece.setId(2);
	        piece.setInStart(true);
	        piece.setColor(Color.BLUE);
			this.parchisPieceService.save(piece);
			this.parchisPieceService.delete(piece);
			assertThat(!piece.getId().equals(2));
		}

	
	
}
