package com.example.csfinalproject;

import java.util.Arrays;
public class Sorters {
    // This is where we'll put all the sorting functions. I hope to get this done by the end of the weekend.
    //Other goals include getting the sample text button up and making the text box functional.
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static String[] copyOfRange(String[] words, int start, int end) {
        String[] output = new String[end - start];
        int startProxy = start;
        for (int i = 0; i < end - start; i++) {
            output[i] = words[startProxy];
            startProxy++;
        }
        return output;
    }
    private static int alphabetCompareTo(String first, String second) {
        for (int i = 0; i < Math.min(first.length(), second.length()); i++) {
            if (alphabet.indexOf(first.charAt(i)) < alphabet.indexOf(second.charAt(i))) {
                return -1;
            } else if (alphabet.indexOf(first.charAt(i)) > alphabet.indexOf(second.charAt(i))) {
                return 1;
            }
        }
        if (first.length() == second.length()) {
            return 0;
        } else if (first.length() < second.length()) {
            return -1;
        } else {
            return 0;
        }
    }
    public static String[] alphabetMerge(String[] first, String[] second) {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }
        String[] output = new String[first.length + second.length];
        int outputIndex = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < first.length && secondIndex < second.length) {
            if (alphabetCompareTo(first[firstIndex], second[secondIndex]) > 0) {
                output[outputIndex] = second[secondIndex];
                secondIndex++;
                outputIndex++;
            } else {
                output[outputIndex] = first[firstIndex];
                firstIndex++;
                outputIndex++;
            }
        }
        while (firstIndex < first.length) {
            output[outputIndex] = first[firstIndex];
            firstIndex++;
            outputIndex++;
        }
        while (secondIndex < second.length) {
            output[outputIndex] = second[secondIndex];
            secondIndex++;
            outputIndex++;
        }
        return output;
    }
    /*public static List merge(List<Comparable> first, List<Comparable> second) {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }
        List output = new ArrayList(0);
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < first.size() && secondIndex < second.size()) {
            if (first.get(firstIndex).compareTo(second.get(secondIndex)) > 0) {
                output.add(second.get(secondIndex));
                secondIndex++;
            } else {
                output.add(first.get(firstIndex));
                firstIndex++;
            }
        }
        while (firstIndex < first.size()) {
            output.add(first.get(firstIndex));
            firstIndex++;
        }
        while (secondIndex < second.size()) {
            output.add(second.get(secondIndex));
            secondIndex++;
        }
        return output;
    }*/
    /*public static int[] mergesort(int[] values) {
        if (values == null) {
            return null;
        }
        if (values.length == 1 || values.length == 0) {
            return values;
        }
        int[] firstHalf = copyOfRange(values, 0, values.length / 2);
        int[] secondHalf = copyOfRange(values, values.length / 2, values.length);
        return merge(mergesort(firstHalf), mergesort(secondHalf));
    }*/
    /**
     *
     * @param text
     * @return words sorted in alphabetical order
     */
    public static String[] alphabeticalSort(String[] text) {
        text = lengthSort(text);
        if (text == null) {
            return null;
        }
        if (text.length == 1 || text.length == 0) {
            return text;
        }
        String[] firstHalf = copyOfRange(text, 0, text.length / 2);
        String [] secondHalf = copyOfRange(text, text.length / 2, text.length);
        return alphabetMerge(alphabeticalSort(firstHalf), alphabeticalSort(secondHalf));
    }

    /**
     *
     * @param first sorted array
     * @param second sorted array
     * @return merged string array with words in order by length
     */
    public static String[] lengthMerge(String[] first, String[] second) {
        if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }
        String[] output = new String[first.length + second.length];
        int outputIndex = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < first.length && secondIndex < second.length) {
            if (first[firstIndex].length() > second[secondIndex].length()) {
                output[outputIndex] = second[secondIndex];
                secondIndex++;
                outputIndex++;
            } else {
                output[outputIndex] = first[firstIndex];
                firstIndex++;
                outputIndex++;
            }
        }
        while (firstIndex < first.length) {
            output[outputIndex] = first[firstIndex];
            firstIndex++;
            outputIndex++;
        }
        while (secondIndex < second.length) {
            output[outputIndex] = second[secondIndex];
            secondIndex++;
            outputIndex++;
        }
        return output;
    }

    /**
     *
     * @param text text from the user
     * @return words sorted by length
     */
    public static String[] lengthSort(String[] text) {
        if (text == null) {
            return null;
        }
        if (text.length == 1 || text.length == 0) {
            return text;
        }
        String[] firstHalf = copyOfRange(text, 0, text.length / 2);
        String [] secondHalf = copyOfRange(text, text.length / 2, text.length);
        return lengthMerge(lengthSort(firstHalf), lengthSort(secondHalf));
    }

    /**
     *
     * @param text
     * @return the words sorted by the sum of the binary values of their characters, least to greatest.
     */
    public String binarySort(String text) {
        return null;
    }
}
