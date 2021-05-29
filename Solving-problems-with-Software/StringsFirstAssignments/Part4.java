
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    public void youtubeSearch (URLResource url) {
        String youtubeUrl = "";
        int startIndex = 0;
        int stopIndex = 0;
        
        for (String originalStr : url.lines()) {
            //System.out.println(str);
            String str = originalStr.toLowerCase();
            
            if (str.contains("youtube.com")) {
                startIndex = str.indexOf("href=\"");
                stopIndex = str.indexOf("\"", startIndex + 6);
                youtubeUrl = originalStr.substring(startIndex + 6, stopIndex);
                
                System.out.println(youtubeUrl);
            }
        }
    }
    
    public void testyoutubeSearch () {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        
        System.out.println("YouTube links in url:\n");
        youtubeSearch(url);
    }
    
    public static void main (String[] args) {
        Part4 pt4 = new Part4();
        pt4.testyoutubeSearch();
    }
}
