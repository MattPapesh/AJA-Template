package lower_level;

import java.awt.event.*;

public class userInput implements KeyListener
{
    private int key_pressed = 0;
    private int key_released = 0; 

    protected KeyListener getKeyListener()
    {
        return this; 
    }

    protected int getCurrentKeyPressed()
    {
        return key_pressed;
    }
    
    protected int getLastKeyReleased()
    {
        return key_released;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        key_pressed = e.getKeyCode();

        if(e.getKeyCode() == key_released)
        {
            key_released = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        key_released = e.getKeyCode();
        
        if(e.getKeyCode() == key_pressed)
        {
            key_pressed = 0; 
        }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {}
}
