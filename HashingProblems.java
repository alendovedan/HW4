/*
 * *** Alen Dovedan / Section 002 ***
 *
 * This HashingProblems object contains three methods / problems that you must
 * complete utilize the HashMap object within the Java's Collection Framework Library.
 *
 * The three methods / problems you need to solve are:
 *  - getAverage
 *  - odd
 *  - twoSums
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class HashingProblems {

    /*
     * Method getAverage()
     *
     * This method accepts two parameters. The first is a HashMap object, while the
     * second
     * is an array of integers. This method must compute the average of the values
     * for each
     * 'key' that is found in BOTH the HashMap and the array.
     *
     * For example, if only the keys 1 and 2 from the array are present in the
     * HashMap, and
     * lets say their values were 10 and 20, respectively, then the average is
     * calculated
     * as (10+20)/2 = 15. Lets also say the keys ‘7’ and ‘8’ are in the array, but
     * those keys
     * are not present in the HashMap. That means their corresponding values in the
     * HashMap
     * are not included in the average calculation.
     */

    public double getAverage(HashMap<Integer, Integer> map, int[] array) {
        int sum = 0;
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            if (map.containsKey(key)) {
                sum += map.get(key);
                count++;
            }
        }

        // If no common keys are found, return NaN (0.0 / 0.0)
        if (count == 0) {
            return 0.0 / 0.0;
        } else {
            return (double) sum / count;
        }
    }

    /*
     * Method odd()
     *
     * This method accepts a HashMap object, and returns an ArrayList object with
     * the
     * values of the corresponding keys that are odd.
     */

    public ArrayList<String> odd(HashMap<Integer, String> map) {
        ArrayList<String> result = new ArrayList<>();

        /*
         * Iterating over the keys in the HashMap to check if they are odd.
         * If a key is odd, add its corresponding value to the result list.
         */
        for (Object keyObject : map.keySet()) {
            Integer key = (Integer) keyObject;
            if (key % 2 != 0) { // Check if the key is odd
                result.add(map.get(key));
            }
        }
        return result;
    }

    /*
     * Method twoSums()
     *
     * You ARE to solve this problem in time complexity O(n). The submittals will be
     * spot checked.
     *
     * Problem statement:
     * Suppose you are given an integer array containing the values [1,4,5,7,8,9]
     * along with the
     * value k=4, where k is the difference between two array elements. How many
     * times does k appear
     * in that list?
     *
     * With the above numbers, it will be three times:
     * k = 4
     * (5 - 1) = k
     * (8 - 4) = k
     * (9 - 5) = k
     * k appears 3 times.
     *
     * In order to solve this problem in O(n) complexity, utilize a HashMap (or a
     * HashSet).
     *
     * You are to solve this using a HashMap (or you can use a HashSet, which is
     * implemented
     * using HashMap). To solve this, you should populate the HashMap (or HashSet)
     * based on
     * the array (this will be complexity time on the order of 'n'). After
     * populating the HashMap,
     * consider a for-loop that does a lookup (probe) of the HashMap (or HashSet) on
     * each iteration
     * of the loop. This will also have a complexity on the order of 'n', as the
     * hashing probes are a
     * constant time complexity (after removing any constant based on collisions).
     */

    public int twoSums(int[] numbers, int k) {
        Set<Integer> numSet = new HashSet<>(); // HashSet to store numbers for O(1) lookups
        int count = 0;

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            // Check if num - k exists in the set
            if (numSet.contains(num - k)) {
                count++;
            }
            // Check if num + k exists in the set
            if (numSet.contains(num + k)) {
                count++;
            }
            // Add current number to the set
            numSet.add(num);
        }

        return count;
    }
} /* end class HashingProblems */
