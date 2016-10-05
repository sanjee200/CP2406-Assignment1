import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

import java.util.Arrays;

/**
 * Created by Sanjeewa on 26/09/2016.
 *
 * Game related methods
 *
 */


public class MyGame
{

    protected static boolean myDisplayDetails1 = false ;   // Display additional information
    protected static int myDealer = 0 ;
         // Select the player to deal first

    // Create all players card pack
    // 0.Physical 1-5. Computer
    protected static MyPlayer[] myCardsPackPlayers =
        new MyPlayer[ MyConfig.myNumberOfPlayers + 1 ] ;


    public static boolean gameStart()
    {
        boolean myExitGame = false ;

        while ( true)
        {
            // Initialise variables
            if ( !(myExitGame = gameInitialise()) )
            {
                break ;
            }

            if ( !(myExitGame = gameStartPlay()) )
            {
                break ;
            }

            break ;
        }

        return myExitGame ;
    }


    // Initialise and deal the cards to players
    public static boolean gameInitialise()
    {
        boolean isNoErrors = true;

        while ( true )
        {

            // Initialise the dynamic card pack - myCardsPackDeck
            MyConfig.initialiseCardPackDeck();

            // Shuffle the cards

            // Get the dealer -- generate random number from 0 to number of players
            myDealer = 0;
            //myDealer = MyCommon.randomInt( 0, MyConfig.myNumberOfPlayers - 1 ) ;


            // Create each players card pack
            // (0) - Physical, (1-5) Computer
            for (int i = 0; i <= MyConfig.myNumberOfPlayers; i++ )
            {
                myCardsPackPlayers[i] = new MyPlayer() ;
            }

            // Move the top card from deck (myCardPackDeck) to each player (myCardsPackPlayers)
            for ( int i = 0; i < MyConfig.INITIAL_DEAL; i++ )
            {
                int j = myDealer ;     // start from the randamize number
                while ( j <= MyConfig.myNumberOfPlayers )
                {
                    myCardsPackPlayers[j].myAdd(MyConfig.myCardsPackDeck.get(0)) ;
                    MyConfig.myCardsPackDeck.remove(0) ;
                    j++ ;
                }
            }

            // Display card numbers of all the players
            if ( myDisplayDetails1 )
            {
                for ( int myPlayerNumber=0; myPlayerNumber<= MyConfig.myNumberOfPlayers ; myPlayerNumber++ )
                {
                    System.out.print( myPlayerNumber == 0
                        ? "Physical Player   => "
                        : "Computer Player " + myPlayerNumber + " => " ) ;
                    myCardsPackPlayers[myPlayerNumber].displayAllCardNumbers() ;
                }
                System.out.println("") ;
            }
            break ;
        }
        return isNoErrors ;
    }

