import java.math.BigDecimal;
import java.math.BigInteger;

public class Fibonacci {

    public static final BigDecimal ONE = new BigDecimal("1");
    public static final BigDecimal TWO = new BigDecimal("2");
    public static final BigDecimal SQRT_FIVE = new BigDecimal(Math.sqrt(5));

    /** 
     * @param n 
     * @return Computes the n-th number in Fibonacci series
     */
    public static int fib(int n) {
        BigInteger x = new BigInteger("0");
        BigInteger y = new BigInteger("1");
        BigInteger z = new BigInteger("1");

        for(int i = 0; i < n; i++) {
            x = y;
            y = z;

            z = x.add(y);
        }

        return x.intValue();
    }

    public static int find_index(int precision) {
        BigDecimal desiredGoldenRatio = ONE.add(SQRT_FIVE).divide(TWO, precision, BigDecimal.ROUND_DOWN);
        BigDecimal actualGoldenRatio;

        int i = 3;

        while(true) {
            BigDecimal a = new BigDecimal(fib(i));
            BigDecimal b = new BigDecimal(fib(i - 1));

            actualGoldenRatio = a.divide(b, precision, BigDecimal.ROUND_DOWN);

            if(actualGoldenRatio.equals(desiredGoldenRatio))
                return i;

            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println(find_index(5));
    }

}