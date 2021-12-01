package org.springframework.samples.ocayparchis.parchisgame;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.model.ParchisTurn;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.squares.Square;
import org.springframework.samples.ocayparchis.squares.SquareService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/parchisTurn")
public class ParchisTurnController {
	
	private ParchisTurnService parchisTurnService;

	private PlayerService playerService;
	
	private ParchisPieceService parchisPieceService;

	private ParchisGameService parchisGameService;
	
	private SquareService squareService;
	
	@Autowired
	public ParchisTurnController(ParchisTurnService parchisTurnService, PlayerService playerService,
			ParchisPieceService parchisPieceService, ParchisGameService parchisGameService, SquareService squareService) {
		super();
		this.parchisTurnService = parchisTurnService;
		this.playerService = playerService;
		this.parchisPieceService = parchisPieceService;
		this.parchisGameService = parchisGameService;
		this.squareService = squareService;
	}
	@GetMapping(path="/{parchisGameId}/{playerId}")
	public  ModelAndView playTurn (@PathVariable("parchisGameId") int parchisGameId,@PathVariable("playerId") Integer playerId,@AuthenticationPrincipal Authentication user){
		ModelAndView mav = new ModelAndView("parchisGames/parchisTurnDetails");
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		if(!turn.getIsThrowed()){
			turn.throwDices();
		}
		String username = user.getName();
		Player currentPlayer = this.playerService.findPlayerByUsername(username).iterator().next();
		this.parchisTurnService.save(turn);
		mav.addObject(turn);
		mav.addObject(currentPlayer);
		return mav;		
	}
	
	@GetMapping(path="/{parchisGameId}/{playerId}/{dice}")
	public  ModelAndView moveSelection (@PathVariable("parchisGameId") int parchisGameId,@PathVariable("playerId") Integer playerId,@PathVariable("dice") Integer diceNumber){
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		Integer dice = 0;
		if(diceNumber ==1) {
			dice = turn.getDice1();
		}else if(diceNumber==2) {
			dice = turn.getDice2();
		}
		ModelAndView mav = new ModelAndView("parchisGames/parchisDiceSelection");
		mav.addObject("dice", dice);
		return mav;
	}
	
	@PostMapping(path="/{parchisGameId}/{playerId}/{dice}")
	public ModelAndView assignPosition (@PathVariable("parchisGameId") int parchisGameId,@PathVariable("playerId") Integer playerId,@PathVariable("dice") Integer diceNumber, BindingResult result, @AuthenticationPrincipal Authentication user) {
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		Integer dice = 0;
		if(diceNumber ==1) {
			dice = turn.getDice1();
		}else if(diceNumber==2) {
			dice = turn.getDice2();
		}
		ParchisPiece piece = (ParchisPiece) result.getFieldValue("pieces");
		Square actual_square = piece.getSquare();
		List<ParchisPiece> actual_square_pieces = actual_square.getPieces(); //crear servicio de eliminar ficha de square y añadir ficha en square
		actual_square_pieces.remove(piece);
		actual_square.setPieces(actual_square_pieces);
		this.squareService.save(actual_square);
		Square next_square= this.squareService.findByPosition(actual_square.getPosition()+dice);
		piece.setSquare(next_square);
		List<ParchisPiece> next_square_pieces = next_square.getPieces();
		next_square_pieces.add(piece);
		next_square.setPieces(next_square_pieces);
		this.squareService.save(next_square);
		this.parchisPieceService.save(piece);
		
		String username = user.getName();
		Player currentPlayer = this.playerService.findPlayerByUsername(username).iterator().next();
		
		ModelAndView mav = new ModelAndView("parchisGames/parchisTurnDetails");
		mav.addObject(currentPlayer);
		mav.addObject(turn);
		return mav;
	}
	
	@ModelAttribute("pieces")
	public Collection<ParchisPiece> populateParchisPieces(@AuthenticationPrincipal Authentication user){
		String username = user.getName();
		Player currentPlayer = this.playerService.findPlayerByUsername(username).iterator().next();
		return this.parchisPieceService.findByPlayerId(currentPlayer.getId());
	}

	

}
