import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * Created by Sanjeewa on 03/10/2016.
 *
 * Categories (trumps)
 */

public class MyCategory
{


    public static boolean myCleavageHigher( String pCleavage, Double pCleavageCurrent )
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
        boolean myHigher = false ;
        int myCleavageCurrent = pCleavageCurrent.intValue() ;

        // boolean found = ArrayUtils.contains(myCleavageArray, pCleavage) ;
        int myIndex = MyCommon.findString(myCleavageArray, pCleavage) ;

        if ( myIndex >= myCleavageCurrent ) // no need to chaeck => && ( myIndex != myCleavageArray.length )
        {
            myHigher = true ;
        }
        return myHigher ;
    }

}
