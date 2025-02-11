package com.spring.chatserver.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSummaryDto {
    public MessageSummaryDto(Long userId2, Long valueOf, Long valueOf2, String string, Object object, String string3,
			Long valueOf3) {
		// TODO Auto-generated constructor stub
	}
	private Long userId;
    private Long correspondUserId;
    private Long messageId;
    private String chatRecipientName;
    private String message;
    private String status;
    private Long latestMessageUserId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCorrespondUserId() {
		return correspondUserId;
	}
	public void setCorrespondUserId(Long correspondUserId) {
		this.correspondUserId = correspondUserId;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getChatRecipientName() {
		return chatRecipientName;
	}
	public void setChatRecipientName(String chatRecipientName) {
		this.chatRecipientName = chatRecipientName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getLatestMessageUserId() {
		return latestMessageUserId;
	}
	public void setLatestMessageUserId(Long latestMessageUserId) {
		this.latestMessageUserId = latestMessageUserId;
	}
}
