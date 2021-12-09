package org.springframework.samples.ocayparchis.parchisgame;


import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
import org.springframework.samples.ocayparchis.model.ParchisTurn;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.player.PlayerService;
import org.springframework.samples.ocayparchis.squares.Color;
import org.springframework.samples.ocayparchis.squares.Square;
import org.springframework.samples.ocayparchis.squares.SquareService;
import org.springframework.samples.parchisBusinessRules.ParchisRules;
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
		List<ParchisPiece> pieces = (List<ParchisPiece>) this.parchisPieceService.findByPlayerId(currentPlayer.getId()); 
		int i=1;
		for(ParchisPiece p : pieces) {
			mav.addObject("piece"+i,p);
			i++;
		}
		return mav;		
	}
	
	@GetMapping(path="/{parchisGameId}/{playerId}/{dice}")
	public  ModelAndView moveSelection (@PathVariable("parchisGameId") int parchisGameId,@PathVariable("playerId") Integer playerId,@PathVariable("dice") Integer diceNumber,@AuthenticationPrincipal Authentication user){
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		Integer dice = 0;
		if(diceNumber ==1) {
			dice = turn.getDice1();
		}else if(diceNumber==2) {
			dice = turn.getDice2();
		}
		String username = user.getName();
		Player currentPlayer = this.playerService.findPlayerByUsername(username).iterator().next();
		for(ParchisPiece p:currentPlayer.getPieces()) {
			
			p.setCanMove(true);
			if(!this.squareService.findByPosition(p.posicionActual()).isHouse()) {
				canMove(dice,p);
			}
			
			this.parchisPieceService.save(p);
		}
		
		ModelAndView mav = new ModelAndView("parchisGames/parchisPieceSelection");
		mav.addObject("dice", dice);
		
		List<ParchisPiece> pieces = (List<ParchisPiece>) this.parchisPieceService.findByPlayerId(currentPlayer.getId()); 
		int i=1;
		for(ParchisPiece p : pieces) {
			mav.addObject("piece"+i,p);
			i++;
		}
		
		mav.addObject(currentPlayer);
		mav.addObject(turn);
		return mav;
	}
	
	@GetMapping(path="/{parchisGameId}/{playerId}/{dice}/{ParchisPieceId}")
	public String assignPosition (@PathVariable("parchisGameId") int parchisGameId,
			@PathVariable("playerId") Integer playerId,@PathVariable("dice") Integer diceNumber, @PathVariable("ParchisPieceId") int parchisId) {
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		ParchisPiece piece = this.parchisPieceService.findPieceById(parchisId);
		Square next_square = new Square();
		if(diceNumber!=0) {
			turn.setDicesAvailable(turn.getDicesAvailable()-1);
			next_square = movePiece(piece, diceNumber);
		}
		if(turn.getDice1().equals(diceNumber)) {
			turn.setDice1(0);
		}else {
			turn.setDice2(0);
		}
		this.parchisTurnService.save(turn);
		this.squareService.save(next_square);
		this.parchisPieceService.save(piece);
		if(turn.getDicesAvailable()<1) {
			turn.nextTurn();
			this.parchisTurnService.save(turn);
			return "redirect:/parchisGames/"+parchisGameId;
		}
		if(piece.getColor().equals(Color.BLUE)){//para probar canmove
			piece.setSquare(this.squareService.findByPosition(40));
			this.parchisTurnService.save(turn);
			this.squareService.save(next_square);
			this.parchisPieceService.save(piece); 
		}
			
		return "redirect:/parchisTurn/"+parchisGameId+"/"+playerId;
	}
	
	public Square movePiece(ParchisPiece piece,Integer diceNumber) {
		Square actual_square = piece.getSquare();
		List<ParchisPiece> actual_square_pieces = actual_square.getPieces(); 
		actual_square_pieces.remove(piece);
		actual_square.setPieces(actual_square_pieces);
		this.squareService.save(actual_square);
		Square next_square=new Square();
		if(diceNumber==5&&actual_square.isHouse()) {
			next_square= takePieceOut(piece,diceNumber,actual_square);
		}else if(!piece.getInStart()){
			next_square = this.squareService.findByPosition(actual_square.getPosition()+diceNumber);
		}else {
			next_square=actual_square;
		}
		
		piece.setSquare(next_square);
		List<ParchisPiece> next_square_pieces = next_square.getPieces();
		next_square_pieces.add(piece);
		next_square.setPieces(next_square_pieces);
		return next_square;
	}
	
	public Square takePieceOut(ParchisPiece piece,Integer diceNumber, Square actual_square) {
		if(actual_square.isHouse()&&Color.BLUE==piece.getColor()) {
			piece.setInStart(false);
			this.parchisPieceService.save(piece);
			return this.squareService.findByPosition(22);
		}else if(actual_square.isHouse()&&Color.YELLOW==piece.getColor()) {
			piece.setInStart(false);
			this.parchisPieceService.save(piece);
			return this.squareService.findByPosition(5);
		}else if(actual_square.isHouse()&&Color.GREEN==piece.getColor()) {
			piece.setInStart(false);
			this.parchisPieceService.save(piece);
			return this.squareService.findByPosition(56);
		}else {
			piece.setInStart(false);
			this.parchisPieceService.save(piece);
			return this.squareService.findByPosition(39);
		}
	}
	public void canMove(Integer dice,ParchisPiece p) {
		
		 Integer pos=p.posicionActual();
		 Integer a=pos+1;
		int nextPos=pos+dice;
		for(int i =a;i<=nextPos;i++) {
			Square s=this.squareService.findByPosition(pos);
			if(s.isBloqueo()) { 
				p.setCanMove(false);
				break;
			}
		if(p.getSquare().isStair()) {
			if(p.getColor().equals(Color.BLUE)&&nextPos>84) { 
				p.setCanMove(false);
			}
			else if(p.getColor().equals(Color.YELLOW)&&nextPos>76) {
				p.setCanMove(false);
			}
			else if(p.getColor().equals(Color.RED)&&nextPos>92) {
				p.setCanMove(false);
			}
			else if(p.getColor().equals(Color.GREEN)&&nextPos>100) {
				p.setCanMove(false);
			}
		}
				
			}
		
		
		 
			
	
	
		
		
	}
	
	@ModelAttribute("pieces")
	public Collection<ParchisPiece> populateParchisPieces(@AuthenticationPrincipal Authentication user){
		String username = user.getName();
		Player currentPlayer = this.playerService.findPlayerByUsername(username).iterator().next();
		return this.parchisPieceService.findByPlayerId(currentPlayer.getId());
	}

	

}
