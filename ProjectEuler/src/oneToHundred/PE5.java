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
public class PE5 {
    public int resoudrePE5() {
        int res = 0;
        boolean isSolve = false;
        
        int nb = 1;
        while(!isSolve) {
            boolean nbIsRes = true;
            for(int i = 1 ; i <= 20 && nbIsRes; i++) {
                if(nb % i != 0) {
                    nbIsRes = false;
                }
            }
            if(nbIsRes) {
                isSolve = true;
                res = nb;
            }
            else {
                nb++;
                nbIsRes = true;
            }
        }
        return res;
    }
}
