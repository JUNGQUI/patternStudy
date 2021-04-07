package com.jk.study.pattern.adapter;

public class InsectActingClass implements InsectActing {

  @Override
  public void actingSomething() {
    System.out.println("Insect Acting Something");
  }
}
