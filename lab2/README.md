# MaxFinder Exercise

In this exercise, you will implement a Chisel module that finds the maximum value in a vector of unsigned integers using a tree-based reduction (`reduceTree`).

## Steps

1. Implement a `MaxFinder` module that takes a `Vec` of `UInt` values as input and outputs the maximum value.
	- Use Chisel's `reduceTree` or a similar tree-based approach for efficiency.

2. Write a `ChiselTest` for your `MaxFinder` to verify its functionality:
	- Test with different input vectors to ensure the correct maximum is found.
	- Include edge cases, such as all zeros or all equal values.

3. (Optional) Extend the circuit to also return the index of the maximum value. Hint: lookup `zipWithIndex` and use tuples. Use `reduce` to find both the maximum value and its index.

4. Emit Verilog for your `MaxFinder` module and explore the generated code. You can try to synthesize it.