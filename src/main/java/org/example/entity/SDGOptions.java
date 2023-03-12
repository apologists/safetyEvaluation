//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entity;

import java.util.List;

public class SDGOptions {
    private List<SDGNode> nodes;
    private List<SDGEdges> edges;

    public SDGOptions() {
    }

    public List<SDGNode> getNodes() {
        return this.nodes;
    }

    public List<SDGEdges> getEdges() {
        return this.edges;
    }

    public void setNodes(final List<SDGNode> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(final List<SDGEdges> edges) {
        this.edges = edges;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SDGOptions)) {
            return false;
        } else {
            SDGOptions other = (SDGOptions)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$nodes = this.getNodes();
                Object other$nodes = other.getNodes();
                if (this$nodes == null) {
                    if (other$nodes != null) {
                        return false;
                    }
                } else if (!this$nodes.equals(other$nodes)) {
                    return false;
                }

                Object this$edges = this.getEdges();
                Object other$edges = other.getEdges();
                if (this$edges == null) {
                    if (other$edges != null) {
                        return false;
                    }
                } else if (!this$edges.equals(other$edges)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SDGOptions;
    }

    public String toString() {
        return "SDGSummary(nodes=" + this.getNodes() + ", edges=" + this.getEdges() + ")";
    }
}
