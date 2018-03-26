package com.example.bank;

public class Money {
  private final double amount;

  private Money(double amount) {
    this.amount = amount;
  }

  public static Money getMoney(double amount) throws InvalidAmount {
    if(amount<0) throw new InvalidAmount();
    return new Money(amount);
  }
  public double getAmount(){
    return amount;
  }
}
