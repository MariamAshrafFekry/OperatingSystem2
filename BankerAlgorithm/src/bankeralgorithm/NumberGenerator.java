/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankeralgorithm;

/**
 *
 * @author Andrew
 */
public class NumberGenerator {

    public static String Generate(int num) {
        String ans = "";

        while (num > 0) {
            int rem = num % 26;
            // If remainder is 0, then a 'Z' must be there in output
            if (rem == 0) {
                ans = 'Z'+ans;
                num = (num / 26) - 1;
            }
            else // If remainder is non-zero
            {
                ans = (char)((rem - 1) + 'A')+ans;
                num = num / 26;
            }
        }
        return ans;
    }
}
