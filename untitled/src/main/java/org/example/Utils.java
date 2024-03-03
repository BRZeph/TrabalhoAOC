package org.example;

import java.util.HashMap;
import java.util.Scanner;
public class Utils {
    private static HashMap<Integer, Integer> rest = new HashMap<>();
    private static HashMap<Character, Integer> extendedNumbers = new HashMap<>();
    public static void registerLetters(){
        extendedNumbers.put('a',10);
        extendedNumbers.put('b',11);
        extendedNumbers.put('c',12);
        extendedNumbers.put('d',13);
        extendedNumbers.put('e', 14);
        extendedNumbers.put('f', 15);
        extendedNumbers.put('g', 16);
        extendedNumbers.put('h', 17);
        extendedNumbers.put('i', 18);
        extendedNumbers.put('j', 19);
        extendedNumbers.put('k', 20);
        extendedNumbers.put('l', 21);
        extendedNumbers.put('m', 22);
        extendedNumbers.put('n', 23);
        extendedNumbers.put('o', 24);
        extendedNumbers.put('p', 25);
        extendedNumbers.put('q', 26);
        extendedNumbers.put('r', 27);
        extendedNumbers.put('s', 28);
        extendedNumbers.put('t', 29);
        extendedNumbers.put('u', 30);
        extendedNumbers.put('v', 31);
        extendedNumbers.put('w', 32);
        extendedNumbers.put('x', 33);
        extendedNumbers.put('y', 34);
        extendedNumbers.put('z', 35);
    }
    public static boolean allowedNumberBaseCombination(String IN, int IB){
        if (IB < 2){
            throw new RuntimeException("The base must be greater than 2");
        }
        for (char digitChar : IN.toCharArray()){
            int digitValue = getNumericValue(digitChar);
            if (digitValue > IB){
                return false;
            }
        }
        return true;
    }
    public static int getNumericValue(char c){
        if (Character.isDigit(c)){
            return Character.getNumericValue(c);
        } else {
            for (char c2 : extendedNumbers.keySet()){
                if (Character.toLowerCase(c) == c2){
                    return extendedNumbers.get(c2);
                }
            }
            throw new RuntimeException("Number not registered");
        }
    }
    public static String askQuestionString(String question){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(question);
                return scanner.nextLine().toUpperCase();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
    public static int askQuestionInteger (String question){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(question);
                String answer = scanner.nextLine();
                return Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
    public static String getLetterValue(int i){
        if (i < 10){
            return String.valueOf(i);
        } else {
            for (char c : extendedNumbers.keySet()){
                if (extendedNumbers.get(c) == i){
                    return String.valueOf(c);
                }
            }
            throw new RuntimeException("Unregistered entry");
        }
    }
    public static String transformToBase10(String IN, int IB){
        int i = 1;
        int finalNumber = 0;
        for (char digitChar : IN.toCharArray()){
            int digitValue = getNumericValue(digitChar);
            int additive = ((int) Math.pow(IB, IN.toCharArray().length - i))*digitValue;
            finalNumber = finalNumber + additive;
            i++;
        }
        return String.valueOf(finalNumber);
    }


    public static String transformFromBase10(String IN, int FB){
        boolean dividing = true;
        int count = 1;
        int firstDividend = Integer.parseInt(IN);
        while (dividing){
            int newDividend = (int) (firstDividend/(Math.pow(FB, count - 1)));
            int quotient = newDividend/FB;
            int newRemainder = newDividend%FB;
            rest.put(count, newRemainder);
            count++;
            if (quotient < 1){
                dividing = false;
            }
        }
        StringBuilder sb = new StringBuilder(rest.size());
        for (int i = rest.size(); i > 0; i--){
            int j = rest.get(i);
            String append = getLetterValue(j).toUpperCase();
            sb.append(append);
        }
        return sb.toString();
    }
}
