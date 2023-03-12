//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entity;

public class Edge {
    private Vertex beginVertex;
    private Vertex endVertex;
    private double weight;

    public Edge(Vertex beginVertex, Vertex endVertex, double weight) {
        this.beginVertex = beginVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public Vertex getBeginVertex() {
        return this.beginVertex;
    }

    public Vertex getEndVertex() {
        return this.endVertex;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
