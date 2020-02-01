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
public class AdjustAccount implements Runnable
{

    Account acct = new Account();
    ReentrantLock acctLock = new ReentrantLock();
    int thread;

    AdjustAccount(Account account, int threadNum)
    {
        acct = account;
        thread = threadNum;
    }

    private synchronized void adjust() throws InterruptedException
    {
        acctLock.lock();
        double withdraw = 2000;
        try
        {
            while (true)
            {
                while (acct.getBalance() < withdraw)
                {
                    System.out.println("Thread " + thread + " Balance is " + acct.getBalance());
                    System.out.println("Thread " + thread + " Trying to withdraw " + withdraw);
                    System.out.println("Thread " + thread + " Waiting");
                    wait();
                }
                System.out.println("Thread " + thread + " Balance is good at " + acct.getBalance());
                acct.setBalance(acct.getBalance() - withdraw);
                notifyAll();
            }
        }
        finally
        {
            acctLock.unlock();
        }
    }

    /**
     *
     * @param account
     */
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
