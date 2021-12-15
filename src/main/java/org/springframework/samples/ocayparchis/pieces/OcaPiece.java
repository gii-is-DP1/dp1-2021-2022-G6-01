package org.springframework.samples.ocayparchis.pieces;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.ocayparchis.board.OcaBoard;
import org.springframework.samples.ocayparchis.model.BaseEntity;
import org.springframework.samples.ocayparchis.player.Player;

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
	
	
	@OneToOne
	private Player player;
	
	private Integer penalization = 0;
	
	public OcaPiece() {
		this.position=1;
	}
	
	
public Integer getPositionXInPixels(Integer size) {
		
		if(this.position == 1) {
			return 105;
		} else if(this.position == 2){
			return 263;
		} else if(this.position == 3){
			return 359;
		} else if(this.position == 4){
			return 456;
		} else if(this.position == 5){
			return 560;
		} else if(this.position == 6){
			return 660;
		} else if(this.position == 7){
			return 722;// 
		} else if(this.position == 8){
			return 801;
		} else if(this.position == 9){
			return 867;
		} else if(this.position == 10){
			return 919;
		} else if(this.position == 11){
			return 933;
		} else if(this.position == 12){
			return 926;
		} else if(this.position == 13){
			return 939;
		} else if(this.position == 14){
			return 939;
		} else if(this.position == 15){
			return 941;
		} else if(this.position == 16){
			return 909;
		} else if(this.position == 17){
			return 859;
		} else if(this.position == 18){
			return 790;
		} else if(this.position == 19){
			return 708;
		} else if(this.position == 20){
			return 628;
		} else if(this.position == 21){
			return 545;
		} else if(this.position == 22){
			return 445;
		} else if(this.position == 23){
			return 342;
		} else if(this.position == 24){
			return 266;
		} else if(this.position == 25){
			return 189;
		} else if(this.position == 26){
			return 125;
		} else if(this.position == 27){
			return 71;
		} else if(this.position == 28){
			return 39;
		} else if(this.position == 29){
			return 20;
		} else if(this.position == 30){
			return 18;
		} else if(this.position == 31){
			return 26;
		} else if(this.position == 32){
			return 32;
		} else if(this.position == 33){
			return 76;
		} else if(this.position == 34){
			return 128;
		} else if(this.position == 35){
			return 212;
		} else if(this.position == 36){
			return 293;
		} else if(this.position == 37){
			return 388;
		} else if(this.position == 38){
			return 481;
		} else if(this.position == 39){
			return 586;
		} else if(this.position == 40){
			return 664;
		} else if(this.position == 41){
			return 730;
		} else if(this.position == 42){
			return 789;
		} else if(this.position == 43){
			return 798;
		} else if(this.position == 44){
			return 797;
		} else if(this.position == 45){
			return 795;
		} else if(this.position == 46){
			return 808;
		} else if(this.position == 47){
			return 792;
		} else if(this.position == 48){
			return 730;
		} else if(this.position == 49){
			return 660;
		} else if(this.position == 50){
			return 575;
		} else if(this.position == 51){
			return 476;
		} else if(this.position == 52){
			return 384;
		} else if(this.position == 53){
			return 293;
		} else if(this.position == 54){
			return 219;
		} else if(this.position == 55){
			return 176;
		} else if(this.position == 56){
			return 149;
		} else if(this.position == 57){
			return 162;
		} else if(this.position == 58){
			return 154;
		} else if(this.position == 59){
			return 167;
		} else if(this.position == 60){
			return 220;
		} else if(this.position == 61){
			return 300;
		} else if(this.position == 62){
			return 390;
		} else if(this.position == 63){
			return 606;
		}
		
		
		
		
		
		
		
		return  500;
	}
	
	public Integer getPositionYInPixels(Integer size) {
		
		if(this.position == 1) {
			return 900;
		} else if(this.position == 2){
			return 900;
		} else if(this.position == 3){
			return 900;
		} else if(this.position == 4){
			return 900;
		} else if(this.position == 5){
			return 900;
		} else if(this.position == 6){
			return 900;
		} else if(this.position == 7){
			return 900;//
		} else if(this.position == 8){
			return 822;
		} else if(this.position == 9){
			return 786;
		} else if(this.position == 10){
			return 722;
		} else if(this.position == 11){
			return 639;
		} else if(this.position == 12){
			return 539;
		} else if(this.position == 13){
			return 454;
		} else if(this.position == 14){
			return 362;
		} else if(this.position == 15){
			return 265;
		} else if(this.position == 16){
			return 187;
		} else if(this.position == 17){
			return 112;
		} else if(this.position == 18){
			return 55;
		} else if(this.position == 19){
			return 17;
		} else if(this.position == 20){
			return 6;
		} else if(this.position == 21){
			return 6;
		} else if(this.position == 22){
			return 6;
		} else if(this.position == 23){
			return 6;
		} else if(this.position == 24){
			return 24;
		} else if(this.position == 25){
			return 37;
		} else if(this.position == 26){
			return 90;
		} else if(this.position == 27){
			return 152;
		} else if(this.position == 28){
			return 238;
		} else if(this.position == 29){
			return 322;
		} else if(this.position == 30){
			return 414;
		} else if(this.position == 31){
			return 512;
		} else if(this.position == 32){
			return 592;
		} else if(this.position == 33){
			return 666;
		} else if(this.position == 34){
			return 728;
		} else if(this.position == 35){
			return 757;
		} else if(this.position == 36){
			return 780;
		} else if(this.position == 37){
			return 780;
		} else if(this.position == 38){
			return 780;
		} else if(this.position == 39){
			return 780;
		} else if(this.position == 40){
			return 780;
		} else if(this.position == 41){
			return 741;
		} else if(this.position == 42){
			return 688;
		} else if(this.position == 43){
			return 605;
		} else if(this.position == 44){
			return 512;
		} else if(this.position == 45){
			return 413;
		} else if(this.position == 46){
			return 321;
		} else if(this.position == 47){
			return 140;
		} else if(this.position == 48){
			return 178;
		} else if(this.position == 49){
			return 140;
		} else if(this.position == 50){
			return 145;
		} else if(this.position == 51){
			return 145;
		} else if(this.position == 52){
			return 145;
		} else if(this.position == 53){
			return 145;
		} else if(this.position == 54){
			return 178;
		} else if(this.position == 55){
			return 238;
		} else if(this.position == 56){
			return 315;
		} else if(this.position == 57){
			return 404;
		} else if(this.position == 58){
			return 496;
		} else if(this.position == 59){
			return 574;
		} else if(this.position == 60){
			return 626;
		} else if(this.position == 61){
			return 647;
		} else if(this.position == 62){
			return 645;
		} else if(this.position == 63){
			return 579;
		}
		
		
		
		
		
		
		return  500; 
	}
	
}
