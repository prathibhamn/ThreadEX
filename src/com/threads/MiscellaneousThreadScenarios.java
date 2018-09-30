package com.threads;

public class MiscellaneousThreadScenarios  extends Thread implements Runnable
{
   static MiscellaneousThreadScenarios lMiscellaneousThreadScenarios = new MiscellaneousThreadScenarios();
    public static void main( String[] args ) throws InterruptedException
    {
        MiscellaneousThreadScenarios.sleep( 1000 );
        lMiscellaneousThreadScenarios.start();
        Integer a =5;
        a.wait();//IllegalMonitorStateException
        
    }

    @Override
    public void run()
    {
        System.out.println( "hey there" );
        
    }
}
