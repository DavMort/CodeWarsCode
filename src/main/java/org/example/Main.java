package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {

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
}