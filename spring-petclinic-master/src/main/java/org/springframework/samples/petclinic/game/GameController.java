package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")


public class GameController {
	@Autowired
	private GameService gameService;

	@GetMapping()
	public String ListadoGames(ModelMap modelMap) {
		String vista = "/game/gameList";
		Iterable<Game> games = gameService.findAll();
		modelMap.addAttribute("games", games);
		
		return vista;
	}
}
