<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the piece to show" %>
 <%@ attribute name="parchisPiece" required="true" rtexprvalue="true" type="org.springframework.samples.ocayparchis.pieces.ParchisPiece"
 description="Piece to be rendered" %>
 <script>
 var canvas = document.getElementById("canvas");
 var ctx = canvas.getContext("2d");
 var image = document.getElementById('${parchisPiece.getColorName(parchisPiece)}-ParchisPiece');
 
 var posX = ${parchisPiece.getPositionX(parchisPiece)};
 var posY = ${parchisPiece.getPositionY(parchisPiece)};
 
 ctx.drawImage(image, posX, posY, 45, 45);
 </script>