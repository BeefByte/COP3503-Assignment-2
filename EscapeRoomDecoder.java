import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Escape Room Decoder - Generate All Valid Password Sequences
 * 
 * This class generates all valid passwords from scrambled tiles that meet
 * the escape room security constraints.
 * 
 * @author Your Name
 * @course COP3503
 * @assignment Assignment 2: Escape Room Decoder
 */
public class EscapeRoomDecoder {
    
    /**
     * Generates all valid passwords from the given tiles
     * 
     * @param tiles The input string of scrambled tiles
     * @return ArrayList of all valid passwords in lexicographic order
     */
    public ArrayList<String> generatePasswords(String tiles) {
        ArrayList<String> result = new ArrayList<>();
        
        // Handle edge case: if input length is less than 2, no valid passwords
        if (tiles == null || tiles.length() < 2) {
            System.out.println("Total valid passwords: 0");
            return result;
        }
        
        // Convert string to char array and sort to handle duplicates
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        
        boolean[] visited = new boolean[tiles.length()];
        StringBuilder current = new StringBuilder();
        
        // Generate all passwords of length 2 to tiles.length()
        generateAllPasswords(chars, visited, current, result);
        
        // Sort the final result lexicographically
        Collections.sort(result);
        
        // Print the required output
        System.out.println("Total valid passwords: " + result.size());
        
        return result;
    }
    
    /**
     * Recursive helper method to generate all valid passwords using backtracking
     */
    private void generateAllPasswords(char[] chars, boolean[] visited, 
                                     StringBuilder current, ArrayList<String> result) {
        // If current password length is at least 2, check if it's valid
        if (current.length() >= 2) {
            String password = current.toString();
            // Check if it's not a palindrome and not already in result
            if (!isPalindrome(password)) {
                result.add(password);
            }
        }
        
        // If we've used all tiles, return
        if (current.length() == chars.length) {
            return;
        }
        
        // Try all possible next characters
        for (int i = 0; i < chars.length; i++) {
            // Skip if this position is already visited
            if (visited[i]) {
                continue;
            }
            
            // Skip duplicates at the same recursion level
            if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                continue;
            }
            
            // Choose this character
            visited[i] = true;
            current.append(chars[i]);
            
            // Explore further
            generateAllPasswords(chars, visited, current, result);
            
            // Unchoose (backtrack)
            current.deleteCharAt(current.length() - 1);
            visited[i] = false;
        }
    }
    
    /**
     * Checks if a string is a palindrome
     * 
     * @param str The string to check
     * @return true if the string is a palindrome, false otherwise
     */
    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
