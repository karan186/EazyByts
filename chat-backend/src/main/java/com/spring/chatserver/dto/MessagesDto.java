package com.spring.chatserver.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessagesDto {
    private Long sentBy;
    private Long sentTo;
    private String message;
    private Long messageId;
    private String status;
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
		
	}
	public Long getSentBy() {
		return sentBy;
	}
	public void setSentBy(Long sentBy) {
		this.sentBy = sentBy;
	}
	public Long getSentTo() {
		return sentTo;
	}
	public void setSentTo(Long sentTo) {
		this.sentTo = sentTo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
