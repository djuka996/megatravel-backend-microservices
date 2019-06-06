package com.megatravel.MessagingService.dto;

public class MessageDTO {

	private Long id;
	private Long chatId;
	private Long userId;
	private String message;
	
	public MessageDTO() { }
	
	public MessageDTO(Long id,Long chatId,Long userId,String message) {
		super();
		this.id = id;
		this.chatId = chatId;
		this.userId = userId;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
