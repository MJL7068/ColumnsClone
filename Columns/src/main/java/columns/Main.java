package columns;

import columns.gui.GraphicalInterface;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(26, 20);
        GraphicalInterface gui = new GraphicalInterface(game);
        
        SwingUtilities.invokeLater(gui);
        
        while (gui.getDrawBoard() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        
        game.setUpdateable(gui.getDrawBoard());
        
        game.start();
    }
    
}
