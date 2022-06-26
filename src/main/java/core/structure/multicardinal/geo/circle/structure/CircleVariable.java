package core.structure.multicardinal.geo.circle.structure;

import core.Diagram;
import core.structure.multicardinal.MultiVariable;
import core.structure.multicardinal.geo.point.structure.*;
import core.structure.unicardinal.alg.symbolic.*;
import javafx.scene.Cursor;

import java.util.*;

public class CircleVariable extends MultiVariable implements Circle {
    /** SECTION: Inner Classes ====================================================================================== */
    public static class MoveableCircleNode extends CircleNode {
        public MoveableCircleNode(CircleVariable c) {
            super(c);
            this.setOnMouseDragged(event -> {
                this.setCursor(Cursor.CLOSED_HAND);
                double x = event.getSceneX() - this.getTranslateX();
                double y = event.getSceneY() - this.getTranslateY();
                c.var_r.value = Math.sqrt(x * x + y * y);
            });
        }
    }

    /** SECTION: Instance Variables ================================================================================= */
    public PointVariable center;
    public SymbolicVariable var_r;

    /** SECTION: Factory Methods ==================================================================================== */
    public static CircleVariable create(Diagram d, String n, double xStart, double yStart, double rStart) {
        return new CircleVariable(d, n, xStart, yStart, rStart);
    }

    public static CircleVariable create(Diagram d, String n, double xStart, double yStart, double rStart, boolean anon) {
        return new CircleVariable(d, n, xStart, yStart, rStart, anon);
    }

    /** SECTION: Protected Constructors ============================================================================= */
    protected CircleVariable(Diagram d, String n, double xStart, double yStart, double rStart) {
        this(d, n, xStart, yStart, rStart, true);
    }

    protected CircleVariable(Diagram d, String n, double xStart, double yStart, double rStart, boolean anon) {
        super(d, n, anon);
        this.center = PointVariable.create(d, n + "\u2092", xStart, yStart, anon);
        this.var_r = SymbolicVariable.create(d, n + "\u1D63", rStart);
        if (!this.anonymous) {
            this.node = new MoveableCircleNode(this);
            this.diagram.root.getChildren().add(this.node);
        }
    }

    /** SECTION: Implementation ===================================================================================== */

    public List<SymbolicExpression> symbolic() {
        return List.of(this.center.var_x, this.center.var_y, this.var_r);
    }

    /** SUBSECTION: Circle ========================================================================================== */
    public Point center() {
        return this.center;
    }
    public SymbolicExpression radius() {
        return this.var_r;
    }
}
