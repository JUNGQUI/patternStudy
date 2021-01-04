package com.jk.study.pattern.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuilderTest {

  @Test
  void builderTest() {

    // 필수 값은 method (builder) 로 꼭 받게 수행하고
    JKAccount accountSuccess = JKAccount.builder("id1", "password")
        // optional 한 부분은 (보통 business logic) builder 를 통해 받아서 수행한다.
        .comment("comment")
        .build();

    Assertions.assertEquals("id1", accountSuccess.getId());
    Assertions.assertEquals("password", accountSuccess.getPassword());
    Assertions.assertThrows(NullPointerException.class, () -> JKAccount.builder(null, null));
  }
}