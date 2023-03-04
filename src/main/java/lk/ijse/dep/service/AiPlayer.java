package lk.ijse.dep.service;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AiPlayer extends Player {
    Winner winner;
    int col;
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int indexOf) {
        //col=bestMove();
        col=findBestCol();
        System.out.println("ai thinking  :"+col);
        //try {Thread.sleep(100);} catch (InterruptedException ignored) {}
        board.updateMove(col,Piece.GREEN);
        board.getBoardUi().update(col,false);
        winner=board.findWinner();
        if (board.findWinner().getWinningPiece()!=Piece.EMPTY){
            board.getBoardUi().notifyWinner(winner);
        }
        if(!board.existLeagalMoves()) {
            board.getBoardUi().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
//============================================================================================================================
    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {
            return -1;
        } else if (board.existLeagalMoves() && depth <= 5) {
            int heuristicVal;
            if (maximizingPlayer) {
                int maxEvA = -1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLeagalMove(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.GREEN);
                        heuristicVal = minimax(depth + 1, false);
                        maxEvA = max(maxEvA,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return maxEvA;
                        }
                    }
                }
            } else {
                int minEva= 1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLeagalMove(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.BLUE);
                        heuristicVal = minimax(depth + 1, true);
                        minEva= min(minEva,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return minEva;
                        }
                    }
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    private int bestMove() {

        boolean isUserWinning = false;
        int winningCol = 0;

        for (int i = 0; i < 6; ++i) {
            if (board.isLeagalMove(i)) {
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i, Piece.GREEN);
                int heuristicVal = minimax(0, false);
                board.updateMove(i, row, Piece.EMPTY);
                System.out.println("value"+heuristicVal);
                if (heuristicVal == 1) {
                    System.out.println("Ai ddd");
                    return i;
                }else if (heuristicVal == -1) {
                    isUserWinning = true;
                    System.out.println("Winning");
                } else {
                    winningCol = i;
                    System.out.println("tiedColumns = "+winningCol);
                }
            }
        }
        System.out.println();

        if (isUserWinning && board.isLeagalMove(winningCol)) {
            System.out.println("winning"+winningCol);
            return winningCol;

        } else {
            int j;
            do {
                j = (int) ((Math.random() * ((5 - 0) + 1)) + 0);
            } while (!board.isLeagalMove(j));
            return j;
        }
    }
