import java.util.Scanner;

/**
 * Created by Sanjeewa on 26/09/2016.
 *
 * All the common methods used by all the classes
 */

public class MyCommon
{

    public static String inputString( String pMessage )
    {
        String myInput ;
        Scanner inputDevice = new Scanner( System.in ) ;

        while ( true )
        {
            System.out.print( pMessage + "\n( Empty to exit ) ==> " ) ;
            myInput = inputDevice.nextLine() ;
            if (!myInput.isEmpty())
            {
                break ;
            }
            if ( inputYesNo() )
            {
                break ;
            }
        }
        return myInput ;
    }


    public static int inputInteger( String pMessage, int pMinimum, int pMaximum )
    {
        int myInput ;
        Scanner inputDevice = new Scanner( System.in ) ;

        while ( true )
        {
            System.out.print( pMessage
                + "\n( Integer between "
                + pMinimum + " and " + pMaximum
                + ", 0 to exit ) ==> "
            ) ;
            myInput = inputDevice.nextInt() ;

            if ( myInput >= pMinimum && myInput <= pMaximum )
            {
                break ;
            }
            if ( myInput == 0 )
            {
                if ( inputYesNo() )
                {
                    break ;
                }
            }

            System.out.println( " has to be an integer between " + pMinimum
                + " and " + pMaximum ) ;
        }
        return myInput ;
    }


    public static boolean inputYesNo()
    {
        char myInput ;
        Scanner inputDevice = new Scanner( System.in ) ;

        while ( true )
        {
            System.out.print( " Do you want to exit (Y,N) ==> " ) ;
            myInput = inputDevice.next().charAt(0) ;

            if ( myInput == 'Y' || myInput == 'N' ||
                 myInput == 'y' || myInput == 'n' )
            {
                break ;
            }
            System.out.println( "Input 'Y' or 'N'" ) ;
        }
        return ( myInput == 'Y' || myInput == 'y' ) ;
    }


    // Search for a character and returns the rest
    public static String findRest( String pString, String pFindChar )
    {
        int myFound = -1 ;
        String myRest ;

        for (int i=0; i<pFindChar.length(); i++)
        {
            myFound = pString.indexOf( pFindChar.charAt(i) ) ;
            if ( myFound >= 0 )
            {
                break ;
            }
        }
        myRest = pString.substring( myFound + 1 ) ;
        return ( myRest ) ;
    }

    // Search for an integer
    public static int findInteger( int[] pArray, int pValue )
    {
        int returnValue = -1 ;
        for (int i=0; i< pArray.length; i++)
        {
            if ( pArray[i] == pValue )
            {
                returnValue = i ;
                break ;
            }
        }
        return ( returnValue ) ;
    }

    /*
    convert string to integer
        Integer.parseInt( myString )
        Integer.valueOf( myString )

    convert string to double
        Double.parseDouble(numberAsString);
        Double.valueOf(numberAsString);
        Double doubleObject = new Double(numberAsString);
        DecimalFormat decimalFormat = new DecimalFormat("#");

     */

}
