import java.awt.Color;
import java.awt.Graphics ;
import java.awt.image.BufferedImage ;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Menu {

    public BufferedImage button1 , button2 ;
    Rect[] rect = new Rect[2];   
    public Rect dest  = new Rect(0,0);
    public GameState game_state = GameState.Pause ; 
    private final int margin = 5 ;  
    private int width = 0  ;
    private int height = 0  ;
    private int index = 0 ; 

    Menu() { 
        try
        { 
            this.button1 = ImageIO.read(getClass().getResourceAsStream("/assets/b1_play.png"));
            this.button2 = ImageIO.read(getClass().getResourceAsStream("/assets/b2_cr.png"));

        } catch (IOException e) {
            
        }
        rect[0] = new Rect(50 , 50);
        rect[1] = new Rect(50 , 150);
        dest.x = rect[0].x - this.margin ;
        dest.y = rect[0].y - this.margin ; 
        this.width = this.button1.getWidth() + 2*this.margin;
        this.height = this.button1.getHeight() + 2*this.margin;

    }

    public void update(boolean move_up , boolean move_down)
    { 
        if (move_up && this.index != 0 )    this.index-- ; 
        if (move_down && this.index != 1)   this.index++ ;

        dest.y = rect[this.index].y - this.margin ; 
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(this.dest.x, this.dest.y, this.width, this.height);
        g.drawImage(this.button1, this.rect[0].x  , this.rect[0].y , null);
        g.drawImage(this.button2, this.rect[1].x  , this.rect[1].y , null);
        
    }
    
}