//==============================================================================================================================
    private int findBestCol(){ // my AI method

        Piece [][] tempPiece= board.getPieces();
        int column;

        for (int i = 0; i < 5; i++) {

            if(tempPiece[0][i]==tempPiece[1][i] && tempPiece[1][i]==tempPiece[2][i] && tempPiece[0][i]==Piece.GREEN){
                if(tempPiece[3][i]==Piece.EMPTY){
                    return 3;
                }
            }
            else if (tempPiece[0][i]==tempPiece[1][i] && tempPiece[1][i]==tempPiece[2][i] && tempPiece[0][i]==Piece.BLUE){
                if(tempPiece[3][i]==Piece.EMPTY){
                    return 3;
                }
            }
            else if(tempPiece[1][i]==tempPiece[2][i] && tempPiece[2][i]==tempPiece[3][i] && tempPiece[1][i]==Piece.GREEN){
                if(tempPiece[4][i]==Piece.EMPTY){
                    return 4;
                }
            }
            else if(tempPiece[1][i]==tempPiece[2][i] && tempPiece[2][i]==tempPiece[3][i] && tempPiece[1][i]==Piece.BLUE){
                if(tempPiece[4][i]==Piece.EMPTY){
                    return 4;
                }
            }
            else if(tempPiece[2][i]==tempPiece[3][i] && tempPiece[3][i]==tempPiece[4][i] && tempPiece[2][i]==Piece.GREEN){
                if(tempPiece[5][i]==Piece.EMPTY){
                    return 5;
                }
            }
            else if (tempPiece[2][i]==tempPiece[3][i] && tempPiece[3][i]==tempPiece[4][i] && tempPiece[2][i]==Piece.BLUE){
                if(tempPiece[5][i]==Piece.EMPTY){
                    return 5;
                }
            }
            else if(tempPiece[3][i]==tempPiece[2][i] && tempPiece[2][i]==tempPiece[1][i] && tempPiece[3][i]==Piece.GREEN){
                System.out.println("come green 0");
                if(tempPiece[0][i]==Piece.EMPTY){
                    return 0;
                }
            }
            else if(tempPiece[3][i]==tempPiece[2][i] && tempPiece[2][i]==tempPiece[1][i] && tempPiece[3][i]==Piece.BLUE){
                System.out.println("come blue 0");
                if(tempPiece[0][i]==Piece.EMPTY){
                    return 0;
                }
            }
            else if(tempPiece[4][i]==tempPiece[3][i] && tempPiece[3][i]==tempPiece[2][i] && tempPiece[3][i]==Piece.GREEN){
                System.out.println("come green 1");
                if(tempPiece[1][i]==Piece.EMPTY){
                    return 1;
                }
            }
            else if(tempPiece[4][i]==tempPiece[3][i] && tempPiece[3][i]==tempPiece[2][i] && tempPiece[3][i]==Piece.BLUE){
                System.out.println("come blue 1");
                if(tempPiece[1][i]==Piece.EMPTY){
                    return 1;
                }
            }
            else if(tempPiece[5][i]==tempPiece[4][i] && tempPiece[4][i]==tempPiece[3][i] && tempPiece[5][i]==Piece.GREEN){
                System.out.println("come green 2");
                if(tempPiece[2][i]==Piece.EMPTY){
                    return 2;
                }
            }
            else if(tempPiece[5][i]==tempPiece[4][i] && tempPiece[4][i]==tempPiece[3][i] && tempPiece[5][i]==Piece.BLUE){
                System.out.println("come blue 2");
                if(tempPiece[2][i]==Piece.EMPTY){
                    return 2;
                }
            }
        }
        for (int i = 0; i < 6; i++) {

            if(tempPiece[i][0]==tempPiece[i][1] && tempPiece[i][1]==tempPiece[i][2] && tempPiece[i][0]==Piece.GREEN){
                if (tempPiece[i][3]==Piece.EMPTY)
                    return i;
            }
            else if(tempPiece[i][0]==tempPiece[i][1] && tempPiece[i][1]==tempPiece[i][2] && tempPiece[i][0]==Piece.BLUE){
                if (tempPiece[i][3]==Piece.EMPTY)
                    return i;
            }
            else if(tempPiece[i][1]==tempPiece[i][2] && tempPiece[i][2]==tempPiece[i][3] && tempPiece[i][1]==Piece.GREEN){
                if (tempPiece[i][4]==Piece.EMPTY)
                    return i;
            }
            else if(tempPiece[i][1]==tempPiece[i][2] && tempPiece[i][2]==tempPiece[i][3] && tempPiece[i][1]==Piece.BLUE){
                if (tempPiece[i][4]==Piece.EMPTY)
                    return i;
            }
        }
        do{
            column=(int)((Math.random() * (6-0))+0);
        }while (!board.isLeagalMove(column));
        return column;
    }
