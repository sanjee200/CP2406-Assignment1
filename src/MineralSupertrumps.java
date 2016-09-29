/**
 * Created by Sanjeewa on 26/09/2016.
 * Mineral Super Trumps
 */

public class MineralSupertrumps
{

    public static void main( String[] args )
    {

        while ( true )
        {
            // Display the main menu
/*

            if ( !MyGame.DisplayMainMenu() )
            {
                break ;
            }

            // continue with a new game
            if ( !MyGame.GetInitialInformation() )
            {
                break ;
            }
*/

            // Initialise the Card Pack
            if ( !MyGame.readCardsDetails(true) )
            {
                break ;
            }

            // MyGame.readStaff();

            // Shuffle the cards
            // Deal the cards 8 each

            // player plays a card
            // if my chance, display the cards, input the selection

            // end
            // display the won players order

            System.out.println( "-------------Done (With No Errors)------------------" ) ;
            break ;
        }
    }

}
