import java.math.BigDecimal;
import java.math.MathContext;

public class Pi {
	public static final MathContext mc = new MathContext(100);
	public static final int PRECISION = 1000000;

	public static void main(String[] args) {
		int i = 1;
		int currentPrecision = 0;
		while (currentPrecision < PRECISION) {
			currentPrecision = piInfo(i);
			i *= 2;
		}
		System.out.println("It took " + i + " iterations to reach a precision of " + PRECISION);
	}

	public static int piInfo(int num) {
		BigDecimal[] arr = new BigDecimal[3];
		arr[0] = piBest(num);
		arr[1] = piBest(num + 1);
		arr[2] = piBest(num + 2);
		String[] arrStrings = new String[3];
		for (int i = 0; i < 3; i++) {
			arrStrings[i] = arr[i].toString();
		}
		String finalPi = "";
		int index = 0;
		boolean done = false;
		while (index < arrStrings[0].length() && !done) {
			if (arrStrings[0].charAt(index) == arrStrings[1].charAt(index) && arrStrings[0].charAt(index) == arrStrings[2].charAt(index) & arrStrings[1].charAt(index) == arrStrings[2].charAt(index)) {
				index++;
				finalPi += arrStrings[0].charAt(index);	
			} else {
				done = true;
			}
		}
		System.out.println("Pi accurate to " + index + " decimal places:");
		System.out.println(finalPi);
		return index;
	}

	public static double calculatePi(int iterations) {
		double pi = 0;
		int n = iterations;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 1) {
				pi -= 1.0 / (i * 2.0 + 1.0);
			} else {
				pi += 1.0 / (i * 2.0 + 1.0);
			}
		}
		return pi * 4; 
	}

	public static double piImproved(int iterations) {
		double sum = 0;
		for (int n = 0; n < iterations; n++) {
			double factor1 = 4.0/(8.0*n+1.0)-2.0/(8.0*n+4.0)-1.0/(8.0*n+5)-1.0/(8.0*n+6);
			double factor2 = Math.pow(1.0/16.0, n);
			sum += factor1 * factor2;
		}
		return sum;
	}
	
	public static BigDecimal piBest(int iterations) {
		BigDecimal sum = new BigDecimal(0);
		for (int n = 0; n < iterations; n++) {
			BigDecimal factor1 = new BigDecimal(4).divide(new BigDecimal(8).multiply(new BigDecimal(n), mc).add(new BigDecimal(1), mc), mc);
			BigDecimal factor2 = new BigDecimal(2).divide(new BigDecimal(8).multiply(new BigDecimal(n), mc).add(new BigDecimal(4), mc), mc);
			BigDecimal factor3 = new BigDecimal(1).divide(new BigDecimal(8).multiply(new BigDecimal(n), mc).add(new BigDecimal(5), mc), mc);
			BigDecimal factor4 = new BigDecimal(1).divide(new BigDecimal(8).multiply(new BigDecimal(n), mc).add(new BigDecimal(6), mc), mc);
			BigDecimal factor5 = factor1.subtract(factor2, mc).subtract(factor3, mc).subtract(factor4, mc);
			BigDecimal factor6 = new BigDecimal(1).divide(new BigDecimal(16), mc).pow(n, mc);
			sum = sum.add(factor5.multiply(factor6, mc));
		}
		return sum;
	}
}
