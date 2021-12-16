These are all of the functions that are available for you to use when creating your app. You can draw geometry and text, 
create and use sprites, interface the keys on the keyboard, use sleep() to temporarily pause the program for a certain amount
of milliseconds, change the color of the window's background (default color is black), and play audio files. 

AVAILABLE FUNCTIONS:
    
    void sleep(int millis)

    void drawLine(Color color, int x1, int y1, int x2, int y2)

    void drawRectangle(Color color, int x, int y, int width, int height)

    void drawRoundedRectangle(Color color, int x, int y, int width, int height, int arc_width, int arc_height)

    void drawOval(Color color, int x, int y, int width, int height)

    void drawArc(Color color, int x, int y, int width, int height, int start_angle, int arc_angle)

    void drawPolygon(Color color, int[] x_points, int[] y_points, int amount_of_vertices)

    void drawText(Color color, int x, int y, String text)

    void createSprite(String sprite_name, String file_name)

    void deleteSprite(String sprite_name)

    void setSpriteImage(String sprite_name, String file_name)

    void toggleSpriteVisibility(String sprite_name, boolean visible)

    void setSpritePose(String sprite_name, int x, int y, int degrees)

    int getSpriteImageWidth(String sprite_name)

    int getSpriteImageHeight(String sprite_name)

    void setWindowBackgroundColor(Color color)

    int getCurrentKeyPressed()

    int getLastKeyReleased()

    void playAudioFile(String file_name)

    void stopAudioFile(String file_name)