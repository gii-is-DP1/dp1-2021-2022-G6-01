package org.springframework.samples.ocayparchis.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.springframework.samples.ocayparchis.model.BaseEntity;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "parchisBoard")
public class ParchisBoard extends BaseEntity{
	
	String background;
    @Positive
    int width;
    @Positive
    int height;

    public ParchisBoard(){
        this.background="/resources/images/tablero_parchis.png";
        this.width=800;
        this.height=800;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "board",fetch = FetchType.EAGER)
    List<ParchisPiece> pieces; 

}
