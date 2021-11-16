package org.springframework.samples.ocayparchis.game;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.OcaBoardService;
import org.springframework.samples.ocayparchis.model.OcaGame;
import org.springframework.samples.ocayparchis.model.OcaTurn;
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
@RequestMapping("/ocaTurn")
public class OcaTurnController {
	
	
	@Autowired
	private OcaTurnService ocaTurnService;
	
	

	
	@GetMapping(path="/{ocaGameId}/{playerId}")
	public String playTurn(@PathVariable("ocaGameId") int ocaGameId,@PathVariable("playerId") int playerId){
		OcaTurn turn = this.ocaTurnService.findTurnById(ocaGameId);
		
		turn.throwDice();
		turn.nextTurn();
		
		this.ocaTurnService.save(turn);
		
		return "redirect:/ocaGames/"+ocaGameId;
		
		
	}
	
	

}