//============================================================================================================================
    /*private int colFind() { // find best col

        Piece[][] p = board.getPieces();
        int col;

        boolean sw = false;

        for (col = 0; col < 6; col++) {

            if (p[col][0] == p[col][1] && p[col][1] == p[col][2] && p[col][0] == Piece.GREEN){
                if(p[col][3] == Piece.EMPTY){
                    sw = true;
                    break;
                }else{
                    break;
                }
            }else if(p[col][1] == p[col][2] && p[col][2] == p[col][3] && p[col][1] == Piece.GREEN){
                if(p[col][4] == Piece.EMPTY){
                    sw = true;
                    break;
                }else{
                    break;
                }
            }

            if (p[col][0] == p[col][1] && p[col][1] == p[col][2] && p[col][0] == Piece.BLUE){
                if(p[col][3] == Piece.EMPTY){
                    sw = true;
                    break;
                }else{
                    break;
                }

            }else if(p[col][1] == p[col][2] && p[col][2] == p[col][3] && p[col][1] == Piece.BLUE){
                if(p[col][4] == Piece.EMPTY){
                    sw = true;
                    break;
                }else{
                    break;
                }
            }

        }

        if(sw == false){

            do{
                col=(int)((Math.random() * (6-0))+0);
            }while (!board.isLeagalMove(col));

            boolean w = false;

            for(int row = 0; row < 5; row++){

                if(p[0][row] == p[1][row] && p[1][row] == p[2][row] && p[0][row] == Piece.GREEN){
                    if(row == 0){
                        if(p[3][row] == Piece.EMPTY){
                            col = 3;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[3][row] == Piece.EMPTY && p[3][row-1] != Piece.EMPTY){
                            col = 3;
                            w = true;
                            break;
                        }
                    }
                }


                if(p[1][row] == p[2][row] && p[2][row] == p[3][row] && p[1][row] == Piece.GREEN){
                    if(row == 0){
                        if(p[4][row] == Piece.EMPTY){
                            col = 4;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[4][row] == Piece.EMPTY && p[4][row-1] != Piece.EMPTY){
                            col = 4;
                            w = true;
                            break;
                        }
                    }
                }


                if(p[2][row] == p[3][row] && p[3][row] == p[4][row] && p[2][row] == Piece.GREEN){
                    if(row == 0){
                        if(p[5][row] == Piece.EMPTY){
                            col = 5;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[5][row] == Piece.EMPTY && p[5][row-1] != Piece.EMPTY){
                            col = 5;
                            w = true;
                            break;
                        }
                    }
                }

                if(p[1][row] == p[2][row] && p[2][row] == p[3][row] && p[1][row] == Piece.GREEN){
                    if(row == 0){
                        if(p[0][row] == Piece.EMPTY){
                            col = 0;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[0][row] == Piece.EMPTY && p[0][row-1] != Piece.EMPTY){
                            col = 0;
                            w = true;
                            break;
                        }
                    }
                }

                if(p[2][row] == p[3][row] && p[3][row] == p[4][row] && p[2][row] == Piece.GREEN){
                    if(row == 0){
                        if(p[1][row] == Piece.EMPTY){
                            col = 1;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[1][row] == Piece.EMPTY && p[1][row-1] != Piece.EMPTY){
                            col = 1;
                            w = true;
                            break;
                        }
                    }
                }

                if(p[3][row] == p[4][row] && p[4][row] == p[5][row] && p[3][row] == Piece.GREEN){
                    if(row == 0){
                        if(p[2][row] == Piece.EMPTY){
                            col = 2;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[2][row] == Piece.EMPTY && p[2][row-1] != Piece.EMPTY){
                            col = 2;
                            w = true;
                            break;
                        }
                    }
                }









                if(p[0][row] == p[1][row] && p[1][row] == p[2][row] && p[0][row] == Piece.BLUE){
                    if(row == 0){
                        if(p[3][row] == Piece.EMPTY){
                            col = 3;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[3][row] == Piece.EMPTY && p[3][row-1] != Piece.EMPTY){
                            col = 3;
                            w = true;
                            break;
                        }
                    }
                }


                if(p[1][row] == p[2][row] && p[2][row] == p[3][row] && p[1][row] == Piece.BLUE){
                    if(row == 0){
                        if(p[4][row] == Piece.EMPTY){
                            col = 4;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[4][row] == Piece.EMPTY && p[4][row-1] != Piece.EMPTY){
                            col = 4;
                            w = true;
                            break;
                        }
                    }
                }


                if(p[2][row] == p[3][row] && p[3][row] == p[4][row] && p[2][row] == Piece.BLUE){
                    if(row == 0){
                        if(p[5][row] == Piece.EMPTY){
                            col = 5;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[5][row] == Piece.EMPTY && p[5][row-1] != Piece.EMPTY){
                            col = 5;
                            w = true;
                            break;
                        }
                    }
                }






                if(p[1][row] == p[2][row] && p[2][row] == p[3][row] && p[1][row] == Piece.BLUE){
                    if(row == 0){
                        if(p[0][row] == Piece.EMPTY){
                            col = 0;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[0][row] == Piece.EMPTY && p[0][row-1] != Piece.EMPTY){
                            col = 0;
                            w = true;
                            break;
                        }
                    }
                }

                if(p[2][row] == p[3][row] && p[3][row] == p[4][row] && p[2][row] == Piece.BLUE){
                    if(row == 0){
                        if(p[1][row] == Piece.EMPTY){
                            col = 1;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[1][row] == Piece.EMPTY && p[1][row-1] != Piece.EMPTY){
                            col = 1;
                            w = true;
                            break;
                        }
                    }
                }

                if(p[3][row] == p[4][row] && p[4][row] == p[5][row] && p[3][row] == Piece.BLUE){
                    if(row == 0){
                        if(p[2][row] == Piece.EMPTY){
                            col = 2;
                            w = true;
                            break;
                        }
                    }else{
                        if(p[2][row] == Piece.EMPTY && p[2][row-1] != Piece.EMPTY){
                            col = 2;
                            w = true;
                            break;
                        }
                    }
                }
            }
            if(w == false){
                do{
                    col=(int)((Math.random() * (6-0))+0);
                }while (!board.isLeagalMove(col));
            }
        }
        return (col);
    }*/


}
