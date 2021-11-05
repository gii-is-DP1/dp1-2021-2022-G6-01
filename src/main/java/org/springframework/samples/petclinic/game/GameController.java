package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Game;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/games")
public class GameController {
	
	private static final String VIEWS_GAME_CREATE_OR_UPDATE_FORM = "games/editGame";
	@Autowired
	private GameService gameService;
	@GetMapping()
	public String gameList(ModelMap modelMap){
		String vista = "games/gameList";
		Iterable<Game> games=gameService.findAll();
		modelMap.addAttribute("games",games);
		return vista;
		
	}
	@GetMapping(path="/new")
	public String createGame(ModelMap modelMap){
		String view = "games/editGame";
		Iterable<Game> games=gameService.findAll();
		modelMap.addAttribute("game",new Game());
		return view;
		
	}
	@PostMapping(path="/save")
	public String saveGame(@Valid Game game,BindingResult result,ModelMap modelMap){
		String view = "games/gameList";
		if(result.hasErrors()) {
			modelMap.addAttribute("game",game);
			return "games/editGame";
		}else
		{
			gameService.save(game);
			modelMap.addAttribute("message","game successfully saved");
			view=gameList(modelMap);
		}
		
		return view;
		
	}
	
//	@GetMapping(path="/delete/{gameId}")
//	public String deleteGame(@PathVariable("gameId") int gameId,ModelMap modelMap){
//		String view = "games/gameList";
//		Optional<Game>game =gameService.findGameById(gameId);
//		if(game.isPresent()) {
//			gameService.delete(game.get());
//			modelMap.addAttribute("message","game successfully deleted");
//		}else {
//			modelMap.addAttribute("message","game not found");
//			view=gameList(modelMap);
//		}
//		return view;
//		
//	}

	@GetMapping(value = "/{gameId}/edit")
	public String initUpdateGameForm(@PathVariable("gameId") int gameId, Model model) {
		Game game = this.gameService.findGameById(gameId);
		model.addAttribute(game);
		return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/{gameId}/edit")
	public String processUpdateGamerForm(@Valid Game game, BindingResult result,
			@PathVariable("gameId") int gameId) {
		if (result.hasErrors()) {
			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		}
		else {
			game.setId(gameId);
			this.gameService.save(game);
			return "redirect:/games/{gameId}";
		}
	}

}
