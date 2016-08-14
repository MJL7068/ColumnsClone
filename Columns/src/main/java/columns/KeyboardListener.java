package columns;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private Game game;
    
    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            game.movePiece(-game.getSide(), 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            game.movePiece(game.getSide(), 0);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            game.switchPieceColors();            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            game.movePiece(0, game.getSide());
        }
        
        game.updateBoard();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
