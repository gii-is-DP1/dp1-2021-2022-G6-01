package org.springframework.samples.ocayparchis.pieces;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.board.ParchisBoard;
import org.springframework.samples.ocayparchis.model.BaseEntity;
import org.springframework.samples.ocayparchis.player.Player;
import org.springframework.samples.ocayparchis.squares.Color;
import org.springframework.samples.ocayparchis.squares.Square;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "parchisPiece")

public class ParchisPiece extends BaseEntity{
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	Square square;

//	@NotNull
	private Color color;
	
	@ManyToOne(cascade = CascadeType.ALL)
    ParchisBoard board;
	
	private Boolean inStart =true;
	
	@ManyToOne
	private Player player;
	
	private String name;
	
	
	
	public Integer casillaActual() {
		return square.getPosition();
	}
	
	public Integer casillaCasa() {
		Integer s;
		if(color==Color.BLUE) {
			s= 101;
		}else if(color==Color.GREEN) {
			s= 102;
		}else if(color==Color.YELLOW) {
			s= 103;
		}else {
			s= 104;
		}
		return s;
	}
	
	public Integer casillaSalida() {
		Integer s;
		if(color==Color.BLUE) {
			s= 22;
		}else if(color==Color.GREEN) {
			s= 56;
		}else if(color==Color.YELLOW) {
			s= 5;
		}else {
			s= 39;
		}
		return s;
	}
	
	
	
	@Override
	public String toString() {
		return name+":"+square.getPosition()+" "+ id;
	}
	
}
