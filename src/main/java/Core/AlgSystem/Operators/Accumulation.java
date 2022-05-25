package Core.AlgSystem.Operators;

import Core.AlgSystem.Constants.Real;
import Core.AlgSystem.Operators.AddReduction.Scale;
import Core.AlgSystem.UnicardinalStructure.*;
import Core.EntityStructure.UnicardinalStructure.Constant;
import Core.EntityStructure.UnicardinalStructure.DefinedExpression;
import Core.EntityStructure.UnicardinalStructure.Expression;

public abstract class Accumulation<T> extends DefinedExpression<T> {
    /** SECTION: Static Data ======================================================================================== */
    public enum Parameter implements InputType {
        COEFFICIENT,
        EXPRESSION
    }
    public static final InputType[] inputTypes = {Parameter.COEFFICIENT, Scale.Parameter.EXPRESSION};

    /** SECTION: Instance Variables ================================================================================= */
    public double coefficient;
    public Expression<T> expression;

    /** SECTION: Abstract Constructor =============================================================================== */
    protected Accumulation(double coefficient, Expression<T> expr, Class<T> type) {
        super(type);
        if (expr instanceof Constant<T> constExpr) {
            this.coefficient = 1;
            this.expression = this.evaluateConstant(coefficient, constExpr);
        } else if (expr.getClass() == this.getClass()) {
            this.coefficient = coefficient * ((Accumulation<T>) expr).coefficient;
            this.expression = ((Accumulation<T>) expr).expression;
        } else {
            this.coefficient = coefficient;
            this.expression = expr;
        }
        this.inputs.get(Parameter.COEFFICIENT).add(Real.create(this.coefficient, TYPE));
        this.inputs.get(Parameter.EXPRESSION).add(this.expression);
    }

    /** SECTION: Interface ========================================================================================== */
    protected abstract Real<T> identity();
    protected abstract Constant<T> evaluateConstant(double c, Constant<T> e);

    /** SECTION: Implementation ===================================================================================== */
    /** SUBSECTION: Entity ========================================================================================== */
    public InputType[] getInputTypes() {
        return Accumulation.inputTypes;
    }

    /** SUBSECTION: Expression ====================================================================================== */
    public Expression<T> close() {
        if (this.coefficient == 1) {
            return this.expression;
        } else if (this.coefficient == 0) {
            return this.identity();
        } else {
            return this;
        }
    }
}
