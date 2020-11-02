package com.company.graph;

public class DirectedEdge {
    final double weight;
    final int v;
    final int w;
    DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return this.w;
    }

    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }

}
