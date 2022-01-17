package org.springframework.samples.ocayparchis.game;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.OcaBoardService;
import org.springframework.samples.ocayparchis.configuration.SecurityConfiguration;
import org.springframework.samples.ocayparchis.ocagame.OcaGameController;
import org.springframework.samples.ocayparchis.ocagame.OcaGameRepository;
import org.springframework.samples.ocayparchis.ocagame.OcaGameService;
import org.springframework.samples.ocayparchis.ocagame.OcaTurnService;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.OcaPieceService;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
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
 * Test class for {@link OcaGameController}
 *
 * @author Colin But
 */
@WebMvcTest(value = OcaGameController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class OcaGameControllerTest {

	 	@MockBean
		OcaGameService ocaGameService;
		@MockBean
		OcaGameRepository ocaGameRepository;
		@MockBean
		OcaBoardService ocaBoardService;
		@MockBean
		PlayerService playerService;
		@MockBean
		OcaTurnService ocaTurnService;
		@MockBean
		OcaPieceService ocaPieceService;
		
	    @Autowired
		private MockMvc mockMvc;

	    @BeforeEach
		void setup() {

			OcaGame ocaGame = new OcaGame();
			ocaGame.setId(7);
			ocaGame.setInGame(true);
			ocaGame.setName("Game1");
			ocaGame.setReward(500);
			ocaGame.setPlayers(2);
			
			OcaTurn ocaTurn = new OcaTurn();
			ocaTurn.setId(7);
			
			OcaPiece ocaPiece = new OcaPiece();
			ocaPiece.setId(7);
			
			OcaBoard ocaBoard = new OcaBoard();
			ocaBoard.setId(7);
			
//			Optional<OcaBoard> board = Optional.of(ocaBoard);
//			Mockito.when(this.ocaGameService.findGameById(7)).thenReturn(ocaGame);
//			Mockito.when(this.ocaTurnService.findTurnById(7)).thenReturn(ocaTurn);
//			Mockito.when(this.ocaPieceService.findByPlayerId(1)).thenReturn(ocaPiece);
//			Mockito.when(this.ocaBoardService.findById(7)).thenReturn(board);
//			Mockito.when(this.ocaTurnService.save(ocaTurn)).thenReturn(null);
//			Mockito.when(this.ocaPieceService.save(ocaPiece)).thenReturn(null);
		}
	    
	    @WithMockUser(value = "spring")
	    @Test
		void testInitCreationForm() throws Exception {
			mockMvc.perform(get("/ocaGames/new")).andExpect(status().isOk())
					.andExpect(view().name("ocaGames/editGame"))
	                .andExpect(model().attributeExists("ocaGame"));
		}
	    
	    
	    @WithMockUser(value = "spring")
	    @Test
		void testInitCreationFormNegative() throws Exception {
			mockMvc.perform(get("/ocaGames/new")).andExpect(status().isOk())
					.andExpect(view().name("ocaGames/editGame"))
	                .andExpect(model().attributeDoesNotExist("ocaTurn"));
		}
	    
	    @WithMockUser(value = "spring")
	    @Test
		void testgameList() throws Exception {
			mockMvc.perform(get("/ocaGames")).andExpect(status().isOk())
					.andExpect(view().name("ocaGames/gameList"))
	                .andExpect(model().attributeExists("games"));
		}

	    @WithMockUser(value = "spring")
	    @Test
		void testgameListNegative() throws Exception {
			mockMvc.perform(get("/ocaGames")).andExpect(status().isOk())
					.andExpect(view().name("ocaGames/gameList"))
	                .andExpect(model().attributeDoesNotExist("game"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @Test
		void testSaveGame() throws Exception {
	    	mockMvc.perform(post("/ocaGames/save")
	    			.with(csrf())
	    			.param("name", "Game1")
	    			.param("reward", "500")
	    			.param("players", "3"))
	    	.andExpect(status().isOk())
	    	.andExpect(view().name("ocaGames/gameList"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @Test
		void testSaveGameWrongGameName() throws Exception {
	    	mockMvc.perform(post("/ocaGames/save")
	    			.with(csrf())
	    			.param("name", "1")
	    			.param("reward", "500")
	    			.param("players", "3"))
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasErrors("ocaGame"))
			.andExpect(view().name("ocaGames/editGame"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @Test
		void testSaveGameWrongReward() throws Exception {
	    	mockMvc.perform(post("/ocaGames/save")
	    			.with(csrf())
	    			.param("name", "1")
	    			.param("players", "3"))
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasErrors("ocaGame"))
			.andExpect(view().name("ocaGames/editGame"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @ParameterizedTest
	    @ValueSource(strings= {"1","5"})
	    void testSaveGameWrongPlayers(String players) throws Exception {
	    	mockMvc.perform(post("/ocaGames/save")
	    			.with(csrf())
	    			.param("name", "1")
	    			.param("reward", "500")
	    			.param("players", players))
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasErrors("ocaGame"))
	    	.andExpect(view().name("ocaGames/editGame"));
	    }
	
//	    @WithMockUser(value = "spring", authorities = {"admin"})
//	    @Test
//		void testShowGame() throws Exception {
//			mockMvc.perform(get("/ocaGames/{ocaGameId}",1)).andExpect(status().isOk())
//					.andExpect(view().name("ocaGames/ocaGameDetails"))
//	                .andExpect(model().attributeExists("ocaGame"));
//		}
//	    
	    

//	    @WithMockUser(value = "spring", authorities = {"admin"})
//	    @Test
//		void testDeleteGame() throws Exception {
//			mockMvc.perform(get("/ocaGames/delete/1").with(csrf())).andExpect(status().isOk())
//					.andExpect(view().name("redirect:/"));
//			assertNull(this.ocaGameService.findGameById(1));
//		}
//	    
	    
}

