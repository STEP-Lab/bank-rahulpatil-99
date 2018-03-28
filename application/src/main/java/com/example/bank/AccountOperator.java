package com.example.bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AccountOperator{
  public static void main(String args[]) throws InvalidAccountNumber, InvalidAmount, MinimumAccountBalance, IOException {
    Money balance = Money.getMoney(1000.00);
    Account account = Account.createAccount(new AccountNumber("1111-1111"),"Rahul",balance);
    System.out.println(account.getBalance());
    balance = Money.getMoney(500.50);
    account.credit(balance,"Rahul");
    System.out.println(account.getBalance());
    balance = Money.getMoney(200.50);
    account.debit(balance,"Vijay");
    System.out.println(account.getBalance());
    System.out.println(account.getSummary());
    FileWriter writer = new FileWriter("sample.csv");
    account.getTransactions().printToCsv(writer);
  }
}