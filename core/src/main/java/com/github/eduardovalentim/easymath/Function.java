package com.github.eduardovalentim.easymath;

import java.math.MathContext;

/**
 * In mathematics, a function[1] is a relation between a set of inputs and a set
 * of permissible outputs with the property that each input is related to
 * exactly one output. An example is the function that relates each real number
 * x to its square x2. The output of a function f corresponding to an input x is
 * denoted by f(x) (read "f of x"). In this example, if the input is −3, then
 * the output is 9, and we may write f(−3) = 9. Likewise, if the input is 3,
 * then the output is also 9, and we may write f(3) = 9. (The same output may be
 * produced by more than one input, but each input gives only one output.) The
 * input variable(s) are sometimes referred to as the argument(s) of the
 * function.
 * 
 * Functions of various kinds are "the central objects of investigation"[2] in
 * most fields of modern mathematics. There are many ways to describe or
 * represent a function. Some functions may be defined by a formula or algorithm
 * that tells how to compute the output for a given input. Others are given by a
 * picture, called the graph of the function. In science, functions are
 * sometimes defined by a table that gives the outputs for selected inputs. A
 * function could be described implicitly, for example as the inverse to another
 * function or as a solution of a differential equation.
 * 
 * The input and output of a function can be expressed as an ordered pair,
 * ordered so that the first element is the input (or tuple of inputs, if the
 * function takes more than one input), and the second is the output. In the
 * example above, f(x) = x2, we have the ordered pair (−3, 9). If both input and
 * output are real numbers, this ordered pair can be viewed as the Cartesian
 * coordinates of a point on the graph of the function.
 * 
 * In modern mathematics,[3] a function is defined by its set of inputs, called
 * the domain; a set containing the set of outputs, and possibly additional
 * elements, as members, called its codomain; and the set of all input-output
 * pairs, called its graph. Sometimes the codomain is called the function's
 * "range", but more commonly the word "range" is used to mean, instead,
 * specifically the set of outputs (this is also called the image of the
 * function). For example, we could define a function using the rule f(x) = x2
 * by saying that the domain and codomain are the real numbers, and that the
 * graph consists of all pairs of real numbers (x, x2). The image of this
 * function is the set of non-negative real numbers. Collections of functions
 * with the same domain and the same codomain are called function spaces, the
 * properties of which are studied in such mathematical disciplines as real
 * analysis, complex analysis, and functional analysis.
 * 
 * In analogy with arithmetic, it is possible to define addition, subtraction,
 * multiplication, and division of functions, in those cases where the output is
 * a number. Another important operation defined on functions is function
 * composition, where the output from one function becomes the input to another
 * function.
 * 
 * @author Eduardo.Valentim
 *
 */
public interface Function<T extends Number> {

	/**
	 * Define the name of the function to be used in the catalog
	 * 
	 * @return The name of this function to register in a catalog
	 */
	public String name();

	/**
	 * Execute the calculation
	 * 
	 * @param mc     Mathematical context to be used in the execution
	 * @param inputs Inputs for the execution
	 * 
	 * @return The result of calculation
	 */
	public T perform(MathContext mc, Number... inputs);

	/**
	 * 
	 * @param inputs
	 */
	public default void validate(Number... inputs) {
		if (inputs == null)
			throw new IllegalArgumentException("Argument 'inputs' cannot be null.");
		if (inputs.length == 0)
			throw new IllegalArgumentException("Argument 'inputs' must have elements.");
		for (int idx = 0; idx < inputs.length; idx++) {
			if (inputs[idx] == null)
				throw new IllegalArgumentException("Element '" + idx + "' in argument 'inputs' cannot be null.");
		}
	}

	public default void validate(int length, Number... inputs) {
		validate(inputs);
		if (inputs.length != length)
			throw new IllegalArgumentException(
					"Argument 'inputs.length' mismath, expected '" + length + "', but found '" + inputs.length + "'");
	}

}
