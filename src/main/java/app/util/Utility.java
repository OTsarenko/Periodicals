package app.util;

import app.entity.Periodical;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public final class Utility {

    /**
     * Method used to hash user password.
     *
     * @return hashed password
     */

    public static List<Integer> countPages (List<Periodical> periodicals){
        int countPages;
        if (periodicals.size()%10 == 0){
            countPages = periodicals.size()/10 ;
        } else{
            countPages = periodicals.size()/10 +1;
        }
        List<Integer> pages = new ArrayList<>();
        for (int i=1; i<=countPages; i++){
            pages.add(i);
        }
        return pages;
    }
}
