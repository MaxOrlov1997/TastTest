import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*Если мы из корректно записанного арифметического выражения, содержащего числа, знаки операций и открывающие и
закрывающие круглые скобки выбросим числа и знаки операций, а затем запишем оставшиеся в выражении скобки без пробелов
между ними, то полученный результат назовем правильным скобочным выражением [скобочное выражение "(()(()))" - правильное,
а "()(" и "())(" - нет]. Найти число правильных скобочных выражений, содержащих N открывающихся и N закрывающихся скобок.
N вводится с клавиатуры. N неотрицательное целое число.*/
public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if (N < 0) {
            System.out.println("Incorrect available N");
        } else {
            List<String> results = generateListBracket(N);
            for (String expression : results) {
                System.out.println(expression);
            }
        }
    }

    // Others correct bracket variant in List
    public static List<String> generateListBracket(int N) {
        List<String> result = new ArrayList<>();
        generateCombinationsBracket(result, "", 0, 0, N);
        return result;
    }

    // method to generate correct bracket, it's my and chatgpt cod
    private static void generateCombinationsBracket(List<String> result, String current, int open, int close, int N) {
        // add correct variant bracket if she has size N * 2
        if (current.length() == N * 2) {
            result.add(current);
            return;
        }

        // Add open bracket, if open bracket < N
        if (open < N) {
            generateCombinationsBracket(result, current + "(", open + 1, close, N);
        }

        // Add close bracket, if close bracket < open bracket
        if (close < open) {
            generateCombinationsBracket(result, current + ")", open, close + 1, N);
        }
    }
}
