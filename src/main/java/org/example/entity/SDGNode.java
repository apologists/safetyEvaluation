//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entity;

public class SDGNode {
    private String id;
    private String label;
    private Integer borderWidth;
    private String shape;

    public SDGNode(String id, String label) {
        this.id = id;
        this.label = label;
        this.borderWidth = 2;
        this.shape = "circle";
    }

    public SDGNode(String id, String label, String shape) {
        this.id = id;
        this.label = label;
        this.borderWidth = 2;
        this.shape = shape;
    }

    public String getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public Integer getBorderWidth() {
        return this.borderWidth;
    }

    public String getShape() {
        return this.shape;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public void setBorderWidth(final Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setShape(final String shape) {
        this.shape = shape;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SDGNode)) {
            return false;
        } else {
            SDGNode other = (SDGNode)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label59;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label59;
                    }

                    return false;
                }

                Object this$label = this.getLabel();
                Object other$label = other.getLabel();
                if (this$label == null) {
                    if (other$label != null) {
                        return false;
                    }
                } else if (!this$label.equals(other$label)) {
                    return false;
                }

                Object this$borderWidth = this.getBorderWidth();
                Object other$borderWidth = other.getBorderWidth();
                if (this$borderWidth == null) {
                    if (other$borderWidth != null) {
                        return false;
                    }
                } else if (!this$borderWidth.equals(other$borderWidth)) {
                    return false;
                }

                Object this$shape = this.getShape();
                Object other$shape = other.getShape();
                if (this$shape == null) {
                    if (other$shape != null) {
                        return false;
                    }
                } else if (!this$shape.equals(other$shape)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SDGNode;
    }


    public String toString() {
        return "SDGNode(id=" + this.getId() + ", label=" + this.getLabel() + ", borderWidth=" + this.getBorderWidth() + ", shape=" + this.getShape() + ")";
    }

    public SDGNode(final String id, final String label, final Integer borderWidth, final String shape) {
        this.id = id;
        this.label = label;
        this.borderWidth = borderWidth;
        this.shape = shape;
    }

    public SDGNode() {
    }
}
