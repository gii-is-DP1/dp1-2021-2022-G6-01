package org.springframework.samples.ocayparchis.parchisgame;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.OcaBoardService;
import org.springframework.samples.ocayparchis.board.ParchisBoardService;
import org.springframework.samples.ocayparchis.configuration.SecurityConfiguration;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.ocagame.OcaGameController;
import org.springframework.samples.ocayparchis.ocagame.OcaGameRepository;
import org.springframework.samples.ocayparchis.ocagame.OcaGameService;
import org.springframework.samples.ocayparchis.ocagame.OcaTurnService;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.OcaPieceService;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.squares.SquareService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ParchisGameController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class ParchisGameControllerTest {

	 	@MockBean
	 	ParchisGameService parchisGameService;
	 	@MockBean
		SquareService squareService;
	 	@MockBean
		ParchisPieceService parchisPieceService;
	 	@MockBean
		ParchisTurnService parchisTurnService;
	 	@MockBean
		PlayerService playerService;
	 	@MockBean
		ParchisBoardService parchisBoardService;
		
	    @Autowired
		private MockMvc mockMvc;
	    
	    @WithMockUser(value = "spring")
	    @Test
		void testInitCreationForm() throws Exception {
			mockMvc.perform(get("/parchisGames/new")).andExpect(status().isOk())
					.andExpect(view().name("parchisGames/editGame"))
	                .andExpect(model().attributeExists("parchisGame"));
		}
	    
	    
	    @WithMockUser(value = "spring")
	    @Test
		void testInitCreationFormNegative() throws Exception {
			mockMvc.perform(get("/parchisGames/new")).andExpect(status().isOk())
					.andExpect(view().name("parchisGames/editGame"))
	                .andExpect(model().attributeDoesNotExist("parchisTurn"));
		}
	    
	    @WithMockUser(value = "spring")
	    @Test
		void testgameList() throws Exception {
			mockMvc.perform(get("/parchisGames")).andExpect(status().isOk())
					.andExpect(view().name("parchisGames/gameList"))
	                .andExpect(model().attributeExists("parchisGames"));
		}

	    @WithMockUser(value = "spring")
	    @Test
		void testgameListNegative() throws Exception {
			mockMvc.perform(get("/parchisGames")).andExpect(status().isOk())
					.andExpect(view().name("parchisGames/gameList"))
	                .andExpect(model().attributeDoesNotExist("game"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @Test
		void testSaveGame() throws Exception {
	    	mockMvc.perform(post("/parchisGames/save")
	    			.with(csrf())
	    			.param("name", "Game1")
	    			.param("reward", "500")
	    			.param("players", "3"))
	    	.andExpect(status().isOk())
	    	.andExpect(view().name("parchisGames/gameList"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @Test
		void testSaveGameWrongGameName() throws Exception {
	    	mockMvc.perform(post("/parchisGames/save")
	    			.with(csrf())
	    			.param("name", "1")
	    			.param("reward", "500")
	    			.param("players", "3"))
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasErrors("parchisGame"))
			.andExpect(view().name("parchisGames/editGame"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @Test
		void testSaveGameWrongReward() throws Exception {
	    	mockMvc.perform(post("/parchisGames/save")
	    			.with(csrf())
	    			.param("name", "1")
	    			.param("players", "3"))
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasErrors("parchisGame"))
			.andExpect(view().name("parchisGames/editGame"));
		}
	    
	    @WithMockUser(value = "spring", authorities = {"admin"})
	    @ParameterizedTest
	    @ValueSource(strings= {"1","5"})
	    void testSaveGameWrongPlayers(String players) throws Exception {
	    	mockMvc.perform(post("/parchisGames/save")
	    			.with(csrf())
	    			.param("name", "1")
	    			.param("reward", "500")
	    			.param("players", players))
	    	.andExpect(status().isOk())
	    	.andExpect(model().attributeHasErrors("parchisGame"))
	    	.andExpect(view().name("parchisGames/editGame"));
	    }
	    
}
