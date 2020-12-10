package com.kodilla.tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton button00;
    private JButton button01;
    private JButton button02;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    Main main = new Main();

    public GUI() {
        super("Kółko i krzyżyk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100, 400, 400);

        panel = new JPanel();
        panel.setLayout(null);
        add (panel);

        GridLayout layout1 = new GridLayout(3,3);
        panel.setLayout(layout1);

        button00 = new JButton(" ");
        button00.addActionListener(this);
        panel.add(button00);

        button01 = new JButton(" ");
        button01.addActionListener(this);
        panel.add(button01);

        button02 = new JButton(" ");
        button02.addActionListener(this);
        panel.add(button02);

        button10 = new JButton(" ");
        button10.addActionListener(this);
        panel.add(button10);

        button11 = new JButton(" ");
        button11.addActionListener(this);
        panel.add(button11);

        button12 = new JButton(" ");
        button12.addActionListener(this);
        panel.add(button12);

        button20 = new JButton(" ");
        button20.addActionListener(this);
        panel.add(button20);

        button21 = new JButton(" ");
        button21.addActionListener(this);
        panel.add(button21);

        button22 = new JButton(" ");
        button22.addActionListener(this);
        panel.add(button22);


    }


    private char activePlayer = 'X';
    private Board board1 = new Board();
    private char[][] board = new char[3][3];
    private boolean win = false;

    public void runGame(int Row, int Column) {
        board[Row][Column] = activePlayer;
        board1.printBoard(board);
        win = board1.winChecker(board, activePlayer);
        if(win){
            this.Winner();
        }
        activePlayer = board1.activePlayerChange(activePlayer);
    }

    @Override
    public void actionPerformed(ActionEvent evt){

        if(evt.getSource()==button00) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button00.setText(String.valueOf(activePlayer));
                this.runGame(0, 0);
            }
        }

        if(evt.getSource()==button01) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button01.setText(String.valueOf(activePlayer));
                this.runGame(0, 1);
            }
        }

        if(evt.getSource()==button02) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button02.setText(String.valueOf(activePlayer));
                this.runGame(0, 2);
            }
        }
        if(evt.getSource()==button10) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button10.setText(String.valueOf(activePlayer));
                this.runGame(1, 0);
            }
        }

        if(evt.getSource()==button11) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button11.setText(String.valueOf(activePlayer));
                this.runGame(1, 1);
            }
        }

        if(evt.getSource()==button12) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button12.setText(String.valueOf(activePlayer));
                this.runGame(1, 2);
            }
        }

        if(evt.getSource()==button20) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button20.setText(String.valueOf(activePlayer));
                this.runGame(2, 0);
            }
        }

        if(evt.getSource()==button21) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button21.setText(String.valueOf(activePlayer));
                this.runGame(2, 1);
            }
        }

        if(evt.getSource()==button22) {
            if (((JButton)evt.getSource()).getText().equals(" ")) {
                button22.setText(String.valueOf(activePlayer));
                this.runGame(2, 2);
            }
        }
    }


    public void Winner (){
        JOptionPane.showMessageDialog(null, activePlayer + " wygrał!");
        System.out.println(activePlayer + " wygrał!");
    }
}

