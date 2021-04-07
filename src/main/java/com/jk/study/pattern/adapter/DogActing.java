package com.jk.study.pattern.adapter;

public class DogActing implements AnimalActing {
  @Override
  public void acting() {
    System.out.println("BITE");
  }

  @Override
  public void shouting() {
    System.out.println("BARK");
  }
}
