/**
 * Adjacency-list representation of a Graph as illustrated in class
 */

import java.util.LinkedList;

public class Graph {

    private int V;
    private LinkedList<Integer>[] adj;

    /**
     * Constructs a Graph or pair-by-pair connection of vertices given the total number of vertices in the graph
     * @param V the total number of vertices in the graph
     */
    public Graph(int V) {
        this.V = V;
        adj = (LinkedList<Integer>[]) new LinkedList[V];

        for(int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    /**
     * Adds an edge or pairwise connection in the graph
     * @param v the vertex
     * @param w the other vertex
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Returns the adjacent vertices for a given vertex
     * @param v the vertex
     * @return the vertices adjacent to the vertex
     */
    public Iterable<Integer> bag(int v) {
        return adj[v];
    }

    /**
     * Returns the size of the graph
     * @return the size of the graph
     */
    public int V () {
        return V;
    }

}
