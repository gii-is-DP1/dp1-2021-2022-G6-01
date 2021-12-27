<%@ attribute name="ocaBoard" required="false" rtexprvalue="true" type="org.springframework.samples.ocayparchis.board.OcaBoard"
 description="OcaBoard to be rendered" %>
<canvas id="canvas" width="${ocaBoard.width}" height="${ocaBoard.height}"></canvas>
<img id="source" src="/resources/images/tablero_oca.jpg" style="display:none">
<img id="redPiece" src="/resources/images/redPiece.png" style="display:none">
<img id="green-ParchisPiece" src="/resources/images/green-ParchisPiece.png" style="display:none">
<script>
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var image = document.getElementById('source');

ctx.drawImage(image, 0, 0, ${ocaBoard.width}, ${ocaBoard.height});
</script>