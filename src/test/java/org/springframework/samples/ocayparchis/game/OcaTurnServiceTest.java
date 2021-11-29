package org.springframework.samples.ocayparchis.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.ocagame.OcaTurnService;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class OcaTurnServiceTest {

	 @Autowired
		protected OcaTurnService ocaTurnService;
	    
	 @Autowired
		private PlayerService playerService;

		@Test
		void shouldFindTurnAttributes() {
			OcaTurn turn = this.ocaTurnService.findTurnById(1);
			assertThat(turn.getTurn().equals(1));
			assertThat(turn.getDice().equals(0));
			assertThat(turn.getI().equals(0));
			assertThat(turn.getPlayer().getId().equals(1));
			assertEquals(0,turn.getPlayers().size());
		}
		
		@Test
		void shouldFindAll() {
			Iterable<OcaTurn> turns = this.ocaTurnService.findAll();
			assertEquals(1,((Collection<OcaTurn>) turns).size());
		}

		@Test
		@Transactional
		public void shouldInsertTurn() {
			Player player = this.playerService.findPlayerById(1);
			OcaTurn turn= new OcaTurn();
			turn.setTurn(3);   
	        turn.setDice(0);
	        turn.setI(2);
	        turn.setPlayer(player);
	        turn.setPlayers(List.of(player));
			this.ocaTurnService.save(turn);
			assertThat(turn.getId().longValue()).isNotEqualTo(0);
		
		}
		
		@Test
		@Transactional
		public void shouldDeleteTurn() {
			OcaTurn turn = new OcaTurn();
			Player player = this.playerService.findPlayerById(1);
			turn.setTurn(3);   
	        turn.setDice(0);
	        turn.setI(2);
	        turn.setPlayer(player);
	        turn.setPlayers(List.of(player));
			this.ocaTurnService.save(turn);
			this.ocaTurnService.delete(turn);
			assertThat(!turn.getI().equals(2));
		}

	
	
}
