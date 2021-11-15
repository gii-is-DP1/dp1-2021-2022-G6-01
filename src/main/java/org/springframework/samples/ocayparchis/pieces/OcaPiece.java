package org.springframework.samples.ocayparchis.pieces;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Range(min=1,max=63)
    int position;
	
	@ManyToOne
    OcaBoard board;

}
