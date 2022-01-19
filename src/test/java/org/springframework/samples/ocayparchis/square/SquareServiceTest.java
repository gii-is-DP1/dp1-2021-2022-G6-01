package org.springframework.samples.ocayparchis.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.ParchisBoard;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.samples.ocayparchis.pieces.ParchisPieceService;
import org.springframework.samples.ocayparchis.squares.Color;
import org.springframework.samples.ocayparchis.squares.Square;
import org.springframework.samples.ocayparchis.squares.SquareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class SquareServiceTest {

	@Autowired
		protected SquareService squareService;
	    
	private Square square_test;
	private OcaBoard board;
	private List<ParchisPiece> pieces;
	
	@BeforeEach
	public void setup() {
		board = new OcaBoard();
		pieces = new ArrayList<ParchisPiece>();
		square_test = new Square(pieces,20,null,board);
		
	}
	
	@Test
	void shouldFindByPosition() {
		Square square = this.squareService.findByPosition(0);
		assertThat(square.getPosition().equals(0));
	}

	
	@Test
	void shouldInsertSquare() {
		Square square = new Square();
		OcaBoard board = new OcaBoard();
		ParchisPiece piece = new ParchisPiece();
		
		List<ParchisPiece> piece_list = List.of(piece);
		
		square.setColor(Color.BLUE);
		square.setId(108);
		square.setPosition(108);
		square.setBoard(board);
		square.setPieces(piece_list);
		
		this.squareService.save(square);
		
		assertThat(square.getId().longValue()).isNotEqualTo(0);
	}
	
	
	@Test
	@Transactional
	public void shouldDeleteSquare() {
		Square square = new Square();
		
		square.setColor(Color.BLUE);
		square.setId(108);
		square.setPosition(108);
		
		this.squareService.save(square);
		this.squareService.delete(square);
		assertThat(!square.getId().equals(108));
	}

	
	@Test
	public void getPiecesGetColorGetBoardTest() {
		assertEquals(pieces,square_test.getPieces());
		assertEquals(board,square_test.getBoard());
		assertNull(square_test.getColor());
	}
	
	@Test
	public void colocarFichaSacarFichaTest() {
		assertEquals(0, square_test.getPieces().size());
		ParchisPiece piece = new ParchisPiece();
		square_test.colocarFicha(piece);
		assertEquals(1, square_test.getPieces().size());
		assertEquals(piece, square_test.getPieces().get(0));
		square_test.quitarFicha(piece);
		assertEquals(0, square_test.getPieces().size());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {12,17,29,34,46,51,63,68})
	public void isSafeGoodPosition(int position) {
		square_test.setPosition(position);
		assertTrue(square_test.isSafe());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {11,15,24})
	public void isSafeBadPosition(int position) {
		square_test.setPosition(position);
		assertFalse(square_test.isSafe());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {5,22,39,56})
	public void isStartGoodPosition(int position) {
		square_test.setPosition(position);
		assertTrue(square_test.isStart());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {11,15,24})
	public void isStartBadPosition(int position) {
		square_test.setPosition(position);
		assertFalse(square_test.isStart());
	}
	
	@Test
	public void isBloqueoGoodPiecesNumber() {
		ParchisPiece piece1 = new ParchisPiece();
		square_test.colocarFicha(piece1);
		ParchisPiece piece2 = new ParchisPiece();
		square_test.colocarFicha(piece2);
		assertTrue(square_test.isBloqueo());
	}
	
	@Test
	public void isBloqueopBadPiecesNumber() {
		assertFalse(square_test.isBloqueo());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {101,102,103,104})
	public void isHouseGoodPosition(int position) {
		square_test.setPosition(position);
		assertTrue(square_test.isHouse());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {11,15,24})
	public void isHouseBadPosition(int position) {
		square_test.setPosition(position);
		assertFalse(square_test.isHouse());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {69,72,78,81,85,90,94,99,100})
	public void isStairGoodPosition(int position) {
		square_test.setPosition(position);
		assertTrue(square_test.isStair());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {11,15,24})
	public void isStairBadPosition(int position) {
		square_test.setPosition(position);
		assertFalse(square_test.isStair());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {68,17,34,51})
	public void isBifurcacionGoodPosition(int position) {
		square_test.setPosition(position);
		assertTrue(square_test.isBifurcacion());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {11,15,24})
	public void isBifurcacionBadPosition(int position) {
		square_test.setPosition(position);
		assertFalse(square_test.isBifurcacion());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {76,84,92,100})
	public void isFinalSquareGoodPosition(int position) {
		square_test.setPosition(position);
		assertTrue(square_test.isFinalSquare());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {11,15,24})
	public void isFinalSquareBadPosition(int position) {
		square_test.setPosition(position);
		assertFalse(square_test.isFinalSquare());
	}
}
