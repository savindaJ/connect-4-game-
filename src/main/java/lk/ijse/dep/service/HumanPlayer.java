package lk.ijse.dep.service;

public class HumanPlayer extends Player {
    Winner winner;
    public HumanPlayer(Board newBoard) {super(newBoard);}
    @Override
    public void movePiece(int indexOf) {
        boolean human=board.isLeagalMove(indexOf);
        if(human){
            board.updateMove(indexOf,Piece.BLUE);
            board.getBoardUi().update(indexOf, true);
            winner = board.findWinner();
            if (board.findWinner().getWinningPiece()!=Piece.EMPTY){
                board.getBoardUi().notifyWinner(winner);
            }
            if(!board.existLeagalMoves()) {
                board.getBoardUi().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}