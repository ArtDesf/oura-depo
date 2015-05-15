/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneToHundred;

import static java.lang.Math.sqrt;

/**
 * ok (chance de dingue)
 * @author admin
 */
public class PE3 {
    long target = 600851475143L;
    
    //tourne dans le vide
    public long resoudrePE3() {
        long res = 0L;
        for(long i = 0 ; i < (target/2) ; i++) 
            if(isPrime(i) && (target % i == 0)) {
                res = i;
                System.out.println(res);
            }
        return res;
    }
    
    /**
     * Teste si le nombre passé en paramètre est pair
     * @param number
     * @return 
     */
    public boolean isPrime(long number) {
        boolean estPremier = false;
        if(number == 2)
            return true;
        if(number % 2 != 0) {
           estPremier = true;
           for(long i = 2 ; i < sqrt(number) && estPremier ; i++) {
               if(number % i == 0)
                   estPremier = false;
           }
        }
        return estPremier;
    }
}
