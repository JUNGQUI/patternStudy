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

  @Test
  void side_effect_test() {
    // side-effect 는 colletion 같은 구조만 가능
    Duration min30 = Duration.ofMinutes(30);
    Duration min60 = Duration.ofMinutes(60);
    Duration min90 = Duration.ofMinutes(90);
    Duration min90ByPlus = sumDuration(min30, min60);

    Assertions.assertEquals(min90, min90ByPlus);
  }

  private Duration sumDuration(Duration a, Duration b) {
    return a.plus(b);
  }
}
