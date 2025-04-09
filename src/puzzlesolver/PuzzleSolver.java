package puzzlesolver;

import java.util.*;

public class PuzzleSolver {
    
    public PuzzleState solve(int[][] initialBoard) {
        if (!isSolvable(initialBoard)) {
            return null;
        }
        
        int zeroRow = -1, zeroCol = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (initialBoard[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                    break;
                }
            }
            if (zeroRow != -1) break;
        }
        
        PriorityQueue<PuzzleState> openSet = new PriorityQueue<>();
        Set<PuzzleState> closedSet = new HashSet<>();
        
        PuzzleState initialState = new PuzzleState(
            initialBoard, 
            zeroRow, 
            zeroCol, 
            0,
            null, 
            "Inicio"
        );
        
        openSet.add(initialState);
        
        while (!openSet.isEmpty()) {
            PuzzleState current = openSet.poll();
            
            if (current.isGoal()) {
                return current;
            }
            
            closedSet.add(current);
            
            for (PuzzleState neighbor : current.getNeighbors()) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                }
            }
        }
        
        return null;
    }
    
    private boolean isSolvable(int[][] board) {
        int inversions = 0;
        int[] flat = new int[9];
        int k = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flat[k++] = board[i][j];
            }
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (flat[i] != 0 && flat[j] != 0 && flat[i] > flat[j]) {
                    inversions++;
                }
            }
        }
        
        return inversions % 2 == 0;
    }
}