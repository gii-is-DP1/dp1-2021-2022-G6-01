package org.springframework.samples.ocaBusinessRules;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@SpringBootConfiguration

public class OcaRulesTest {
	
	@Test
	public void ocaSuccesfull() {
		Integer position = 5;
		if(OcaRules.isOca(position)) {
			position = OcaRules.oca(position);
		}
		assertEquals(9, position);
	}
	
	@Test
	public void ocaUnsuccesfull() {
		Integer position = 4;
		if(OcaRules.isOca(position)) {
			position = OcaRules.oca(position);
		}
		assertEquals(4, position);
	}
	@Test
	public void ocaSuccesfull63() {
		Integer position = 63;
		if(OcaRules.isOca(position)) {
			position = OcaRules.oca(position);
		}
		assertEquals(63, position);
	}
	
	@Test
	public void deathSuccesfull() {
		Integer position = 58;
		if(OcaRules.isDeath(position)) {
			position= OcaRules.death(position);
		}
		assertEquals(1, position);
	}
	
	@Test
	public void deathUnsuccesfull() {
		Integer position = 2;
		if(OcaRules.isDeath(position)) {
			position= OcaRules.death(position);
		}
		assertEquals(2, position);
	}
	
	@Test
	public void pen19Succesfull() {
		Integer position = 19;
		Integer pen = OcaRules.getpen(position);
		assertEquals(1, pen);
	}
	@Test
	public void pen31Succesfull() {
		Integer position = 31;
		Integer pen = OcaRules.getpen(position);
		assertEquals(2, pen);
	}
	@Test
	public void pen52Succesfull() {
		Integer position = 52;
		Integer pen = OcaRules.getpen(position);
		assertEquals(3, pen);
	}
	@Test
	public void penUnsuccesfull() {
		Integer position = 2;
		Integer pen = OcaRules.getpen(position);
		assertEquals(0, pen);
	}
	
	@Test 
    void Bridge6Test() {
		Integer position = 6;
		if(OcaRules.isBridge(position)) {
			position= OcaRules.bridge(position);
		}
		assertEquals(12, position);
    }
	@Test 
    void Bridge12Test() {
		Integer position = 12;
		if(OcaRules.isBridge(position)) {
			position= OcaRules.bridge(position);
		}
		assertEquals(6, position);
    }

    @Test 
    void NotBridgeTest() {
        Integer position = 2;
		position= OcaRules.bridge(position);
		assertEquals(2, position);
    }
    
    @Test 
    void Dices26Test() {
    	Integer position = 26;
		if(OcaRules.isDices(position)) {
			position= OcaRules.dices(position);
		}
		assertEquals(53, position);
    }
    
    @Test 
    void Dices53Test() {
    	Integer position = 53;
		if(OcaRules.isDices(position)) {
			position= OcaRules.dices(position);
		}
		assertEquals(26, position);
    }


    @Test 
    void NotDicesTest() {
    	Integer position = 2;
    	position= OcaRules.dices(position);
		assertEquals(2, position);
    }
    
    @Test 
    void labyrinthTest() {
    	Integer position = 42;
		if(OcaRules.isLabyrinth(position)) {
			position= OcaRules.labyrinth(position);
		}
		assertEquals(30, position);
    }


    @Test 
    void NotLabyrinthTest() {
    	Integer position = 2;
		if(OcaRules.isLabyrinth(position)) {
			position= OcaRules.labyrinth(position);
		}
		assertEquals(2, position);
    }
    
    @ParameterizedTest
    @ValueSource(ints= {5,53,6,9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59,63})
    public void repeatTurnSuccesfull(int argument) {
       assertTrue(OcaRules.repeatTurn(argument));
    }
    
    @ParameterizedTest
    @ValueSource(ints= {7,2,3,100,55})
    public void repeatTurnOcaUnsuccesfull(int arguments) {
       assertFalse(OcaRules.repeatTurn(arguments));
    }
    
    @Test
    public void createOcaRulesSuccesfull() {
    	Integer x =0;
   	  	List<Integer>ocas = List.of(5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59,63);
   	  	OcaRules ocaRules = new OcaRules();
   	  	assertEquals(x, ocaRules.x);
   	  	assertEquals(ocas, ocaRules.ocas);
    }
}
