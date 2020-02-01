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

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Ryan Urbanski, Albana Beqo, James Bristow II, Logan Pratt, Fred Aaron
 */
public class TransferAccount implements Runnable
{

    AccountEx fromAcct;
    AccountEx toAcct;
    int thread;

    TransferAccount(AccountEx account1, AccountEx account2, int threadNum)
    {
        fromAcct = account1;
        toAcct = account2;
        thread = threadNum;
    }

    private synchronized void adjust() throws InterruptedException
    {
        double transferAmount = 1000;
        while (true)
        {
            System.out.println("Thread " + thread + " Account "
                        + fromAcct.getAcct() + " transfer $" + transferAmount
                        + " to Account " + toAcct.getAcct());
            while (fromAcct.getBalance() < transferAmount)
            {
                System.out.println("Thread " + thread + ": Transfer waiting...");
                wait();
            }
            System.out.println("Thread " + thread + " Account " + fromAcct.getAcct()
                    + " Balance is good at " + fromAcct.getBalance());

            fromAcct.decreaseBalance(transferAmount);
            toAcct.increaseBalance(transferAmount);
            notifyAll();
        }
    }

    @Override
    public void run()
    {
        try
        {
            adjust();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Caught Exception failed adjust()");
        }
    }
}
