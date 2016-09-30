/**
 * Created by Sanjeewa on 26/09/2016.
 *
 * Mineral Super Trumps - Main Program
 */

public class MineralSupertrumps
{



    public static void main( String[] args )
    {


        while ( true )
        {
/*
            // Display the main menu
            if ( !MyConfig.DisplayMainMenu() )
            {
                break ;
            }
*/

/*
            // Get Initial Information
            if ( !MyConfig.GetInitialInformation() )
            {
                break ;
            }
*/

            // Start a new game
            if ( !MyGame.newGame() )
            {
                break ;

            }

            // end
            // displayAllCardNumbers the players won in order

            System.out.println(MyConfig.myName);
            System.out.println( "-------------Done (With No Errors)------------------" ) ;
            break ;
        }
    }

}
