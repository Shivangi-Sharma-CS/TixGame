/**
 * Depth First Search on a given graph and a source as illustrated in class
 */
public class DepthFirstSearch {

    private boolean[] marked;

    /**
     * Constructs a depth first search for a graph given a source vertex
     * @param G the Graph
     * @param s the source vertex
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    /**
     * Helper method to construct the depth first search for a graph for any source vertex
     * @param G the Graph
     * @param v the vertex
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for(int w : G.bag(v)) {
            if(!marked[w]) {
                dfs(G,w);
            }
        }
    }

    /**
     * Returns whether a path exists between a given vertex to the source vertex
     * @param v the vertex
     * @return true if path exists, false otherwise
     */
    public boolean marked(int v) {
        return marked[v];
    }
}
