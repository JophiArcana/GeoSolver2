package core.structure.multicardinal.geo.circle.structure;

import core.Diagram;
import core.structure.multicardinal.DefinedMulticardinal;
import core.structure.multicardinal.geo.point.structure.DefinedPoint;
import core.structure.multicardinal.geo.point.structure.Point;
import core.structure.unicardinal.alg.symbolic.SymbolicExpression;

import java.util.List;

public abstract class DefinedCircle extends DefinedMulticardinal implements Circle {
    /** SECTION: Instance Variables ================================================================================= */
    public DefinedPoint center;
    public SymbolicExpression radius;

    /** SECTION: Abstract Constructor =============================================================================== */
    public DefinedCircle(Diagram d, String n) {
        super(d, n);
    }

    public DefinedCircle(Diagram d, String n, boolean anon) {
        super(d, n, anon);
    }

    /** SECTION: Implementation ===================================================================================== */
    /** SUBSECTION: Entity ========================================================================================== */
    public List<SymbolicExpression> symbolic() {
        return this.center.symbolic;
    }

    /** SUBSECTION: Circle ========================================================================================== */
    public Point center() {
        return this.center;
    }

    public SymbolicExpression radius() {
        return this.radius;
    }

    /** SECTION: Interface ========================================================================================== */
    public void setNode() {
        this.node = new CircleNode(this);
        this.diagram.root.getChildren().add(this.node);
    }
}
