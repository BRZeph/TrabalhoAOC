package org.example;

public class Main {
    public static void main(String[] args) {
        Utils.registerLetters();

        String IN = Utils.askQuestionString("What is the initial number?");
        int IB = Utils.askQuestionInteger("What is the initial base?");

        if (!Utils.allowedNumberBaseCombination(IN, IB)){
            throw new RuntimeException("Unacceptable digit for this base");
        }

        int FB = Utils.askQuestionInteger("What is the final base?");

        String finalNumber;
        if (FB == 10){
            finalNumber = Utils.transformToBase10(IN, IB);
        } else if (IB == 10){
            finalNumber = Utils.transformFromBase10(IN, FB);
        } else {
            finalNumber = Utils.transformFromBase10(Utils.transformToBase10(IN, IB), FB);
        }
        System.out.println("The number " + IN + " on base " + IB + " is equals to " + "\n" + finalNumber + " on base " + FB);
    }
}