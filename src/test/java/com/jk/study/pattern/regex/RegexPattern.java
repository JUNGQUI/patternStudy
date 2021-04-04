package com.jk.study.pattern.regex;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegexPattern {

	@Test
	void regexPattern() {
		Pattern specialRegex = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
		Pattern numberRegex = Pattern.compile("[0-9]");
		Pattern alphabetRegex = Pattern.compile("[a-zA-Z]");

		Assertions.assertTrue(specialRegex.matcher("이건 통과!").find());
		Assertions.assertFalse(specialRegex.matcher("이건 불통").find());

		Assertions.assertFalse(alphabetRegex.matcher("이건 불통").find());
		Assertions.assertTrue(alphabetRegex.matcher("passed").find());
		Assertions.assertTrue(alphabetRegex.matcher("but, 이건 통과").find());

		Assertions.assertFalse(numberRegex.matcher("not passed").find());
		Assertions.assertTrue(numberRegex.matcher("pass, because of 1").find());
	}

	@Test
	void containsTest() {
		String object = "development";
		String containsObject = "[development]";

		Assertions.assertTrue(containsObject.contains(object));
	}
}
