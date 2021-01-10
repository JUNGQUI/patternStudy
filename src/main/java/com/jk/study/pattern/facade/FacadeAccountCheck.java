package com.jk.study.pattern.facade;

import java.util.regex.Pattern;

public class FacadeAccountCheck {
	public boolean specialRegex(String password) {
		Pattern patternSpecial = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
		return patternSpecial.matcher(password).find();
	}

	public boolean numberRegex(String password) {
		Pattern patternNumber = Pattern.compile("[0-9]");
		return patternNumber.matcher(password).find();
	}

	public boolean alphabetRegex(String password) {
		Pattern patternAlphabet = Pattern.compile("[a-zA-Z]");
		return patternAlphabet.matcher(password).find();
	}

	public boolean passwordLengthCheck(String password) {
		return password.length() >= 10;
	}
}
