package org.springframework.samples.ocayparchis.game;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.OcaBoardService;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/ocaGames")
public class OcaGameController {
	
	private static final String VIEWS_GAME_CREATE_OR_UPDATE_FORM = "ocaGames/editGame";
	@Autowired
	private OcaGameService ocaGameService;
	@Autowired
	private OcaBoardService ocaBoardService;
	@Autowired
	private PlayerService playerService;
	@GetMapping()
	public String gameList(ModelMap modelMap){
		String vista = "ocaGames/gameList";
		Iterable<OcaGame> games=ocaGameService.findAll();
		modelMap.addAttribute("games",games);
		return vista;
		
	}
	@GetMapping(path="/new")
	public String createGame(ModelMap modelMap){
		String view = "ocaGames/editGame";
		Iterable<OcaGame> games=ocaGameService.findAll();
		modelMap.addAttribute("game",new OcaGame());
		return view;
		
	}
	@PostMapping(path="/save")
	public String saveGame(@Valid OcaGame game,@Valid OcaBoard board,BindingResult result,ModelMap modelMap){
		String view = "ocaGames/gameList";
		if(result.hasErrors()) {
			modelMap.addAttribute("game",game);
			return "ocaGames/editGame";
			
		}else
		{
			ocaBoardService.save(board);
			ocaGameService.save(game);
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
	
	@GetMapping("/{ocaGameId}")
	public ModelAndView showplayer(@PathVariable("ocaGameId") int ocaGameId) {
		ModelAndView mav = new ModelAndView("ocaGames/ocaGameDetails");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			if (auth.isAuthenticated()) {
				String username = auth.getName();
				Player currentPlayer = playerService.findPlayerByUsername(username).iterator().next();
				currentPlayer.setOcaGame(this.ocaGameService.findGameById(ocaGameId));
			}
		}
		mav.addObject(this.ocaGameService.findGameById(ocaGameId));
		mav.addObject(this.ocaBoardService.findById(ocaGameId));
		return mav;
	}
}