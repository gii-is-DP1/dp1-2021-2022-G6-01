package org.springframework.samples.ocayparchis.model;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game extends NamedEntity {
	@NotNull
	private Integer reward;
	
	private Boolean inGame;
	@NotNull
	@DecimalMax(value="4", inclusive=true)
	@DecimalMin(value="2", inclusive=true)
	private Integer players;
	@NotNull
	private String gameMode;

	
	
	
}