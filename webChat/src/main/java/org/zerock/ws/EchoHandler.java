package org.zerock.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/* WebSocketHandler 인터페이스 구현 */
public class EchoHandler extends TextWebSocketHandler {

	/* 연결 시 호출 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.printf("%s 연결됨\n", session.getId());
	}

	/*  웹소켓 클라이언트가 텍스트 메세지를 전송할 때 호출되는 메소드.*/
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.printf("%s로 부터 [%s]받음\n",session.getId(), message.getPayload());
		//전송
		session.sendMessage(new TextMessage("echo: "+ message.getPayload()));
	}

	/* 연결 종료 시 호출 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.printf("%s 연결 끊김\n", session.getId());
	}
	
 
}
