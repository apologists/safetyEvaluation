//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entity;

import java.util.List;

public class Options {
    private List<String> labels;
    private List<Integer> data;

    public Options() {
    }

    public List<String> getLabels() {
        return this.labels;
    }

    public List<Integer> getData() {
        return this.data;
    }

    public void setLabels(final List<String> labels) {
        this.labels = labels;
    }

    public void setData(final List<Integer> data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Options)) {
            return false;
        } else {
            Options other = (Options)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$labels = this.getLabels();
                Object other$labels = other.getLabels();
                if (this$labels == null) {
                    if (other$labels != null) {
                        return false;
                    }
                } else if (!this$labels.equals(other$labels)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Options;
    }


    public String toString() {
        return "Options3(labels=" + this.getLabels() + ", data=" + this.getData() + ")";
    }
}
