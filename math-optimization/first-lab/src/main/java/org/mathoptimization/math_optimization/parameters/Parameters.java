package org.mathoptimization.math_optimization.parameters;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Parameters used for "logging" our methods steps.
 *
 * @see DichotomyParameters
 * @see FibonacciParameters
 * @see GoldenRatioParameters
 * @see ParabolaParameters
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Parameters extends Serializable {
}
