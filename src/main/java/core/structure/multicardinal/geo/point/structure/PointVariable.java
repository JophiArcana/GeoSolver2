package core.structure.multicardinal.geo.point.structure;

import core.structure.multicardinal.*;
import core.structure.unicardinal.alg.symbolic.*;
import javafx.scene.Cursor;

import java.util.*;

public class PointVariable extends MultiVariable implements Point {
    /** SECTION: Inner Classes ====================================================================================== */
    public static class MoveablePointNode extends PointNode {
        public MoveablePointNode(PointVariable p) {
            super(p);
            this.setOnMouseDragged(event -> {
                this.setCursor(Cursor.CLOSED_HAND);
                p.var_x.value = event.getSceneX() - PointNode.x;
                p.var_y.value = event.getSceneY() - PointNode.y;
            });
        }
    }

    /** SECTION: Instance Variables ================================================================================= */
    public SymbolicVariable var_x, var_y;

    /** SECTION: Factory Methods ==================================================================================== */
    public static PointVariable create(String n, double xStart, double yStart) {
        return new PointVariable(n, xStart, yStart);
    }

    public static PointVariable create(String n, double xStart, double yStart, boolean anon) {
        return new PointVariable(n, xStart, yStart, anon);
    }

    /** SECTION: Protected Constructors ============================================================================= */
    protected PointVariable(String n, double xStart, double yStart) {
        this(n, xStart, yStart, true);
    }

    protected PointVariable(String n, double xStart, double yStart, boolean anon) {
        super(n, anon);
        this.var_x = SymbolicVariable.create(this.name + "\u1D6A", xStart);
        this.var_y = SymbolicVariable.create(this.name + "\u1D67", yStart);
        if (!this.anonymous) {
            this.node = new MoveablePointNode(this);
        }
    }

    /** SECTION: Implementation ===================================================================================== */
    /** SUBSECTION: Entity ========================================================================================== */
    public List<SymbolicExpression> symbolic() {
        return List.of(this.var_x, this.var_y);
    }
}