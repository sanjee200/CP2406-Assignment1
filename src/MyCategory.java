import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * Created by Sanjeewa on 03/10/2016.
 *
 * Categories (trumps)
 */

public class MyCategory
{

    static String[] myCleavageArray = {
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

    static String[] myCrustalAbundanceArray = {
          "ultratrace"
        , "trace"
        , "low"
        , "moderate"
        , "high"
        , "very high"
    } ;

    static String[] myEconomicValueArray = {
          "trival"
        , "low"
        , "moderate"
        , "high"
        , "very high"
        , "I’m rich"
    } ;

    public static boolean isCategoryHigherArray( int pCategory,  String pString, Double pHighestIndex )
    {
        boolean myHigher = false ;
        int myHighestIndexInt, myIndex = 0 ;
        myHighestIndexInt = pHighestIndex.intValue() ;

        switch (pCategory)
        {
            case 3 :    // hardness
                myIndex = MyCommon.findString(myCleavageArray, pString) ;
                break ;
            case 4 :    // Specific Gravity
                myIndex = MyCommon.findString(myCrustalAbundanceArray, pString) ;
                break ;
            case 5 :    // Cleavage
                myIndex = MyCommon.findString(myEconomicValueArray, pString) ;
                break ;
            default :
                break ;
        }

        if ( myIndex > myHighestIndexInt ) // no need to chaeck => && ( myIndex != myCleavageArray.length )
        {
            myHigher = true ;
        }
        return myHigher ;
    }

}
