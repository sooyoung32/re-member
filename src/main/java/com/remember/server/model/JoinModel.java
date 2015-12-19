package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinModel {

	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private Date joinAt;

	@NotNull
	private String name;

}
