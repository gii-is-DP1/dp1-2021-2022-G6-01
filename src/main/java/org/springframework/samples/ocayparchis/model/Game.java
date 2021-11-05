package org.springframework.samples.ocayparchis.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game extends NamedEntity {

	private Integer reward;
	
	private Boolean inGame;
	
	private Boolean teamMatch;
	
	
	
	
}