package test.app;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static final Map<String, Integer> romanArabicMap = new HashMap<>(); // инициализация пустой HashMap
    private static final Map<Integer, String> arabicRomanMap = new HashMap<>(); // инициализация пустой HashMap

    static {
        romanArabicMap.put("I", 1);
        romanArabicMap.put("II", 2);
        romanArabicMap.put("III", 3);
        romanArabicMap.put("IV", 4);
        romanArabicMap.put("V", 5);
        romanArabicMap.put("VI", 6);
        romanArabicMap.put("VII", 7);
        romanArabicMap.put("VIII", 8);
        romanArabicMap.put("IX", 9);
        romanArabicMap.put("X", 10);

        arabicRomanMap.put(1, "I");
        arabicRomanMap.put(2, "II");
        arabicRomanMap.put(3, "III");
        arabicRomanMap.put(4, "IV");
        arabicRomanMap.put(5, "V");
        arabicRomanMap.put(6, "VI");
        arabicRomanMap.put(7, "VII");
        arabicRomanMap.put(8, "VIII");
        arabicRomanMap.put(9, "IX");
        arabicRomanMap.put(10, "X");
        arabicRomanMap.put(20, "XX");
        arabicRomanMap.put(30, "XXX");
        arabicRomanMap.put(40, "XL");
        arabicRomanMap.put(50, "L");
        arabicRomanMap.put(60, "LX");
        arabicRomanMap.put(70, "LXX");
        arabicRomanMap.put(80, "LXXX");
        arabicRomanMap.put(90, "XC");
        arabicRomanMap.put(100, "C");
        arabicRomanMap.put(200, "CC");
        arabicRomanMap.put(300, "CCC");
        arabicRomanMap.put(400, "CD");
        arabicRomanMap.put(500, "D");
        arabicRomanMap.put(600, "DC");
        arabicRomanMap.put(700, "DCC");
        arabicRomanMap.put(800, "DCCC");
        arabicRomanMap.put(900, "CM");
        arabicRomanMap.put(1000, "M");

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите выражение: ");
            String input = scanner.nextLine();

            if (!input.contains("exit")){
                System.out.println("Результат операции: "+ getResult(input));
            }

            if (input.equals("exit")) {
                break;
            }
        }
    }
    private static String getResult(String operation) {
        int result = 0;
        //String arabianRegex = "[1-9]|10[\\+\\-\\*/][1-9]|10";
        String arabianRegex = "\\b(?:[1-9]|10)\\s[\\+\\-\\*\\/]\\s(?:[1-9]|10)\\b";
        //String romanRegex = "(I|II|III|IV|V|VI|VII|VIII|IX|X)[\\+\\-\\*/](I|II|III|IV|V|VI|VII|VIII|IX|X)";
        String romanRegex = "\\b(?:I|II|III|IV|V|VI|VII|VIII|IX|X)\\s[\\+\\-\\*\\/]\\s(?:I|II|III|IV|V|VI|VII|VIII|IX|X)\\b";
        String[] operArr = operation.split(" ");
        String strResult = "";
        if (operation.matches(arabianRegex)) {
            int num1 = Integer.parseInt(operArr[0]);
            int num2 = Integer.parseInt(operArr[2]);
            switch (whichOperator(operation)) {
                case "+" -> result = add(num1, num2);
                case "-" -> result = substrat(num1, num2);
                case "*" -> result = multiply(num1, num2);
                case "/" -> result = divide(num1, num2);
            }
            strResult = result + "";
        } else if (operation.matches(romanRegex)) {
            int num1;
            int num2;
            try {
                num1 = romanArabicMap.get(operArr[0]);
                num2 = romanArabicMap.get(operArr[2]);
            } catch (InputMismatchException e) {
                throw new InputMismatchException("Неверный формат введенных данных");
            }
            switch (whichOperator(operation)) {
                case "+" -> result = add(num1, num2);
                case "-" -> result = substrat(num1, num2);
                case "*" -> result = multiply(num1, num2);
                case "/" -> result = divide(num1, num2);
            }
            if (result <= 0 ){
                throw new IndexOutOfBoundsException("Римское число/цифра не может быть меньше нуля \"0\" ");
            }
            strResult = arabianToRomanConverter(result);
        } else throw new InputMismatchException("Неверный формат ввода данных");


        return strResult;
    }


    private static String arabianToRomanConverter(int input) {
        String romanResult = "";
        while (input != 0) {
            if(input >= 1000){
                input = input - 1000;
                romanResult = romanResult + arabicRomanMap.get(1000);
            }
           else if(input >= 500){
                input = input - 500;
                romanResult = romanResult + arabicRomanMap.get(500);
            }
            else if(input >= 100){
                input = input - 100;
                romanResult = romanResult + arabicRomanMap.get(100);
            }
            else if(input >= 50){
                input = input - 50;
                romanResult = romanResult + arabicRomanMap.get(50);
            }
            else if(input >= 10){
                input = input - 10;
                romanResult = romanResult + arabicRomanMap.get(10);
            }
            else if(input >= 5){
                input = input - 5;
                romanResult = romanResult + arabicRomanMap.get(5);
            }
            else if(input >= 1){
                input = input - 1;
                romanResult = romanResult + arabicRomanMap.get(1);
            }
        }


        return romanResult;
    }

    private static String whichOperator(String operation) {
        if (operation.contains("+")) {
            return "+";
        }
        if (operation.contains("-")) {
            return "-";
        }
        if (operation.contains("*")) {
            return "*";
        }
        if (operation.contains("/")) {
            return "/";
        }
        return null;
    }


    private static boolean isOperationHasDoubles(String operation) {
        String[] oper = operation.split(" ");
        boolean hasDouble = false;
        if (operation.contains(".") || operation.contains(",")) {
            hasDouble = true;
        }
        return hasDouble;
    }


    private static int add(int num1, int num2) {
        return num1 + num2;
    }

    private static int substrat(int num1, int num2) {
        return num1 - num2;
    }

    private static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    private static int divide(int num1, int num2) {
        return num1 / num2;
    }
}

