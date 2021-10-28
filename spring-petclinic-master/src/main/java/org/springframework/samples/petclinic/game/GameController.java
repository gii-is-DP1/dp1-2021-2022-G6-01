package org.springframework.samples.petclinic.game;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		modelMap.addAttribute("game", games);
		
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearGame(ModelMap modelMap) {
		String vista = "/game/editGame";
		
		modelMap.addAttribute("game", new Game());
		
		return vista;
	}
	
	@PostMapping(path="/save")
	public String salvarGame(@Valid Game game, BindingResult result, ModelMap modelMap) {
		String vista = "/game/gameList";
		if(result.hasErrors()) {
			modelMap.addAttribute("game", game);
			return "game/editGame";
		}else {
			gameService.save(game);
			modelMap.addAttribute("message", "Game successfully 	saved!");
			vista=ListadoGames(modelMap);
		}
		
		return vista;
	}
	
	@GetMapping(path="/delete/{gameId}")
		public String borrarGame(@PathVariable("gameId") int gameId, ModelMap modelMap) {
		String view = "/game/gameList";
		Optional<Game> game=gameService.findGameById(gameId);
		if(game.isPresent()) {
			gameService.delete(game.get());
			modelMap.addAttribute("message", "Game successfully deleted!");
			
		}
		else {
			modelMap.addAttribute("message", "Game not found");
			
		}
		return view;
		
	}
	
}
