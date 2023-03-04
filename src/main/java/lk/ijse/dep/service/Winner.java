package lk.ijse.dep.service;

public class Winner {
    private Piece winningPiece;

    @Override
    public String toString() {
        return "Winner{" +
                "winningPiece=" + winningPiece +
                ", col1=" + col1 +
                ", row1=" + row1 +
                ", col2=" + col2 +
                ", row2=" + row2 +
                '}';
    }

    private int row1;
    private int col2;
    private int row2;
    private int col1;

    public Winner(Piece winningPiece) {
        //System.out.println("winning piece :"+winningPiece);
        this.winningPiece = winningPiece;
        row1=-1;
        row2=-1;
        col1=-1;
        col2=-1;
    }

    public Winner(Piece winningPiece,int col1,int row1,int col2,int row2){
//        System.out.println("Winner :"+winningPiece);
//        System.out.println("col1 "+col1);
//        System.out.println("col2 "+col2);
//        System.out.println("row1 "+row1);
//        System.out.println("row2 "+row2);
        this.winningPiece=winningPiece;
        this.col2=col2;
        this.col1=col1;
        this.row1=row1;
        this.row2=row2;
    }

    public int getCol1() {
        return col1;
    }

    public int getRow1() {
        return row1;
    }

    public int getCol2() {
        return col2;
    }

    public int getRow2() {
        return row2;
    }

    public Piece getWinningPiece() {
        return winningPiece;
    }

}
