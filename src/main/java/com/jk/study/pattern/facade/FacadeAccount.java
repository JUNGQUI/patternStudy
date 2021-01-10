package com.jk.study.pattern.facade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacadeAccount {
	private String id;
	private String password;
}
