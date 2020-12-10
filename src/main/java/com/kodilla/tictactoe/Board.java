package com.kodilla.tictactoe;

public class Board {
    private int row;
    private int column;

    public void printBoard(char[][] board){
        int rozmiar = board.length;

        System.out.print("\t");
        for (int i=0; i<rozmiar; i++){ //drukuje nagłówek kolumn
            System.out.print(i+"\t");
        }
        System.out.println();
        for (int row=0; row<rozmiar;row++){
            System.out.print(row+":\t");
            for (int column=0; column<rozmiar; column++){
                System.out.print(board[row][column]+"\t");
            }
            System.out.println();
        }
    }
    public boolean isEmpty (int row, int column, char[][] board){
        if (board[row][column]==0){
            return true;
        }
        else{
            return false;
        }
    }
    public char activePlayerChange (char activePlayer){
        if (activePlayer == 'X') {
            return 'O'; //                              <------zmienić po testach na 'O'
        } else {
            return 'X';
        }
    }
    public boolean isAGoodRowColumn (char[][] board, int playerNumber){
        int rozmiar= board.length-1;
        if (playerNumber>rozmiar || playerNumber<0){
            return false;
        } else {
            return true;
        }
    }


    //sprawdzanie czy ktoś wygrał
    public boolean rowWin(char[][] board, char activePlayer){
        int rozmiar= board.length;
        for (int row=0; row<rozmiar;row++){
            boolean win = true;
            for (int column=0; column<rozmiar; column++) {
                if (board[row][column]!=activePlayer){
                    win=false;
                    break;
                }
            }
            if (win){
                return true;
            }
        }
        return false;
    }
    public boolean columnWin(char[][] board, char activePlayer){
        int rozmiar= board.length;
        for (int column=0; column<rozmiar;column++){
            boolean win = true;
            for (int row=0; row<rozmiar; row++) {
                if (board[row][column]!=activePlayer){
                    win=false;
                    break;
                }
            }
            if (win){
                return true;
            }
        }
        return false;
    }
    public boolean diagonalWinOne (char[][] board, char activePlayer){
        int rozmiar= board.length -1;
        boolean win=true;
        for (int i=0; i<=rozmiar;i++) {
            if (board[i][rozmiar-i] != activePlayer) {
                win = false;
                break;
            }
        }
        if (win){
            return true;
        }
        return false;
    }
    public boolean diagonalWinTwo (char[][] board, char activePlayer){
        int rozmiar= board.length-1;
        boolean win=true;
        for (int i=0; i<=rozmiar;i++) {
            if (board[i][i] != activePlayer) {
                win = false;
                break;
            }
        }
        if (win){
            return true;
        }
        return false;
    }
    public boolean winChecker(char[][] board, char activePlayer) {
        if (this.rowWin(board,activePlayer)==true || this.columnWin(board,activePlayer)||this.diagonalWinOne(board,activePlayer)||this.diagonalWinTwo(board,activePlayer)){
            return true;
        } else {
            return false;
        }
    }

    // gettery
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }


    // konstruktory podzielone na dwa
    public void setRow (int Row){
        this.row=Row;
    }
    public void setColumn (int Column){
        this.column=Column;
    }
}
