<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the piece to show" %>
 <%@ attribute name="ocaPiece" required="true" rtexprvalue="true" type="org.springframework.samples.ocayparchis.pieces.OcaPiece"
 description="Piece to be rendered" %>
 <script>
 var canvas = document.getElementById("canvas");
 var ctx = canvas.getContext("2d");
 var image = document.getElementById('redPiece');
 ctx.drawImage(image,${ocaPiece.getPositionXInPixels(size)},${ocaPiece.getPositionYInPixels(size)},${size},${size}+20);
 </script>