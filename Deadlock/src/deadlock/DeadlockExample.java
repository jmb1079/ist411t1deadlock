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
public class DeadlockExample
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        multiThreadedApp();
        //multiThredTransfer();
    }
    
    public static void multiThreadedApp()
    {
        AccountEx acct1 = new AccountEx(1);
        AccountEx acct2 = new AccountEx(2);
        AccountEx acct3 = new AccountEx(3);
        AccountEx acct4 = new AccountEx(4);
        AdjustAccount adjustAccount1 = new AdjustAccount(acct1, 1);
        Thread adjustThread1 = new Thread(adjustAccount1);
        adjustThread1.start();
        AdjustAccount adjustAccount2 = new AdjustAccount(acct2, 2);
        Thread adjustThread2 = new Thread(adjustAccount2);
        adjustThread2.start();
        AdjustAccount adjustAccount3 = new AdjustAccount(acct3, 3);
        Thread adjustThread3 = new Thread(adjustAccount3);
        adjustThread3.start();
        AdjustAccount adjustAccount4 = new AdjustAccount(acct4, 4);
        Thread adjustThread4 = new Thread(adjustAccount4);
        adjustThread4.start();
    }
    
    public static void multiThredTransfer()
    {
        AccountEx acct1 = new AccountEx(1);
        AccountEx acct2 = new AccountEx(2);
        TransferAccount transferAccount1 = new TransferAccount(acct1, acct2, 1);
        TransferAccount transferAccount2 = new TransferAccount(acct2, acct1, 2);
        Thread transferThread1 = new Thread(transferAccount1);
        Thread transferThread2 = new Thread(transferAccount2);
        transferThread1.start();
        transferThread2.start();
    }
}
