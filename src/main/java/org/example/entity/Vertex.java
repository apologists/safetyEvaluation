//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Vertex<T> {
    private T label;
    private List<Edge> edgeList;
    private boolean visited;
    private Vertex previousVertex;
    private double cost;

    public Vertex(T label, double cost) {
        this.label = label;
        this.edgeList = new LinkedList();
        this.visited = false;
        this.previousVertex = null;
        this.cost = cost;
    }

    public T getLabel() {
        return this.label;
    }

    public boolean equals(Object otherVertex) {
        if (otherVertex != null && this.getClass() == otherVertex.getClass()) {
            Vertex other = (Vertex)otherVertex;
            boolean result = this.label.equals(other.getLabel());
            return result;
        } else {
            return false;
        }
    }

    public Iterator<Edge> getEdgeIterator() {
        return this.edgeList.iterator();
    }

    public int getEdgeCount() {
        return this.edgeList.size();
    }

    public boolean connect(Vertex endVertex, double weight) {
        Iterator<Edge> iterator = this.getEdgeIterator();
        Edge edge = null;
        Vertex vertex = null;

        do {
            if (!iterator.hasNext()) {
                edge = new Edge(this, endVertex, weight);
                this.edgeList.add(edge);
                return true;
            }

            edge = (Edge)iterator.next();
            vertex = edge.getEndVertex();
        } while(!vertex.equals(endVertex));

        edge.setWeight(weight);
        return false;
    }

    public boolean disconnect(Vertex endVertex) {
        Iterator<Edge> iterator = this.getEdgeIterator();
        Edge edge = null;
        Vertex vertex = null;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            edge = (Edge)iterator.next();
            vertex = edge.getEndVertex();
        } while(!vertex.equals(endVertex));

        iterator.remove();
        return true;
    }

    public Edge hasNeighbourVertex(Vertex endVertex) {
        Iterator<Edge> iterator = this.getEdgeIterator();
        Edge edge = null;
        Vertex vertex = null;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            edge = (Edge)iterator.next();
            vertex = edge.getEndVertex();
        } while(!vertex.equals(endVertex));

        return edge;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void visit() {
        this.visited = true;
    }

    public void unVisit() {
        this.visited = false;
    }

    public Vertex getUnvisitedVertex() {
        Iterator<Edge> iterator = this.getEdgeIterator();
        Edge edge = null;
        Vertex vertex = null;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            edge = (Edge)iterator.next();
            vertex = edge.getEndVertex();
        } while(vertex.isVisited());

        return vertex;
    }

    public Vertex getPreviousVertex() {
        return this.previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
