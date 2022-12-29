import java.awt.event.KeyEvent ; 
import java.awt.event.KeyListener ;

public class KeyUsed implements KeyListener {
 
    public  boolean comparision = false , move_up = true , move_down = false  ; 
    public StringBuilder string_input = new StringBuilder();
    public GameState gameState = GameState.Pause ; 

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode() ; 
        if (code >= KeyEvent.VK_0 && code <= KeyEvent.VK_9 && gameState == GameState.Play )   
        {
            this.string_input.append(e.getKeyChar());
        }
        switch (code)
        {
            case KeyEvent.VK_W : case KeyEvent.VK_UP :
                move_up = true ; move_down = false; 
                break ; 

            case KeyEvent.VK_S : case KeyEvent.VK_DOWN :
                move_down = true ; move_up = false ; 
                break; 

            case KeyEvent.VK_ENTER :
            
                if(gameState == GameState.Play ) this.comparision = true ;
                if(move_up && gameState != GameState.Play)     gameState = GameState.Play ; 
                if(move_down && gameState != GameState.Play)   gameState = GameState.Credits ;     
                break;

            case KeyEvent.VK_BACK_SPACE :    
                this.string_input.delete(0, this.string_input.length());
                break;
            
            case KeyEvent.VK_ESCAPE :
                this.gameState = GameState.Pause; 
                break; 
        }     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode() ; 

        switch (code)
        {
            case KeyEvent.VK_ENTER:
                this.comparision = false ;
                break;
        }
              
    }
    
}