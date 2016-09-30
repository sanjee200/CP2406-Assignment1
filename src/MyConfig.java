import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sanjeewa on 29/09/2016.
 *
 * Initialisation Methods
 *
 */

public class MyConfig
{
    public  static String myName = "" ;                 // Name of the physical player
    public  static int    myNumberOfPlayers = 3 ;       // Number of computer players

    public  static final int NUMBER_OF_CARDS = 60 ;     // Number of cards in the pack
    public  static MyCard[] myCardsPackMain = new MyCard[ NUMBER_OF_CARDS + 1  ] ;  // Main Card Pack

    public  static ArrayList<Integer> myCardsPackDeck = new ArrayList<Integer>() ;  // Main Dynamic Deck

    public  static final int INITIAL_DEAL= 3 ;          // Number of cards to deal initially


    // Constants
    private static final int    NUMBER_OF_PLAYERS_MINIMUM = 3 ;
    private static final int    NUMBER_OF_PLAYERS_MAXIMUM = 5 ;
    private static final String XML_FILE = "MstCards_151021.xml" ; //"MstCards_151021.xml" ;


    public static boolean DisplayMainMenu()
    {
        int myMenuChoice ;
        System.out.println( "Welcome to Mineral Supertrumps" ) ;
        System.out.println( "1. Play a new game" ) ;
        System.out.println( "0. Exit" ) ;

        myMenuChoice = MyCommon.inputInteger( "Input the choice", 0, 1 ) ;
        return ( myMenuChoice == 1 ) ;
    }


    public static boolean GetInitialInformation()
    {
        boolean isContinue = false ;

        while ( true )
        {
            myName = MyCommon.inputString( "Input your name" ) ;
            if ( myName.isEmpty() )
            {
                break ;
            }

            myNumberOfPlayers = MyCommon.inputInteger( myName
                    + ", with how many players would you like to play the game "
                , NUMBER_OF_PLAYERS_MINIMUM
                , NUMBER_OF_PLAYERS_MAXIMUM
            ) ;
            isContinue = true ;
            break ;
        }
        return ( isContinue ) ;
    }


    // Read XML file and copy it to card pack
    public static boolean readCardsDetails(boolean pDisplay)    // MyCard[] pCardsPackMain
    {
        boolean isFileRead = false ;
        String myKey, myString ;
        myCardsPackMain[0] = new MyCard() ;     // Pack starts from 1, leave 0 empty

        try
        {
            File myXmlFile = new File( XML_FILE ) ;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance() ;
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder() ;
            Document myDoc = docBuilder.parse( myXmlFile ) ;
            myDoc.getDocumentElement().normalize() ;
            NodeList nodeList = myDoc.getElementsByTagName( "dict" ) ;
            //System.out.println("Root : " + myDoc.getDocumentElement().getNodeName());


            for (int i=0; i < NUMBER_OF_CARDS; i++ )
            {
                Node myNode = nodeList.item( i ) ;
                // " Element : " + myNode.getNodeName()
                if ( pDisplay )
                {
                    System.out.println( i+1 );
                }

                if ( myNode.getNodeType() == Node.ELEMENT_NODE )
                {
                    myCardsPackMain[ i+1 ] = new MyCard() ;
                    Element myElement = ( Element ) myNode ;

                    //System.out.println("Staff id : " + myElement.getAttribute("id"));
                    int j=0, keyCounter=0, stringCounter=0 ;
                    while ( j < ( myElement.getElementsByTagName( "key" ).getLength() - 1 ) )
                    {
                        myKey = myElement.getElementsByTagName( "key" ).item( keyCounter ).getTextContent() ;
                        keyCounter++ ;

                        if ( myKey.equals( "card_type" ) ) //( j == 2 )
                        {
                            // two <key> tags are found in the 3rd row (card_type)
                            myString = myElement.getElementsByTagName( "key" ).item( keyCounter ).getTextContent() ;
                            keyCounter++ ;
                        }
                        else
                        {
                            myString = myElement.getElementsByTagName( "string" ).item( stringCounter ).getTextContent() ;
                            stringCounter++ ;
                            if ( myKey.equals("occurrence")) //( j == 7 )
                            {
                                // three <string> tags are found in the 8th row (occurrence)
                                while ( myElement.getElementsByTagName( "string" ).item( stringCounter ).getParentNode().getNodeName()
                                    == "array" )
                                {
                                    myString += ", " + myElement.getElementsByTagName( "string" ).item( stringCounter ).getTextContent() ;
                                    stringCounter++ ;
                                }
                            }
                        }

                        // Display card information
                        if ( pDisplay )
                        {
                            System.out.print(j);
                            System.out.print("\t" + myKey);
                            System.out.print("\t" + myString);
                            System.out.println("");
                        }

                        myCardsPackMain[i+1].set( myKey, myString ) ;
                        j++ ;
                    }
                    if ( pDisplay )
                    {
                        System.out.println( "-----------------------" ) ;
                    }
                }
            }

            if (pDisplay)
            {
                for (int i=0; i <= NUMBER_OF_CARDS; i++ )
                {
                    myCardsPackMain[i].displayAll();
                    System.out.println("-----------------\n");
                }
            }
            isFileRead = true ;
        }
        catch ( Exception e )
        {
            System.out.println( e ) ;
        }

        return ( isFileRead ) ;
    }


    public static void initialiseCardPackDeck()
    {
        for (int i=0; i<NUMBER_OF_CARDS; i++)
        {
            myCardsPackDeck.add(i+1);
        }
    }
}
