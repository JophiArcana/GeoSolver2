
import Core.AlgSystem.UnicardinalTypes.*;
import Core.AlgSystem.UnicardinalRings.*;
import Core.GeoSystem.Points.PointTypes.*;
import Core.Utilities.*;

import java.util.*;

import static Core.Utilities.GeoEngine.*;

public class SpeedTest {
    public static void main(String[] args) {
        AlgeEngine<Symbolic> e1 = Utils.getEngine(Symbolic.class);

        Phantom p = new Phantom("P");
        Phantom q = new Phantom("Q");
        Phantom r = new Phantom("R");
        Point o = circumcenter(p, q, r);

        Expression<Symbolic> expr = (Expression<Symbolic>) o.expression(Point.PointExpressionType.X);
        expr = e1.mul(expr, 2);
        ArrayList<Expression<Symbolic>> exprInputs = Utils.cast(expr.getInputs().get("Terms"));
        exprInputs = Utils.cast(exprInputs.get(0).getInputs().get("Terms"));

        int cycles = 100000;
        double startTime = System.nanoTime();
        for (int i = 0; i < cycles; i++) {
            e1.GCDReduction(exprInputs);
        }
        double endTime = System.nanoTime();
        System.out.println((endTime - startTime) / (1E6 * cycles) + "ms");
    }
}
