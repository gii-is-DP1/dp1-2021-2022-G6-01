package org.springframework.samples.ocayparchis.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.OcaPieceService;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class OcaPieceServiceTest {

	 @Autowired
		protected OcaPieceService ocaPieceService;
	    

		@Test
		void shouldFindPieceAttributes() {
			OcaPiece piece = this.ocaPieceService.findPieceById(1);
			assertThat(piece.getPenalization().equals(0));
			assertThat(piece.getPosition().equals(4));
		
			
		}
		
		@Test
		void shouldFindPieceByPlayerId() {
			OcaPiece piece = this.ocaPieceService.findByPlayerId(1);
			assertThat(piece.getPenalization().equals(0));
			assertThat(piece.getPosition().equals(4));	
		}
		
		@Test
		void shouldFindAll() {
			Iterable<OcaPiece> pieces = this.ocaPieceService.findAll();
			assertEquals(1,((Collection<OcaPiece>) pieces).size());
		}

		
		@Test
		@Transactional
		public void shouldInsertPiece() {

			OcaPiece piece= new OcaPiece();
			piece.setId(2);;   
	        piece.setPosition(2);;
	        piece.setPenalization(0);
			this.ocaPieceService.save(piece);
			assertThat(piece.getId().longValue()).isNotEqualTo(0);
		
		}
		
		@Test
		@Transactional
		public void shouldDeletePiece() {
			OcaPiece piece= new OcaPiece();
			piece.setId(2);;   
	        piece.setPosition(2);;
	        piece.setPenalization(0);
			this.ocaPieceService.save(piece);
			this.ocaPieceService.delete(piece);
			assertThat(!piece.getId().equals(2));
		}

	
	
}
