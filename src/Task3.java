import java.math.BigInteger;
/*Find the sum of the digits in the number 100! (i.e. 100 factorial)
{Correct answer: 648}*/
public class Task3 {
    public static void main(String[] args) {
        int correctSum = 0;
        BigInteger valueSumFact = calculateFactorial(100);
        // factorial transformed to string
        String factNum = valueSumFact.toString();
        for (char t: factNum.toCharArray()) {
            // transformed figure to number and sum
            correctSum += Character.getNumericValue(t);
        }
        System.out.println(correctSum);
    }
// cod return factorial 100, (int) - very small for factorial 100
    public static BigInteger calculateFactorial(int num) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            // calculating factorial 100
          result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
