import java.text.DecimalFormat;
import java.util.*;

public class ReadNumbers {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);             // A Scanner object to help us read from the standard input
       List<Integer> numbers = new ArrayList<>();            // The list which contains our inserted numbers
       Set<Integer> uniqueNumbers = new HashSet<>();         // A set where we store unique numbers
       Map<Integer, Integer> frequencyMap = new HashMap<>(); // Keeps track of frequency of each unique number
       int evenCount = 0;                                    // Counts even numbers
       int oddCount = 0;                                     // Counts odd numbers
       int maxFrequencyNumber = 0;                           // Number with maximum occurrence
       int maxFrequency = 0;                                 // Maximum occurrence
       int largestNumber = Integer.MIN_VALUE;                // Variable for the largest number (MIN_VALUE is used to ensure largestNumber is
       // initialized to the smallest possible value for an integer
       int smallestNumber = Integer.MAX_VALUE;               // Variable for the smallest number (MAX_VALUE is used to ensure smallestNumber is initialized
       // to the largest possible value for an integer
       int secondSmallestNumber = Integer.MAX_VALUE;         // Variable for the second-smallest number
       int sum = 0;                                          // Sum of entered numbers
       int sumSquaredDiff = 0;                               // Sum of squared differences for variance


       System.out.println("Enter numbers (enter a non-integer to finish):"); //Message for the user to insert numbers


       while (scanner.hasNextInt()) {       // Used to continuously read integers from the input as long as there are more integers available
           int number = scanner.nextInt();  // scans for the integer and proceeds to call 'add' which adds it to our list
           numbers.add(number);             // Called each time a number is read and adds it to the list
           uniqueNumbers.add(number);       // Called each time a new different/unique number is read and adds it to the list

           if (number % 2 == 0) {           // Checks if the number is even and increments evenCount by 1 when it is even
               evenCount++;
           } else {                         // If it is not even (is odd), increments the value of oddCount
               oddCount++;
           }

           int frequency = frequencyMap.getOrDefault(number, 0) + 1; // Stores the frequency update into frequency variable, also if the number is already in the map,
           frequencyMap.put(number, frequency);                                 // it increments the existing count by 1,
           // If it is not, it adds the number to the map with a count of 1 .


           if (frequency > maxFrequency) { // Updates max frequency information if a new number with max occurrence is encountered
               maxFrequency = frequency;
               maxFrequencyNumber = number;
           }

           if (number > largestNumber) { // Checks the numbers and changes the largestNumber if it encounters a larger number
               largestNumber = number;
           }

           if (number < smallestNumber) { // Checks the numbers and changes the smallestNumber and the secondSmallestNumber if it encounters a smaller number
               secondSmallestNumber = smallestNumber;
               smallestNumber = number;
           } else if

           (number < secondSmallestNumber && number != smallestNumber) { // If the encountered number is smaller than the second smallest
               // and different from the smallest, sets a new value for the second smallest
               secondSmallestNumber = number;
           }

           sum += number;
           sumSquaredDiff += (int) Math.pow(number - (double) sum / numbers.size(), 2); // Updates sum and sum of squared differences

       }


       int palindromicCount = 0;
       int largestPalindromic = 0;

       for (int number : numbers) {
           if (isPalindromic(number) && numberOfDigits(number) > 1) { // Checks if the number is palindrome with a condition that it has more than 1 digit (using function)
               palindromicCount++;
               if (number > largestPalindromic && number < largestNumber) { // The check for largest palindrome that is smaller than the largest number
                   largestPalindromic = number;
               }
           }
       }


       System.out.println("Number of elements(numbers):" + numbers.size());                 // Prints number of elements
       System.out.println("Number of different elements(numbers):" + uniqueNumbers.size()); // Prints number of different elements
       System.out.println("Number of even numbers:" + evenCount);                           // Prints number of even numbers
       System.out.println("Number of odd numbers:" + oddCount);                             // Prints number of odd numbers

       System.out.println("Frequency of repetition for each number (in percentage):");

       DecimalFormat df = new DecimalFormat("0.00"); // Used to easily round values to desired decimal

       boolean repeatingNumbersExist = false; // Used to track whether there are repeating numbers

       for (int num : uniqueNumbers) { // Updates the frequency accordingly
           int frequency = frequencyMap.get(num);

           if (frequency > 1) { // Checks if the numbers repeat (if there are repeating numbers in our list of entered numbers)
               repeatingNumbersExist = true; // Does the calculation if there are repeating numbers
               double percentage = (frequency / (double) numbers.size()) * 100; // Calculates the frequency of repetition
               System.out.println(num + ":" + df.format(percentage) + "%");  // Prints the frequency of repetition for each number
           }
       }

       if (repeatingNumbersExist) {
           System.out.println("Number with the maximum occurrence: " + maxFrequencyNumber + " (occurred " + maxFrequency + " times)."); // Prints the number with maximum occurrence with the number of occurrences

       } else {
           System.out.println("There are no repeating numbers, therefore there is neither frequency of repetition nor the number with max occurrence."); // If the user did not repeat numbers on entry it prints a message
       }

       if (numbers.stream().distinct().count() == 1) {
           System.out.println("There is neither biggest nor smallest/second smallest number."); // If all the entered numbers are the same, prints a message saying it
       } else { // Makes sure that if not all numbers are the same, it prints out the following

           System.out.println("The largest number is: " + largestNumber); // Prints the value of the largest number entered if the numbers are not the same or there is only one number
           System.out.println("The smallest number is: " + smallestNumber); // Prints the value of the smallest number entered if the numbers are not the same or there is only one number
           System.out.println("The second smallest number is: " + secondSmallestNumber); // Prints the value of the second-smallest number if the numbers are not the same or there is only one number
       }

       if (numbers.size() > 1) { // Checks if the user entered more than one number
           double average = (double) sum / numbers.size(); // Calculating the average
           System.out.println("Average of entered numbers: " + df.format(average)); // Printing average of the entered numbers
       } else { // If the user entered only one number it prints the following:
           System.out.println("Please enter more than one number for the average to be calculated!");
       }

       if (numbers.size() > 1) {
           double variance = (double) sumSquaredDiff / numbers.size(); // Calculating standard deviation into variable variance
           double standardDeviation = Math.sqrt(variance); // Assigning variable for squared variance
           System.out.println("Standard deviation of entered numbers: " + df.format(standardDeviation)); // Printing the standard deviation
       } else {
           System.out.println("Please enter more than one number for the standard deviation to be calculated");
       }
       if (numbers.size() > 1) {
           double median;
           if (numbers.size() % 2 == 0) { // Calculating median if even number of elements
               int middle1 = numbers.size() / 2 - 1;
               int middle2 = numbers.size() / 2;
               median = (numbers.get(middle1) + numbers.get(middle2)) / 2.0;
           } else {                      // Calculating median of odd number of elements
               int middle = numbers.size() / 2;
               median = numbers.get(middle);
           }
           System.out.println("Median of entered numbers: " + df.format(median));
       } else {
           System.out.println("Please enter more than one number for the median to be calculated");
       }


       if (numbers.size() > 1) { // Checks if the user entered more than one number
           System.out.println("Sum of all numbers: " + sum);

       } else { // If the user entered only one number it prints the following:
           System.out.println("Please enter more than one number for the sum to be calculated!");
       }
       System.out.println("Number of palindromic numbers: " + palindromicCount); // Prints the number of palindromes

       if (largestPalindromic > 0) {
           System.out.println("Largest palindromic number smaller than the largest number: " + largestPalindromic); // Prints largest palindromic...
       } else {
           System.out.println("There is no palindromic number smaller than the largest number.");
       }

       System.out.println("Entered numbers: ");
       for (int i = numbers.size() - 1; i >= 0; i--) {
           System.out.print(numbers.get(i));
           if (i > 0) {
               System.out.print(", ");
           }
       }


   }

   private static int numberOfDigits(int number) { // A method for checking how many digits does an inserted number have
       return String.valueOf(number).length();
   }
    private static boolean isPalindromic(int number) { // A method for checking if the number is palindromic
        String str = String.valueOf(number);
        String reversedStr = new StringBuilder(str).reverse().toString();
        return str.equals(reversedStr);
    }



}
