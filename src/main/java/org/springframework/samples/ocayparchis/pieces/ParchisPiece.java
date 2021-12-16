package org.springframework.samples.ocayparchis.pieces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	private Boolean canMove=true;
	
	static final List<Integer> listPositionX = getListX();
	static final List<Integer> listPositionY = getListY();
	
	public static Integer getPositionX(ParchisPiece piece){
		
		List<Integer> multiPosition = List.of(101,102,103,104);
		
		Integer pixels = listPositionX.get(piece.posicionActual());
		
		//Casillas especiales en las que hay más de 2 fichas
		if(multiPosition.contains(piece.posicionActual())) {
			
			if(piece.getName().equals("Ficha 1")) {
				pixels = pixels - 46;
			} else if(piece.getName().equals("Ficha 2")) {
				pixels = pixels - 46;
			} else if(piece.getName().equals("Ficha 3")) {
				pixels = pixels + 46;
			} else {
				pixels = pixels + 46;
			}
		
		//Si hay un bloqueo, mostrar las dos fichas, se comprueba la si la casilla está en horizontal
		} else if (piece.getSquare().isBloqueo() && ((1 <= piece.posicionActual() && piece.posicionActual() <= 8) || (26 <= piece.posicionActual() && piece.posicionActual() <= 42) || (60 <= piece.posicionActual() && piece.posicionActual() <= 68))) {
	
			//Para darle mover una de las fichas del bloqueo
			if(piece.getSquare().getPieces().get(0).getId() == piece.getId()) {
				pixels = pixels + 23;
			} else {
				pixels = pixels - 23;
			}
		}
		
		
		return pixels;
	}

	public static Integer getPositionY(ParchisPiece piece){
		
		List<Integer> multiPosition = List.of(101,102,103,104);
		
		Integer pixels = listPositionY.get(piece.posicionActual());
		
		//Casillas especiales en las que hay más de 2 fichas
		if(multiPosition.contains(piece.posicionActual())) {
			
			if(piece.getName().equals("Ficha 1")) {
				pixels = pixels - 46;
			} else if(piece.getName().equals("Ficha 2")) {
				pixels = pixels + 46;
			} else if(piece.getName().equals("Ficha 3")) {
				pixels = pixels - 46;
			} else {
				pixels = pixels + 46;
			}
		
		//Si hay un bloqueo, mostrar las dos fichas, se comprueba la si la casilla está en vertical
		} else if (piece.getSquare().isBloqueo() && ((9 <= piece.posicionActual() && piece.posicionActual() <= 25) || ((43 <= piece.posicionActual() && piece.posicionActual() <= 59)))) {
	
			//Para darle mover una de las fichas del bloqueo
			if(piece.getSquare().getPieces().get(0).getId() == piece.getId()) {
				pixels = pixels + 23;
			} else {
				pixels = pixels - 23;
			}
		}
		
		
		return pixels;
	}

	
	public static List<Integer> getListX() {

		List<Integer> list = new ArrayList<Integer>();
        String fileName = "src/main/resources/static/resources/txt/getParchisPositionX.txt";
        
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Integer position = Integer.valueOf(line.trim());
                list.add(position);
            }
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		return list;
	}
	
	
	public static List<Integer> getListY(){
		
		List<Integer> list = new ArrayList<Integer>();
        String fileName = "src/main/resources/static/resources/txt/getParchisPositionY.txt";
        
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                Integer position = Integer.valueOf(line.trim());
                list.add(position);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		
		return list;
	}
	

	public Integer posicionActual() {
        return square.getPosition();
    }
	
    public Integer casillaCasa() {
        Integer s;
        if(this.color==Color.BLUE) {
            s= 101;
        }else if(this.color==Color.GREEN) {
            s= 102;
        }else if(this.color==Color.YELLOW) {
            s= 103;
        }else {
            s= 104;
        }
        return s;
    }

    public Integer casillaSalida() {
        Integer s;
        if(this.color==Color.BLUE) {
            s= 22;
        }else if(this.color==Color.GREEN) {
            s= 56;
        }else if(this.color==Color.YELLOW) {
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
