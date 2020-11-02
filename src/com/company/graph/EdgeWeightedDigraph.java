package com.company.graph;

import java.util.ArrayList;

public class EdgeWeightedDigraph {
    final int V;
    int E;
    private ArrayList<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new ArrayList[V];

        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }
    public Iterable<DirectedEdge> edges() {
        ArrayList<DirectedEdge> bag = new ArrayList<>();

        for (int v = 0; v < this.V; v++) {
            for (DirectedEdge e: adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }

    public int getE() {
        return E;
    }

    public int getV() {
        return V;
    }


    public double[] shortest(int s) {
        DijkstraSp sp = new DijkstraSp(this, s);

        return sp.getDistTo();
    }
}
