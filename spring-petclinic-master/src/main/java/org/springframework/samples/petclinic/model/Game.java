package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game extends NamedEntity {

	private Integer review;
	
	private boolean inGame = Boolean.FALSE;
	
	private boolean teamMatch = Boolean.FALSE;
	
	
	
	
}