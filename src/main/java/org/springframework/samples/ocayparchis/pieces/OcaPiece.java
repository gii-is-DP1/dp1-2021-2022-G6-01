package org.springframework.samples.ocayparchis.pieces;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.model.BaseEntity;

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
	
	public OcaPiece() {
		this.position=1;
	}
}
