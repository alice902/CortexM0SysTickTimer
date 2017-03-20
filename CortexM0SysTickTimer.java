import java.util.Observable;

/**
 * Write a description of class CortexM0SysTickTimer here.
 * 
 * @author
 * @version 20-03-2017
 */
public class CortexM0SysTickTimer extends Observable
{
    private int registerRVR, registerCVR;
    private boolean countflag, enable, tickint;
    
    /**
     * Constructor for objects of class CortexM0SysTick with no arguments
     */

    public CortexM0SysTickTimer()
    {
    }

    public void setRVR(int RVR)
    {
        //if(registerRVR >= 0 && registerRVR<(1<<24))
        registerRVR = RVR;
        //assert RVR>0 : "The RVR register value must be beetwen 0 and 16777216 (24 bits).";
        //assert RVR<(1<<24) : "The RVR register value must be beetwen 0 and 16777216 (24 bits).";
    }
    
    public void setCVR(int CVR)
    {
        registerCVR = 0;
        countflag = false;
    }
    
    public void setEnable(boolean flag)
    {
        enable = flag;
    }
    
    public void setTickint(boolean flag)
    {
        tickint = flag;
    }
    
    public int getRVR()
    {
        return registerRVR;
    }
    
    public int getCVR()
    {
        countflag = false;
        return registerCVR;
    }
    
    public boolean isCountflag()
    {
        boolean tmp = countflag;
        countflag = false;
        return tmp;
    }
    
    public boolean isEnable()
    {
        countflag = false;
        return enable;
    }
    
    public boolean isTickint()
    {
        countflag = false;
        return tickint;
    }
    
    public void tick()
    {
        if(enable && registerRVR!=0)
            {
                registerCVR--;
                
                if(registerCVR==0)
                    countflag = true;
                
                if(registerCVR<0)
                {
                    registerCVR=registerRVR;
                    if(tickint)
                    {
                        setChanged();
                        notifyObservers();
                    }
                }
                
                
            }
    }
    
    public String toString()
    {
        return ("\nenable = " + enable
                + "\ncountflag = " + countflag
                + "\ntickint = " + tickint
                + "\nRVR = " + registerRVR
                + "\nCVR = " + registerCVR
        );
    }
    
    }
    // SysTickTimer
    // countflag: 0 when R/W to CVR, 1 when timer counted to 0
    // enable: 0 disable, 1 enable