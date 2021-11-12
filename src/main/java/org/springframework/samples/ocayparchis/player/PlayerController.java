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
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.ocayparchis.user.AuthoritiesService;
import org.springframework.samples.ocayparchis.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
@RequestMapping("/players")
public class PlayerController {

	private static final String VIEWS_PLAYER_CREATE_OR_UPDATE_FORM = "players/createOrUpdateOwnerForm";

	private final PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService, UserService userService, AuthoritiesService authoritiesService) {
		this.playerService = playerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/new")
	public String initCreationForm(Map<String, Object> model) {
		Player player = new Player();
		model.put("player", player);
		return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Player player, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating player, user and authorities
			this.playerService.savePlayer(player);
			
			return "redirect:/players/" + player.getId();
		}
	}

	@GetMapping(value = "/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("player", new Player());
		return "players/findPlayers";
	}

	@GetMapping()
	public String playerList(Player player, BindingResult result, ModelMap modelMap){
		String vista = "players/playersList";
		if(player.getUser().getUsername()=="") {
			Iterable<Player> results=playerService.findAll();
			modelMap.addAttribute("players",results);
			return vista;
		}
		Collection<Player> results = playerService.findPlayerByUsername(player.getUser().getUsername());
		if(results.isEmpty()) {
			result.rejectValue("username", "notFound", "not found");
			return "player/findPlayers";
		}
			else if(results.size()==1) {
			player = results.iterator().next();
			return "redirect:/players/"+player.getId();
		}
		else {
			modelMap.addAttribute("players",results);
			return vista;
		}
		
		
	}

	@GetMapping(value = "/{playerId}/edit")
	public String initUpdateplayerForm(@PathVariable("playerId") int playerId, ModelMap model) {
		Player player = this.playerService.findPlayerById(playerId);
		model.addAttribute(player);
		return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/{playerId}/edit")
	public String processUpdateplayerForm(@Valid Player player, BindingResult result,
			@PathVariable("playerId") int playerId) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		}
		else {
			player.setId(playerId);
			this.playerService.savePlayer(player);
			return "redirect:/players/{playerId}";
		}
	}

	/**
	 * Custom handler for displaying an player.
	 * @param playerId the ID of the player to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/{playerId}")
	public ModelAndView showplayer(@PathVariable("playerId") int playerId) {
		ModelAndView mav = new ModelAndView("players/playerDetails");
		mav.addObject(this.playerService.findPlayerById(playerId));
		return mav;
	}

}
