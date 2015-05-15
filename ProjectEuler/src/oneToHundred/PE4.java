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
public class PE4 {
    public int resoudrePE4() {
        int res = 0;
        for(int i = 999 ; i >= 0 ; i--) {
            for(int j = 999 ; j >= 0 ; j--) {
                if(estPalindrome(i * j) && (i*j)>res) {
                    res = i*j;
                }
            } 
        }
       return res;
    }
    
    private boolean  estPalindrome(int nombre) {
        boolean estPalin = true;
        String nb = String.valueOf(nombre);
        for(int i = 0 ; i < nb.length()/2 && estPalin ; i++) {
            if(nb.charAt(i) != nb.charAt(nb.length()-1-i))
                estPalin = false;
        }
        return estPalin;
    }
}
