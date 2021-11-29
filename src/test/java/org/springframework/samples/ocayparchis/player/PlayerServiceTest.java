package org.springframework.samples.ocayparchis.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.samples.ocayparchis.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test of the Service and the Repository layer.
 * <p>
 * ClinicServiceSpringDataJpaTests subclasses benefit from the following services provided
 * by the Spring TestContext Framework:
 * </p>
 * <ul>
 * <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li>
 * <li><strong>Dependency Injection</strong> of test fixture instances, meaning that we
 * don't need to perform application context lookups. See the use of
 * {@link Autowired @Autowired} on the <code>{@link
 * PlayerServiceTests#clinicService clinicService}</code> instance variable, which uses
 * autowiring <em>by type</em>.
 * <li><strong>Transaction management</strong>, meaning each test method is executed in
 * its own transaction, which is automatically rolled back by default. Thus, even if tests
 * insert or otherwise change database state, there is no need for a teardown or cleanup
 * script.
 * <li>An {@link org.springframework.context.ApplicationContext ApplicationContext} is
 * also inherited and can be used for explicit bean lookup if necessary.</li>
 * </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Dave Syer
 */

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PlayerServiceTest {                
        @Autowired
	protected PlayerService playerService;
    
	@Test
	public void shouldFindPlayerByUserName() {
		Collection<Player> players = this.playerService.findPlayerByUsername("pacoJeje");
		assertThat(players.size()).isEqualTo(1);

		players = this.playerService.findPlayerByUsername("pacooJeje");
		assertThat(players.isEmpty()).isTrue();
	}
	
	@Test
	public void shouldFindAll() {
		Iterable<Player> players = this.playerService.findAll();
		assertEquals(1,((Collection<Player>) players).size());
	}

	@Test
	public void shouldFindPieceAtributes() {
		Player player = this.playerService.findPlayerById(1);
		assertThat(player.getLastName()).startsWith("Lop");
		assertThat(player.getDescription()).startsWith("jugador");
		assertThat(player.getUser().getUsername()).isEqualTo("pacoJeje");
		assertThat(player.getPoints()).isEqualTo(0);
	}

	@Test
	@Transactional
	public void shouldInsertPlayer() {
		Collection<Player> players = this.playerService.findPlayerByUsername("pacoJeje");
		int found = players.size();

		Player player = new Player();
		player.setFirstName("Pepe");
		player.setDescription("es una descripción");
		player.setLastName("Lopez");
		User user=new User();
		user.setUsername("paco");
		user.setPassword("supersecretpassword");
		user.setEnabled(true);
		player.setUser(user);                

		this.playerService.savePlayer(player);
		assertThat(player.getId().longValue()).isNotEqualTo(0);

		players = this.playerService.findPlayerByUsername("paco");
		assertThat(players.size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	public void shouldUpdateOwner() {
		Player player = this.playerService.findPlayerById(1);
		String oldLastName = player.getLastName();
		String newLastName = oldLastName + "X";

		player.setLastName(newLastName);
		this.playerService.savePlayer(player);

		// retrieving new name from database
		player = this.playerService.findPlayerById(1);
		assertThat(player.getLastName()).isEqualTo(newLastName);
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionInsertingPlayersWithTheSameUsername() {
		Player player = new Player();
		User user = new User();
		user.setUsername("1");
		user.setPassword("1");
		user.setEnabled(true);
		player.setUser(user);
		player.setDescription("1");
		player.setFirstName("1");
		player.setLastName("1");
		try {
			playerService.savePlayer(player);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user2 = new User();
		user2.setUsername("1");
		user2.setPassword("1");
		user2.setEnabled(true);
		Player anotherPlayerWithTheSameUsername = new Player();	
		anotherPlayerWithTheSameUsername.setDescription("2");
		anotherPlayerWithTheSameUsername.setFirstName("2");
		anotherPlayerWithTheSameUsername.setLastName("2");
		anotherPlayerWithTheSameUsername.setUser(user2);
		assertThrows(Exception.class, () ->{
			playerService.savePlayer(anotherPlayerWithTheSameUsername);
		});		
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionInsertingPlayerWithEmptyFirstName() {
		Player player = new Player();
		User user = new User();
		user.setUsername("1");
		user.setPassword("1");
		user.setEnabled(true);
		player.setUser(user);
		player.setLastName("1");
		player.setDescription("1");
		assertThrows(Exception.class, () ->{
			playerService.savePlayer(player);
		});		
	}

	@Test
	@Transactional
	public void shouldThrowExceptionInsertingPlayerWithEmptyLastName() {
		Player player = new Player();
		User user = new User();
		user.setUsername("1");
		user.setPassword("1");
		user.setEnabled(true);
		player.setUser(user);
		player.setFirstName("1");
		player.setDescription("1");
		assertThrows(Exception.class, () ->{
			playerService.savePlayer(player);
		});		
	}

	@Test
	@Transactional
	public void shouldThrowExceptionInsertingPlayerWithEmptyDescription() {
		Player player = new Player();
		User user = new User();
		user.setUsername("1");
		user.setPassword("1");
		user.setEnabled(true);
		player.setUser(user);
		player.setLastName("1");
		player.setFirstName("1");
		assertThrows(Exception.class, () ->{
			playerService.savePlayer(player);
		});		
	}


}