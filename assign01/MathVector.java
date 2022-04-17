package assign01;

import java.util.Arrays;
import java.lang.Math;
import java.util.*;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (i.e., along the columns).
 * In a column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Erin Parker & Ryan Russell (STUDENT: Replace the ?? with your first and last name.)
 * @version January 13, 2022
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;      
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;
	
	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not 
	 *         compatible with a row or column vector
	 */
	public MathVector(double[][] data) {
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");
		
		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");
		
		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i=0; i < this.data.length; i++) { 
			for(int j=0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same 
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MathVector))
			return false;
		
		MathVector otherVec = (MathVector)other;

		if(this.vectorSize != otherVec.vectorSize) {
			// Check if both vectors are same size
			return false;
		}
		if(this.isRowVector != otherVec.isRowVector) {
			// Check if both vectors are each column or row
			return false;
		}
		// Compares values based on index
		if(!(this.isRowVector)) {
			for(int i = 0; i < this.vectorSize; ++i) {
				if (this.data[i][0] != otherVec.data[i][0]) {
					return false;
				}
			}
		} else {
			for(int i = 0; i < this.vectorSize; ++i) {
				if (this.data[0][i] != otherVec.data[0][i]) {
					return false;
				}
			}
		}

		return true;
	}
	
	/**
	 * Generates and returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose() {
		// Converts column vector to row vector
		if (!(this.isRowVector)) {
			double [][]newVec = new double [1][vectorSize];
			for(int i = 0; i < this.vectorSize; ++i) {
				newVec[0][i] = this.data[i][0];
			}
			return new MathVector(newVec);
		// Converts row vector to column vector
		} else {
			double [][]newVec = new double [vectorSize][1];
			for(int j = 0; j < this.vectorSize; ++j) {
				newVec[j][0] = this.data[0][j];
			}
			return new MathVector(newVec);
		}
	}
	
	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		MathVector otherVec = (MathVector)other;

		if(this.vectorSize != otherVec.vectorSize) {
			throw new IllegalArgumentException("Vectors must be same in size.");
		}
		if(this.isRowVector != otherVec.isRowVector) {
			throw new IllegalArgumentException("Vector must be of same type (row or col).");
		}
		// Adds row vectors and column vectors
		if (!(this.isRowVector)) {
			double [][]sumVec = new double [vectorSize][1];
			for(int i = 0; i < this.vectorSize; i++) {
				sumVec[i][0] = this.data[i][0] + otherVec.data[i][0];
			}
			return new MathVector(sumVec);
		} else {
			double [][]sumVec = new double [1][vectorSize];
			for(int i = 0; i < this.vectorSize; i++) {
				sumVec[0][i] = this.data[0][i] + otherVec.data[0][i];
			}
			return new MathVector(sumVec);
		}
	}

	
	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */	
	public double dotProduct(MathVector other) {
		MathVector otherVec = (MathVector)other;
		if(this.vectorSize != otherVec.vectorSize) {
			throw new IllegalArgumentException("Vectors must be same in size.");
		}
		if(this.isRowVector != otherVec.isRowVector) {
			throw new IllegalArgumentException("Vector must be of same type (row or col).");
		}
		// Calculates dot product with row vectors and column vectors
		if (!(this.isRowVector)) {
			double result = 0.0;
		    for (int i = 0; i < this.vectorSize; i++) {
		    	result += (this.data[i][0] * otherVec.data[i][0]);
		    }
		    return result;
		} else {
			double result = 0.0;
		    for (int i = 0; i < this.vectorSize; i++) {
		    	result += (this.data[0][i] * otherVec.data[0][i]);
		    }
		    return result;
		}
	}
	
	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length) .
	 */
	public double magnitude() {
		// Calculates magnitude with row vectors and column vectors
		if (!(this.isRowVector)) {
			double result = 0.0;
	        for (int i = 0; i < this.vectorSize; i++) {
		    	result += this.data[i][0] * this.data[i][0];
	        }
			return Math.sqrt(java.lang.Math.abs(result));
		} else {
			double result = 0.0;
	        for (int i = 0; i < this.vectorSize; i++) {
		    	result += this.data[0][i] * this.data[0][i];
	        }
			return Math.sqrt(java.lang.Math.abs(result));
		}
	}
	
	/** 
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		// Normalizes row or col vectors
		if (!(this.isRowVector)) {
			double [][]normVec = new double [vectorSize][1];
			for (int i = 0; i < this.vectorSize; i++) {
				//divide each element by the vectors magnitude.
		    	normVec[i][0] += this.data[i][0] / this.magnitude();
			}
			return new MathVector(normVec);
		} else {
			double [][]normVec = new double [1][vectorSize];
			for (int i = 0; i < this.vectorSize; i++) {
		    	normVec[0][i] += this.data[0][i] / this.magnitude();
			}
			return new MathVector(normVec);
		}
	}
	
	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and 
	 * "1.0
	 *  2.0
	 *  3.0
	 *  4.0
	 *  5.0" for a sample column vector of length 5. 
	 *  In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		//Prints column vector
		if (!(this.isRowVector)) {
			String str = "";
			for(int i = 0; i < this.vectorSize - 1; i++) {
				str += this.data[i][0];
				str += "\n";
		    }
			str += this.data[this.vectorSize - 1][0];
			return str;
		} else {
		//Prints row vector
			String str = "";
			for(int i = 0; i < this.vectorSize - 1; i++) {
				str += this.data[0][i];
				str += " ";
			}
			str += this.data[0][this.vectorSize - 1];
			return str;
		}
	}
}
