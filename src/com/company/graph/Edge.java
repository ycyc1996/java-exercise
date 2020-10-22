package com.company.graph;

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public int either() {
        return this.v;
    }

    public int other(int vertex) {
        if (vertex == this.v) return this.w;
        if (vertex == this.w) return this.v;
        throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(Edge o) {
        double diff = this.getWeight() - o.getWeight();
        if (diff == 0) return 0;
        else if (diff > 0) return 1;
        else return -1;
    }

    @Override
    public String toString() {
        return String.format("%d-%d(%.2f)", v, w, this.getWeight());
    }

}
