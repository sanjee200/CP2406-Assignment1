/**
 * Created by Sanjeewa on 26/09/2016.
 *
 * Mineral Super Trumps - Main Program
 *
 */


public class MineralSupertrumps
{


    public static void main( String[] args )
    {

        while ( true )
        {

            // Display the main menu
            if ( !MyConfig.DisplayMainMenu() )
            {
                break ;
            }
            System.out.println("");

            // Get Initial Information
            if ( !MyConfig.GetInitialInformation() )
            {
                break ;
            }
            System.out.println("");

            // Initialise the Main Card Pack from XML file
            if ( !MyConfig.readCardsDetails( false ) )
            {
                break ;
            }

            // Start a new game
            if ( !MyGame.gameStart() )
            {
                break ;

            }

            // end
            System.out.println(MyConfig.myName);
            System.out.println( "-------------Done (With No Errors)------------------" ) ;
            break ;
        }
    }
}
