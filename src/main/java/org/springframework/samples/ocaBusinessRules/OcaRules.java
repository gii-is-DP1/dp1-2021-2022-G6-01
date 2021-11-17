package org.springframework.samples.ocaBusinessRules;

import java.util.List;

public class OcaRules {
	  private static List<Integer>ocas = List.of(5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59,63);
	  public static Boolean isOca(Integer position) {
	       return ocas.contains(position);

	    }
	  public static Boolean isLabyrinth(Integer position) {
	       return position==42;

	    }
	  public static boolean isDices(Integer newPosition) {
		  
			return newPosition==26||newPosition==53;
		}
	  public static boolean isBridge(Integer newPosition) {
			
			return newPosition==12||newPosition==6;
		}
	  public static Boolean repeatTurn(Integer position) {
	       return isOca(position)||isDices(position)||isBridge(position);

	    }
	  public static boolean isDeath(Integer newPosition) {
		  
			return newPosition==58;
		}

    public static Integer oca(Integer position) {
    	
        Integer newPosition=position;
        if(newPosition!=63) {
            newPosition=ocas.get(ocas.indexOf(position)+1);

        }



        return newPosition;

    }
 public static Integer labyrinth(Integer position) {
    	      
        return 30;

    }
 public static Integer dices(Integer position) {
 	
     Integer newPosition=position;
     if(newPosition==26) {
         newPosition=53;

     }
     if(newPosition==53) {
         newPosition=26;

     }

     return newPosition;

 }
 public static Integer bridge(Integer position) {
	 	
     Integer newPosition=position;
     if(newPosition==6) {
         newPosition=12;

     }
     if(newPosition==12) {
         newPosition=6;

     }



     return newPosition;




 }
 public static Integer death(Integer position) {
	 	
	return 1;


	
	
}
 public static Integer getpen(Integer position) {
	
Integer penal =0;
	 if(position==19) {
		 penal= 1;
	 }
	 if(position==52) {
		 penal= 3;
	}
	 if(position==31) {
		 penal= 2;
	 }
	return penal;

}
 
 
 

}
