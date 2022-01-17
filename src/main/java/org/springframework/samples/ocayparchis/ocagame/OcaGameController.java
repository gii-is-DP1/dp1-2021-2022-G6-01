 package org.springframework.samples.ocayparchis.ocagame;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.OcaBoardService;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.OcaPieceService;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	
	private OcaGameService ocaGameService;
	
	private OcaBoardService ocaBoardService;
	
	private PlayerService playerService;
	
	private OcaTurnService ocaTurnService;
	
	private OcaPieceService ocaPieceService;
	
	
	@Autowired
	public OcaGameController(OcaGameService ocaGameService, OcaBoardService ocaBoardService,
			PlayerService playerService, OcaTurnService ocaTurnService, OcaPieceService ocaPieceService) {
		super();
		this.ocaGameService = ocaGameService;
		this.ocaBoardService = ocaBoardService;
		this.playerService = playerService;
		this.ocaTurnService = ocaTurnService;
		this.ocaPieceService = ocaPieceService;
	}
	
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
			this.ocaPieceService.delete(this.ocaPieceService.findByPlayerId(p.getId()));
		}
		turn.setPlayer(null);
		turn.setPlayers(null);
		this.ocaGameService.delete(game);
		this.ocaTurnService.delete(turn);
		return "redirect:/";
	}
	
	@GetMapping(path="/new")
	public String createGame(ModelMap modelMap){
		String view = "ocaGames/editGame";
		modelMap.addAttribute("ocaGame",new OcaGame());
		return view;
		
	}
	
	@PostMapping(path="/save")
	public String saveGame(@Valid OcaGame game, BindingResult result,ModelMap modelMap){
		
		String view = "ocaGames/gameList";
		if(result.hasErrors()) {
			modelMap.addAttribute("ocaGame",game);
			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
			
		}else{
			OcaTurn turn=new OcaTurn();
			OcaBoard board=new OcaBoard();
			turn.TurnInit();
			ocaTurnService.save(turn);
			ocaBoardService.save(board);
			ocaGameService.save(game);
			modelMap.addAttribute("message","game successfully saved");
			view=gameList(modelMap);
		}
		
		return view;
		
	}

	@GetMapping("/{ocaGameId}")
	public ModelAndView showGame(@PathVariable("ocaGameId") int ocaGameId, HttpServletResponse response,@AuthenticationPrincipal Authentication user) {
		response.addHeader("Refresh", "2");
		ModelAndView mav = new ModelAndView("ocaGames/ocaGameDetails");
		OcaTurn turn = this.ocaTurnService.findTurnById(ocaGameId);
		OcaPiece piece = new OcaPiece();
		String username = user.getName();
		Player currentPlayer = playerService.findPlayerByUsername(username).iterator().next();
		List<Player> players = turn.getPlayers();
		if(!(players.contains(currentPlayer))) {		
			players.add(currentPlayer);
			if(currentPlayer==turn.getPlayers().get(0)) {
				turn.setPlayer(currentPlayer);
				this.ocaTurnService.save(turn);
			}
			piece.setPlayer(currentPlayer);
			this.ocaPieceService.save(piece);
		}
		piece.setPlayer(currentPlayer);
		this.ocaPieceService.save(piece);
		OcaPiece piece_2=this.ocaPieceService.findByPlayerId(currentPlayer.getId());
		if(piece_2!=null) {
			piece = piece_2;
		}
		mav.addObject(piece);
		mav.addObject(currentPlayer);
		mav.addObject(turn);
		mav.addObject(this.ocaGameService.findGameById(ocaGameId));
		mav.addObject(this.ocaBoardService.findById(ocaGameId));
		return mav;
	}

}