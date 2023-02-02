package com.bank;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account 
{
   //variables
	//non-static data member
	private int  customerNumber;
	private int pinNumber;
	private double checkingBalance = 0.0;
	private double savingBalance = 0.0;
	
    Scanner input = new Scanner(System.in);
    //cntr+alt +4 = rupee symbol/ regex
    DecimalFormat moneyFormat = new DecimalFormat(" '₹'###,##0.00");
    
    public Account()
    {
    }
    
    public Account(int customerNumber, int pinNumber)
    {
    	this.customerNumber = customerNumber;
    	this.pinNumber = pinNumber;
    }
    public Account(int customerNumber, int pinNumber,double checkingBalance,
			 double savingBalance)
	{
		this.customerNumber= customerNumber;
		this.pinNumber= pinNumber;
		this.checkingBalance=checkingBalance;
		this.savingBalance= savingBalance;}
    
    public int setCustomerNumber(int customerNumber)
    {
    	this.customerNumber = customerNumber;
    	return customerNumber;
    }
    public int getCustomernumber()
    {
    	return customerNumber;
    }

	public int getPinNumber() 
	{
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) 
	{
		this.pinNumber = pinNumber;
	}

	public double getCheckingBalance() 
	{
		return checkingBalance;
	}

	public double getSavingBalance() 
	{
		return savingBalance;
	}
	
	public double calcCheckingWithdraw(double amount)
	{
		checkingBalance = (checkingBalance - amount);
		return checkingBalance;
	}
	
	public double calcSavingWithdraw(double amount)
	{
		savingBalance = (savingBalance - amount);
		return savingBalance;
	}
	
	public double calcCheckingDeposit(double amount)
	{
		checkingBalance = (checkingBalance + amount);
		return checkingBalance;
	}
	
	public double calcSavingDeposit(double amount)
	{
		savingBalance = (savingBalance + amount);
		return savingBalance;
	}
	
	//transferring the money from checking to savings
	public void calcCheckTransfer(double amount)
	{
		checkingBalance = checkingBalance + amount;
		savingBalance = savingBalance + amount;
	}
	
	//transferring the money from savings to checking
	public void calcSavingTransfer(double amount)
	{
		savingBalance = savingBalance + amount;
		checkingBalance = checkingBalance + amount;
	}
	
	public void getCheckingWithdrawInput()
	{
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("/nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
			    System.out.println("/nAmount you want to withdraw from Checking Account: ");
			    double amount = input.nextDouble();
			    
			    if((checkingBalance - amount) >= 0 && amount >= 0)
			    {
			    	calcCheckingWithdraw(amount);
			    	System.out.println("/nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
			    	end = true; 
			    }
			    else
			    {
			    	System.out.println("/n Balance Cannot be Negative");
			    }
			}
			    catch(InputMismatchException ex) 
			     {
				  System.out.println("/n Incalid Choice");
				  input.next();
			    }
		}
	}
	
	public void getSavingWithdrawInput()
	{
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("/nCurrent Saving Account Balance: " + moneyFormat.format(savingBalance));
			    System.out.println("/nAmount you want to withdraw from Saving Account: ");
			    double amount = input.nextDouble();
			    
			    if((savingBalance - amount) >= 0 && amount >= 0)
			    {
			    	calcSavingWithdraw(amount);
			    	System.out.println("/nCurrent Saving Account Balance: " + moneyFormat.format(checkingBalance));
			    	end = true; 
			    }
			    else
			    {
			    	System.out.println("/n Balance Cannot be Negative");
			    }
			}
			    catch(InputMismatchException ex)
			     {
				  System.out.println("\n Incalid Choice");
				  input.next();
			    }
		}
	}
	
	public void getCheckingDepositInput()
	{
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
			    System.out.println("\nAmount you want to deposit from Checking Account: ");
			    double amount = input.nextDouble();
			    
			     if(!(amount <= 0) && (checkingBalance + amount) > 0 && amount > 0)
			    {
			    	calcCheckingDeposit(amount);
			    	System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
			    	end = true; 
			    }
			    else
			    {
			    	System.out.println("\n Cannot Deposit Negative amount");
			    }
			    
			}
			    catch(InputMismatchException ex)
			     {
				  System.out.println("\n Incalid Choice");
				  input.next();
			    }
		}
	}
	
	public void getSavingDepositInput()
	{
		boolean end = false;
		while(!end)
		{
			try
			{
				System.out.println("/nCurrent Saving Account Balance: " + moneyFormat.format(savingBalance));
			    System.out.println("/nAmount you want to withdraw to Saving Account: ");
			    double amount = input.nextDouble();
			    
			    if(!(amount <= 0) && (savingBalance + amount) > 0 && amount > 0)
			    {
			    	calcSavingDeposit(amount);
			    	System.out.println("/nCurrent Saving Account Balance: " + moneyFormat.format(checkingBalance));
			    	end = true; 
			    }
			    else
			    {
			    	System.out.println("/n Cannot Deposit Negative or 0 amount");
			    }
			}
			    catch(InputMismatchException ex)
			     {
				  System.out.println("\n Incalid Choice");
				  input.next();
			    }
		}
	}
	
	public void getTransferInput(String accType)
	{
		boolean end = false;
		
		while(!end)
		{
			try
			{
			if (accType.equals("Checking"))	
			{
				System.out.println("\n Select an account you wish to transfer found to: ");
				System.out.println("1. Saving");
				System.out.println("2. Exit");
				System.out.println("\nChoice: ");
				int choice = input.nextInt();
				switch(choice)
				{
				case 1: System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
				System.out.println("\nAmount you want to deposit into your saving Account: ");
				double amount = input.nextDouble();
				if(!(amount <= 0) && (savingBalance + amount) > 0 && (checkingBalance - amount) > 0 && amount > 0)
				{
					calcCheckTransfer(amount);
					System.out.println("\n Current Saving Account Balance: " + moneyFormat.format(savingBalance));
					System.out.println("\nCurrent Checking Account Balance:" + moneyFormat.format(checkingBalance));
					end = true;
				}
				else
				{
					System.out.println("\nCannot Transer Negative or 0 amount.");
				}
				break;
				case 2:
				    return;
				    default:
				    	System.out.println("\nInvalid Choice. ");
				    	break;
				}
			}	//line 
					
				else if (accType.equals("\savings"))
				{
					System.out.println("\nSelect an account you wish to transer found to: ");
					System.out.println("1.Checking");
					System.out.println("2. Exit");
					System.out.println("\nChoice: ");
					int choice = input.nextInt();
					switch(choice)
					{
					case 1: System.out.println("\nCurrent Saving Account Balance: " + moneyFormat.format(savingBalance));
					System.out.println("\nAmount you want to deposit into your checking account: ");
					double amount = input.nextDouble();
					if(!(amount < 0) && (checkingBalance + amount) > 0 && (savingBalance - amount) > 0 && amount >=0)
					{
						calcSavingTransfer(amount);
						System.out.println("\nCurrent checking account balance: " + moneyFormat.format(checkingBalance));
						System.out.println("\nCurrent saving account balance: " + moneyFormat.format(savingBalance));
						end = true;
					}
					else
					{
						System.out.println("\n Cannot Transfer Negative or 0 amount");
					}
					break;
					case 2:
						    return;
					default:
						    System.out.println("\nInvalid Choice.");
						    break;
					}
				}
			}	
				catch(InputMismatchException ex)
				{
					System.out.println("\nInvalid Choice. ");
					input.next();
				}
			}
	}//line 
}


	
    
    
    
    
    

