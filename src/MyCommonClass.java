import java.util.Scanner;

/**
 * Created by Sanjeewa on 26/09/2016.
 * All the common methods used by all the classes
 */

public class MyCommonClass
{

    public static String inputString( String pMessage )
    {
        String myInput ;
        Scanner inputDevice = new Scanner( System.in ) ;

        while ( true )
        {
            System.out.print( pMessage + " ( Empty to exit ) ==> " ) ;
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
                + " ( Integer between "
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

}
