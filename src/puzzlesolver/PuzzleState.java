package puzzlesolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleState implements Comparable<PuzzleState> {
    int[][] board;
    int zeroRow, zeroCol;
    int cost;
    int heuristic;
    PuzzleState parent;
    String move;
    
    public PuzzleState(int[][] board, int zeroRow, int zeroCol, 
                     int cost, PuzzleState parent, String move) {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            this.board[i] = Arrays.copyOf(board[i], 3);
        }
        this.zeroRow = zeroRow;
        this.zeroCol = zeroCol;
        this.cost = cost;
        this.parent = parent;
        this.move = move;
        this.heuristic = calculateHeuristic();
    }
    
    private int calculateHeuristic() {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 0) {
                    int targetRow = (board[i][j] - 1) / 3;
                    int targetCol = (board[i][j] - 1) % 3;
                    total += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return total;
    }
    
    public boolean isGoal() {
        int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        return Arrays.deepEquals(board, goal);
    }
    
    public List<PuzzleState> getNeighbors() {
        List<PuzzleState> neighbors = new ArrayList<>();
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        String[] moves = {"Arriba", "Abajo", "Izquierda", "Derecha"};
        
        for (int i = 0; i < 4; i++) {
            int newRow = zeroRow + dr[i];
            int newCol = zeroCol + dc[i];
            
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int[][] newBoard = new int[3][3];
                for (int r = 0; r < 3; r++) {
                    newBoard[r] = Arrays.copyOf(board[r], 3);
                }
                newBoard[zeroRow][zeroCol] = newBoard[newRow][newCol];
                newBoard[newRow][newCol] = 0;
                
                neighbors.add(new PuzzleState(
                    newBoard, 
                    newRow, 
                    newCol, 
                    cost + 1,
                    this, 
                    moves[i]
                ));
            }
        }
        return neighbors;
    }
    
    public List<PuzzleState> getPath() {
        List<PuzzleState> path = new ArrayList<>();
        PuzzleState current = this;
        while (current != null) {
            path.add(0, current);
            current = current.parent;
        }
        return path;
    }
    
    @Override
    public int compareTo(PuzzleState other) {
        return Integer.compare(
            this.cost + this.heuristic, 
            other.cost + other.heuristic
        );
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PuzzleState other = (PuzzleState) obj;
        return Arrays.deepEquals(board, other.board);
    }
    
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}