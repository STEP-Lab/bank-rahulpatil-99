package com.example.bank;

public class InvalidAmount extends Throwable {
  public InvalidAmount() {
    super("Requested transaction can not be processed due to invalid amount");
  }
}
