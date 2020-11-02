package com.company.graph;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstraSp {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Pair> pq;

    private class Pair {
        int idx;
        double val;
        Pair(int idx, double val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public DijkstraSp(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.getV()];
        distTo = new double[G.getV()];

        Comparator<Pair> cpm = (o1, o2) -> (int) (o1.val - o2.val);
        pq = new PriorityQueue<>(cpm);

        for (int v = 0; v < G.getV(); v++) {
            distTo[v] = v == s ? 0.0 : Double.POSITIVE_INFINITY;
        }

        pq.add(new Pair(s, 0.0));

        while (!pq.isEmpty()) {
            relax(G, pq.remove().idx);
        }
    }


    private void relax(EdgeWeightedDigraph G, int v) {

        System.out.println("remove -> " + v);

        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                pq.add(new Pair(w, distTo[w]));
            }
        }

    }


    public double[] getDistTo() {
        return distTo;
    }


    public String toString() {
        String s = "最短路径结果：";
        for (double x: distTo) {
            s += " " + x  + " ";
        }
        s += "\n";
        return s;
    }
}
