/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.ocayparchis.player;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.ocayparchis.user.AuthoritiesService;
import org.springframework.samples.ocayparchis.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class PlayerService {

	private PlayerRepository playerRepository;	
	
	private UserService userService;
	
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public PlayerService(PlayerRepository playerRepository, UserService userService,
			AuthoritiesService authoritiesService) {
		super();
		this.playerRepository = playerRepository;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	
	public Iterable<Player> findAll() {
		return playerRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Player findPlayerById(int id) throws DataAccessException {
		return playerRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public Collection<Player> findPlayerByUsername(String username) throws DataAccessException {
		return playerRepository.findByUserName(username);
	}

	@Transactional
	public void savePlayer(Player player) throws DataAccessException {
		//creating owner
		playerRepository.save(player);		
		//creating user
		userService.saveUser(player.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(player.getUser().getUsername(), "player");
	}		

}
