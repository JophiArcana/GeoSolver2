package Core.AlgSystem.UnicardinalTypes;

import Core.AlgSystem.UnicardinalRings.DirectedAngle;
import Core.AlgSystem.UnicardinalRings.Symbolic;
import Core.EntityTypes.Mutable;
import Core.Utilities.*;

import java.util.*;

public class Univariate<T extends Expression<T>> extends Mutable implements Expression<T> {
    public static final int naturalDegreesOfFreedom = 1;

    public final Class<T> TYPE;

    public Univariate(String n, Class<T> type) {
        super(n);
        this.TYPE = type;
    }

    public ArrayList<Expression<Symbolic>> symbolic() {
        if (this.TYPE == Symbolic.class) {
            return new ArrayList<>(Collections.singletonList((Univariate<Symbolic>) this));
        } else if (this.TYPE == DirectedAngle.class) {
            return new ArrayList<>(Collections.singletonList(new Univariate<>(this.name + "\u24E3", Symbolic.class)));
        } else {
            return null;
        }
    }

    public Expression<T> reduce() {
        return this;
    }

    public Expression<T> expand() {
        return this;
    }

    public Expression<T> close() {
        return this;
    }

    public Factorization<T> normalize() {
        TreeMap<Expression<T>, Constant<T>> factors = new TreeMap<>(Utils.PRIORITY_COMPARATOR);
        factors.put(this, Constant.ONE(TYPE));
        return new Factorization<>(Constant.ONE(TYPE), factors, TYPE);
    }

    public Expression<T> derivative(Univariate<T> var) {
        return this.name.equals(var.name) ? Constant.ONE(TYPE) : Constant.ZERO(TYPE);
    }

    public int getNaturalDegreesOfFreedom() {
        return Univariate.naturalDegreesOfFreedom;
    }

    public Class<T> getType() {
        return this.TYPE;
    }

    public AlgeEngine<T> getEngine() {
        return Utils.getEngine(TYPE);
    }
}