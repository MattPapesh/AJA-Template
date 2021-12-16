package lower_level.fundamentals;

public class coordinates 
{
    public int x = 0;
    public int y = 0; 
    public double radians = 0;
    
    public coordinates(int x, int y, int degrees)
    {
        this.x = x;
        this.y = y;
        this.radians = (degrees * Math.PI) / 180; 
    }
}