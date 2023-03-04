package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board {

    Piece[][] piece;
    BoardUI boardUI;
    BoardController boardController;

    public BoardImpl(BoardController boardController) {

        piece = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardController=boardController;
        boardUI=boardController;

        for (int i = 0; i < NUM_OF_COLS; i++){
           for (int j=0; j<NUM_OF_ROWS; j++){
               piece[i][j]=Piece.EMPTY;
           }
        }
    }

    @Override
    public BoardUI getBoardUi() {
        return boardController;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for(int i = 0; i < NUM_OF_ROWS; i++) {
            if(piece[col][i] == Piece.EMPTY){
                return(i);
            }
        }
            return(-1);
    }
    @Override
    public boolean isLeagalMove(int col) {
        int place=findNextAvailableSpot(col);
        if (place==-1){
            return false;
        }
        return (true);
    }
    @Override
    public boolean existLeagalMoves() {
        for (int i=0; i< NUM_OF_COLS; i++){
            for(int j=0; j<NUM_OF_ROWS; j++){
                if(piece[i][j]==Piece.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void updateMove(int col, Piece move) {
        int spot=findNextAvailableSpot(col);

        try{
            piece[col][spot] = move;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("array index out - updateMove");
        }
    }
    @Override
    public Winner findWinner() {
        Piece winningPiece;
        int col1;
        int col2;
        int row1;
        int row2;

        for(int i =0; i< NUM_OF_ROWS; i++){// rows checker....

            if(piece[2][i]==piece[3][i] && piece[3][i]==piece[4][i] && piece[4][i]==piece[5][i]){
                if (piece[2][i]==Piece.BLUE){
                    System.out.println("1 st statement");
                    winningPiece=Piece.BLUE;
                    col1=2;
                    col2=5;
                    row1=i;
                    row2=i;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }
                else if(piece[2][i]==Piece.GREEN){
                    winningPiece=Piece.GREEN;
                    col1=2;
                    col2=5;
                    row1=i;
                    row2=i;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }

            }
            else if(piece[0][i]==piece[1][i]&&piece[1][i]==piece[2][i]&&piece[2][i]==piece[3][i]){
                if (piece[0][i]==Piece.BLUE){
                    System.out.println("2 nd statement");
                    System.out.println(piece[0][i]);
                    winningPiece=Piece.BLUE;
                    col1=0;
                    col2=3;
                    row1=i;
                    row2=i;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }
                else if(piece[0][i]==Piece.GREEN){
                    winningPiece=Piece.GREEN;
                    col1=0;
                    col2=3;
                    row1=i;
                    row2=i;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }

            }
            else if(piece[1][i]==piece[2][i] && piece[2][i]==piece[3][i] && piece[3][i]==piece[4][i]){
                if (piece[1][i]==Piece.BLUE){
                    System.out.println("3 rd statement");
                    winningPiece=Piece.BLUE;
                    col1=1;
                    col2=4;
                    row1=i;
                    row2=i;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }
                else if(piece[1][i]==Piece.GREEN){
                    winningPiece=Piece.GREEN;
                    col1=1;
                    col2=4;
                    row1=i;
                    row2=i;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }

            }
        }
        for (int i = 0; i < NUM_OF_COLS; i++) {// col checker...

            if(piece[i][0]==piece[i][1] && piece[i][1]==piece[i][2] && piece[i][2]==piece[i][3]){
                if(piece[i][0]==Piece.BLUE){
                    System.out.println("4 th statement");
                    winningPiece=Piece.BLUE;
                    col1=i;
                    col2=i;
                    row1=0;
                    row2=3;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }
                else if(piece[i][0]==Piece.GREEN){
                    winningPiece=Piece.GREEN;
                    col1=i;
                    col2=i;
                    row1=0;
                    row2=3;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }
            }
            else if(piece[i][1]==piece[i][2] && piece[i][2]==piece[i][3] && piece[i][3]==piece[i][4]){
                if(piece[i][1]==Piece.BLUE){
                    System.out.println("5 th statement");
                    winningPiece=Piece.BLUE;
                    col1=i;
                    col2=i;
                    row1=1;
                    row2=4;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }
                else if(piece[i][1]==Piece.GREEN){
                    winningPiece=Piece.GREEN;
                    col1=i;
                    col2=i;
                    row1=1;
                    row2=4;
                    return new Winner(winningPiece,col1,row1,col2,row2);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

    @Override
    public void updateMove(int x, int y, Piece move) {
        piece[x][y]=move;
    }

    @Override
    public Piece[][] getPieces() {
        return piece;
    }
}
