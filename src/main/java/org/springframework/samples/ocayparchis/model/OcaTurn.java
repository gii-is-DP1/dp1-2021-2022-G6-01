package org.springframework.samples.ocayparchis.model;

import java.util.List;

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
    private Integer dice;

	
}
