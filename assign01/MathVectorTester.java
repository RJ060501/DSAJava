package assign01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author Erin Parker & ?? (STUDENT: Replace the ?? with your first and last name.)
 * @version January 13, 2022
 */
class MathVectorTester {
	
	private MathVector rowVec, rowVecTranspose, unitVec, sumVec, colVec, otherUnitVec, colVecTranspose, otherSumVec;

	@Before
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});
		
		// Creates a row vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVecTranspose = new MathVector(new double[][]{{-11, 2.5, 36, -3.14, 7.1}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a column vector with five elements: 1.0, 1.0, 1.0, 1.0, 1.0
		otherUnitVec = new MathVector(new double[][]{{1}, {1}, {1}, {1}, {1}});
		
		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});
		
		// Creates a column vector with five elements: 10, 3.5, 37, -2.14, 8.1
		otherSumVec = new MathVector(new double[][]{{-10}, {3.5}, {37}, {-2.14}, {8.1}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});		
	}

	@After
	void tearDown() throws Exception {
	}
	
	@Test
	void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	void smallColVectorEquality() {
		assertTrue(colVec.equals(new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}})));
	}
	
	@Test
	void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}
	
	@Test
	void smallColVectorSizeInequality() {
		assertFalse(colVec.equals(unitVec));
	}

	@Test
	public void createVectorFromBadArray() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}
	
	@Test
	void transposeSmallColVector() {
		MathVector transposeResult = colVec.transpose();
		assertTrue(transposeResult.equals(colVecTranspose));
	}
	
	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));		
	}
	
	// Added "otherSumVec" to "setUp()"
	@Test
	void addSmallColVectors() {
		MathVector addResult = colVec.add(otherUnitVec);
		assertTrue(addResult.equals(otherSumVec));		
	}
	
	@Test
	public void dotRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.dotProduct(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}

	@Test
	void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);		
	}
	
	@Test
	void dotProductSmallColVectors() {
		double dotProdResult = colVec.dotProduct(otherUnitVec);
		assertEquals(dotProdResult, -11 * 1.0 + 2.5 * 1.0 + 36 * 1.0 + -3.14 * 1.0 + 7.1 * 1.0);		
	}
	
	@Test
	void smallRowVectorLength() {
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	
	//ADDED
	@Test
	void smallColVectorLength() {
		double vecLength = colVec.magnitude();
		assertEquals(vecLength, Math.sqrt(-11 * -11 + 2.5 * 2.5 + 36 * 36 + -3.14 * -3.14 + 7.1 * 7.1));		
	}
	
	@Test
	void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));		
	}
	
	//ADDED
	@Test
	void smallColVectorNormalize() {
		MathVector normalVec = colVec.normalize();
		double length = Math.sqrt(-11 * -11 + 2.5 * 2.5 + 36 * 36 + -3.14 * -3.14 + 7.1 * 7.1);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{-11 / length}, {2.5 / length}, {36 / length}, {-3.14 / length}, {7.1 / length}})));		
	}
	
	@Test
	void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());		
	}
	
	@Test
	void smallRowVectorToString() {
		String resultStr = "3.0 1.0 2.0";
		assertEquals(resultStr, rowVec.toString());		
	}


}

// Other Test Ideas in mind !
// Row vector to string
// Adding two col vectors
// Add rowvecdifflength
// illegalarguement exception for dot product (different types, same type different length)
// large vectors for every test make vector with 10+ items?
