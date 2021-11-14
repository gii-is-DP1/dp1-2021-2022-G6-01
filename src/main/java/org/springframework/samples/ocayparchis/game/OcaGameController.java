package org.springframework.samples.ocayparchis.game;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ocaGames")
public class OcaGameController {
	
	private static final String VIEWS_GAME_CREATE_OR_UPDATE_FORM = "ocaGames/editGame";
	@Autowired
	private OcaGameService gameService;
	@GetMapping()
	public String gameList(ModelMap modelMap){
		String vista = "ocaGames/gameList";
		Iterable<OcaGame> games=gameService.findAll();
		modelMap.addAttribute("games",games);
		return vista;
		
	}
	@GetMapping(path="/new")
	public String createGame(ModelMap modelMap){
		String view = "ocaGames/editGame";
		Iterable<OcaGame> games=gameService.findAll();
		modelMap.addAttribute("game",new OcaGame());
		return view;
		
	}
	@PostMapping(path="/save")
	public String saveGame(@Valid OcaGame game,BindingResult result,ModelMap modelMap){
		String view = "ocaGames/gameList";
		if(result.hasErrors()) {
			modelMap.addAttribute("game",game);
			return "ocaGames/editGame";
			
		}else
		{
			gameService.save(game);
			modelMap.addAttribute("message","game successfully saved");
			view=gameList(modelMap);
		}
		
		return view;
		
	}
	

//	@GetMapping(value = "/{gameId}/edit")
//	public String initUpdateGameForm(@PathVariable("gameId") int gameId, Model model) {
//		OcaGame game = this.gameService.findGameById(gameId);
//		model.addAttribute(game);
//		return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
//	}
//
//	@PostMapping(value = "/{gameId}/edit")
//	public String processUpdateGamerForm(@Valid OcaGame game, BindingResult result,
//			@PathVariable("gameId") int gameId) {
//		if (result.hasErrors()) {
//			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
//		}
//		else {
//			game.setId(gameId);
//			this.gameService.save(game);
//			return "redirect:/ocaGames/{gameId}";
//		}
//	}

}