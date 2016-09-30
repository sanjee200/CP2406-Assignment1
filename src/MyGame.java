/**
 * Created by Sanjeewa on 26/09/2016.
 *
 * Game related methods
 *
 */

public class MyGame
{

    public static boolean newGame()
    {
        boolean isNoErrors = true ;
        int myDealer ;

        while (true)
        {
            // Initialise the Main Card Pack from XML file
            if ( !MyConfig.readCardsDetails( false ) )
            {
                isNoErrors = false ;
                break ;
            }

            // Initialise the dynamic card pack
            MyConfig.initialiseCardPackDeck();

            // Shuffle the cards

            // Get the dealer -- generate random number from 0 to number of players
            myDealer = 0 ;

            // Create all players card pack
            MyPlayer[] myCardsPackPlayers =
                new MyPlayer[ MyConfig.myNumberOfPlayers +1 ] ;

            // Create each players card pack
            // (0) - Physical, (1-5) Computer
            for (int i=0; i<= MyConfig.myNumberOfPlayers; i++ )
            {
                myCardsPackPlayers[i] = new MyPlayer() ;
            }

            // Move the top card from deck (myCardPackDeck) to each player (myCardsPackPlayers)
            for (int i=0; i< MyConfig.INITIAL_DEAL; i++ )
            {
                int j = myDealer ;     // start from the randamize number
                while ( j <= MyConfig.myNumberOfPlayers )
                {
                    myCardsPackPlayers[j].myAdd( MyConfig.myCardsPackDeck.get(0) );
                    MyConfig.myCardsPackDeck.remove(0) ;
                    j++ ;
                }
            }

            // Display card numbers of all the players
            // remove later
            for (int j=0; j<= MyConfig.myNumberOfPlayers ; j++)
            {
                myCardsPackPlayers[j].displayAllCardNumbers() ;
            }

            // Display physical players cards
            myCardsPackPlayers[0].displayAllCardDetails();

            // Start the game
            int myCardChoice = 1;
            int myCategoryChoice = 1 ;

            while (true)
            {
                int j = myDealer ;     // start from the randamise number
                while ( j <= MyConfig.myNumberOfPlayers )
                {
                    // Players turn
                    if ( j==0 )
                    {
                        myCardChoice = MyCommon.inputInteger(
                            "Input the card number", 1, myCardsPackPlayers[j].myLength() ) ;
                        if ( myCardChoice == 0 )
                        {
                            break ;
                        }

                        myCategoryChoice = MyCommon.inputInteger(
                              "Input the category number\n"
                            + "1.Hardness, 2.Specific Gravity, 3.Cleavage, 4.Crustal Abundance, 5.Economic Value"
                            , 1, 5 ) ;
                        if ( myCategoryChoice == 0 )
                        {
                            break ;
                        }

                        // Move the card from player (myCardsPackPlayers) to the deck (myCardPackDeck)
                        // Display the selected card and category

                        System.out.println("Your Selected Card");
                        myCardsPackPlayers[j].displayCardCatogory( myCardChoice, myCategoryChoice );

                    }
                    else
                    {

                    }


                    j++ ;
                }
                if ( myCardChoice == 0 || myCategoryChoice == 0 )
                {
                    System.out.println( "" );
                    System.out.println( "-----------------------------------------" ) ;
                    System.out.println( " Game was abandoned half way by the user " ) ;
                    System.out.println( "-----------------------------------------" ) ;
                    isNoErrors = false ;
                    break ;
                }

                break ;
            }

            // player plays a card
            // if my chance, displayAllCardNumbers the cards, input the selection


            break ;
        }
        return isNoErrors ;
    }

}
