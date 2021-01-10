package com.jk.study.pattern.facade;

public class FacadeCreateAccountAndLogin {
	public boolean joinAndLogin (String id, String password) {
		FacadeLogin facadeLogin = new FacadeLogin();
		FacadeAccountCheck facadeAccountCheck = new FacadeAccountCheck();

		boolean check = facadeAccountCheck.alphabetRegex(password)
				&& facadeAccountCheck.numberRegex(password)
				&& facadeAccountCheck.specialRegex(password)
				&& facadeAccountCheck.passwordLengthCheck(password);

		if (!check) {
			System.out.println("password invalid");
			return false;
		}

		FacadeAccount facadeAccount = new FacadeAccount(id, password);

		return facadeLogin.login(id, password, facadeAccount);
	}
}
