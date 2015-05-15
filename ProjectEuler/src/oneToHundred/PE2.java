/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneToHundred;

/**
 * ok
 * @author admin
 */
public class PE2 {
    
    public int resoudrePE2() {
        int res = 2;
        int i = 1;
        int y = 2;
        while(y <= 4000000) {
            int temp = this.fibonnaci(i, y);
            i = y;
            y = temp;
            if(y % 2 == 0) {
                res += y;
            }
        }
        return res;
    }
    
    private int fibonnaci(int a, int b) {
        return a+b;
    }
}
