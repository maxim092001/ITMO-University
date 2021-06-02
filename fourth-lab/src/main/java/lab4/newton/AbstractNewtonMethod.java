package lab4.newton;

import lab4.matrix.FullMatrix;
import lab4.matrix.Vector;
import lab4.utils.IterationStep;
import lab4.utils.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractNewtonMethod implements NewtonMethod {

    protected final Function<Vector, Double> function;
    protected final Double eps;
    protected final Vector startPoint;
    protected final int size;
    private final Steps steps;


    public AbstractNewtonMethod(final Function<Vector, Double> function, final Double eps, final Vector startPoint) {
        this.function = function;
        this.eps = eps;
        this.startPoint = startPoint;
        this.size = startPoint.size();
        this.steps = new Steps(new ArrayList<>(), startPoint);
    }

    protected Vector gradient(final Vector vector) {
        final double[] result = new double[size];
        final double f0 = function.apply(vector);
        for (int i = 0; i < size; i++) {
            final var curVector = vector.add(i, eps);
            result[i] = Math.abs(f0 - function.apply(curVector)) / eps;
        }
        return Vector.of(result);
    }

    // TODO protected
    public double[][] hesseMatrixCalculation(final Vector vector) {
        final double[][] result = new double[size][size];
        final double f0 = function.apply(vector);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final double fij = function.apply(vector.add(i, eps).add(j, eps));
                final double fi = function.apply(vector.add(i, eps));
                final double fj = function.apply(vector.add(j, eps));
                result[i][j] = (fij - fi - fj + f0) / (eps * eps);
            }
        }
        return result;
    }


    @Override
    public Vector minimize() {
        Vector xPrev = startPoint;
        while (true) {
            final var gradient = gradient(xPrev);
            final var H = (new FullMatrix(hesseMatrixCalculation(xPrev)));
            final var pk = getDirection(H, gradient, eps);
            final double alpha = getAlpha(xPrev, pk);
            final var xK = xPrev.add(pk.mul(alpha));
            steps.addIteration(alpha, xK, pk, function.apply(xK));
            if (xK.sub(xPrev).norm() < eps) {
                xPrev = xK;
                break;
            }
            xPrev = xK;
        }
        return xPrev;
    }


    public Steps getSteps() {
        return steps;
    }
}
