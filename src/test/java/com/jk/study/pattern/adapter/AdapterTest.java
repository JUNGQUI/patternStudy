package com.jk.study.pattern.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdapterTest {
  @Test
  void adapterTest() {
    AnimalActing dogActing = new DogActing();
    InsectActing insectActing = new InsectActingClass();
    AnimalActing insectActingAdapter = new InsectActingAdapter(insectActing);

    // acting 들
    dogActing.acting();
    insectActing.actingSomething();
    insectActingAdapter.acting();

    // insectActing 에는 shouting 이 없지만, adapter 는 두 가지 모두를 받아서 사용하기에 있는것처럼 사용이 가능하다.
    // 비즈니스 로직이 별도로 있다면 해당 로직을 녹여내서 동일 수행 또한 가능하다.
    dogActing.shouting();
    insectActingAdapter.shouting();
  }
}
