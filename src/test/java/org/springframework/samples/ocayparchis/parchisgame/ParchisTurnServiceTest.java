package org.springframework.samples.ocayparchis.parchisgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.model.ParchisTurn;
import org.springframework.samples.ocayparchis.ocagame.OcaTurnService;
import org.springframework.samples.ocayparchis.parchisgame.ParchisTurnService;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class ParchisTurnServiceTest {

	 @Autowired
		protected ParchisTurnService parchisTurnService;
	    
	 @Autowired
		private PlayerService playerService;

		@Test
		void shouldFindTurnAttributes() {
			ParchisTurn turn = this.parchisTurnService.findTurnById(1);
			assertThat(turn.getTurn().equals(1));
			assertThat(turn.getDice1().equals(0));
			assertThat(turn.getDice2().equals(0));
			assertThat(turn.getI().equals(0));
			assertThat(turn.getPlayer().getId().equals(1));
			assertEquals(0,turn.getPlayers().size());
		}
		
		@Test
		void shouldFindAll() {
			Iterable<ParchisTurn> turns = this.parchisTurnService.findAll();
			assertEquals(1,((Collection<ParchisTurn>) turns).size());
		}

		@Test
		@Transactional
		public void shouldInsertTurn() {
			Player player = this.playerService.findPlayerById(1);
			ParchisTurn turn= new ParchisTurn();
			turn.setTurn(3);   
	        turn.setDice1(0);
	        turn.setDice2(0);
	        turn.setI(2);
	        turn.setPlayer(player);
	        turn.setPlayers(List.of(player));
			this.parchisTurnService.save(turn);
			assertThat(turn.getId().longValue()).isNotEqualTo(0);
		
		}
		
		@Test
		@Transactional
		public void shouldDeleteTurn() {
			ParchisTurn turn = new ParchisTurn();
			Player player = this.playerService.findPlayerById(1);
			turn.setTurn(3);   
	        turn.setDice1(0);
	        turn.setDice2(0);
	        turn.setI(2);
	        turn.setPlayer(player);
	        turn.setPlayers(List.of(player));
			this.parchisTurnService.save(turn);
			this.parchisTurnService.delete(turn);
			assertThat(!turn.getI().equals(2));
		}

		@Test
		@Transactional
		public void nextTurn() {
			ParchisTurn turn = this.parchisTurnService.findTurnById(1);
			Player player1 = new Player();
			player1.setId(2);
			turn.getPlayers().add(this.playerService.findPlayerById(1));
			turn.getPlayers().add(player1);
			for(int i = 0; i<=turn.getPlayers().size();i++) {
				assertEquals(i, turn.getTurn());
				if(i==2) {
					assertEquals(0, turn.getI());
					assertEquals(turn.getPlayers().get(0), turn.getPlayer());
				}else {
					assertEquals(i,turn.getI());
					assertEquals(turn.getPlayers().get(i), turn.getPlayer());
				}
				turn.nextTurn();
				}
		}
		
		@Test
		@Transactional
		public void throwDice() {
			ParchisTurn turn = this.parchisTurnService.findTurnById(1);
			Integer notValidDiceNumber = 8;
			turn.setDice1(notValidDiceNumber);
			turn.setDice2(notValidDiceNumber);
			assertEquals(false, turn.getIsThrowed());
			assertEquals(0, turn.getDicesAvailable());
			turn.throwDices();
			assertEquals(2, turn.getDicesAvailable());
			assertEquals(true, turn.getIsThrowed());
			turn.setIsThrowed(false);
			assertEquals(false, turn.getIsThrowed());
			assertNotEquals(notValidDiceNumber, turn.getDice1());
			assertNotEquals(notValidDiceNumber, turn.getDice2());
			assertThat(turn.getDice1()<=6&&turn.getDice1()>=1);
			assertThat(turn.getDice2()<=6&&turn.getDice1()>=2);
		}
	
		@ParameterizedTest
	    @ValueSource(ints= {1,0,2})
		public void testSetDicesAvailable(int dicesAvailable) {
			ParchisTurn turn = this.parchisTurnService.findTurnById(1);
			turn.setDicesAvailable(dicesAvailable);
			assertEquals(dicesAvailable,turn.getDicesAvailable());
		}
		
		@ParameterizedTest
	    @ValueSource(ints= {1,0,2})
		public void testSetDoubleCount(int doubleCount) {
			ParchisTurn turn = this.parchisTurnService.findTurnById(1);
			turn.setDoubleCount(doubleCount);
			assertEquals(doubleCount,turn.getDoubleCount());
		}
		
		@Test
		@Transactional
		public void testSetRepeatTurn() {
			ParchisTurn turn = this.parchisTurnService.findTurnById(1);
			turn.setRepeatTurn(true);
			assertEquals(true,turn.getRepeatTurn());
		}
}
