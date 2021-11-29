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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/parchisTurn")
public class ParchisTurnController {
	
	private ParchisTurnService parchisTurnService;

	private PlayerService playerService;
	
	private ParchisPieceService parchisPieceService;

	private ParchisGameService parchisGameService;
	
	@Autowired
	public ParchisTurnController(ParchisTurnService parchisTurnService, PlayerService playerService,
			ParchisPieceService parchisPieceService, ParchisGameService parchisGameService) {
		super();
		this.parchisTurnService = parchisTurnService;
		this.playerService = playerService;
		this.parchisPieceService = parchisPieceService;
		this.parchisGameService = parchisGameService;
	}
	@GetMapping(path="/{parchisGameId}/{playerId}")
	public  ModelAndView playTurn (@PathVariable("parchisGameId") int parchisGameId,@PathVariable("playerId") Integer playerId){
		ModelAndView mav = new ModelAndView("parchisGames/parchisTurnDetails");
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		turn.throwDices();
		this.parchisTurnService.save(turn);
		mav.addObject(turn);
		return mav;		
	}
//	public  String moveSelection (@PathVariable("parchisGameId") int parchisGameId,@PathVariable("playerId") Integer playerId){
//		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
//		Integer dice1=turn.getDice1();
//		Integer dice2=turn.getDice2();
//		String view = "ocaGames/editGame";
//		modelMap.addAttribute("ocaGame",new OcaGame());
//		return view;
//		
//		
//
//	
//	}
	@ModelAttribute("pieces")
    public Collection<ParchisPiece> populateParchisPieces(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Player currentPlayer = new Player();
        if(auth!=null) {
            if (auth.isAuthenticated()) {
                String username = auth.getName();
                currentPlayer = this.playerService.findPlayerByUsername(username).iterator().next();
            }
        }
        return this.parchisPieceService.findByPlayerId(currentPlayer.getId());
    }
	

}
