package org.springframework.samples.ocayparchis.game;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.OcaBoardService;
import org.springframework.samples.ocayparchis.model.Game;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.OcaPieceService;
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
import org.springframework.web.bind.annotation.PutMapping;
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
	@Autowired
	private OcaTurnService ocaTurnService;
	@Autowired
	private OcaPieceService ocaPieceService;
	@GetMapping()
	public String gameList(ModelMap modelMap){
		String vista = "ocaGames/gameList";
		Iterable<OcaGame> games=ocaGameService.findAll();
		modelMap.addAttribute("games",games);
		return vista;
		
	}
	@GetMapping(path="/winner/{ocaGameId}/{playerId}")
	public ModelAndView showWinner(@PathVariable("playerId") int playerId,@PathVariable("ocaGameId") int ocaGameId) {
		ModelAndView mav = new ModelAndView("ocaGames/winner");
		Player player = this.playerService.findPlayerById(playerId);
		OcaGame ocaGame = this.ocaGameService.findGameById(ocaGameId);
		player.setPoints(player.getPoints()+ocaGame.getReward());
		this.playerService.savePlayer(player);
		mav.addObject(ocaGame);
		mav.addObject(player);
		return mav;
	}
	
	@GetMapping(path="/delete/{ocaGameId}")
	public String deleteGame(@PathVariable("ocaGameId") int ocaGameId){
		OcaGame game =this.ocaGameService.findGameById(ocaGameId);
		OcaTurn turn = this.ocaTurnService.findTurnById(ocaGameId);
		for(Player p: turn.getPlayers()) {
			this.ocaPieceService.delete(p.getOcaPiece());
		}
		this.ocaGameService.delete(game);
		this.ocaTurnService.delete(turn);
		return "redirect:/";
		
	}
	@GetMapping(path="/new")
	public String createGame(ModelMap modelMap){
		String view = "ocaGames/editGame";
		Iterable<OcaGame> games=ocaGameService.findAll();
		modelMap.addAttribute("game",new OcaGame());
		return view;
		
	}
	@PostMapping(path="/save")
	public String saveGame(@Valid OcaGame game,@Valid OcaBoard board,@Valid OcaTurn turn,
			@Valid OcaPiece piece,BindingResult result,ModelMap modelMap){
		String view = "ocaGames/gameList";
		if(result.hasErrors()) {
			modelMap.addAttribute("game",game);
			return "ocaGames/editGame";
			
		}else
		{
			ocaTurnService.save(turn);
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
		OcaTurn turn = this.ocaTurnService.findTurnById(ocaGameId);
		
		if(auth!=null) {
			if (auth.isAuthenticated()) {
				String username = auth.getName();
				Player currentPlayer = playerService.findPlayerByUsername(username).iterator().next();
				currentPlayer.setOcaGame(this.ocaGameService.findGameById(ocaGameId));
				List<Player> players = turn.getPlayers();
				if(!(players.contains(currentPlayer))) {
					OcaPiece piece = new OcaPiece();
					players.add(currentPlayer);
					currentPlayer.setOcaTurns(turn);
					if(currentPlayer==turn.getPlayers().get(0)) {
						turn.setPlayer(currentPlayer);
						this.ocaTurnService.save(turn);
					}
					this.ocaPieceService.save(piece);
					currentPlayer.setOcaPiece(piece);
				}
			}
		}
		String username = auth.getName();
		Player currentPlayer = playerService.findPlayerByUsername(username).iterator().next();
		mav.addObject(currentPlayer);
		mav.addObject(turn);
		mav.addObject(this.ocaGameService.findGameById(ocaGameId));
		mav.addObject(this.ocaBoardService.findById(ocaGameId));
		return mav;
	}

}