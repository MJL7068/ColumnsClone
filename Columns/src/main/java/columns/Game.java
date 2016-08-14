package columns;

import columns.gui.Updateable;
import columns.piece.Block;
import columns.piece.Piece;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Game extends Timer implements ActionListener {

    private int length;
    private int side;

    private Piece piece;
    private Updateable dboard;

    private int[][] squares;

    public Game(int length, int side) {
        super(1000, null);

        this.length = length;
        this.side = side;
        this.squares = new int[length][12];

        this.piece = new Piece();

        for (Block block : piece.getBlocks()) {
            squares[block.getY() / side][block.getX() / side] = block.getColor();
        }

        addActionListener(this);
    }

    public void setUpdateable(Updateable updateable) {
        this.dboard = updateable;
    }

    public int getLength() {
        return length;
    }

    public int getSide() {
        return side;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int[][] getSquares() {
        return squares;
    }

    public int getSquare(int x, int y) {
        return squares[y][x];
    }

    public void movePiece(int x, int y) {
        if (isEmpty(piece.getX() + x, piece.getY() + y) && (piece.getY() + y) < (24 * side) && (piece.getX() + x) < (side * 12) && (piece.getX() + x) >= 0) {
            for (Block block : piece.getBlocks()) {
                squares[block.getY() / side][block.getX() / side] = 0;
            }
            piece.movePiece(x, y);
            for (Block block : piece.getBlocks()) {
                squares[block.getY() / side][block.getX() / side] = block.getColor();
            }
        }
    }

    public void switchPieceColors() {
        piece.switchColors();
        for (Block block : piece.getBlocks()) {
            squares[block.getY() / side][block.getX() / side] = block.getColor();
        }
        updateBoard();
    }

    public void removePieces() {
        ArrayList<String> piecesToRemove = new ArrayList<>();
        int countX = 0;
        int countY = 0;
        int countD = 0;
        int countD2 = 0;

        // Remove diagonally
        for (int y1 = 2; y1 < (squares.length); y1++) {
            int x1 = 0;
            int y2 = y1;

            int x2 = squares[0].length - 1;
            int y3 = y1;

            while (x1 < squares[0].length - 1 && y2 > 0) {
                if (squares[y2][x1] != 0 && squares[y2 - 1][x1 + 1] == squares[y2][x1]) {
                    countD++;
                } else if (countD >= 2) {
                    int h = countD;

                    while (h >= 0) {
                        piecesToRemove.add((y2 + h) + ":" + (x1 - h));
                        h--;
                    }
                    countD = 0;
                } else {
                    countD = 0;
                }

                x1++;
                y2--;
            }

            while (x2 >= 0 && y3 >= 0) {
                if (squares[y3][x2] != 0 && squares[y3 - 1][x2 - 1] == squares[y3][x2]) {
                    countD2++;
                } else if (countD2 >= 2) {
                    int h = countD2;

                    while (h >= 0) {
                        piecesToRemove.add((y3 + h) + ":" + (x2 + h));
                        h--;
                    }
                    countD2 = 0;
                } else {
                    countD2 = 0;
                }

                x2--;
                y3--;
            }
        }

        for (int x1 = 1; x1 < squares[0].length; x1++) {
            int y2 = squares.length - 1;
            int x2 = x1;

            while (x2 < squares[0].length - 1 && y2 > 0) {
                if (squares[y2][x2] != 0 && squares[y2 - 1][x2 + 1] == squares[y2][x2]) {
                    countD++;
                } else if (countD >= 2) {
                    int h = countD;

                    while (h >= 0) {
                        piecesToRemove.add((y2 + h) + ":" + (x2 - h));
                        h--;
                    }
                    countD = 0;
                } else {
                    countD = 0;
                }

                x2++;
                y2--;
            }
        }

        for (int x1 = squares[0].length - 2; x1 > 0; x1--) {
            int y2 = squares.length - 1;
            int x2 = x1;
            countD = 0;

            while (x2 > 0 && y2 >= 0) {
                if (squares[y2][x2] != 0 && squares[y2 - 1][x2 - 1] == squares[y2][x2]) {
                    countD++;
                    System.out.println("count " + countD);
                } else if (countD >= 2) {
                    int h = countD;

                    while (h >= 0) {
                        System.out.println("remove " + (y2 + h) + ":" + (x2 + h));
                        piecesToRemove.add((y2 + h) + ":" + (x2 + h));
                        h--;
                    }
                    countD = 0;
                } else {
                    countD = 0;
                }

                x2--;
                y2--;
            }
        }

        // Remove vertically
        for (int i = squares.length - 1; i >= 0; i--) {
            if (i == squares.length - 1) {
                for (int x = 0; x < squares[i].length; x++) {
                    for (int y = i; y > 0; y--) {
                        if (squares[y][x] != 0 && squares[y - 1][x] == squares[y][x]) {
                            countY++;
                        } else if (countY >= 2) {
                            for (int l = countY; l >= 0; l--) {
                                piecesToRemove.add((y + l) + ":" + x);
                            }
                            countY = 0;
                        } else {
                            countY = 0;
                        }
                    }
                }
            }

            // Remove horizontally
            for (int j = 0; j < squares[i].length; j++) {
                if (j < squares[i].length - 1 && squares[i][j] != 0 && squares[i][j + 1] == squares[i][j]) {
                    countX++;
                } else if (countX >= 2) {
                    for (int k = countX; k >= 0; k--) {
                        piecesToRemove.add(i + ":" + (j - k));
                    }
                    countX = 0;
                } else {
                    countX = 0;
                }

            }
            countX = 0;
        }

        removePiecesFromTheArray(piecesToRemove);
    }

    public void removePiecesFromTheArray(ArrayList<String> pieces) {
        for (String piece : pieces) {
            String[] coords = piece.split(":");
            int y = Integer.parseInt(coords[0]);
            int x = Integer.parseInt(coords[1]);
            squares[y][x] = 0;
        }

        if (pieces.size() > 0) {
            dropPieces();
        }
    }

    public void dropPieces() {
        for (int i = 22; i >= 0; i--) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j] != 0 && squares[i + 1][j] == 0) {
                    int k = 1;
                    while ((i + k) <= 23) {
                        if (squares[i + k][j] == 0) {
                            squares[i + k][j] = squares[i + k - 1][j];
                            squares[i + k - 1][j] = 0;
                        } else {
                            break;
                        }
                        k++;
                    }
                }
            }
        }

        removePieces();
    }

    public void generateNewPiece() {
        this.piece = new Piece();
    }

    public boolean isEmpty(int x, int y) {
        if (x > 240 || x < 0) {
            return false;
        }

        if (getSquare(x / 20, y / 20) == 0) {
            return true;
        }

        return false;
    }

    public void updateBoard() {
        dboard.update();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ((piece.getY()) >= (460) || (!isEmpty(piece.getX(), piece.getY() + side))) {
            removePieces();

            dropPieces();

            generateNewPiece();
        }

        movePiece(0, side);
        updateBoard();
    }

}
