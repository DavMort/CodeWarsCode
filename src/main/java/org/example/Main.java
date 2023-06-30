package org.example;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Long list of random solutions for CodeWars problems I have solved
// Documentation on what problem they "solve" is lacking as I didn't think to add it on most of them.
public class Main {
    public static void main(String[] args) {
        String aaa = "abcde";
        System.out.println(aaa.codePointAt(0));
    }


    // Return the word in a given string with the highest value (a = 1, b = 2, c = 3 etc..)
    public static String high(String s) {
        String result = "";
        int score = 0;
        String[] arr = s.split("");
        for (String str : arr) {
            int newScore = 0;
            for (int i = 0; i < str.length(); i++) {
                newScore += str.codePointAt(i) - 96;
            }
            if (newScore > score) {
                score = newScore;
                result = str;
            }
        }
        return result;
    }

    //Return sum of all positive numbers in an array
    public static int sum(int[] arr){
        int sum = 0;
        for (int i : arr) {
            if (i > 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static String camelCase(String input) {
        return String.join(" ", input.split("((?=[A-Z]))"));
    }

    public static class Fighter {
        public String name;
        public int health, damagePerAttack;
        public Fighter(String name, int health, int damagePerAttack) {
            this.name = name;
            this.health = health;
            this.damagePerAttack = damagePerAttack;
        }
    }
    public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
        int count1 = 0;
        int count2 = 0;
        do {
            fighter2.health -= fighter1.damagePerAttack;
            count1++;
        } while (fighter2.health > 0);
        do {
            fighter1.health -= fighter2.damagePerAttack;
            count2++;
        } while (fighter1.health > 0);
        if (fighter1.name.equals(firstAttacker)) {
            count2++;
        } else {
            count1++;
        }
        return count1 < count2 ? fighter1.name : fighter2.name;
    }


    // Take an integer n (n >= 0) and a digit d (0 <= d <= 9) as an integer.
    // Square all numbers k (0 <= k <= n) between 0 and n.
    // Count the numbers of digits d used in the writing of all the k**2.
    // Return number of times int (d) is used in squared numbers of (n) starting from 0 to n.
    public static int nbDig(int n, int d) {
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (String.valueOf(i * i).contains(String.valueOf(d))) {
                int length = String.valueOf(i * i).length() - String.valueOf(i * i).replaceAll(String.valueOf(d), "").length();
                count += length;
            }
        }
        return count;
    }

    // Square every digit of a number and concatenate them
    // Example, if we run 9119 through the function, 811181 will come out, because 9^2 is 81 and 1^2 is 1. (81-1-1-81)
    public static int squareDigits(int n) {
        String[] temp = String.valueOf(n).split("");
        StringBuilder res = new StringBuilder();
        for (String s : temp) {
            res.append(Integer.parseInt(s) * Integer.parseInt(s));
        }
        return Integer.parseInt(res.toString());
    }

    // Multiplying a given number by eight if it is an even number and by nine otherwise.
    public static int simpleMultiplication(int n) {
        if (n % 2 == 0) {
            return n * 8;
        }
        return n * 9;
        // Single line: return n % 2 == 0 ? n * 8 : n * 9;
    }

    // Very simple, given an integer or a floating-point number, find its opposite.
    public static int opposite(int number) {
        return number * -1;
    }

    // Given a list of numbers, return a fixed list so that the values increment by 1 for each index from the minimum value up to the maximum value (both included).
    // example input 1, 3, 5, 7 -> output 1, 2, 3, 4, 5, 6, 7
    public static int[] pipeFix(int[] numbers) {
        int[] temp = new int[numbers[numbers.length - 1] - numbers[0] + 1];
        temp[0] = numbers[0];
        for (int i = 1; i < temp.length; i++) {
            temp[i] = numbers[0] + i;
        }
        return temp;
        // BETTER SOLUTION:
        // return IntStream.rangeClosed(numbers[0], numbers[numbers.length - 1]).toArray();
    }

    // Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence,
    // which is the number of times you must multiply the digits in num until you reach a single digit.
    public static int persistence(long n) {
        if (n < 10) {
            return 0;
        }
        int count = 0;
        do {
            long[] numArr = Arrays.stream(String.valueOf(n).split("")).mapToLong(Long::parseLong).toArray();
            n = 1;
            for (long l : numArr) {
                n *= l;
            }
            count++;
        } while (n >= 10);
        return count;
    }

    public static int expressionsMatter(int a, int b, int c) {
        int max = 0;
        max = Math.max(Math.max(Math.max((a + b + c), ((a + b) * c)), (a * c) * b), (c + b) * a);
        return max;
    }

    public static String expandedForm(int num) {
        //your code here
        StringBuilder result = new StringBuilder();
        String temp = String.valueOf(num);
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != '0') {
                result.append(temp.charAt(i)).append("0".repeat(temp.length() - i - 1)).append(" + ");
            }
        }
        return result.substring(0, result.length() - 3);
    }

    public static String order(String words) {
        String[] wordArr = words.split(" ");
        String[] sorted = new String[wordArr.length];

        for (String s : wordArr) {
            for (int i = 0; i < s.length(); i++) {
                if (s.substring(i, i + 1).matches("[1-9]")) {
                    int num = Integer.parseInt(s.substring(i, i + 1));
                    sorted[num - 1] = s;
                }
            }
        }
        return String.join(" ", sorted);
    }



    public class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(Node l, Node r, int v) {
            left = l;
            right = r;
            value = v;
        }
    }
    public static List<Integer> treeByLevels(Node node) {
        if (node == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(node);
        while (!nodes.isEmpty()) {
            Node n = nodes.remove();
            result.add(n.value);
            if (n.left != null) {
                nodes.add(n.left);
            }
            if (n.right != null) {
                nodes.add(n.right);
            }
        }
        return result;
    }

    public static String[] stringToArray(String s) {
        //your code
        return s.split(" " + ", ");
    }

    static int stray(int[] numbers) {
        int[] n = Arrays.stream(numbers).sorted().toArray();
        if (numbers[0] == numbers[1]) {
            return numbers[numbers.length - 1];
        } else return numbers[0];
    }

    public static boolean isSquare(int n) {
        for (int i = 1; i < n; i++) {
            if (i * i == n) {
                return true;
            }
        }
        return false;
        //return Math.sqrt(n) % 1 == 0;
    }

    public static int sortDesc(final int num) {
        String numString = String.valueOf(num);
        String[] numArr = numString.split("");
        for (int i = 1; i < numArr.length; i++) {
            if (Integer.parseInt(numArr[i - 1]) < Integer.parseInt(numArr[i])) {
                String temp = numArr[i - 1];
                numArr[i - 1] = numArr[i];
                numArr[i] = temp;
                i = 0;
            }
        }
        //Your code
        return Integer.parseInt(String.join("", numArr));
    }

    //Swap all characters from uppercase to lowercase and lowercase to uppercase in a string
    public static String toAlternativeString(String string) {
        return Arrays.stream(string.split("")).map(s -> s.matches("[a-z]+") ? s.toUpperCase() : s.toLowerCase()).collect(Collectors.joining());
    }

    public static String hoopCount(int n){
        return n >= 10 ? "Great, now move on to tricks" : "Keep at it until you get it";
    }

    public static String reverseWords(final String original) {

        return original.replaceAll(" ", "").length() > 0 ? Arrays.stream(original.split(" ")).map(s -> new StringBuilder(s).reverse().toString()).collect(Collectors.joining(" ")) : original;
    }

    public static boolean solution(String str, String ending) {
        return str.endsWith(ending);
    }

    public String toJadenCae(String phrase) {
        return phrase != null || phrase.equals("") ? Arrays.stream(phrase.split(" ")).map(s -> s.replace(s.charAt(0), s.substring(0, 1).toUpperCase().charAt(0))).collect(Collectors.joining(" ")).trim() : null;
    }

    //In this kata you will create a function that takes a list of non-negative integers and strings and returns a new list with the strings filtered out.
    public static List<Object> filterList(final List<Object> list) {
        return list.stream().map(o -> o instanceof Integer ? (Object)Integer.parseInt(o.toString()) : null).filter(Objects::nonNull).toList();
        //return list.stream().filter(o -> o instanceof Integer).collect(Collectors.toList());
    }

    //Move the first letter of each word to the end of it, then add "ay" to the end of the word. Leave punctuation marks untouched.
    public static String pigIt(String str) {
        return Arrays.stream(str.split(" ")).map(s -> s.matches("[a-zA-Z]+") ? s.substring(1) + s.charAt(0) + "ay" : s).collect(Collectors.joining(" "));
    }
    public static long nextBiggerNumber(long n) {
        char[] numbers = String.valueOf(n).toCharArray();
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                char old = numbers[i - 1];
                char newNum = numbers[i];
                numbers[i - 1] = newNum;
                numbers[i] = old;
                for (int k = numbers.length - 1; k > i; k--) {
                    if (numbers[k] > old && numbers[k] < newNum) {
                        numbers[i - 1] = numbers[k];
                        numbers [k] = newNum;
                        newNum = numbers[i - 1];
                        k = numbers.length - 1;
                    }
                }
                for (int j = numbers.length - 1; j > i; j--) {
                    if (numbers[j] < numbers[j - 1]) {
                        char smaller = numbers[j];
                        numbers[j] = numbers[j - 1];
                        numbers[j - 1] = smaller;
                        j = numbers.length;
                    }
                }
                StringBuilder res = new StringBuilder();
                for (char c : numbers) {
                    res.append(c);
                }
                return Long.parseLong(res.toString());
            }
        }
        return -1L;
    }

    public static int calculateYears(double principal, double interest,  double tax, double desired) {
        int count = 0;
        double taxx;
        if (principal == desired) {
            return 0;
        }
        do {
            taxx = Math.round(principal * interest * tax);
            principal = Math.round(principal * interest);
            principal -= taxx;
            count++;
        } while (principal < desired);
        return count;
    }
    public static int[] between(int a, int b) {
        int[] res = new int[b - a + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = a;
            a++;
        }
        return res;
    }
    public static String noSpace(final String x) {
        return x.replace(" ", "");
    }
    public static long[] powersOfTwo(int n) {
        long[] result = new long[n + 1];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = (long)i * 2;
        }
        return result;
    }

    public static String howMuchILoveYou(int nb_petals) {
        while (nb_petals > 6) {
            nb_petals -= 6;
        }
        //your code here :)

        switch (nb_petals) {
            case 1:
                return  "I love you";
            case 2:
                return  "a little";
            case 3:
                return  "a lot";
            case 4:
                return  "passionately";
            case 5:
                return  "madly";
            case 6:
                return  "not at all";
            default:
                return null;
        }
    }

    public int Liters(double time)  {
        return (int)Math.floor(time * 0.5);
    }

    public static int TwiceAsOld(int dadYears, int sonYears){
        //TODO: Add code here
        int count = 0;
        if ((dadYears / sonYears) > 2) {
            do {
                dadYears -= 1;
                sonYears -= 1;
                count++;
            } while (dadYears / sonYears != 2);
        } else {
            do {
                dadYears += 1;
                sonYears += 1;
                count++;
            } while (dadYears / sonYears != 2);
        }
        return count;
    }

    public static int findShort(String s) {
        List<String> sList = new ArrayList<>(Arrays.stream(s.split(" ")).toList());
        int small = sList.get(0).length();
        for (String str : sList) {
            small = Math.min(small, str.length());
        }
        return small;
    }

    public static String rps(String p1, String p2) {
        // your code
        if (p1.equals(p2)) {
            return "Draw!";
        }
        if ((p1.equals("rock") && p2.equals("scissors")) || (p1.equals("scissors") && p2.equals("paper")) || (p1.equals("paper") && p2.equals("rock"))) {
            return "Player 1 won!";
        } else {
            return "Player 2 won!";
        }
    }

    public static String highAndLow(String numbers) {
        // Code here or
        List<Integer> sList = new ArrayList<>(Arrays.stream(numbers.split(" ")).toList().stream().map(Integer::parseInt).toList());
        Collections.sort(sList);
        return String.format("%d %d", sList.get(sList.size() - 1), sList.get(0));
    }

    public static int countDuplicate(List<Integer> numbers) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (Objects.equals(numbers.get(i), numbers.get(j))) {
                    temp.add(numbers.get(i));
                }
            }
        }
        return (int)temp.stream().distinct().count();
    }

    //Return true of there is an equal number of X's and O's in a string
    public static boolean getXO (String str) {
        str = str.toLowerCase();
        return str.replace("o", "").length() == str.replace("x", "").length();
    }
    //Return inverted numbers, positive become negative, negative become positive
    public static int[] invert(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= -1;
        }
        return array;
    }
    //Jaden(Smith)-Cased strings - Every Word Is Capitalized
    public String toJadenCase(String phrase) {
        if (phrase == null || phrase.equals("")) {
            return null;
        }
        String[] strArr = phrase.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].substring(0, 1).toUpperCase() + strArr[i].substring(1);
        }
        return String.join(" ", strArr);
    }
    //Return the smalles integer of an array
    public static int findSmallestInt(int[] args) {
        int small = args[0];
        for (int i : args) {
            if (i < small) {
                small = i;
            }
        }
        return small;
    }
    //Return true if a triangle can be made with sides of length (a, b and c)
    public static boolean isTriangle(int a, int b, int c){
        int max = Math.max(Math.max(a, b), c);
        int total = a + b + c;
        if ((total - max) < max) {
            return false;
        }
        return true;
    }
    //Convert an array of int (binary) to int number
    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        StringBuilder num = new StringBuilder();
        for (Integer i : binary) {
            num.append(i);
        }
        return Integer.parseInt(num.toString(), 2);
    }
        //int to string
    public static String numberToString(int num) {
        // Return a string of the number here!
        return String.valueOf(num);
    }

    public static boolean validatePin(String pin) {
        // Your code here...
        if (pin.matches("[0-9]+") && (pin.length() == 4 || pin.length() == 6)) {
            return true;
        } else {
            return false;
        }
        //return pin.matches("[0-9]{4}|[0-9]{6}");
    }

    public static int nbYear(int p0, double percent, int aug, int p) {
        // your code
        int years = 0;
        while (p0 <= p) {
            p0 *= (percent / 100);
            p0 += aug;
            years++;
        }
        return years;
    }

    public static void fizzBuzz(int n) {
        for (int i = 0; i < n; i++) {
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else {
                System.out.println(i);
            }
        }
    }

    public static List<String> number(List<String> lines) {
        List<String> result = new ArrayList<>(lines.size());
        for (int i = 0; i < result.size(); i++) {
            result.add((i + 1) + ": " + lines.get(i));
        }
        return result;
    }
    public static int howOld(final String herOld) {

        //your code here, return correct age as int ; )
        return Integer.parseInt(herOld.substring(0, 1));
    }

    public static Integer basicMath(String op, int v1, int v2) {
        switch (op) {
            case "+" -> {
                return v1 + v2;
            }
            case "-" -> {
                return v1 - v2;
            }
            case "*" -> {
                return v1 * v2;
            }
            case "/" -> {
                return v1 / v2;
            }
            default -> {
                return null;
            }
        }
    }
    public static int areaOrPerimeter (int l, int w) {
        // code away...
        if (l == w) {
            return l * w;
        }
        return l * 2 + w * 2;
    }

    public static boolean betterThanAverage(int[] classPoints, int yourPoints) {
        // Your code here
            return (double)yourPoints > Arrays.stream(classPoints).average().getAsDouble();
    }
    public static String findNeedle(Object[] haystack) {
        // Your code here
        int index = 0;
        for (Object o : haystack) {
            if (o.equals("needle")) {
                return "found the needle at position " + index;
            }
            index++;
        }
        return null;
    }

    public static String oddOrEven (int[] array) {
        // your code
        // if empty consider array as 0 "even"
        return array.length == 0 || Arrays.stream(array).sum() % 2 == 0 ? "even" : "odd";

    }
}