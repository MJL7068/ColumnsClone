package columns.piece;
import java.util.Random;

public class Piece {

    private Block[] blocks;

    public Piece() {
        this.blocks = new Block[3];

        blocks[0] = new Block(6 * 20, 2 * 20, getRandomColor());
        blocks[1] = new Block(6 * 20, 20, getRandomColor());
        blocks[2] = new Block(6 * 20, 0, getRandomColor());
    }
    
    public int getRandomColor() {
        return new Random().nextInt(4) + 1;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void movePiece(int dx, int dy) {
        for (Block block : blocks) {
            block.setX(dx);
            block.setY(dy);
        }
    }
    
    public void switchColors() {
        int tmp = blocks[1].getColor();
        
        blocks[1].setColor(blocks[0].getColor());
        blocks[0].setColor(blocks[2].getColor());
        blocks[2].setColor(tmp);
    }

    public int getY() {
        int y = blocks[0].getY();
        for (int i = 1; i < blocks.length; i++) {
            if (blocks[i].getY() > y) {
                y = blocks[i].getY();
            }
        }

        return y;
    }

    public int getX() {
        return blocks[0].getX();
    }
}
