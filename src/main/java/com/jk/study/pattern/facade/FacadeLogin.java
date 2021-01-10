package com.jk.study.pattern.facade;

public class FacadeLogin {

	public boolean login(String id, String password, FacadeAccount facadeAccount) {
		boolean result = facadeAccount.getId().equals(id) && facadeAccount.getPassword().equals(password);

		if (result) {
			System.out.println("login success");
		} else {
			System.out.println("login failed");
		}

		return result;
	}

}
