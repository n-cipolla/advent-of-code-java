package main.java.adventofcode.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class MultiGraph<E> {
    ArrayList<E> vertices;
    HashMap<E, ArrayList<E>> edges;

    public MultiGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new HashMap<>();
    }

    /**
     * Use this method to add to the graph
     * @param from where this value connects in the graph, a mutual connection is established.
     * @param to value of the element being added
     */
    public void addNode(E from, E to) {
//        if(!vertices.contains(from)) throw new NoSuchElementException("Second parameter 'from' not in graph.");

        if (!vertices.contains(to)) {
            vertices.add(to);
            edges.put(to, new ArrayList<E>());
        }

        if (!vertices.contains(from)) {
            vertices.add(from);
            edges.put(from, new ArrayList<>());
        }

        edges.get(to).add(from);
        edges.get(from).add(to);
    }

    /**
     * Use this method to add to an empty graph.
     * @param to the 'start' value
     */
    public void addNode(E to) {
        vertices.add(to);
        edges.put(to, new ArrayList<>());
    }

    public boolean isEmpty() {
        return this.vertices.isEmpty();
    }

    @Override
    public String toString() {
        return "Vertices: " + vertices.toString() + "\n" + "Edges: " + edges.toString();
    }

    public ArrayList<E> getVertices() {
        return new ArrayList<E>(vertices);
    }

    public HashMap<E, ArrayList<E>> getEdges() {
        return new HashMap<>(edges);
    }



}
