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
public class Dices extends NamedEntity {
	@DecimalMax(value="6", inclusive=true)
	@DecimalMin(value="1", inclusive=true)
	private Integer value1;
	@DecimalMax(value="6", inclusive=true)
	@DecimalMin(value="1", inclusive=true)
	private Integer value2;
	
	
}