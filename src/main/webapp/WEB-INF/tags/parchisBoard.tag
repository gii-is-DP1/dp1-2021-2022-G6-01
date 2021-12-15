<%@ attribute name="parchisBoard" required="false" rtexprvalue="true" type="org.springframework.samples.ocayparchis.board.ParchisBoard"
 description="ParchisBoard to be rendered" %>
<canvas id="canvas" width="${parchisBoard.width}" height="${parchisBoard.height}"></canvas>
<img id="source" src="/resources/images/tablero_cadiz.jpeg" style="display:none">
<img id="redPiece" src="/resources/images/redPiece.png" style="display:none">
<script>
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var image = document.getElementById('source');

ctx.drawImage(image, 0, 0, ${parchisBoard.width}, ${parchisBoard.height});
</script>