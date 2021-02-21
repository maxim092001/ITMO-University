package org.mathoptimization.math_optimization.parameters;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoldenRatioParameters extends AbstractParameters {
    public GoldenRatioParameters(final Double left, final Double right, final Double value, final Double argument) {
        super(left, right, value, argument);
    }
}
