package com.jk.study.pattern.java;

import java.time.Duration;
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

  @Test
  void durationTest() {
    Duration duration1 = Duration.ofHours(10);
    Duration duration2 = Duration.ofHours(10).plusMinutes(30);
    Duration duration3 = Duration.ofMinutes(630);
    duration1 = duration1.plusMinutes(30);

    Assertions.assertEquals(duration1, duration2);
    Assertions.assertEquals(duration1, duration3);
    Assertions.assertEquals(duration2, duration3);
    Assertions.assertNotEquals(duration2, duration3.plusMinutes(30));
  }
}
