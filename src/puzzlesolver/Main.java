package puzzlesolver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] initialBoard = new int[3][3];
        
        System.out.println("Introduce el estado inicial del 8-Puzzle (usa 0 para espacio vacío):");
        System.out.println("Ejemplo: 1 2 3 4 0 5 7 8 6");
        
        for (int i = 0; i < 3; i++) {
            System.out.print("Fila " + (i+1) + ": ");
            String[] input = scanner.nextLine().trim().split("\\s+");
            for (int j = 0; j < 3; j++) {
                initialBoard[i][j] = input[j].equals("_") ? 0 : Integer.parseInt(input[j]);
            }
        }
        
        PuzzleSolver solver = new PuzzleSolver();
        PuzzleState solution = solver.solve(initialBoard);
        
        if (solution != null) {
            System.out.println("\nSolución encontrada!");
            PuzzleGUI.showSolution(solution.getPath());
        } else {
            System.out.println("\nNo tiene solución. Verifica el tablero ingresado.");
        }
        scanner.close();
    }
}