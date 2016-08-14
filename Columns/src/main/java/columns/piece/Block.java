package columns.piece;

public class Block {
    private int x;
    private int y;
    private int color;

    public Block(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int dx) {
        x += dx;
    }

    public void setY(int dy) {
        y += dy;
    }

    public void setColor(int color) {
        this.color = color;
    }
      
}
