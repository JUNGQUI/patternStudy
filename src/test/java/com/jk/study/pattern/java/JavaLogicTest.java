package com.jk.study.pattern.java;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JavaLogicTest {

  @Test
  void localDateTimeSubTest() {
    LocalDateTime max = LocalDateTime.now();
    LocalDateTime min = max.minusMinutes(30);

    Assertions.assertEquals(30, Math.abs(max.getMinute() - min.getMinute()));
  }
}
