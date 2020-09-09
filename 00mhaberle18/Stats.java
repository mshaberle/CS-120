import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Math;

/**
 * This class takes in a list of positive numbers from user input
 * until a negative number is entered to terminate the input
 *
 * Using the list of positive numbers, the smallest number, largest number,
 * median, mean, standard deviation, first and third quartile, range,
 * and interquatile range are determined
 *
 * @author Miller Haberle
 */

public class Stats {
  public static void main(String[] args) {

    ArrayList<Double> numbers = new ArrayList<Double>(); // Holds an undefined amount of positive user inputs
    Scanner scanner = new Scanner(System.in); // Used to parse numbers from the user

    do { // Starts a loop for value input
      System.out.println("Enter a positive number to continue or a negative number to stop: ");
      numbers.add(scanner.nextDouble()); // Adds the value input by a user to the "numbers" array list
    } while(numbers.get(numbers.size()-1)>=0); // Terminates the do while loop when a negative value is entered

    scanner.close(); // Closes the scanner

    Collections.sort(numbers); // Sorts all numbers that were input
    numbers.remove(0); // Since the numbers are sorted, the negative value was moved to the front so it is removed
    double[] quartiles = quartiles(numbers); // Set equal to the array containing the quartile values
    /**
     * Prints out all requested statistics by doing simple math or calling the proper method
     */
    System.out.println("\nSmallest Number: " + numbers.get(0)); // Since the array list is sorted, the smalled number is at index 0. The newline at the begging of the string is used to prettify the output
    System.out.println("Largest Number: " + numbers.get(numbers.size()-1)); // Since the array list is sorted, the largest number is at the last index
    System.out.println("Median: " + median(numbers)); // Calls the "median" method passing the "numbers" array list as an argument
    System.out.println("Mean: " + mean(numbers)); // Calls the "mean" method passing the "numbers" array list as an argument
    System.out.println("Standard Deviation: " + stdDeviation(numbers)); // Calls the "stdDeviation" method passing the "numbers" array list as an argument
    System.out.println("First Quartile: " + quartiles[0]); // Displays the value of first quartile stored in the first position of the "quartiles" array
    System.out.println("Third Quartile: " + quartiles[1]); // Displays the value of third quartile stored in the first position of the "quartiles" array
    System.out.println("Range: " + (numbers.get(numbers.size()-1)-numbers.get(0))); // Since the array list is sorted, the number at the first position is subtracted from the number at the last position
    System.out.println("Interquartile Range: " + (quartiles[1]-quartiles[0])); // Interquartile range is the range between the first quartile (Q1) and third quartile (Q3) so subtract Q1 from Q3
  }

  /**
   * Finds the median (middle) of the values of passed array list
   * @param  list the resulting list after passing the "numbers" array list as an parameter
   * @return      returns the median of the list of numbers
   */
  public static double median(List<Double> list) {
    int length = list.size(); // Used for fetching middle values of the array list
    if(length%2 == 0) { // Determine whether the number of values in array list is even or odd
      return (list.get((length/2)-1)+list.get(length/2))/2; // If the number of values is even, then average the two middle numbers
    } else {
      return list.get((length/2)); // If the number of values is odd, then just return the middle number
    }
  }

  /**
   * Finds the mean (average) of all the values in the passed array list
   * @param  list the resulting array list after passing the "numbers" array list as an parameter
   * @return      returns the mean of the array list of numbers
   */
  public static double mean(ArrayList<Double> list) {
    double total = 0; // Used to hold the sums of the values in the array list
    for(double number : list) { // Iterates through all of the values in the array list "list"
      total += number; // Adds the number from "list" to the total
    }
    return total/list.size();
  }

  /**
   * Finds the population standard deviation of all the values in the passed array list
   * @param  list the resulting array list after passing the "numbers" array list as an parameter
   * @return      returns the standard deviation of the array list of numbers
   */
  public static double stdDeviation(ArrayList<Double> list) {
    double avg = mean(list); // The variable stores the value returned by the "mean" method when the array list "list" is passed
    double total = 0; // Used to hold the sums of the difference of the values of the array list "list" and the mean which are then squared
    for(double number : list) { // Iterates through all the values in the array list "list"
      total += Math.pow(number-avg, 2); // Sums the difference of the number from the mean squared
    }
    return Math.sqrt(total/list.size()); // Takes the square root of "total" divided by the size of the list
  }

  /**
   * Finds the first and third quartiles of the values in the passed array list
   * @param  list the resulting array list after passing the "numbers" array list as an parameter
   * @return      returns the values of the first and third quartiles of the array list of numbers as an array
   */
  public static double[] quartiles(ArrayList<Double> list) {
    int length = list.size(); // Used for using middle values of the array list as endpoints
    ArrayList<Double> lowerHalf, upperHalf; // Holds all the values in the array list "list" that are below the median
    double[] values = new double[2]; // Initialize values as an array to hold the values of the first and third quartile
    if(list.size() % 2 == 0) { // Checks if the array list is of even length
      values[0] = median(new ArrayList<Double>(list.subList(0, length/2))); // Finds the median of the lower half of "list"
      values[1] = median(new ArrayList<Double>(list.subList(length/2, length))); // Finds the median of the upper half of "list"
    } else {
      lowerHalf = new ArrayList<Double>(list.subList(0,length/2+1)); // Sets lowerHalf to the first half of "list" using a sublist including the median of "list"
      upperHalf = new ArrayList<Double>(list.subList(length/2, length)); // // Sets upperHalf to the second half of "list" using a sublist including the median of "list"
      values[0] = (median(lowerHalf)+median(lowerHalf.subList(0, lowerHalf.size()-1)))/2; // Averages the median of lowerHalf and the median of lowerHalf without the median value together
      values[1] = (median(upperHalf)+median(upperHalf.subList(1, upperHalf.size())))/2; // Averages the median of upperHalf and the median of upperHalf without the median value together
    }
    return values;
  }
}
