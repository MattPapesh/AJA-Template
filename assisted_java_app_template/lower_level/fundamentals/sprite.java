package lower_level.fundamentals;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class sprite 
{
    private final String SPRITE_BASE_DIR = "sprites/";
    private final String SPRITE_NAME; 
    private String sprite_dir = "";
    private coordinates sprite_coords = new coordinates(0, 0, 0); 
    private BufferedImage sprite_b_image = null;
    private boolean visible = true; 

    public sprite(String sprite_name, String file_name)
    {
        SPRITE_NAME = sprite_name;
        sprite_dir = SPRITE_BASE_DIR + file_name; 
        setSpriteImage(file_name);  
    }

    public void setSpriteImage(String file_name)
    {   
        try
        {
            sprite_dir = SPRITE_BASE_DIR + file_name;
            sprite_b_image = ImageIO.read(new File(sprite_dir));
        }
        catch(IOException e) 
        {
            System.err.println("sprite.java: Caught exeception! Could not find a sprite's image! \n");
        }
    }

    public void setSpriteCoords(int x, int y, int degrees)
    {
        this.sprite_coords.x = x;
        this.sprite_coords.y = y; 
        this.sprite_coords.radians = (degrees * Math.PI) / 180; 
    }

    public void setVisibilityStatus(boolean visible)
    {
        this.visible = visible; 
    }

    public boolean getVisibilityStatus()
    {
        return visible; 
    }

    public String getSpriteDir()
    {
        return sprite_dir; 
    }

    public String getSpriteName()
    {
        return SPRITE_NAME; 
    }

    public BufferedImage getSpriteBufferedImage()
    {
        return sprite_b_image; 
    }

    public coordinates getSpriteCoords()
    {
        return sprite_coords; 
    }
}
