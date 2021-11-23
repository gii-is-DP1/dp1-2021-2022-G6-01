package org.springframework.samples.ocayparchis.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.springframework.samples.ocayparchis.model.BaseEntity;
import org.springframework.samples.ocayparchis.pieces.OcaPiece;

import lombok.Getter;
import lombok.Setter;

 
@Entity
@Getter
@Setter

@Table(name = "ocaBoard") 
  
public class OcaBoard extends BaseEntity {
    String background;
    @Positive
    int width;
    @Positive
    int height;

    public OcaBoard(){
        this.background="/resources/images/tablero_oca.jpg";
        this.width=1022;
        this.height=1016; 
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "board",fetch = FetchType.EAGER)
    List<OcaPiece> pieces; 
}