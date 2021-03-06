package tesky.mn2;

import org.junit.Test;
import tesky.mn2.eigenvalues.Eigenvalue;
import tesky.mn2.eigenvalues.Main;
import tesky.mn2.util.Matrix;
import tesky.mn2.util.Result;
import tesky.mn2.util.Vector;

import static org.junit.Assert.assertEquals;

public class UtilTest {
    @Test
    public void shouldValidateCopyVector() {
        Vector v1 = generateVector();
        Vector v2 = generateAnotherVector();

        v1.copy(v2);

        for (int i = 0; i < v2.vector.size(); i++) {
            assertEquals(v2.vector.get(i), v1.vector.get(i));
        }
    }

    @Test
    public void shouldValidateDotProduct() {
        Double dotProduct = generateVector().dotProduct(generateAnotherVector());
        Double expectedProduct = 13.0;

        assertEquals(expectedProduct, dotProduct);
    }

    @Test
    public void shouldValidateMatrixVectorMultiplication() {
        Vector vectorCalculated = generateMatrix().vectorMultiplication(generateVector());

        assertEquals(Double.valueOf(12.0), vectorCalculated.vector.get(0));
        assertEquals(Double.valueOf(11.0), vectorCalculated.vector.get(1));
        assertEquals(Double.valueOf(9.0), vectorCalculated.vector.get(2));
    }

    @Test
    public void shouldValidateNormVector() {
        Double norm = generateVector().normCalculate();
        assertEquals(Double.valueOf(3.7416573867739413), norm);
    }

    @Test
    public void shouldValidateNormalizeVector() {
        Vector normalizedVector = generateVector().normalize();

        assertEquals(Double.valueOf(0.2672612419124244), normalizedVector.vector.get(0));
        assertEquals(Double.valueOf(0.5345224838248488), normalizedVector.vector.get(1));
        assertEquals(Double.valueOf(0.8017837257372732), normalizedVector.vector.get(2));
    }

    @Test
    public void shouldSwapValuesInVector() {
        Vector vector = generateVector();
        Vector.swap(vector, 0, 1);

        assertEquals(Double.valueOf(2.0), vector.get(0));
        assertEquals(Double.valueOf(1.0), vector.get(1));
        assertEquals(Double.valueOf(3.0), vector.get(2));
    }

    @Test
    public void shouldSwapValuesInMatrix() {
        Matrix matrix = generateMatrix();
        Matrix.swap(matrix, 0, 0, 2, 2);

        assertEquals(Double.valueOf(2.0), matrix.get(0, 0));
        assertEquals(Double.valueOf(3.0), matrix.get(1, 1));
        assertEquals(Double.valueOf(5.0), matrix.get(2, 2));
    }

    @Test
    public void test() {
        Result result1 = Eigenvalue.inversePowerLU(generateMatrix(), new Vector(1.0, 1.0, 1.0), 0.00001);
        Result result2 = Eigenvalue.inversePower(generateMatrix(), new Vector(1.0, 1.0, 1.0), 0.00001);

        System.out.println("Autovalor R1: " + result1.getEigenvalue());
        System.out.println("Autovetor R1: " + result1.getEigenvector());
        System.out.println();
        System.out.println("Autovalor R2: " + result2.getEigenvalue());
        System.out.println("Autovetor R2: " + result2.getEigenvector());
    }

    private Matrix generateMatrix() {
        return new Matrix(new Vector(5.0, 2.0, 1.0),
                new Vector(2.0, 3.0, 1.0),
                new Vector(1.0, 1.0, 2.0));
    }

    private Vector generateVector() {
        return new Vector(1.0, 2.0, 3.0);
    }

    private Vector generateAnotherVector() {
        return new Vector(2.0, 1.0, 3.0);
    }
}
