package org.zerock.ws;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {
	
	/* 접속자 저장 */
	private Map<String, WebSocketSession> users=new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			log(session.getId() + "연결 됨");
			//맵에 id-session 저장
			users.put(session.getId(), session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log(session.getId()+"로 부터 메세지 수신: " +message.getPayload());
		for(WebSocketSession s:users.values()) {
			 s.sendMessage(message);
			 log(s.getId() +"에 메시지 발송: " + message.getPayload());
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId()+"익셉션 발생: " + exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log(session.getId()+"연결 종료됨");
		users.remove(session.getId());
	}

	private void log(String logmsg) {
		   System.out.println(new Date() + " : " + logmsg);
		}
	
}
