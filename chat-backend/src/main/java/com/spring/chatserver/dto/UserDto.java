package com.spring.chatserver.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String name;
	public Long getUserId() {
		// TODO Auto-generated method stub
		return null;
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUserId(Long userId) {
	}
}
