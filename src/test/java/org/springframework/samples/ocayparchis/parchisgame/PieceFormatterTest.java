package org.springframework.samples.ocayparchis.parchisgame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.ocayparchis.pieces.ParchisPiece;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PieceFormatterTest {

    @Autowired
    PieceFormatter formatter;

    @Test
    public void test7(){
        testFormatterIsInjected();
        testFormatterObject2String();
        testFormatterString2Object();
        testFormatterString2ObjectNotFound();
    }


    
    public void testFormatterIsInjected(){
        assertNotNull(formatter);
    }

    
    public void testFormatterObject2String(){
        ParchisPiece piece=new ParchisPiece();
        piece.setName("Prueba");
        String result=formatter.print(piece, null);
        assertEquals("Prueba",result);
    }

    
    public void testFormatterString2Object(){
        String name="Ficha 1";
        ParchisPiece piece;
        try {
            piece = formatter.parse(name, null);
            assertNotNull(piece);
            assertEquals(piece.getName(),name);
        } catch (ParseException e) {           
            e.printStackTrace();
            fail(e.getMessage());
        }
        
    }

    
    public void testFormatterString2ObjectNotFound(){
        String name="Invalid Piece name";
        assertThrows(ParseException.class, () -> formatter.parse(name, null));          
    }
}
