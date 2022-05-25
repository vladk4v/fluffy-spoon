import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorTest {

	static Stream<Arguments> input() {
		return Stream.of(
				arguments(2.5, 3.0, 5.5),
				arguments(5.1, 5.0, 10.1)
		);
	}

	@ParameterizedTest
	@MethodSource("input")
	void addOperandTest(Double a, Double b, Double c) {
		Calculator.Formula formula = new Calculator.Formula();
		formula.a = a;
		formula.b = b;
		Throwable ex = assertThrows(IllegalStateException.class, () ->
				formula.addOperand(c)
		);
		assertEquals("Formula is full of operands", ex.getMessage());
	}

	@ParameterizedTest
	@EnumSource(value = Calculator.Operation.class)
	void calculateTest(Calculator.Operation op) {
		Calculator.Formula formula = new Calculator.Formula();
		Throwable ex = assertThrows(IllegalStateException.class, () ->
				formula.calculate(op)
		);
		assertEquals("Not enough operands!", ex.getMessage());
	}

	@Test
	void resultTest() {
		Throwable ex = assertThrows(IllegalStateException.class, () ->
				new Calculator.Formula().result()
		);
		assertEquals("Formula is not computed!", ex.getMessage());
	}
}