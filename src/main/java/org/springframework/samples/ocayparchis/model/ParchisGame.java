package org.springframework.samples.ocayparchis.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import javax.validation.constraints.NotNull;

import org.springframework.samples.ocayparchis.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ParchisGame extends NamedEntity {
	@NotNull
	private Integer reward;
	
	private Boolean inGame;
	
	@NotNull
	@DecimalMax(value="4", inclusive=true)
	@DecimalMin(value="2", inclusive=true)
	private Integer players;

	
	
	
}