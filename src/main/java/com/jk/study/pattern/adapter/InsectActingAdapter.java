package com.jk.study.pattern.adapter;

public class InsectActingAdapter implements AnimalActing {

  private InsectActing insectActing;

  public InsectActingAdapter(InsectActing insectActing) {
    this.insectActing = insectActing;
  }

  @Override
  public void acting() {
    insectActing.actingSomething();
  }

  @Override
  public void shouting() {
    System.out.println("Do Nothing");
  }
}
