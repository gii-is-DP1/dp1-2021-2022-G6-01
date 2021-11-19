package org.springframework.samples.ocayparchis.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.ocayparchis.configuration.SecurityConfiguration;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.OcaPieceService;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerController;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.user.AuthoritiesService;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.samples.ocayparchis.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for {@link PlayerController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers = OcaTurnController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class OcaTurnControllerTests {

	@Autowired
	private OcaTurnController ocaTurnController;

	@Autowired
	private OcaTurnService ocaTurnService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private OcaPieceService ocaPieceService;
	@Autowired
	private OcaGameService ocaGameService;
	private static final int TEST_PLAYER_ID = 2;
	private Player mario;
	private static final int TEST_PLAYER2_ID = 3;
	private Player pepe;
	private static final int TEST_GAME_ID = 2;
	private OcaGame game;
	private static final int TEST_TURN_ID = 2;
	private OcaTurn turn;
	private static final int TEST_PIECE_ID = 2;
	private OcaPiece piece;
	
	
	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
//CREAR JUGADORES
		mario = new Player();
		mario.setId(TEST_PLAYER_ID);
		mario.setFirstName("Mario");
		mario.setLastName("Rey");
		mario.setDescription("me gusta mucho el cadiz");
		User user=new User();
        user.setUsername("mario");
        user.setPassword("supersecretpassword");
        user.setEnabled(true);
        mario.setUser(user);        
		mario.setPoints(1);
		given(this.playerService.findPlayerById(TEST_PLAYER_ID)).willReturn(mario);
		
		pepe = new Player();
		pepe.setId(TEST_PLAYER2_ID);
		pepe.setFirstName("Pepe");
		pepe.setLastName("Ruiz");
		pepe.setDescription("me gustan las palomas");
		User user2=new User();
        user2.setUsername("pepe");
        user2.setPassword("12341234");
        user2.setEnabled(true);
        pepe.setUser(user2);        
		pepe.setPoints(4);
		given(this.playerService.findPlayerById(TEST_PLAYER2_ID)).willReturn(pepe);
		
//CREAR JUEGO
		
		
		game = new OcaGame();
		mario.setId(TEST_GAME_ID);
		game.setInGame(Boolean.FALSE);   
        game.setName("JuegoOca");
        game.setPlayers(2);
        game.setReward(20);
        given(this.ocaGameService.findGameById(TEST_GAME_ID)).willReturn(game);

	
	
	//CREAR TURNO
	
	
	 turn= new OcaTurn();
	 turn.setTurn(3);   
	 turn.setDice(0);
	 turn.setI(0);
	 turn.setPlayer(mario);
	 turn.setPlayers(List.of(mario, pepe));;
	 given(this.ocaTurnService.findTurnById(TEST_TURN_ID)).willReturn(turn);

		
	//CREAR FICHA
	
	
		piece= new OcaPiece();
		piece.setPosition(3);  
		 piece.setPlayer(mario);
		 given(this.ocaPieceService.findPieceById(TEST_PIECE_ID)).willReturn(piece);

			}

//	@Test
//	void shouldModifyTurnAttributes() {
//		mockMvc.perform(get("/ocaTurn"+"/"+TEST_GAME_ID+"/"+TEST_PLAYER_ID));
//		OcaTurn turn = this.ocaTurnService.findTurnById(TEST_TURN_ID);
//		assertThat(!turn.getDice().equals(0));
//		assertThat(turn.getTurn().equals(4));
//		assertThat(turn.getI().equals(1));
//	}


}