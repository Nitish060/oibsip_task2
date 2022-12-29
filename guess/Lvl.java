import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.awt.Graphics ;
import java.awt.image.BufferedImage ;
import javax.imageio.ImageIO;

public class Lvl extends JPanel implements Runnable { 

    settings util = new settings();

    Thread currenThread ;
    public BufferedImage back_ground , credits ,  sq1 , sq2 , m1 , m2 ;
    int  mess_index = 0 , sq_index = 0;
    KeyUsed key = new KeyUsed();
    Menu menu = new Menu(); 
    Sound sound = new Sound();

    Random rand = new Random();
    int random_num = rand.nextInt(20);
    private int round = 0 , life = 5; 

    Font font = new Font("Dialog" , Font.PLAIN , 30); 
    String que = "Enter the num " ;
    String range = "0 to " + (random_num + rand.nextInt(6)) ; 
    public StringBuilder string_input  = new StringBuilder();   

    Lvl()
    {
        this.setPreferredSize(new Dimension(this.util.WIDTH , this.util.HEIGHT ));
        this.setBackground(Color.GRAY);
        this.addKeyListener(key);
        this.setFocusable(true);

        // System.out.println(random_num);to print the num

        sound.soundFile(0);
        sound.Play();
        sound.loop();

        try
        {
            this.back_ground = ImageIO.read(getClass().getResourceAsStream("/assets/back_g1.png"));
            this.sq1 = ImageIO.read(getClass().getResourceAsStream("/assets/sq_h_1.png"));
            this.sq2 = ImageIO.read(getClass().getResourceAsStream("/assets/sq_s_1.png")); 
            this.credits = ImageIO.read(getClass().getResourceAsStream("/assets/cr_1.png"));
            this.m1 = ImageIO.read(getClass().getResourceAsStream("/assets/mg_1.png")); 
            this.m2 = ImageIO.read(getClass().getResourceAsStream("/assets/mg_2.png")); 
        } catch (IOException e) {
            
        }

    }
    public void updateThread()
    {
        this.currenThread = new Thread(this);
        this.currenThread.start();    
    }


    public void toCompare()
    {
        if (this.key.comparision)
        {
            int val = Integer.parseInt(this.key.string_input.toString());
            // System.out.println(val);
            if (this.random_num == val )
            {
                sound.soundFile(2);
                sound.Play();  
                random_num =  rand.nextInt(40);
                // System.out.println(random_num); to print thr num  
                this.round ++;  
                que = "Enter the num " ;
                range = (random_num - rand.nextInt(6) )+ " to " + (random_num + rand.nextInt(6)) ;
                this.sq_index = 0  ;
                this.mess_index = 0 ;
            } 
            else if (this.random_num != val )
            { 
                this.life-- ;
                this.sq_index = 1  ;
                this. mess_index = val > this.random_num ? 1 : 2 ; 
                sound.soundFile(1);
                sound.Play();  
            }   
            this.key.comparision = false;          
        }
    }

    public void toCheck()
    {
        if (this.round == 3 || this.life == 0 )
        {
            this.key.gameState = GameState.Credits ; 
            this.round = 0 ; 
            this.life =  5 ;
            this.random_num = rand.nextInt(40);
            range = (random_num - rand.nextInt(7) )+ " to " + (random_num + rand.nextInt(7)) ;
            this.sq_index = 0 ; 
            this.mess_index = 0 ; 
        }
    }
    // update and repaint sections

    private void update()
    {   
        if (this.key.gameState == GameState.Play)
        {
            this.string_input = this.key.string_input ; 
            toCompare();
            toCheck();
        }
        if (this.key.gameState == GameState.Pause)
            menu.update(this.key.move_up , this.key.move_down);   
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.drawImage(this.back_ground, 0, 0 , null);

        if (this.key.gameState == GameState.Pause)
            this.menu.paint(g);

        else if (this.key.gameState == GameState.Play)
        {
            g.setFont(font);
            switch (sq_index) 
            {
                case 0: g.drawImage(this.sq1, 370, 40, null) ; break;
                case 1: g.drawImage(this.sq2, 370, 40, null) ; break;
            }
            switch (mess_index) 
            {
                case 1: g.drawImage(this.m1, 320 , 40, null) ; break;
                case 2: g.drawImage(this.m2, 320 , 40, null) ; break;
            }

            g.drawString( que + " " + range , 50   , 300);
            g.drawString( "Enter the num :  " + this.string_input.toString(), 50,350 );
            g.drawString("ROUND  " + Integer.toString(this.round) + "/3", 50, 50 ); 
            g.drawString("LIFE  " +  Integer.toString(this.life) , 50, 100);
        }   

        else if (this.key.gameState == GameState.Credits)
            g.drawImage(this.credits , 0 , 0 , null);

        g.dispose();
        
    }

    @Override
    public void run()
    {
        this.util.end = System.currentTimeMillis();

        while ( currenThread != null )
        {
            this.util.beg  = System.currentTimeMillis();
            this.util.dt = (this.util.beg - this.util.end) ;
            
            if (this.util.dt >= 1 )
            {
                update();
                repaint();
                this.util.dt-- ; 
            }
            this.util.end = this.util.beg ;        
        }
    }   
}