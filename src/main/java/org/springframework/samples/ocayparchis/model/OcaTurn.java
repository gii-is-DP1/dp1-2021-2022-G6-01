package org.springframework.samples.ocayparchis.model;

import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.ocayparchis.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OcaTurn extends BaseEntity{
	private Integer turn;
    private Integer i;//puntero
    
    @OneToMany
    private List<Player>players;
    @OneToOne
    private Player player;
    private Integer dice;

    public OcaTurn(){
        this.turn=0;
        this.i=0;

    }
    public void throwDice() {
        Random r = new Random();
        this.dice=r.nextInt(6)+1;// Entre 0 y 5, más 1

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
