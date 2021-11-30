package org.springframework.samples.ocayparchis.ocagame;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ocaBusinessRules.OcaRules;
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
	
	private OcaTurnService ocaTurnService;
	
	private OcaPieceService ocaPieceService;

	@Autowired	
	public OcaTurnController(OcaTurnService ocaTurnService, OcaPieceService ocaPieceService) {
		super();
		this.ocaTurnService = ocaTurnService;
		this.ocaPieceService = ocaPieceService;
	}




	@GetMapping(path="/{ocaGameId}/{playerId}")
	public String playTurn(@PathVariable("ocaGameId") int ocaGameId,@PathVariable("playerId") Integer playerId){
		OcaPiece piece = this.ocaPieceService.findByPlayerId(playerId);
		OcaTurn turn = this.ocaTurnService.findTurnById(ocaGameId);
		if(piece.getPenalization()<=0) {
			
			turn.throwDice();
			Integer dice = turn.getDice();
			
			if ((piece.getPosition()+dice)==63||piece.getPosition()==63) {
				return "redirect:/ocaGames/winner/"+ocaGameId+"/"+playerId;
			}else {
				if (piece.getPosition()+dice>63) {
					piece.setPosition(63-(piece.getPosition()+dice-63));
				}else {
					piece.setPosition(piece.getPosition()+dice);
				}
			}
			Integer position=piece.getPosition();
			

			
			Integer pen=OcaRules.getpen(position);
			if(pen>0){
		    		piece.setPenalization(pen);
		    		
		    	}
		    
		    
		    
			if(OcaRules.isLabyrinth(position)) {
				piece.setPosition(OcaRules.labyrinth(position));
			}
			if(OcaRules.isDeath(position)) {
				piece.setPosition(OcaRules.death(position));
			}
			if(OcaRules.repeatTurn(position)) {
				if(OcaRules.isOca(position)) {
					piece.setPosition(OcaRules.oca(position));
					
					
				}
				else if(OcaRules.isDices(position)) {
					piece.setPosition(OcaRules.dices(position));
					
					
				}
				else if(OcaRules.isBridge(position)) {
					piece.setPosition(OcaRules.bridge(position));
					
					
				}
				

				this.ocaPieceService.save(piece);
				this.ocaTurnService.save(turn);
				return "redirect:/ocaGames/"+ocaGameId;
				
				
				
			
			}
			
			turn.nextTurn();
			
				this.ocaPieceService.save(piece);
				this.ocaTurnService.save(turn);
			
			
			return "redirect:/ocaGames/"+ocaGameId;
			
			
		}
		else {
			piece.setPenalization(piece.getPenalization()-1);
			
			this.ocaPieceService.save(piece);
			turn.nextTurn();
			
			
			this.ocaTurnService.save(turn);
			
			return "redirect:/ocaGames/"+ocaGameId;
			
		}
		

		
	}
	
	

}