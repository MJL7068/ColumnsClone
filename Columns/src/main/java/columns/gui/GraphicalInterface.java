package columns.gui;

import columns.Game;
import columns.KeyboardListener;
import columns.gui.DrawBoard;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GraphicalInterface implements Runnable {
    private JFrame frame;
    private DrawBoard dboard;
    private Game game;
    
    private int side;
    
    public GraphicalInterface(Game game) {
        this.game = game;
        
        this.side = game.getSide();
    }

    @Override
    public void run() {
        frame = new JFrame("Columns");                
        
        frame.setPreferredSize(new Dimension(12 * side + side, game.getLength() * side));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());                
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
        this.dboard = new DrawBoard(game);
        container.add(dboard);
        
        frame.addKeyListener(new KeyboardListener(game));
    }
    
    public Updateable getDrawBoard() {
        return this.dboard;
    }
    
}
