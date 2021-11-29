package org.springframework.samples.ocayparchis.parchisgame;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.OcaBoardService;
import org.springframework.samples.ocayparchis.board.ParchisBoardService;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.model.ParchisGame;
import org.springframework.samples.ocayparchis.model.ParchisTurn;
import org.springframework.samples.ocayparchis.ocagame.OcaGameService;
import org.springframework.samples.ocayparchis.ocagame.OcaTurnService;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.OcaPieceService;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/parchisGames")
public class ParchisGameController {
	
	private static final String VIEWS_GAME_CREATE_OR_UPDATE_FORM = "parchisGames/editGame";
	
	private ParchisGameService parchisGameService;
	
	private ParchisPieceService parchisPieceService;
	
	private ParchisTurnService parchisTurnService;
	
	private PlayerService playerService;

	private ParchisBoardService parchisBoardService;
	
	@Autowired
	public ParchisGameController(ParchisGameService parchisGameService, ParchisPieceService parchisPieceService,
			ParchisTurnService parchisTurnService, PlayerService playerService,ParchisBoardService parchisBoardService) {
		super();
		this.parchisGameService = parchisGameService;
		this.parchisPieceService = parchisPieceService;
		this.parchisTurnService = parchisTurnService;
		this.playerService = playerService;
		this.parchisBoardService = parchisBoardService;
	}

	@GetMapping()
	public String gameList(ModelMap modelMap){
		String vista = "parchisGames/gameList";
		Iterable<ParchisGame> games=parchisGameService.findAll();
		modelMap.addAttribute("parchisGames",games);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String createGame(ModelMap modelMap){
		modelMap.addAttribute("parchisGame",new ParchisGame());
		return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(path="/save")
	public String saveGame(@Valid ParchisGame game, BindingResult result,ModelMap modelMap){
		String view = "parchisGames/gameList";
		if(result.hasErrors()) {
			modelMap.addAttribute("parchisGame",game);
			return VIEWS_GAME_CREATE_OR_UPDATE_FORM;
		}else{
			ParchisTurn turn= new ParchisTurn();
			this.parchisTurnService.save(turn);
			parchisGameService.save(game);
			modelMap.addAttribute("message","game successfully saved");
			view=gameList(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{parchisGameId}")
	public String deleteGame(@PathVariable("parchisGameId") int parchisGameId){
		ParchisGame game =this.parchisGameService.findGameById(parchisGameId);
		this.parchisGameService.delete(game);
		return "redirect:/";
		
	}
	
	@GetMapping("/{parchisGameId}")
	public ModelAndView showGame(@PathVariable("parchisGameId") int parchisGameId, HttpServletResponse response) {
		response.addHeader("Refresh", "2");
		ModelAndView mav = new ModelAndView("parchisGames/parchisGameDetails");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		ParchisPiece piece = new ParchisPiece();
		if(auth!=null) {
			if (auth.isAuthenticated()) {
				String username = auth.getName();
				Player currentPlayer = playerService.findPlayerByUsername(username).iterator().next();
				List<Player> players = turn.getPlayers();
				if(!(players.contains(currentPlayer))) {
					
					players.add(currentPlayer);
					if(currentPlayer==turn.getPlayers().get(0)) {
						turn.setPlayer(currentPlayer);
						this.parchisTurnService.save(turn);
					}
					piece.setPlayer(currentPlayer);
					this.parchisPieceService.save(piece);
					 
				}
				piece.setPlayer(currentPlayer);
				this.parchisPieceService.save(piece);
				ParchisPiece piece_2=this.parchisPieceService.findByPlayerId(currentPlayer.getId()).iterator().next();
				if(piece_2!=null) {
					piece = piece_2;
				}
			}
		}
		mav.addObject(piece);
		String username = auth.getName();
		Player currentPlayer = playerService.findPlayerByUsername(username).iterator().next();
		mav.addObject(currentPlayer);
		mav.addObject(turn);
		mav.addObject(this.parchisGameService.findGameById(parchisGameId));
//		mav.addObject(this.parchisBoardService.findById(parchisGameId));
		return mav;
	}
}
