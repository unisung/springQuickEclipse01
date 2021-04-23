<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>채팅</title>
<!-- <script src="/resouces/vendor/js/jquery.js"></script> -->
<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
<script>
	var wsocket;
	
	function connect(){
		wsocket=new WebSocket("ws://localhost:8282/chat-ws");
		wsocket.onmessage=onMessage;
		wsocket.onclose=onClose;
		wsocket.onopen=onOpen;
	}
	
	function onOpen(e){
		 appendMessage("연결되었습니다.");
	}
function onMessage(evt){
	var data =evt.data;
	if(data.substring(0,4)=="msg:"){
		appendMessage(data.substring(4));
	}
}
function onClose(e){
	 appendMessage("연결을 끊었습니다.");
}

function send(){
	var nickname=$("#nickname").val();
	var msg = $("#message").val();
	wsocket.send("msg:"+nickname+":"+msg);
	$("#message").val("");
}

function appendMessage(msg){
	$("#chatMessageArea").append(msg+"<br>");
	var chatAreaHeight = $("#chatArea").height();
	var maxScroll =$("#chatMessageArea").height() - chatAreaHeight;
	$("#chatArea").scrollTop(maxScroll);
}

function disconnect(){wsocket.close();}

$(document).ready(function(){
	$("#message").keypress(function(e){
		var keycode = (e.keyCode ? e.keyCode:e.which);
		if(keycode=='13') send();
		e.stopPropagation();//이벤트 버블링 금지.
	});
	
	$('#sendBtn').click(function(){send();});
	$('#enterBtn').click(function(){connect();});
	$('#exitBtn').click(function(){disconnect();});
	
	
});
</script>
<style>
#chatArea{
	width:200px; height:100px; overflow-y:auto; border: 1px solid black; 
}
</style>
</head>
<body>
  이름:<input id="nickname">
  <input type="button" id="enterBtn" value="입장">
  <input type="button" id="exitBtn" value="나가기">
  
  <h1>대화 영역</h1>
  <div id='chatArea'><div id='chatMessageArea'></div></div>
  <br>
  <input  id='message'>
  <input type='button' id='sendBtn' value='전송'>
</body>
</html>