import java.util.Arrays;

/**
 * Created by Sanjeewa on 26/09/2016.
 *
 * Game related methods
 *
 */

public class MyGame
{

    protected static boolean myDisplayDetails1 = true ;   // Display additional information
    protected static int myDealer = 0 ;     // Select the player to deal first

    // Create all players card pack
    // 0.Physical 1-5. Computer
    protected static MyPlayer[] myCardsPackPlayers =
        new MyPlayer[MyConfig.myNumberOfPlayers + 1];


    public static boolean gameStart()
    {
        boolean myExitGame = true ;

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

        while (true)
        {

            // Initialise the dynamic card pack - myCardsPackDeck
            MyConfig.initialiseCardPackDeck();

            // Shuffle the cards

            // Get the dealer -- generate random number from 0 to number of players
            myDealer = 0;


            // Create each players card pack
            // (0) - Physical, (1-5) Computer
            for (int i = 0; i <= MyConfig.myNumberOfPlayers; i++) {
                myCardsPackPlayers[i] = new MyPlayer();
            }

            // Move the top card from deck (myCardPackDeck) to each player (myCardsPackPlayers)
            for (int i = 0; i < MyConfig.INITIAL_DEAL; i++) {
                int j = myDealer;     // start from the randamize number
                while (j <= MyConfig.myNumberOfPlayers) {
                    myCardsPackPlayers[j].myAdd(MyConfig.myCardsPackDeck.get(0));
                    MyConfig.myCardsPackDeck.remove(0);
                    j++;
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
                System.out.println("");
            }
            break ;
        }
        return isNoErrors ;
    }

    // Initialise and deal the cards to players
    public static boolean gameStartPlay()
    {

        // Start the game
        boolean myExitGame = false;        // Exit game

        // Player
        int myCurrentPlayer = 0 ;    // start from the randomise number
        int myPlayerCardChoicePhysical = 1;    // Physical player selects the card
        int myPlayerCardChoiceComputer = 1;    // Computer player selects the card
        int myCardIndex = 0 ;                   // User selected card from myCardsPackPlayers[myCurrentPlayer]
        int myCardNumber;                  // Actual Card Number

        int[] myPlayerWon = new int[MyConfig.myNumberOfPlayers + 1];   // 1.Still playing 0.Won
        int[] myPlayerPass = new int[MyConfig.myNumberOfPlayers + 1];   // 1.Can play 0.Pass
        Arrays.fill(myPlayerWon, 0);  // 1.Won  0.Playing
        int myTotalWonPlayers = 0;           // Total number of won players
        int myTotalPassPlayers = 0;      // Total number of Active players (not passed)
        int myCurrentHand = 1 ;      // Current hand


        // Category
        int myPlayerCategoryChoice = 1;    // Default category choice of the computer player
        double myCurrentCategoryValue1 = 0.0;   // temp
        int myCurrentCategoryValue2 = 1;       // temp


        // Main loop where other than won players play their cards
        while (myTotalWonPlayers < MyConfig.myNumberOfPlayers)
        {

            if (myCurrentPlayer > MyConfig.myNumberOfPlayers)
            {
                myCurrentPlayer = 0;
            }

            // If the current player is won loop to the next player
            if (myPlayerWon[myCurrentPlayer] == 1)
            {
                myCurrentPlayer++;
                continue;
            }

            // Select the dealer
            if ( myDealer >=0 )
            {
                myCurrentPlayer = myDealer;
                myDealer = -1 ;
            }
            else
            {
                myCurrentPlayer = MyCommon.findInteger(myPlayerPass, 0 ) ;
            }
            // Activate all the players
            // 1.Pass 0.Playing
            System.arraycopy(myPlayerWon, 0, myPlayerPass, 0, myPlayerPass.length);
            myTotalPassPlayers = 0;      // Total number of Active players (not passed)
            myCurrentHand = 1 ;      // Current hand


            // Loop where only active players play their cards
            while (myTotalPassPlayers < MyConfig.myNumberOfPlayers)
            {

                if (myCurrentPlayer > MyConfig.myNumberOfPlayers)
                {
                    myCurrentPlayer = 0;
                }
                // Give the turn only to active players
                if (myPlayerPass[myCurrentPlayer] == 1)
                {
                    myCurrentPlayer++;
                    continue;

                }


                // Physical Players turn
                if (myCurrentPlayer == 0)
                {
                    String myDisplayMessage1 = "Your turn, Input the card number" ;
                    int myPackLength = myCardsPackPlayers[myCurrentPlayer].myLength() ;

                    // Not the first chance to physical player
                    //  should be able to pass
                    if ( myCurrentHand > 1 )
                    {
                        myPackLength ++ ;
                        myDisplayMessage1 += " ( " + myPackLength + " to Pass ) " ;
                    }

                    // Display physical players cards
                    System.out.println("Your Card Details");
                    System.out.println("~~~~~~~~~~~~~~~~~");
                    System.out.println("");
                    myCardsPackPlayers[myCurrentPlayer].displayAllCardDetails();

                    while ( true )
                    {
                        myPlayerCardChoicePhysical = MyCommon.inputInteger(
                            myDisplayMessage1, 1, myPackLength);
                        if ( myPlayerCardChoicePhysical == 0 )
                        {
                            myExitGame = true;
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

                        // not the first and not passed
                        if ( myCurrentHand > 1 && myCardIndex >= 0 )
                        {
                            double myCategoryValue = myCardsPackPlayers[ myCurrentPlayer ]
                                .getCategoryValue( myCardIndex, myPlayerCategoryChoice ) ;
                            if ( myCategoryValue <= myCurrentCategoryValue1 )
                            {
                                System.out.println("\nCategory (trump) value has to be higher\n");
                                continue ;
                            }
                        }
                        break ;
                    }
                    if ( myExitGame )
                    {
                        break;
                    }


                    if ( myCurrentHand == 1 )   // Your Chance to select the category
                    {
                        myPlayerCategoryChoice = MyCommon.inputInteger(
                            "\nInput the category (trump) number\n"
                                + "1.Hardness, 2.Specific Gravity, 3.Cleavage, "
                                + "4.Crustal Abundance, 5.Economic Value"
                            , 1, 5);
                        if (myPlayerCategoryChoice == 0)
                        {
                            myExitGame = true;
                            break;
                        }
                    }

                    if ( myCardIndex >= 0 )
                    {
                        // if a higher category found move that card from the player to deck
                        // Display card with category details
                        // Display the selected card and category
                        System.out.println("\nYour Selected Card");
                        myCardsPackPlayers[myCurrentPlayer].displayCardCategory(myCardIndex, myPlayerCategoryChoice);
                        // Value of the selected category
                        myCurrentCategoryValue1 =
                            myCardsPackPlayers[myCurrentPlayer].getCategoryValue(myCardIndex, myPlayerCategoryChoice);

                        // Move the card from player (myCardsPackPlayers) to the deck (myCardPackDeck)
                        moveCardToDeck(myCardsPackPlayers, myCurrentPlayer, myCardIndex);

                    }
                    else
                    {
                        // if a higher category not found move a card from the deck to the player
                        // pass
                        System.out.println("\nPhysical Player Passed");
                        myPlayerPass[myCurrentPlayer] = 1;
                        myTotalPassPlayers++;
                        moveCardToPlayer(myCardsPackPlayers, myCurrentPlayer);

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
                    }
                    else
                    {
                        // search for a card from the players hand for a higher card than the selected category
                        myPlayerCardChoiceComputer =
                            myCardsPackPlayers[myCurrentPlayer].
                                isCategoryHigher(myPlayerCategoryChoice, myCurrentCategoryValue1);
                        myCardIndex = myPlayerCardChoiceComputer;    // Array starts with 0

                    }


                    if (myDisplayDetails1)
                    {
                        System.out.println("Current Player : " + myCurrentPlayer);
                        System.out.println("Selected card index : " + myCardIndex);

                    }


                    if (myCardIndex >= 0)
                    {
                        // if a higher category found move that card from the player to deck
                        // Display card with category details
                        System.out.println("\nPlayer " + myCurrentPlayer + " Played A Card");
                        myCardsPackPlayers[myCurrentPlayer].
                            displayCardCategory(myCardIndex, myPlayerCategoryChoice);

                        // Update the current category value
                        myCurrentCategoryValue1 = myCardsPackPlayers[myCurrentPlayer].
                            getCategoryValue(myCardIndex, myPlayerCategoryChoice);

                        myCardNumber = myCardsPackPlayers[myCurrentPlayer].myGet(myCardIndex);
                        if (myDisplayDetails1)
                        {
                            System.out.println("Selected number : " + myCardNumber);
                        }

                        moveCardToDeck(myCardsPackPlayers, myCurrentPlayer, myCardIndex);

                    }
                    else
                    {
                        // if a higher category not found move a card from the deck to the player
                        // pass
                        System.out.println("\nPlayer " + myCurrentPlayer + " Passed");
                        myPlayerPass[myCurrentPlayer] = 1;
                        myTotalPassPlayers++;
                        moveCardToPlayer(myCardsPackPlayers, myCurrentPlayer);
                    }

                }   // 0.User 1-5.Players


                // Display card numbers of all the players
                // remove later
                if (myDisplayDetails1)
                {
                    for (int myPlayerNumber = 0; myPlayerNumber <= MyConfig.myNumberOfPlayers; myPlayerNumber++)
                    {
                        System.out.print(myPlayerNumber == 0
                            ? "Physical Player   => "
                            : "Computer Player " + myPlayerNumber + " => ");
                        myCardsPackPlayers[myPlayerNumber].displayAllCardNumbers();
                    }
                    System.out.println("Table top deck ");
                    System.out.println(MyConfig.myCardsPackDeck);
                    System.out.println("");
                }

                System.out.println("------------------------\n");
                myCurrentHand ++ ;
                myCurrentPlayer++;
            }   // Main loop where player plays their cards

            if ( myExitGame )
            {
                System.out.println("");
                System.out.println("-----------------------------------------");
                System.out.println(" Game was abandoned half way by the user ");
                System.out.println("-----------------------------------------");
                break;
            }
        }   // Maim loop for players not yet won

        return ( myExitGame ) ;

        // player plays a card
        // if my chance, displayAllCardNumbers the cards, input the selection
        // Main Loop
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
