package org.springframework.samples.ocayparchis.squares;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.model.BaseEntity;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.samples.ocayparchis.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "Square")

public class Square extends BaseEntity{
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@Size(min=0, max=2)
    List<ParchisPiece> pieces;

    private Integer position;
	
   
    
    Color color;
    
	@ManyToOne(cascade = CascadeType.ALL)
    OcaBoard board;
	
	public Square(@Size(min = 0, max = 2) List<ParchisPiece> pieces, Integer position, Color color, OcaBoard board) {
		super();
		this.pieces = pieces;
		this.position = position;
		this.color = color;
		this.board = board;
	}
	
	public Square() {
		super();
	};
	
	 public  Boolean isSafe() {
		 List<Integer>safe=List.of(12,17,29,34,46,51,63,68);
		return safe.contains(this.position);
		 
	 }
	 
	 public  Boolean isStart() {
		 List<Integer>start=List.of(-3,-2,-1,0);
		return start.contains(this.position);
		 
	 };
	 public  Boolean isStair() {
		 List<Integer>stair=List.of();
		 if(this.color.equals(Color.YELLOW)) {
			 stair=IntStream.rangeClosed(69, 76).boxed().collect(Collectors.toList());
		 }
		 else if(this.color.equals(Color.BLUE)){
			 stair=IntStream.rangeClosed(77, 84).boxed().collect(Collectors.toList());
		 }
		 else if(this.color.equals(Color.RED)){
			 stair=IntStream.rangeClosed(85, 92).boxed().collect(Collectors.toList());
		 }
		 else if(this.color.equals(Color.GREEN)){
			 stair=IntStream.rangeClosed(93, 100).boxed().collect(Collectors.toList());
		 }
		return stair.contains(this.position);
		 
	 }

	
	 


	

	
	
}
