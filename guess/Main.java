import javax.swing.JFrame;
import java.awt.Color ; 

public class Main { 
    public static void main(String[] args) {
        Lvl lvl = new Lvl() ;
        JFrame Window_screen ; 
        Window_screen = new JFrame();
        Window_screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window_screen.setResizable(false);
        Window_screen.setTitle("THE FATE");
        Window_screen.setBackground(Color.black);
        Window_screen.add(lvl);
        Window_screen.setVisible(true);
        Window_screen.pack();
        lvl.updateThread();   
    }
}