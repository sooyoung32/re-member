package com.remember.server.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
	
	 private String content;
	 
	 private Date createdAt;
	 
	 private UserModel creator;
}
