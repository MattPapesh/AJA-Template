import lower_level.app;

/* 
    This is where your app should be created. All code relavent to your app goes in this file, and any
    images for sprites should be put into the "sprites" folder. 

    When programming, there will always be a main() function; it's what is called(ran) by the operating system 
    so your computer knows where to start in terms of executing your code. However, this time you will be 
    treating the "execute()" function as the "main()" or "public static void main(String[] args)" function. 
    
    NOTE: You can do really anything in this file, but just don't change anything inside of the
    appContainer() function!

    "BUILT_IN FUNCTIONS":
    
    - createSprite(String sprite_name, String file_name) -> creates a sprite given the file name from the sprites folder.
    - deleteSprite(String sprite_name) 
    - setSpritePose(String sprite_name, int x, int y) -> sets a sprite's coordinates, but it may or may 
                                                           not be visible depending on the sprite's visibility status.
     
    - toggleSpriteVisibility(String sprite_name, boolean visible) -> visible = true shows a sprite, and visible = false
                                                                       hides the sprite.
     
    Both functions below are to get the input from your computer's keyboard, but what it returns is an integer and 
    not a character given that not every key has a character.  
     
    - getKeyPressed()
    - getKeyReleased()
*/
    

// All code should be written in here:
public class appContainer extends app                   
{ 
    // NOTE: This attempt at recreating pong works, but that's all it does. It might feel like that this looks overly complicated, and honestly, it is. 
    // There are many other ways to go about creating the game, and our goal is not only to make it work, but also make it do so with as little code as possible. 
    // (A usual goal when programming) Lastly, regarding this example's flaws, there are better ways to do things, but some of those "better ways" involve more advanced
    // concepts that we'll eventually get to. Maybe we can apply those changes once we get to them as a way to show why things like classes are so useful (compared to this 
    // file's endless "if" statements and so on)
    public appContainer()
    {
        super();
    }

    // The status can be only 1 of 3 things at any time. It can be either the primary status, the secondary status, 
    // or the idle status. 
    char getControllerStatus(char current, char primary, char secondary, char idle)  
    {
        if((char)getCurrentKeyPressed() == primary && (char)getLastKeyReleased() != primary)
            {
                return primary; 
            }
            if((char)getCurrentKeyPressed() == secondary && (char)getLastKeyReleased() != secondary)
            {
                return secondary;
            }
            else if((char)getLastKeyReleased() == current)
            {
                return idle; 
            } 

        return current; 
    }

    public int getRandomAccel(int min, int max)
    {
        return (int)((Math.random() * (max - min)) + min); 
    }

    int controller_length_x = 33;
    int controller_length_y = 200;
    int ball_diameter = 50; 

    char controller_1 = ' ', controller_2 = ' '; 
    int controller_1_x = 25, controller_2_x = 1382;
    int controller_1_y = 156, controller_2_y = 156;
    int controller_velo_y = 2; 
    
    int ball_x = 375, ball_y = 325;
    int ball_velo_x = -3, ball_velo_y = 1; 
    int ball_min_accel = 0, ball_max_accel = 2;  

    int controller_min_y = 0, controller_max_y = 650 - controller_length_y;
    int ball_min_y = 0, ball_max_y = 650;  

    public void execute()
    {
        createSprite("controller_1", "paddle.png");
        createSprite("controller_2", "paddle.png"); 
        createSprite("ball", "ball.png"); 

        while(true)
        {
            sleep(5);

            controller_1 = getControllerStatus(controller_1, 'W', 'S', ' ');
            controller_2 = getControllerStatus(controller_2, 'I', 'K', ' ');

            if(controller_1 == 'W' && controller_1_y > controller_min_y) // move the 1st contoller up/down in a given range
            {
                controller_1_y = controller_1_y - controller_velo_y;
            }
            else if(controller_1 == 'S' && controller_1_y < controller_max_y)
            {
                controller_1_y = controller_1_y + controller_velo_y;
            }

            if(controller_2 == 'I' && controller_2_y > controller_min_y) // move the 2nd controller up/down in a given range
            {
                controller_2_y = controller_2_y - controller_velo_y;
            }
            else if(controller_2 == 'K' && controller_2_y < controller_max_y)
            {
                controller_2_y = controller_2_y + controller_velo_y;
            }  

            if(ball_x <= controller_1_x + controller_length_x 
            && controller_1_y < (ball_y + (ball_diameter/2)) && (controller_1_y + controller_length_y) > ball_y 
            && (controller_1_x - ball_x) < 5) // change the ball's horizontal velocity given where the 1st controlled paddle is (left side of the screen)
            {
                ball_velo_x = ball_velo_x * -1;
                ball_velo_y = -(ball_velo_y + getRandomAccel(ball_min_accel, ball_max_accel));
            }
            
            if(ball_x >= controller_2_x - ball_diameter && controller_2_y < (ball_y + (ball_diameter/2)) && (controller_2_y + controller_length_y) > ball_y 
            && (ball_x - controller_2_x + ball_diameter) < 5) // change the ball's horizontal velocity given where the 2nd controlled paddle is (right side of the screen)
            {
                ball_velo_x = ball_velo_x * -1; 
                ball_velo_y = ball_velo_y + getRandomAccel(ball_min_accel, ball_max_accel);
            }

            if(ball_y < ball_min_y || ball_y > ball_max_y) // Allow the ball to bounce off the top/bottom of the screen (change the ball's vertical velocity)
            {
                ball_velo_y = ball_velo_y * -1;
            }

            // Move the ball given its current position and its velocity (change its coordinates)
            ball_x = ball_x + ball_velo_x; 
            ball_y = ball_y + ball_velo_y;
            
            // Update the positions of the sprites on screen depending on their coordinates
            setSpritePose("controller_1", controller_1_x, controller_1_y, 0);
            setSpritePose("controller_2", controller_2_x, controller_2_y, 0);
            setSpritePose("ball", ball_x, ball_y, 0); 
        }
    }
}
