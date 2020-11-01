package com.company.graph;

import java.util.ArrayList;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private ArrayList<Edge>[] Bags;

    public EdgeWeightedGraph(int v) {
        this.V = v;
        this.E = 0;

        this.Bags = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            this.Bags[i] = new ArrayList();
        }

    }

    public int getV() { return this.V; }

    public int getE() { return this.E; }


    public Edge[] getEdges() {

        ArrayList<Edge> edges = new ArrayList<>();

        for (ArrayList<Edge> bag: Bags) {
            for (Edge edge: bag) {

                if (!edges.contains(edge)) {
                    edges.add(edge);
                }
            }
        }


        return edges.toArray(new Edge[edges.size()]);
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        this.Bags[v].add(e);
        this.Bags[w].add(e);
    }

    public Edge[] minTree() {
        Edge[] treeEdges = new Edge[this.V - 1];

        Edge[] curEdges = new Edge[this.V];

        if (this.V == 0) return treeEdges;


        Boolean[] inTree = new Boolean[this.V];
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

    public double[] bellmanShortestPath() throws RuntimeException{

        int v = this.getV();
        Edge[] edges = getEdges();
        double[] stortestPaths = new double[v];

        for(int i = 1;i < v;i++) {
            stortestPaths[i] = Integer.MAX_VALUE;  //初始化第0个顶点到其它顶点之间的距离为无穷大，此处用Integer型最大值表示
        }

        for (int i = 1; i < v; i++) {
            for (int j = 0; j < edges.length; j++) {
                Edge e = edges[j];
                int either = e.either();
                int other = e.other(either);
                if (stortestPaths[other] > stortestPaths[either] + e.getWeight()) {
                    stortestPaths[other] = (stortestPaths[either] + e.getWeight());
                }
            }
        }

        for (int j = 0; j < edges.length; j++) {
            Edge e = edges[j];
            int either = e.either();
            int other = e.other(either);
            if (stortestPaths[other] > stortestPaths[either] + e.getWeight()) {
                throw new RuntimeException("存在负权值的边");
            }
        }
        return stortestPaths;
    }


    public double[] DijkstraShortestPath() throws RuntimeException { return  DijkstraShortestPath(0); }

    public double[] DijkstraShortestPath(int index) throws RuntimeException {
        int v = this.getV();
        Edge[] edges = getEdges();
        double[] stortestPaths = new double[v];
        boolean[] st = new boolean[v];

        for (int i = 0; i < v; i++) {
            stortestPaths[i] = i == index ? 0 : Integer.MAX_VALUE;
        }


        return stortestPaths;
    }



}
