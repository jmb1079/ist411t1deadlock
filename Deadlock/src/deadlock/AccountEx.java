/* 
 Project: Lab 2 - Group
 Purpose Details: Deadlock application
 Course: IST 411
 Author: Ryan Urbanski, Albana Beqo, James Bristow II, Logan Pratt, Fred Aaron
 Date Developed: 2/1/2020
 Last Date Changed:2/1/2020
 Revision: 1
 */
package deadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Ryan Urbanski, Albana Beqo, James Bristow II, Logan Pratt, Fred Aaron
 */
public class AccountEx
{
    private int acct;
    private double balance = 10000;
    private Lock accountLock;
    private Condition sufficientFunds;

    AccountEx(int accountNum)
    {
        acct = accountNum;
        accountLock = new ReentrantLock();
        sufficientFunds = accountLock.newCondition();
    }

    /**
     * @return the account
     */
    public int getAcct()
    {
        return acct;
    }

    /**
     * @return the balance
     */
    public synchronized double getBalance()
    {
        accountLock.lock();
        try
        {
            return balance;
        }
        finally
        {
            accountLock.unlock();
        }
    }

    /**
     * @param newBalance
     */
    public synchronized void setBalance(double newBalance) throws InterruptedException
    {
        accountLock.lock();
        try
        {
            while(newBalance < 0) 
            {
                System.out.println("Account " + acct + " balance waiting; Insufficient funds");
                sufficientFunds.await();
            }
            System.out.println("Account " + acct + ": Old balance: $" + balance 
            + "; New Balance: $" + newBalance);
            balance = newBalance;
        }
        finally
        {
            accountLock.unlock();
        }
    }
    
    public void increaseBalance(double amountToIncrement) throws InterruptedException
    {
        accountLock.lock();
        try
        {
            this.setBalance(balance + amountToIncrement);
        }
        finally
        {
            accountLock.unlock();
        }
    }
    
    public void decreaseBalance(double amountToDecrement) throws InterruptedException
    {
        accountLock.lock();
        try
        {
            this.setBalance(balance - amountToDecrement);
        }
        finally
        {
            accountLock.unlock();
        }
    }
}
