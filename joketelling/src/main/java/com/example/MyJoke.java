package com.example;

import java.util.Random;

public class MyJoke {
    private static final String[] jokes = {
            "Why is it that women find C to be more attractive than Java?\n" +
                    "Because C doesn't treat them like objects.",
            "A guy walks into a bar and asks for 1.014 root beers.\n" +
                    "The bartender says, \"I'll have to charge you extra, that\'s a root beer float\".\n" +
                    "So the guy says, \"In that case, better make it a double.\"",
            "Command line Russian roulette : [ $[ $RANDOM % 6 ] == 0 ] && rm -rf / || echo *Click*",
            "An int, a char, and a string walk into a bar and order some drinks.\n" +
                    "A short while later, the int and char start hitting on the waitress who gets very uncomfortable and walks away.\n" +
                    "The string walks up to the waitress and says \"You'll have to forgive them, they’re primitive types.\"",
            "A group of DBAs walk into a diner.\n" +
                    "One waved over the waiter: \"Can we join these tables?\"."

    };

    public static String getJoke(){
        int size = jokes.length;

        Random r = new Random();
        int index = r.nextInt(size);

        return jokes[index];
    }
}
