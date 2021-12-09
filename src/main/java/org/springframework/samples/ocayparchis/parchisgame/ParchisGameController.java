package org.springframework.samples.ocayparchis.parchisgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.samples.ocayparchis.squares.Color;
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
@RequestMapping("/parchisGames")
public class ParchisGameController {
	
	private static final String VIEWS_GAME_CREATE_OR_UPDATE_FORM = "parchisGames/editGame";
	
	private ParchisGameService parchisGameService;
	
	private SquareService squareService;
	
	private ParchisPieceService parchisPieceService;
	
	private ParchisTurnService parchisTurnService;
	
	private PlayerService playerService;

	private ParchisBoardService parchisBoardService;
	
	@Autowired
	public ParchisGameController(ParchisGameService parchisGameService, ParchisPieceService parchisPieceService,
			ParchisTurnService parchisTurnService, PlayerService playerService,ParchisBoardService parchisBoardService,SquareService squareService) {
		super();
		this.parchisGameService = parchisGameService;
		this.parchisPieceService = parchisPieceService;
		this.parchisTurnService = parchisTurnService;
		this.playerService = playerService;
		this.parchisBoardService = parchisBoardService;
		this.squareService=squareService;
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
			turn.TurnInit();
			this.squareService.generateSquares();
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
	public ModelAndView showGame(@PathVariable("parchisGameId") int parchisGameId, HttpServletResponse response,@AuthenticationPrincipal Authentication user) {

		response.addHeader("Refresh", "2");
		ModelAndView mav = new ModelAndView("parchisGames/parchisGameDetails");
		String username = user.getName();
		Player currentPlayer = playerService.findPlayerByUsername(username).iterator().next();
		ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		List<Player> players = turn.getPlayers();

		if(!(players.contains(currentPlayer))) {
			
			createAndAsignPieces(players, currentPlayer);
			players.add(currentPlayer);
			if(currentPlayer==turn.getPlayers().get(0)) {
				turn.setPlayer(currentPlayer);
				
				
			}
			
			turn.setPlayers(players);
			this.parchisTurnService.save(turn);
		}
		mav.addObject(currentPlayer);
		mav.addObject(turn);
		List<ParchisPiece> pieces = (List<ParchisPiece>) this.parchisPieceService.findByPlayerId(currentPlayer.getId()); 
		Integer i=1;
		for(ParchisPiece p : pieces) {
			mav.addObject("piece"+i,p);
			i++;
		}
		mav.addObject(this.parchisGameService.findGameById(parchisGameId));
		
		//		mav.addObject(this.parchisBoardService.findById(parchisGameId));
		return mav;
	}
	
	
	public Color nextFreeColor(List<Player>players) {
		List<Color> colores= Arrays.asList(Color.values());
		List<Color>	usedColors=new ArrayList<Color>();
		Color freeColor = Color.BLUE;
		for(Player p:players) {
			Color c=p.getPieces().get(1).getColor();
			usedColors.add(c);
		}
		for(Color c:colores) {
			if(!usedColors.contains(c)) {
				freeColor=c;
				break;
			}
		}

		return freeColor;

	}

	public void createAndAsignPieces(List<Player> players, Player player) {
		Color c = nextFreeColor(players);
		Square s = new Square();
		if(c==Color.BLUE) {
			s= this.squareService.findByPosition(101);
		}else if(c==Color.GREEN) {
			s= this.squareService.findByPosition(102);
		}else if(c==Color.YELLOW) {
			s= this.squareService.findByPosition(103);
		}else {
			s= this.squareService.findByPosition(104);
		}
		
		List<ParchisPiece> pieces4 = s.getPieces();
		for(int i=1;i<5;i++) {
			ParchisPiece p = new ParchisPiece();
			p.setPlayer(player);
			p.setName("Ficha "+i);
			p.setColor(c);
			p.setSquare(s);
			pieces4.add(p);
			this.parchisPieceService.save(p);
			player.addPiece(p);;
			this.playerService.savePlayer(player);
		}	
		s.setPieces(pieces4);
		this.squareService.save(s);
	}
}
