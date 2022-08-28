package app.util;

import app.entity.Periodical;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Utility {

    /**
     * Method used to count pages for pagination.
     *
     * @return number of pages
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

    /**
     * Method used to validate enter data.
     *
     * @return valid data
     * @throws IOException if data not valid
     */
    public static String validateData (String str) throws IOException {
        if(str != null && !str.equals("")) return str;
        throw new IOException("Data not valid");
    }
}
