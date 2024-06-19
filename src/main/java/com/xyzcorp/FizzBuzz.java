package com.xyzcorp;

public class FizzBuzz {
    public static void main(String[] args) {
        //Fizz Buzz
        int num = 100;
        fizzBuzzTo(num);
    }

    /**
     * Prints numbers from 1 to the given number, replacing multiples of 3 with "Fizz",
     * multiples of 5 with "Buzz", and multiples of both 3 and 5 with "FizzBuzz".
     *
     * @param num The number up to which to print the FizzBuzz sequence.
     */
    private static void fizzBuzzTo(int num) {
        for (int i = 1; i <= num; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}
