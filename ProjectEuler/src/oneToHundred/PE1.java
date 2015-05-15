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
public class PE1 {
    public int resoudrePE1() {
        int res = 0;
        for(int i = 0 ; i < 1000 ; i++) {
           if(this.nombreEstMultiple(i, 3) || this.nombreEstMultiple(i, 5))
               res += i;
        }
        return res;
    }
    
    private boolean nombreEstMultiple(int nombre, int multiple) {
        return nombre % multiple == 0;
    }
}
