/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author James Bristow <jmb1079@psu.edu>
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
                        + "to Account " + toAcct.getAcct());
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
