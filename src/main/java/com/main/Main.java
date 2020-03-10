package com.main;

import com.tictac.TicTac;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String turn ="X";
        String move = "";
        TicTac ticTac = new TicTac();
        ticTac.display_instruct();
        ticTac.pieces();
        List<String> board = ticTac.new_board();
        ticTac.display_board(board);
        while (ticTac.winner(board).equals("NONE")){
            if (turn.equals(ticTac.human)){
                move = ticTac.human_move(board);
                board.remove(Integer.parseInt(move));
                board.add(Integer.parseInt(move),ticTac.human);
            }
            else {
                move = ticTac.computer_move(board);
                System.out.println(move);
                board.remove(Integer.parseInt(move));
                board.add(Integer.parseInt(move), ticTac.computer);
            }
            ticTac.display_board(board);
            turn = ticTac.next_turn(turn);
        }
        String the_winner = ticTac.winner(board);
        ticTac.congratulation_winner(the_winner);
    }
}
