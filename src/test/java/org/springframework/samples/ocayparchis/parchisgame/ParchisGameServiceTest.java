package org.springframework.samples.ocayparchis.parchisgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.ParchisGame;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class ParchisGameServiceTest {

	 @Autowired
		protected ParchisGameService parchisGameService;
	    

		@Test
		void shouldFindGameAttributes() {
			ParchisGame game = this.parchisGameService.findGameById(1);
			assertThat(game.getName()).startsWith("Lop");
			assertThat(game.getPlayers().equals(2));
			assertThat(game.getReward().equals(10));
			assertThat(game.getInGame().equals(Boolean.FALSE));
		}

		@Test
		void shouldFindAll() {
			Iterable<ParchisGame> games= this.parchisGameService.findAll();
			assertEquals(1,((Collection<ParchisGame>) games).size());
		}
		
		@Test
		@Transactional
		public void shouldInsertGame() {

			ParchisGame game = new ParchisGame();
			game.setInGame(Boolean.FALSE);   
	        game.setName("JuegoOca");
	        game.setPlayers(2);
	        game.setReward(20);
			this.parchisGameService.save(game);
			assertThat(game.getId().longValue()).isNotEqualTo(0);
			assertThat(game.getName().equals("JuegoOca"));
		}
		
		@Test
		@Transactional
		public void shouldDeleteGame() {
			ParchisGame game = new ParchisGame();
			game.setInGame(Boolean.FALSE);   
	        game.setName("JuegoOca");
	        game.setPlayers(2);
	        game.setReward(20);
			this.parchisGameService.save(game);
			this.parchisGameService.delete(game);
			assertThat(!game.getName().equals("JuegoOca"));
		}

	
	
}