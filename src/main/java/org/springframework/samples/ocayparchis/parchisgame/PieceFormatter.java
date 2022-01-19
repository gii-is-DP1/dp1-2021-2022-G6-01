package org.springframework.samples.ocayparchis.parchisgame;


import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.stereotype.Component;
import java.text.ParseException;

@Component
public class PieceFormatter implements Formatter<ParchisPiece> {

	private final ParchisPieceService parchisPieceService;

	@Autowired
	public PieceFormatter(ParchisPieceService parchisPieceService) {
		this.parchisPieceService = parchisPieceService;
	} 

	@Override
	public String print(ParchisPiece piece, Locale locale) {
		return piece.getName();
	}

	@Override
	public ParchisPiece parse(String text, Locale locale) throws ParseException {
		Collection<ParchisPiece> pieces = (Collection<ParchisPiece>) this.parchisPieceService.findAll();
		for (ParchisPiece piece : pieces) {
			if (piece.toString().equals(text)) {
				return piece;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}
}
