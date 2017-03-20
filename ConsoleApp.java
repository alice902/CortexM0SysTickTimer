import java.util.Observer;
import java.util.Observable;

/**
 * Write a description of class ConsoleApp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConsoleApp implements Observer
{
    private CortexM0SysTickTimer demoCounter;

    /**
     * Constructor for objects of class ConsoleApp
     */
    public ConsoleApp()
    {
        demoCounter = new CortexM0SysTickTimer();
        demoCounter.addObserver(this);
        demoApp();
    }
    
    public void demoApp()
    {
        displayMsg("Hello, user!");
        // make show
        displayMsg("Counter state is: " + demoCounter);
        demoCounter.setEnable(true);
        demoCounter.setRVR(4);
        demoCounter.setTickint(true);
        displayMsg("I will send 6 ticks.");
        for(int i=0; i<5; i++)
        {
            demoCounter.tick();
            displayMsg("After " + (i+1) + "ticks state changed to: " + demoCounter);
        }
    }
    
    public void displayMsg(String msg)
    {
         System.out.println(msg);
    }
    
    public void update(Observable subject, Object arg)
    {
        displayMsg("---INTERRUPT---");
    }
    
    /**
    * Metoda main - klasa wiodonca
    **/
    
    public static void main(String[] arg)
    {
        new ConsoleApp();
    }
}
