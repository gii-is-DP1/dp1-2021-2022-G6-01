package org.springframework.samples.ocayparchis.game;

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


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.ocayparchis.configuration.SecurityConfiguration;
import org.springframework.samples.ocayparchis.ocagame.OcaGameController;
import org.springframework.samples.ocayparchis.ocagame.OcaGameService;
import org.springframework.samples.ocayparchis.model.OcaGame;
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

@WebMvcTest(controllers = OcaGameController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class OcaGameControllerTests {

	private static final int TEST_PLAYER_ID = 6;

	private static final Integer TEST_OCAGAME_ID = 2;

	@MockBean
	private OcaGameController ocaGameController;

	@MockBean
	private PlayerService playerService;

	@MockBean
	private OcaGameService ocaGameService;

	@Autowired
	private MockMvc mockMvc;

	private Player mario;
	
	private OcaGame pato;
	

	@BeforeEach
	void setup() {

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
		pato = new OcaGame();
		pato.setId(TEST_OCAGAME_ID);
		pato.setInGame(true);
		pato.setName("pato");
		pato.setPlayers(2);
		pato.setReward(100);
		given(this.ocaGameService.findGameById(TEST_OCAGAME_ID)).willReturn(pato);
	}
//	@WithMockUser(value = "admin1")
//	@Test
//	void testcreateGames() throws Exception {
//		mockMvc.perform(get("/ocaGames/new")).andExpect(status().isOk()).andExpect(model().attributeExists("ocaGame"))
//				.andExpect(view().name("ocaGames/editGame"));
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessCreationFormSuccess() throws Exception {
//		mockMvc.perform(post("/players/new").param("first_name", "Mario").param("last_name", "Rey").with(csrf())
//				.param("description", "me gusta mucho el cadiz"))
//				.andExpect(status().is2xxSuccessful());
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/players/new").with(csrf()).param("first_name", "Joe").param("last_name", "Bloggs"))
//				.andExpect(status().isOk()).andExpect(model().attributeHasErrors("player"))
//				.andExpect(model().attributeHasFieldErrors("player", "description"))
//				.andExpect(view().name("players/createOrUpdateOwnerForm"));
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testInitFindForm() throws Exception {
//		mockMvc.perform(get("/players/find")).andExpect(status().isOk()).andExpect(model().attributeExists("player"))
//				.andExpect(view().name("players/findPlayers"));
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessFindFormSuccess() throws Exception {
//		given(this.playerService.findPlayerById(6)).willReturn(mario);
//
//		mockMvc.perform(get("/players/6")).andExpect(status().isOk()).andExpect(view().name("players/playerDetails"));
//	}
//	@WithMockUser(value = "spring")
//    @Test
//    void testProcessFindByUsernameFormSuccess() throws Exception {
//        given(this.playerService.findPlayerByUsername("mario")).willReturn(List.of(mario));
//
//        mockMvc.perform(get("/players/6")).andExpect(status().isOk()).andExpect(view().name("players/playerDetails"));
//    }
//

}

