package lower_level;

import javax.swing.*;

import lower_level.fundamentals.constants;
import lower_level.fundamentals.sprite;
import java.awt.*;
import java.util.LinkedList; 

public class appWindow extends JPanel
{
    private LinkedList<sprite> sprites = new LinkedList<sprite>();
    private Color window_background_color = constants.DEFAULT_BACKGROUND_COLOR;
    private LinkedList<draw_geometry_behavior> draw_geometry_behavior = new LinkedList<draw_geometry_behavior>();

    @FunctionalInterface
    private interface cycle_sprite_behavior
    {
        int execute(int i);
    };

    @FunctionalInterface 
    private interface draw_geometry_behavior
    {
        void execute(Graphics graphics);
    };

    private int cycleSprites(cycle_sprite_behavior behavior)
    {
        int data = 0;

        for(int i = 0; i < sprites.size(); i++)
        {
            data = behavior.execute(i);
        }

        return data;
    }

    @Override
    protected void paintComponent(Graphics graphics) 
    {
        graphics = getPreparedGraphics(graphics);
        displaySpriteGraphics(graphics); 
    }

    private Graphics getPreparedGraphics(Graphics graphics)
    {        
        super.paintComponent(graphics);
        super.repaint();
        super.setBackground(window_background_color);

        try
        {
            Thread.sleep(constants.DELAY_IN_MILLIS);
        }
        catch(InterruptedException e){}

        return graphics;
    }

    private void displaySpriteGraphics(Graphics graphics)
    {
        Graphics2D graphics_2d = (Graphics2D)graphics; 

        cycleSprites
        (
            (int i) ->
            {
                sprite current_sprite = sprites.get(i);

                if(current_sprite.getVisibilityStatus() == true)
                {
                    graphics_2d.rotate
                    (
                        current_sprite.getSpriteCoords().radians, 
                        current_sprite.getSpriteCoords().x + current_sprite.getSpriteBufferedImage().getWidth() / 2, 
                        current_sprite.getSpriteCoords().y + current_sprite.getSpriteBufferedImage().getHeight() / 2
                    );

                    graphics_2d.drawImage
                    (
                        current_sprite.getSpriteBufferedImage(), 
                        current_sprite.getSpriteCoords().x,
                        current_sprite.getSpriteCoords().y,
                        null
                    );
                }
                
                return 0;
            }
        );

        for(int i = 0; i < draw_geometry_behavior.size(); i++)
        {
            if(draw_geometry_behavior.get(i) != null)
            {
                draw_geometry_behavior.get(i).execute(graphics);
            }
        }
    }

    protected void drawLine(Color color, int x1, int y1, int x2, int y2)
    {
        draw_geometry_behavior.addLast
        (
            new draw_geometry_behavior()
            {
                @Override
                public void execute(Graphics graphics) 
                {
                    graphics.setColor(color);
                    graphics.drawLine(x1, y1, x2, y2);
                }   
            }
        );
    }

    protected void drawRectangle(Color color, int x, int y, int width, int height)
    {
        draw_geometry_behavior.addLast
        (
            new draw_geometry_behavior()
            {
                @Override
                public void execute(Graphics graphics)
                {
                    graphics.setColor(color);
                    graphics.drawRect(x, y, width, height);
                }    
            }
        );
    }

    protected void drawRoundedRectangle(Color color, int x, int y, int width, int height, int arc_width, int arc_height)
    {
        draw_geometry_behavior.addLast
        (
            new draw_geometry_behavior()
            {
                @Override
                public void execute(Graphics graphics)
                {
                    graphics.setColor(color);
                    graphics.drawRoundRect(x, y, width, height, arc_width, arc_height);
                }    
            }
        ); 
    }

    protected void drawOval(Color color, int x, int y, int width, int height)
    {
        draw_geometry_behavior.addLast
        ( 
            new draw_geometry_behavior()
            {
                @Override
                public void execute(Graphics graphics)
                {
                    graphics.setColor(color);
                    graphics.drawOval(x, y, width, height);
                }    
            }
        );
    }

    protected void drawArc(Color color, int x, int y, int width, int height, int start_angle, int arc_angle)
    {
        draw_geometry_behavior.addLast
        (
            new draw_geometry_behavior()
            {
                @Override
                public void execute(Graphics graphics)
                {
                    graphics.setColor(color);
                    graphics.drawArc(x, y, width, height, start_angle, arc_angle);
                }    
            }
        );
    }

    protected void drawPolygon(Color color, int[] x_points, int[] y_points, int amount_of_vertices)
    {
        draw_geometry_behavior.addLast
        (
            new draw_geometry_behavior()
            {
                @Override
                public void execute(Graphics graphics)
                {
                    graphics.setColor(color);
                    graphics.drawPolygon(x_points, y_points, amount_of_vertices);
                }    
            }
        );
    }

    protected void drawText(Color color, int x, int y, String text)
    {
        draw_geometry_behavior.addLast
        (
            new draw_geometry_behavior()
            {
                @Override
                public void execute(Graphics graphics)
                {
                    graphics.setColor(color);
                    graphics.drawString(text, x, y);
                }    
            }
        ); 
    }

    protected void clearDrawings()
    {
        draw_geometry_behavior.clear();
    }

    protected void createSpriteObject(String sprite_name, String file_name)
    {
        sprites.addLast(new sprite(sprite_name, file_name));
    }

    protected void setSpriteObjectImage(String sprite_name, String file_name)
    {
        for(int i = 0; i < sprites.size(); i++)
        {
            if(sprites.get(i).getSpriteName() == sprite_name)
            {
                sprites.get(i).setSpriteImage(file_name);
            }
        }
    }

    protected int getSpriteObjectImageWidth(String sprite_name)
    {
        return cycleSprites
        (
            (int i) ->
            {
                if(sprites.get(i).getSpriteName() == sprite_name)
                {
                    return sprites.get(i).getSpriteBufferedImage().getWidth();
                }

                return 0;
            }
        );
    }

    protected int getSpriteObjectImageHeight(String sprite_name)
    {
        return cycleSprites
        (
            (int i) ->
            {
                if(sprites.get(i).getSpriteName() == sprite_name)
                {
                    return sprites.get(i).getSpriteBufferedImage().getHeight();
                }

                return 0;
            }
        );
    }

    protected void deleteSpriteObject(String sprite_name)
    {
        cycleSprites
        (
            (int i) ->
            {
                if(sprites.get(i).getSpriteName() == sprite_name)
                {
                    sprites.remove(i); 
                }

                return 0;
            }
        );
    }

    protected void setSpriteObjPose(String sprite_name, int x, int y, int degrees)
    { 
        cycleSprites
        (
            (int i) ->
            {
                if(sprites.get(i).getSpriteName() == sprite_name)
                {
                    sprites.get(i).setSpriteCoords(x, y, degrees);
                }

                return 0;
            }
        );
    }

    protected void toggleSpriteObjectVisibility(String sprite_name, boolean visible)
    {
        cycleSprites
        (
            (int i) ->
            {
                if(sprites.get(i).getSpriteName() == sprite_name)
                {
                    sprites.get(i).setVisibilityStatus(visible);
                }

                return 0;
            }
        );
    }

    protected void setWindowBackgroundColor(Color color)
    {
        window_background_color = color; 
    }
}