import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


/**
 * Created by Sanjeewa on 26/09/2016.
 * The initialisation methods
 *
 *
 */

public class MyGame
{
    private static final int    NUMBER_OF_PLAYERS_MINIMUM = 3 ;
    private static final int    NUMBER_OF_PLAYERS_MAXIMUM = 5 ;
    private static final String XML_FILE = "MstCards_151021.xml" ; //"MstCards_151021.xml" ;
    private static final int    NUMBER_OF_CARDS = 60 ;


    public static String    myName ;
    public static int       myNumberOfPlayers ;

    public static boolean DisplayMainMenu()
    {
        int myMenuChoice ;
        System.out.println( "Welcome to Mineral Supertrumps" ) ;
        System.out.println( "1. Play a new game" ) ;
        System.out.println( "0. Exit" ) ;

        myMenuChoice = MyCommonClass.inputInteger( "Input the choice", 0, 1 ) ;
        return ( myMenuChoice == 1 ) ;
    }

    public static boolean GetInitialInformation()
    {
        boolean isContinue = false ;

        while ( true )
        {
            myName = MyCommonClass.inputString( "Input your name" ) ;
            if ( myName.isEmpty() )
            {
                break ;
            }

            myNumberOfPlayers = MyCommonClass.inputInteger( myName
                    + ", with how many players would you like to play the game "
                    , NUMBER_OF_PLAYERS_MINIMUM
                    , NUMBER_OF_PLAYERS_MAXIMUM
            ) ;
            isContinue = true ;
            break ;
        }
        return ( isContinue ) ;
    }

    public static boolean readCardsDetails(boolean pDisplay)
    {
        boolean isFileRead = false ;
        String myKey ;
        String myString ;
        MyCardsPack[] myCardsPackMain = new MyCardsPack[ NUMBER_OF_CARDS + 1  ] ;
        myCardsPackMain[0] = new MyCardsPack() ;

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
                if ( pDisplay )
                {
                    System.out.println( ( i+1 ) + " Element : " + myNode.getNodeName() ) ;
                    System.out.println() ;
                }

                if ( myNode.getNodeType() == Node.ELEMENT_NODE )
                {
                    myCardsPackMain[ i+1 ] = new MyCardsPack() ;

                    Element myElement = ( Element ) myNode ;
                    System.out.println( "No\tKey\t\t\tString" ) ;
                    System.out.println( "~~\t~~~\t\t\t~~~~~~" ) ;

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

                        {
                            if ( myKey.equals( "fileName" ) )
                                myCardsPackMain[ i+1 ].fileName = myString;
                            else if (myKey.equals( "imageName") )
                                myCardsPackMain[ i+1 ].imageName = myString;
                            else if (myKey.equals( "card_type") )
                                myCardsPackMain[ i+1 ].cardType = myString;
                            else if (myKey.equals( "title") )
                                myCardsPackMain[ i+1 ].title = myString;
                            else if (myKey.equals( "chemistry") )
                                myCardsPackMain[ i+1 ].chemistry = myString;
                            else if (myKey.equals( "classification") )
                                myCardsPackMain[ i+1 ].classification = myString;
                            else if (myKey.equals( "crystal_system") )
                                myCardsPackMain[ i+1 ].crystalSystem = myString;
                            else if (myKey.equals( "occurrence") )
                                myCardsPackMain[ i+1 ].occurrence = myString;
                            else if (myKey.equals( "hardness") )
                                myCardsPackMain[ i+1 ].hardness = myString;
                            else if (myKey.equals( "specific_gravity") )
                                myCardsPackMain[ i+1 ].specificGravity = myString;
                            else if (myKey.equals( "cleavage") )
                                myCardsPackMain[ i+1 ].cleavage = myString;
                            else if (myKey.equals( "crustal_abundance") )
                                myCardsPackMain[ i+1 ].crustalAbundance = myString;
                            else if (myKey.equals( "economic_value") )
                                myCardsPackMain[ i+1 ].economicValue = myString;
                            else if (myKey.equals( "subtitle") )
                                myCardsPackMain[ i+1 ].subTitle = myString;
                        }

                        j++ ;
                    }
                    if ( pDisplay )
                    {
                        System.out.println( "-----------------------" ) ;
                    }
                }
            }

            if (true)
            {
                for (int i=0; i <= NUMBER_OF_CARDS; i++ )
                {
                    System.out.println( myCardsPackMain[ i ].fileName ) ;
                    System.out.println( myCardsPackMain[ i ].imageName ) ;
                    System.out.println( myCardsPackMain[ i ].cardType ) ;
                    System.out.println( myCardsPackMain[ i ].title ) ;
                    System.out.println( myCardsPackMain[ i ].chemistry ) ;
                    System.out.println( myCardsPackMain[ i ].classification ) ;
                    System.out.println( myCardsPackMain[ i ].crystalSystem ) ;
                    System.out.println( myCardsPackMain[ i ].occurrence ) ;
                    System.out.println( myCardsPackMain[ i ].hardness ) ;
                    System.out.println( myCardsPackMain[ i ].specificGravity ) ;
                    System.out.println( myCardsPackMain[ i ].cleavage ) ;
                    System.out.println( myCardsPackMain[ i ].crustalAbundance ) ;
                    System.out.println( myCardsPackMain[ i ].economicValue ) ;
                    System.out.println( myCardsPackMain[ i ].subTitle ) ;
                    System.out.println("-----------------");
                    System.out.println("") ;
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

}
