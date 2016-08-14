package columns.gui;

import columns.Game;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawBoard extends JPanel implements Updateable {

    private int side;

    private Game game;
    private Color[] colors;

    public DrawBoard(Game game) {
        this.side = 20;

        this.game = game;
        this.colors = new Color[]{Color.white, Color.red, Color.yellow, Color.blue, Color.green};
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

//        for (Block block : game.getPiece().getBlocks()) {
//            graphics.setColor(colors[block.getColor()]);
//
//            graphics.fill3DRect(block.getX(), block.getY(), side, side, true);
//        }
//
//        for (Piece piece : game.getOldPieces()) {
//            for (Block block : piece.getBlocks()) {
//                graphics.setColor(colors[block.getColor()]);
//
//                graphics.fill3DRect(block.getX(), block.getY(), side, side, true);
//            }
//        }
        this.setBackground(Color.white);

        for (int i = 0; i < game.getSquares().length; i++) {
            for (int j = 0; j < game.getSquares()[i].length; j++) {
                int number = game.getSquare(j, i);

                if (number != 0) {
                    graphics.setColor(colors[number]);
                    graphics.fill3DRect(j * 20, i * 20, 20, 20, true);
                }
                
//                graphics.setColor(Color.black);
//                graphics.drawString(""+number, j*20 + 10, i*20 + 10);
            }
        }

    }

    @Override
    public void update() {
        this.repaint();
    }

}
