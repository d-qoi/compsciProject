package testing;

import org.ejml.data.DenseMatrix64F;

public class Matrixtesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] testdata = {{1.1,2.2,3.3},{4.4,5.5,6.6}};
		DenseMatrix64F test = new DenseMatrix64F(testdata);
		System.out.println(test.get(0,0));

	}

}
