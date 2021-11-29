package org.springframework.samples.ocayparchis.model;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.ocayparchis.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ParchisTurn extends BaseEntity{
	
	private Integer turn;
    private Integer i;//puntero
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Player>players;
    @OneToOne
    private Player player;
    private Integer dice1;
    private Integer dice2;
    
    public  void TurnInit(){
        this.turn=0;
        this.i=0;
        this.player=null;
        this.players=List.of();
        this.dice1=null;
        this.dice2=null;
    }
    public void throwDice1() {
        Random r = new Random();
        this.dice1=r.nextInt(6)+1;// Entre 0 y 5, más 1

    }
    public void throwDice2() {
        Random r = new Random();
        this.dice2=r.nextInt(6)+1;// Entre 0 y 5, más 1
    }
    
    
    public void nextTurn() {
        this.turn++;
        if(this.i+1==this.players.size()) {
        this.i=0;
        }
        else {
            this.i++;
        }
        this.player=players.get(i); 

    }

}
