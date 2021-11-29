package org.springframework.samples.ocayparchis.parchisgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parchisGames")
public class ParchisGameController {
	
	@Autowired
	private ParchisGameService parchisGameService;
	
}
