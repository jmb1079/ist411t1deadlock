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

/**
 *
 * @author Ryan Urbanski, Albana Beqo, James Bristow II, Logan Pratt, Fred Aaron
 */
public class Account
{

    private int acct;
    private double balance = 10000;

    Account()
    {
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
    public double getBalance()
    {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
