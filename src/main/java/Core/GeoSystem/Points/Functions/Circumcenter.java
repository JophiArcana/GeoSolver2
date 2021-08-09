package Core.GeoSystem.Points.Functions;

import Core.AlgeSystem.UnicardinalTypes.*;
import Core.EntityTypes.Entity;
import Core.AlgeSystem.UnicardinalRings.Distance;
import Core.GeoSystem.Points.PointTypes.*;
import Core.Utilities.*;

import java.util.*;
import java.util.function.Function;

public class Circumcenter extends Center {
    public Entity create(HashMap<String, ArrayList<Entity>> args) {
        ArrayList<Entity> points = args.get("Points");
        return new Circumcenter(this.name, (Point) points.get(0), (Point) points.get(1), (Point) points.get(2));
    }

    public static ArrayList<Unicardinal> formula(HashMap<String, ArrayList<ArrayList<Unicardinal>>> args) {
        final AlgeEngine<Distance> ENGINE = Utils.getEngine(Distance.class);
        ArrayList<Expression<Distance>> argTerms = Utils.map(args.get("Points"), arg -> (Expression<Distance>) arg.get(0));
        Function<ArrayList<Expression<Distance>>, Expression<Distance>> funcN = terms -> ENGINE.mul(
                ENGINE.pow(ENGINE.abs(terms.get(0)), 2), ENGINE.sub(terms.get(1), terms.get(2)));
        Function<ArrayList<Expression<Distance>>, Expression<Distance>> funcD = terms -> ENGINE.imaginary(
                ENGINE.mul(ENGINE.conjugate(terms.get(0)), terms.get(1)));
        Expression<Distance> numerator = ENGINE.cyclicSum(funcN, argTerms);
        Expression<Distance> denominator = ENGINE.mul(2, Constant.I(Distance.class), ENGINE.cyclicSum(funcD, argTerms));
        return new ArrayList<>(Collections.singletonList(ENGINE.div(numerator, denominator)));
    }

    public Circumcenter(String n, Point a, Point b, Point c) {
        super(n, a, b, c);
    }

    public Circumcenter(Point a, Point b, Point c) {
        super("", a, b, c);
    }

    public Entity simplify() {
        return this;
    }

    public Function<HashMap<String, ArrayList<ArrayList<Unicardinal>>>, ArrayList<Unicardinal>> getFormula() {
        return Circumcenter::formula;
    }
}
