package org.springframework.samples.ocayparchis.pieces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.model.BaseEntity;
import org.springframework.samples.ocayparchis.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "ocaPiece")

public class OcaPiece extends BaseEntity{
	
	@DecimalMax(value="63", inclusive=true)
	@DecimalMin(value="1", inclusive=true)
    private Integer position;
	
	@ManyToOne(cascade = CascadeType.ALL)
    OcaBoard board;
	
	
	@OneToOne
	private Player player;
	
	private Integer penalization = 0;
	
	public OcaPiece() {
		this.position=1;
	}
	
	
	static final List<Integer> listPositionX = getListX();
	static final List<Integer> listPositionY = getListY();	
	
	
	public static Integer getPositionX(OcaPiece piece) {
		
		return listPositionX.get(piece.getPosition());
	}
	
	public static Integer getPositionY(OcaPiece piece) {
		
		return listPositionY.get(piece.getPosition());
	}
	
	
	
	public static List<Integer> getListX() {

		List<Integer> list = new ArrayList<Integer>();
        String fileName = "src/main/resources/static/resources/txt/getOcaPosicionX.txt";
        
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
        String fileName = "src/main/resources/static/resources/txt/getOcaPosicionY.txt";
        
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

	
}
