package lk.ijse.dep.service;

public interface Board {
     int NUM_OF_ROWS=5;
     int NUM_OF_COLS=6;

     BoardUI getBoardUi();
     int findNextAvailableSpot(int col);
     boolean isLeagalMove(int col);
     boolean existLeagalMoves();
     void updateMove(int col,Piece move);
     Winner findWinner();
     void updateMove(int x,int y,Piece move);
     Piece[][] getPieces();
}
