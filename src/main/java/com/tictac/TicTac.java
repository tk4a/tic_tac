package com.tictac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTac {

    private String X = "X", O = "O", EMPTY = " ", TIE = "DRAW";
    private int num_squares = 9;
    private List<String> board;
    private List<String> moves = new ArrayList<>();
    private static List<String> best_moves = new ArrayList<>();
    public String computer = "", human = "";

    static {
        best_moves.add("4"); best_moves.add("0"); best_moves.add("2"); best_moves.add("6"); best_moves.add("8");
        best_moves.add("1"); best_moves.add("3"); best_moves.add("5"); best_moves.add("7");
    }
    public void start_game(){
        String turn ="X";
        String move = "";
        display_instruct();
        pieces();
        List<String> board = new_board();
        display_board(board);
        while (winner(board).equals("NONE")){
            if (turn.equals(human)){
                move = human_move(board);
                while (move.equals("error")) {
                    move = human_move(board);
                }
                board.remove(Integer.parseInt(move));
                board.add(Integer.parseInt(move),human);
            }
            else {
                move = computer_move(board);
                System.out.println(move);
                board.remove(Integer.parseInt(move));
                board.add(Integer.parseInt(move), computer);
            }
            display_board(board);
            turn = next_turn(turn);
        }
        String the_winner = winner(board);
        congratulation_winner(the_winner);
        String answer = ask_yes_no("Want to play again?");
        if(answer.equals("yes"))
            start_game();
        else
            System.out.println("End of the game!");
    }

    private void display_instruct(){
        System.out.println("Welcome to the Tic Tac game\nTo make a move, enter number from 0 to 8!");
        System.out.println("\n\t0 | 1 | 2 \n\t---------\n\t3 | 4 | 5 \n\t---------\n\t6 | 7 | 8 ");
    }
    private String ask_yes_no(String question){
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        if (result.equalsIgnoreCase("i"))
            return "i";
        if (result.equalsIgnoreCase("yes"))
            return "yes";
        return "n";
    }
    private int ask_number(){
        System.out.println("Enter number of cell");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }
    public void pieces(){
        String first = ask_yes_no("Who going first?");
        if (first.equals("i")){
            human = "X";
            computer = "O";
        }
        else{
            human = "O";
            computer = "X";
        }

    }
    private List<String> new_board(){
        board = new ArrayList<>();
        for (int i = 0; i < num_squares; i++) {
            board.add(EMPTY);
        }
        return board;
    }
    private void display_board(List<String> board){
        System.out.println("\n" + board.get(0) + " | " + board.get(1) + " | " + board.get(2));
        System.out.println("---------");
        System.out.println(board.get(3) + " | " + board.get(4) + " | " + board.get(5));
        System.out.println("---------");
        System.out.println(board.get(6) + " | " + board.get(7) + " | " + board.get(8));
    }
    private List<String> legal_moves(List<String> board){
        moves = new ArrayList<>();
        for (int i = 0; i < num_squares; i++) {
            if (board.get(i).equals(EMPTY))
                moves.add(Integer.toString(i));
        }
        return moves;
    }
    private String winner(List<String> board){
        String winner = "NONE";
        int[][] ways_to_win =
                       {{0,1,2},
                        {3,4,5},
                        {6,7,8},
                        {0,3,6},
                        {1,4,7},
                        {2,5,8},
                        {0,4,8},
                        {2,4,6}};
        for (int[] i : ways_to_win){
            if (board.get(i[0]).equals(board.get(i[1])) && board.get(i[0]).equals(board.get(i[2])) && !board.get(i[0]).equals(EMPTY))
                winner = board.get(i[0]);
            }
        if (!board.contains(EMPTY))
            return winner = TIE;
        return winner;
        }

    private String human_move(List<String> board){
        List<String> legal = legal_moves(board);
        while (legal != null) {
            int move = ask_number();
            if (move < 0 || move > 8){
                System.out.println("Index out of bound\nTry again!");
                return "error";
            }
            if (!legal.contains(Integer.toString(move))) {
                System.out.println("Field is busy\nTry again!");
                return "error";
            }
            return String.valueOf(move);
        }
        return "none";

    }
    private String move(List<String> board, String who){
        for (String move : legal_moves(board)){
            board.remove(Integer.parseInt(move));
            board.add(Integer.parseInt(move), who);
            if (winner(board).equals(who)){
                System.out.println("i'll choose field number " + move);
                board.remove(Integer.parseInt(move));
                board.add(Integer.parseInt(move), EMPTY);
                return move;
            }
            else {
                board.remove(Integer.parseInt(move));
                board.add(Integer.parseInt(move), EMPTY);
            }
        }
            return "none";
    }

    private String computer_move(List<String> board){

        String move = move(board, computer);
        if (!move.equals("none"))
            return move;
        move = move(board, human);
        if (!move.equals("none"))
            return move;
        for (String iter : best_moves){
            if (legal_moves(board).contains(iter)){
                System.out.println("i'll choose field number " + iter);
                return iter;
            }
        }
        return "none";
    }

    private String next_turn(String turn){
        if (turn.equals("X"))
            return "O";
        else
            return "X";
    }
    private void congratulation_winner(String the_winner){
        if (!the_winner.equals(TIE))
            System.out.println("Congratulations " + the_winner);
        else
            System.out.println("DRAW");
        if (the_winner.equals(computer)){
            System.out.println("Computer wins");
        }
        else if (the_winner.equals(human)){
            System.out.println("Human wins");
        }
    }
    }

