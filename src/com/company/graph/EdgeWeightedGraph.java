package com.company.graph;

import java.util.ArrayList;

public class EdgeWeightedGraph {
    private final int v;
    private int E;
    private ArrayList[] Bags;

    public EdgeWeightedGraph(int v) {
        this.v = v;
        this.E = 0;

        this.Bags = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            this.Bags[i] = new ArrayList();
        }

    }

    public int getV() { return this.v; }

    public int getE() { return this.E; }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        this.Bags[v].add(e);
        this.Bags[w].add(e);
    }


    public Edge[] minTree() {
        Edge[] treeEdges = new Edge[this.v - 1];

        Edge[] curEdges = new Edge[this.v];

        if (this.v == 0) return treeEdges;


        Boolean[] inTree = new Boolean[this.v];
        for (int i = 0; i < inTree.length; i++) {
            inTree[i] = false;
        }

        int p = 0, edgesCount = 0;
        inTree[p] = true;
        System.out.println("将顶点" + p + "加入子图");


        while (edgesCount < treeEdges.length) {
            ArrayList<Edge> edges = this.Bags[p];

            for (Edge e: edges) {
                int other = e.other(p);
                if (inTree[other]) continue;
                if (curEdges[other] == null) curEdges[other] = e;
                else if (curEdges[other].getWeight() > e.getWeight()) curEdges[other] = e;
            }
            Edge nextEdge = null;
            for (int i = 0; i < curEdges.length; i++) {
                if (inTree[i]) continue;
                Edge e = curEdges[i];
                if (e == null) continue;
                if (nextEdge == null) nextEdge = e;
                else if (nextEdge.getWeight() > e.getWeight()) nextEdge = e;
            }

            p = nextEdge.either();

            if (inTree[p]) p = nextEdge.other(p);
            System.out.println(p);
            inTree[p] = true;
            curEdges[p] = null;
            System.out.println("将顶点" + p + "加入子图");
            treeEdges[edgesCount++] = nextEdge;
        }

        return treeEdges;
    }

}
