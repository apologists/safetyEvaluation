//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entity;

public class SDGEdges {
    private String from;
    private String to;
    private boolean dashes;

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public boolean isDashes() {
        return this.dashes;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public void setDashes(final boolean dashes) {
        this.dashes = dashes;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SDGEdges)) {
            return false;
        } else {
            SDGEdges other = (SDGEdges)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label39: {
                    Object this$from = this.getFrom();
                    Object other$from = other.getFrom();
                    if (this$from == null) {
                        if (other$from == null) {
                            break label39;
                        }
                    } else if (this$from.equals(other$from)) {
                        break label39;
                    }

                    return false;
                }

                Object this$to = this.getTo();
                Object other$to = other.getTo();
                if (this$to == null) {
                    if (other$to != null) {
                        return false;
                    }
                } else if (!this$to.equals(other$to)) {
                    return false;
                }

                if (this.isDashes() != other.isDashes()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SDGEdges;
    }


    public String toString() {
        return "SDGEdges(from=" + this.getFrom() + ", to=" + this.getTo() + ", dashes=" + this.isDashes() + ")";
    }

    public SDGEdges(final String from, final String to, final boolean dashes) {
        this.from = from;
        this.to = to;
        this.dashes = dashes;
    }

    public SDGEdges() {
    }
}
