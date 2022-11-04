import java.util.*;

/**
 * Class to model the play of the game
 *
 * You should implement the public interface methods below
 * Interface provided by Prof. Andrew Godbout
 * Implemented by @author Shivangi Sharma
 */
public class GameModel {
    private BoardPositionHash hash;
    private Map<Integer, Integer> dataOfVertex;
    private int V;
    private Graph G;
    private int sz;

    private int startLoop ;
    private int endLoop ;

    private DepthFirstSearch pathFinder;

    /**
     * Construct a game with given sizexsize
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
        hash = new BoardPositionHash(sz,sz);
        dataOfVertex = new HashMap<>();
        V = sz*sz;
        G = new Graph(V);
        this.sz = sz;
        startLoop = V - 1;
        endLoop = startLoop - sz;
    }


    /**
     * Can a play be made at position row, col?
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     */
    public boolean canPlay(int row, int col) {
        int v = hash.getIndex(row, col);
        if(dataOfVertex.containsKey(v)) return false;

        return true;
    }

    /**
     * play a piece at the specified spot by the specified player
     * @param row the row where a piece is played
     * @param col the col where a piece is played
     * @param player -1 for white and 1 for black
     * @return true if the game is over and false otherwise
     */
    public boolean makePlay(int row, int col, int player) {
        int v = hash.getIndex(row, col);
        dataOfVertex.put(v, player);
        perimeterCheck(v, player);
        //printBoard();                                                     // if printing the board on console

        if(player == -1) {
            for (int i = 0; i < sz; i++) {
                if (dataOfVertex.containsKey(i)) {
                    for (int j = startLoop; j > endLoop; j--) {
                        if (dataOfVertex.containsKey(j)) {
                            pathFinder = new DepthFirstSearch(G, i);
                            if (pathFinder.marked(j)) return true;
                        }
                    }
                }
            }
        }
        else {

            for(int i = 0; i < V; i=i+sz) {
                if(dataOfVertex.containsKey(i)) {
                    for(int j = V; j > 0; j=j-sz) {
                        int l = j-1;
                        if(dataOfVertex.containsKey(l)) {
                            pathFinder = new DepthFirstSearch(G, i);
                            if(pathFinder.marked(l)) return true;
                        }
                    }
                }
            }

        }

        return false;
    }

    /**
     * Print the board to console along with the player representing that move and adjacent vertices list for the vertex
     */
    private void printBoard() {
        for(int i : dataOfVertex.keySet()) {
            System.out.println(i + ":-  Player: " + dataOfVertex.get(i) + " Bag: " + G.bag(i));
        }
    }

    /**
     * Checks if a vertex exists in the up, down, left, right, upRight, and downLeft positions and adds the edge in the bag for that player
     * @param v the vertex
     * @param player -1 if white, 1 for the black pieces
     */
    private void perimeterCheck(int v, int player) {

        int right = v+1;
        if(validVertex(right)) {
            if ((right % sz) != 0) addEdgeNow(v, right, player);
        }

        int left = v-1;
        if(validVertex(left)) {
            if( (v % sz) != 0) addEdgeNow(v, left, player);
        }

        int up = v-sz;
        if(validVertex(up)) addEdgeNow(v, up, player);

        int down = v+sz;
        if(validVertex(down)) addEdgeNow(v, down, player);

        int thenRight = up + 1;
        if(validVertex(up)) {
            if(validVertex(thenRight)) {
                if((thenRight % sz) != 0) {
                    int upRight = (v - sz) + 1;
                    addEdgeNow(v, upRight, player);
                }
            }
        }

        int thenLeft = down-1;
        if(validVertex(down)) {
            if(validVertex(thenLeft)) {
                if((down % sz) != 0) {
                    int downLeft = (v + sz) - 1;
                    addEdgeNow(v, downLeft, player);
                }
            }
        }
    }

    /**
     * Helper method to add an edge found in the neighboring perimeters for a player
     * @param v the vertex
     * @param adjV the adjacent vertex for that player
     * @param player -1 for white, 1 for the black pieces
     */
    private void addEdgeNow(int v, int adjV, int player) {
        if(dataOfVertex.containsKey(adjV)) {
            if(dataOfVertex.get(adjV) == player) {
                G.addEdge(adjV, v);
            }
        }
    }

    /**
     * Helper method to check if any given vertex is a valid vertex in the board
     * @param v the vertex to be inspected
     * @return true if valid vertex of the board, false otherwise
     */
    private boolean validVertex(int v) {
        if( (v < 0) || (v > V)) return false;
        return true;
    }

}
