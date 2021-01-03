package com.jk.study.pattern.pubsub;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PubSubTest {

  @Autowired private PubSub pubSub;

  @Test
  void PublisherSubscriberTest() {
    List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> integersByPubSub = pubSub.integerBuilder();

    for (int i = 0; i < 10; i++) {
      Assertions.assertEquals(integers.get(i), integersByPubSub.get(i));
    }
  }
}