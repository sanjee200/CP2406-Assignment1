import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * Created by Sanjeewa on 03/10/2016.
 *
 * Categories (trumps)
 */

public class MyCategory
{


    public static boolean myCleavageHigher(String pCleavage)
    {
        String[] myCleavageArray = {
              "none"
            , "poor/none"
            , "1 poor"
            , "2 poor"
            , "1 good"
            , "1 good 1 poor"
            , "2 good"
            , "3 good"
            , "1 perfect"
            , "1 perfect 1 good"
            , "1 perfect 2 good"
            , "2 perfect 2 good"
            , "3 perfect"
            , "4 perfect"
            , "6 perfect"
        } ;
        boolean myFound = false ;

        // boolean found = ArrayUtils.contains(myCleavageArray, pCleavage) ;

        int myIndex = MyCommon.findString(myCleavageArray, pCleavage) ;
        if ( myIndex >=0 && myIndex != myCleavageArray.length )
        {
            myFound = true ;
        }
        return myFound ;
    }

}
