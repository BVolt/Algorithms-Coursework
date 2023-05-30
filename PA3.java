package com.mycompany.pa3;

import java.util.LinkedList;
import java.util.Random;

public class PA3 {
    private static LinkedList<String> path = new LinkedList<>();
    private static int n = 5, m = 5;
    
    public static void main(String[] args) {
        int[][] board = new int[n][m];
        populateBoard(board, n, m);
//        int[][] board = {
//            {3, 4, -1, 9, 3},
//            {3, 2, -1, 4, 5},
//            {1, -1, -1, 8, 7},
//            {1, -1, 3, 1, 3},
//            {1, 1, 1, 4, 2}
//        };
        
        displayBoard(board);
        int[][] A = MaximumPath(board);
        findPath( A, n, m);
        System.out.println("Max Route:");
        System.out.println(path);
    }
    
    //Calculates Max Path
    static int[][] MaximumPath(int[][] board) {
        int[][] A = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                A[i][j] = Math.max(A[i - 1][j], A[i][j - 1]) + board[i - 1][j - 1];
            }
        }
        return A;
    }
    

    //Finds the path after max has been calculated
    public static void findPath(int[][] A, int x, int y){
        if(x == 1 && y == 1)
            path.add("Start");
        else if(x == 1){
            findPath(A, x, y - 1);
            path.add("Right");
        }
        else if(y == 1){
            findPath(A, x - 1, y);
            path.add("Down");
        }
        else{
            int max = max3(A[x - 1][y - 1], A[x - 1][y], A[x][y - 1]);
            if(max == A[x - 1][y - 1]){
                findPath(A, x - 1, y - 1);
                path.add("Diagonal");
            }
            else if (max == A[x - 1][y]){
                findPath(A, x - 1, y);
                path.add("Down");
            }
            else {
                findPath(A, x, y - 1);
                path.add("Right");
            }
        }
            
    }

    //Max of 3 values
    public static int max3(int first, int second, int third) {
        return Math.max(Math.max(first, second), third);
    }

    //Populates 2D Matrix with random integers
    public static void populateBoard(int[][] board, int n, int m) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = random.nextInt(-10, 10);
            }
        }
    }

    //Display 2D Matrix
    public static void displayBoard(int[][] board) {
        System.out.println("Board");
        for (int i = 0; i < n; i++) {
            System.out.print("{");
            for (int j = 0; j < m; j++) {
                System.out.print(board[i][j]);
                if (j != m - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("},");
        }
        System.out.println();
    }
}


/*
Example Output

Board
{3, 4, -1, 9, 3},
{3, 2, -1, 4, 5},
{1, -1, -1, 8, 7},
{1, -1, 3, 1, 3},
{1, 1, 1, 4, 2},

Max Path: 
[Start, Right, Right, Right, Down, Down, Right, Down, Down]
*/