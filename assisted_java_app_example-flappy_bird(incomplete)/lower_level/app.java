package lower_level;

import java.awt.*;
import javax.swing.*;

import lower_level.fundamentals.constants;

public class app extends JFrame
{
    private appWindow window = new appWindow(); 
    private appAudio audio = new appAudio(); 
    private userInput input = new userInput();

    public app()
    {
        super.setSize(constants.FRAME_BORDER_X, constants.FRAME_BORDER_Y);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.add(window);
        super.setVisible(true);
        super.addKeyListener(input.getKeyListener());
    }

    public void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch(InterruptedException e){}
    }

    public void drawLine(Color color, int x1, int y1, int x2, int y2)
    {
        window.drawLine(color, x1, y1, x2, y2);
    }

    public void drawRectangle(Color color, int x, int y, int width, int height)
    {
        window.drawRectangle(color, x, y, width, height);
    }

    public void drawRoundedRectangle(Color color, int x, int y, int width, int height, int arc_width, int arc_height)
    {
        window.drawRoundedRectangle(color, x, y, width, height, arc_width, arc_height);
    }

    public void drawOval(Color color, int x, int y, int width, int height)
    {
        window.drawOval(color, x, y, width, height);
    }

    public void drawArc(Color color, int x, int y, int width, int height, int start_angle, int arc_angle)
    {
        window.drawArc(color, x, y, width, height, start_angle, arc_angle);
    }

    public void drawPolygon(Color color, int[] x_points, int[] y_points, int amount_of_vertices)
    {
        window.drawPolygon(color, x_points, y_points, amount_of_vertices);
    }

    public void drawText(Color color, int x, int y, String text)
    {
        window.drawText(color, x, y, text);
    }

    public void clearDrawings()
    {
        window.clearDrawings();
    }

    public void createSprite(String sprite_name, String file_name)
    {
        window.createSpriteObject(sprite_name, file_name);
    }

    public void deleteSprite(String sprite_name)
    {
        window.deleteSpriteObject(sprite_name);
    }

    public void setSpriteImage(String sprite_name, String file_name)
    {
        window.setSpriteObjectImage(sprite_name, file_name);
    }

    public void toggleSpriteVisibility(String sprite_name, boolean visible)
    {
        window.toggleSpriteObjectVisibility(sprite_name, visible);
    }

    public void setSpritePose(String sprite_name, int x, int y, int degrees)
    {
        window.setSpriteObjPose(sprite_name, x, y, degrees);
    }

    public int getSpriteImageWidth(String sprite_name)
    {
        return window.getSpriteObjectImageWidth(sprite_name);
    }

    public int getSpriteImageHeight(String sprite_name)
    {
        return window.getSpriteObjectImageHeight(sprite_name);
    }

    public void setWindowBackgroundColor(Color color)
    {
        window.setWindowBackgroundColor(color);
    }

    public int getCurrentKeyPressed()
    {
        return input.getCurrentKeyPressed();
    }

    public int getLastKeyReleased()
    {
        return input.getLastKeyReleased(); 
    }

    public void playAudioFile(String file_name)
    {
        audio.playAudioFile(file_name);
    }

    public void stopAudioFile(String file_name)
    {
        audio.stopAudioFile(file_name);
    }
}