    // Initialise and deal the cards to players
    public static boolean gameStartPlay()
    {

        // Start the game
        int myExitGameStatus = 0 ;        // If greater than 0 Exit game

        // Player
        int myCurrentPlayer = 0 ;    // start from the randomise number
        int myPlayerCardChoicePhysical = 1 ;    // Physical player selects the card
        int myPlayerCardChoiceComputer = 1 ;    // Computer player selects the card
        int myCardIndex = 0 ;                   // User selected card from myCardsPackPlayers[myCurrentPlayer]
        int myCardNumber ;                  // Actual Card Number

        int[] myPlayerWon = new int[ MyConfig.myNumberOfPlayers + 1 ] ;   // 1.Still playing 0.Won
        int[] myPlayerPass = new int[ MyConfig.myNumberOfPlayers + 1 ] ;   // 1.Can play 0.Pass
        Arrays.fill( myPlayerWon, 0 ) ;  // 1.Won  0.Playing
        int myTotalWonPlayers = 0 ;           // Total number of won players
        int myTotalPassPlayers ;      // Total number of Active players (not passed)
        int myCurrentHand = 1 ;      // Current hand

        // Category
        int myPlayerCategoryChoice = 1 ;    // Default category choice of the computer player
        double myHighestCategoryValue = 0.0 ;   // Highest value in the selected category ( double, array element )

        // Main loop where other than won players play their cards
        while ( myTotalWonPlayers < MyConfig.myNumberOfPlayers )
        {

            // Select the dealer
            if ( myDealer >=0 )     // First time select the dealer
            {
                myCurrentPlayer = myDealer ;
                myDealer = -1 ;
            }
            else                    // Next times select not passed player
            {
                myCurrentPlayer = MyCommon.findInteger( myPlayerPass, 0 ) ;
            }

            // Activate all the players, who has not won
            // 1.Pass 0.Playing
            System.arraycopy( myPlayerWon, 0, myPlayerPass, 0, myPlayerPass.length ) ;
            myTotalPassPlayers = myTotalWonPlayers ;      // Total number of Active players (not passed)
            myCurrentHand = 1 ;      // The first player to play the card, can hange the trump


            // Loop where only active players play their cards
            while ( myTotalPassPlayers < MyConfig.myNumberOfPlayers )
            {

                if ( myCurrentPlayer > MyConfig.myNumberOfPlayers )
                {
                    myCurrentPlayer = 0 ;
                }
                // Give the turn only to active players
                if ( myPlayerPass[myCurrentPlayer] == 1 )
                {
                    myCurrentPlayer++ ;
                    continue ;

                }

                // Physical Players turn
                if ( myCurrentPlayer == 0 )
                {
                    String myDisplayMessage1 = "" ;
                    if ( myCurrentHand == 1 )
                    {
                        myDisplayMessage1 += "You can change the category (trump)\n" ;
                    }
                    else
                    {
                        myDisplayMessage1 += "Your turn, " ;
                    }
                    myDisplayMessage1 += "Input the card number" ;
                    int myPackLength = myCardsPackPlayers[ myCurrentPlayer ].myLength() ;

                    // Not the first chance to physical player
                    //  should be able to pass
                    if ( myCurrentHand > 1 )
                    {
                        myPackLength ++ ;
                        myDisplayMessage1 += " ( " + myPackLength + " to Pass ) " ;
                    }

                    // Display physical players cards
                    System.out.println( "Your Card Details" ) ;
                    System.out.println( "~~~~~~~~~~~~~~~~~" ) ;
                    System.out.println( "" ) ;
                    myCardsPackPlayers[ myCurrentPlayer ].displayAllCardDetails() ;

                    while ( true )
                    {
                        myPlayerCardChoicePhysical = MyCommon.inputInteger(
                            myDisplayMessage1, 1, myPackLength ) ;
                        if ( myPlayerCardChoicePhysical == 0 )
                        {
                            myExitGameStatus = 1 ;
                            break;
                        }

                        if ( myCurrentHand > 1 && myPlayerCardChoicePhysical >= myPackLength )
                        {
                            // Pass
                            myCardIndex = -1 ;
                        }
                        else
                        {
                            myCardIndex = myPlayerCardChoicePhysical - 1 ;    // Array starts with 0
                        }

                        // if not the first time, if cannt select a card, has to pass
                        if ( myCurrentHand > 1 && myCardIndex >= 0 )
                        {
                            //double myCategoryValue = myCardsPackPlayers[ myCurrentPlayer ]
                            //    .getCategoryValue( myCardIndex, myPlayerCategoryChoice ) ;
                            // if ( myCategoryValue <= myHighestCategoryValue )
                            if ( !myCardsPackPlayers[ myCurrentPlayer ].isCategoryHigherOne(
                                myCardIndex, myPlayerCategoryChoice, myHighestCategoryValue ))
                            {
                                System.out.println( "\nCategory (trump) value has to be higher\n" ) ;
                                continue ;
                            }
                        }
                        break ;
                    }
                    if ( myExitGameStatus > 0 )
                    {
                        break ;
                    }

                    if ( myCurrentHand == 1 )   // Your Chance to select the category
                    {
                        myPlayerCategoryChoice = MyCommon.inputInteger(
                            "\nYour turn to select the category (trump) number\n"
                                + "1.Hardness, 2.Specific Gravity, 3.Cleavage, "
                                + "4.Crustal Abundance, 5.Economic Value"
                            , 1, 5 ) ;
                        if ( myPlayerCategoryChoice == 0 )
                        {
                            myExitGameStatus = 2 ;
                            break ;
                        }
                    }

                    if ( myCardIndex >= 0 )     // card selected
                    {
                        // if a higher category found move that card from the player to deck
                        // Display card with category details
                        // Display the selected card and category
                        System.out.println( "\nYour Selected Card " ) ;
                        myCardsPackPlayers[ myCurrentPlayer ]
                            .displayCardCategory( myCardIndex, myPlayerCategoryChoice ) ;
                        // Value of the selected category
                        myHighestCategoryValue =
                            myCardsPackPlayers[ myCurrentPlayer ]
                            .getCategoryValue( myCardIndex, myPlayerCategoryChoice ) ;

                        // Move the card from player (myCardsPackPlayers) to the deck (myCardPackDeck)
                        moveCardToDeck( myCardsPackPlayers, myCurrentPlayer, myCardIndex ) ;
                    }
                    else                    // pass
                    {
                        // if a higher category not found move a card from the deck to the player
                        // pass
                        System.out.println( "\nPhysical Player Passed" ) ;
                        myPlayerPass[myCurrentPlayer] = 1 ;
                        myTotalPassPlayers++ ;
                        moveCardToPlayer( myCardsPackPlayers, myCurrentPlayer ) ;
                    }
                }
                else
                {
                    // Computer players
                    // sort the players card by the category

                    // Select a card and a category
                    if ( myCurrentHand == 1 )
                    {
                        myCardIndex = 0 ;               // randomise between the length
                        myPlayerCategoryChoice = 1 ;    // randomise between 1-5

/*
                        // randomise between 0 to (length-1) of the hand
                        myCardIndex = MyCommon.randomInt(
                            0, myCardsPackPlayers[ myCurrentPlayer ].myLength()-1 ) ;
                        // randomise between 1-5
                        myPlayerCategoryChoice = MyCommon.randomInt( 1, 5) ;
*/
                        System.out.println( "Player " + myCurrentPlayer + " selected a new category (trump)" ) ;
                    }
                    else
                    {
                        // search for a card from the players hand for a higher card than the selected category
                        myPlayerCardChoiceComputer =
                            myCardsPackPlayers[ myCurrentPlayer ].
                            isCategoryHigherAll(myPlayerCategoryChoice, myHighestCategoryValue) ;
                        myCardIndex = myPlayerCardChoiceComputer ;    // Array starts with 0

                    }

                    if (myDisplayDetails1)
                    {
                        System.out.println( "Current Player : " + myCurrentPlayer ) ;
                        System.out.println( "Selected card index : " + myCardIndex ) ;
                    }

                    if ( myCardIndex >= 0 )
                    {
                        // if a higher category found move that card from the player to deck
                        // Display card with category details
                        if ( myCurrentHand != 1 )
                        {
                            System.out.println( "\nPlayer " + myCurrentPlayer + " played a card" ) ;
                        }
                        myCardsPackPlayers[ myCurrentPlayer ].
                            displayCardCategory( myCardIndex, myPlayerCategoryChoice ) ;

                        // Update the current category value
                        myHighestCategoryValue = myCardsPackPlayers[ myCurrentPlayer ].
                            getCategoryValue( myCardIndex, myPlayerCategoryChoice ) ;

                        myCardNumber = myCardsPackPlayers[ myCurrentPlayer ].myGet( myCardIndex ) ;
                        if ( myDisplayDetails1 )
                        {
                            System.out.println( "Selected number : " + myCardNumber ) ;
                        }

                        moveCardToDeck( myCardsPackPlayers, myCurrentPlayer, myCardIndex ) ;
                    }
                    else
                    {
                        // if a higher category not found move a card from the deck to the player
                        // pass
                        System.out.println( "\nPlayer " + myCurrentPlayer + " Passed" ) ;
                        myPlayerPass[ myCurrentPlayer ] = 1 ;
                        myTotalPassPlayers++ ;
                        moveCardToPlayer( myCardsPackPlayers, myCurrentPlayer ) ;
                    }

                }   // 0.User 1-5.Players

                // Display card numbers of all the players
                // remove later
                if ( myDisplayDetails1 )
                {
                    for ( int myPlayerNumber = 0; myPlayerNumber <= MyConfig.myNumberOfPlayers; myPlayerNumber++ )
                    {
                        System.out.print( myPlayerNumber == 0
                            ? "Physical Player   => "
                            : "Computer Player " + myPlayerNumber + " => " ) ;
                        myCardsPackPlayers[ myPlayerNumber ].displayAllCardNumbers();
                    }
                    System.out.println( "Table top deck " ) ;
                    System.out.println( MyConfig.myCardsPackDeck ) ;
                    System.out.println( "" ) ;
                }
                System.out.println( "------------------------\n" ) ;

                // Player won
                if ( myCardsPackPlayers[ myCurrentPlayer].myIsEmpty() )
                {
                    String myDisplayMessage2 =
                        myCurrentPlayer == 0 ? "You" : "Player " + myCurrentPlayer ;
                    myDisplayMessage2 += " Won the hand" ;
                    System.out.println( "--------------------------------" ) ;
                    System.out.println( myDisplayMessage2 ) ;
                    System.out.println( "--------------------------------" ) ;

                    myPlayerPass[ myCurrentPlayer ] = 1 ;
                    myPlayerWon[ myCurrentPlayer ] = 1 ;
                    myTotalPassPlayers ++ ;
                    myTotalWonPlayers ++ ;
                    myCurrentHand = 0 ;     // next player, new trump

                    if ( myCurrentPlayer == 0 )
                    {
                        if ( !(MyCommon.inputYesNo( "Do you want to still watch the game ?" ) ) )
                        {
                            myExitGameStatus = 3 ;
                            break ;
                        }
                    }
                }

                myCurrentHand ++ ;
                myCurrentPlayer++ ;
            }   // Main loop where player plays their cards

            if ( ( myExitGameStatus > 0 ) && ( myExitGameStatus < 3 ) )
            {
                System.out.println( "" ) ;
                System.out.println( "---------------------------" ) ;
                System.out.println( " Game was abandoned by you " ) ;
                System.out.println( "---------------------------" ) ;
            }

            if ( myExitGameStatus > 0 )
            {
                break ;
            }


            System.out.println( "--------------------------------" ) ;
            System.out.println( " Number of Players Won " + myTotalWonPlayers ) ;
            System.out.println( "--------------------------------" ) ;

        }   // Main loop for players not yet won

        if ( myTotalWonPlayers == MyConfig.myNumberOfPlayers )
        {
            System.out.println( "--------------------------------------------" ) ;
            System.out.println( " All the computer players have won the game " ) ;
            System.out.println( "--------------------------------------------" ) ;
        }

        return ( myExitGameStatus == 0 || myExitGameStatus == 3 ) ;
    }


    // Move the card from player (myCardsPackPlayers) to the deck (myCardPackDeck)
    public static void moveCardToDeck( MyPlayer[] pCardsPackPlayers, int pCurrentPlayer, int pCardIndex )
    {
        MyConfig.myCardsPackDeck.add( pCardsPackPlayers[ pCurrentPlayer ].myGet( pCardIndex ) ) ;   // Appends the players card number to the deck
        pCardsPackPlayers[ pCurrentPlayer ].myRemove( pCardIndex ) ;    // Removes card number (element) from the players hand

    }


    // Move the top card from deck (myCardPackDeck) to player (myCardsPackPlayers)
    public static void moveCardToPlayer( MyPlayer[] pCardsPackPlayers, int pCurrentPlayer )
    {
        int myCardNumber = MyConfig.myCardsPackDeck.get( 0 ) ;       // Card Number in top of the deck
        pCardsPackPlayers[ pCurrentPlayer ].myAdd( myCardNumber ) ; // Appends the card number to player (pCardsPackPlayers)
        MyConfig.myCardsPackDeck.remove( 0 ) ;  // Removes top card from the deck (myCardsPackDeck)
    }

}
