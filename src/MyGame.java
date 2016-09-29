import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


/**
 * Created by Sanjeewa on 26/09/2016.
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
        System.out.println("Welcome to Mineral Supertrumps");
        System.out.println("1. Play a new game");
        System.out.println("0. Exit");

        myMenuChoice = MyCommonClass.inputInteger("Input the choice", 0, 1) ;
        return (myMenuChoice == 1) ;
    }

    public static boolean GetInitialInformation()
    {
        boolean isContinue = false ;

        while (true)
        {
            myName = MyCommonClass.inputString("Input your name") ;
            if ( myName.isEmpty() )
            {
                break ;
            }

            myNumberOfPlayers = MyCommonClass.inputInteger(
                    myName
                    + ", with how many players would you like to play the game "
                    , NUMBER_OF_PLAYERS_MINIMUM
                    , NUMBER_OF_PLAYERS_MAXIMUM
            ) ;
            isContinue = true ;
            break ;
        }
        return isContinue ;
    }

    public static boolean readCardsDetails()
    {
        boolean isFileRead = false ;
        // String[] myCardsPack = new String[ NUMBER_OF_CARDS+1 ] ;
        MyCardsPack[] myCardsArray = new MyCardsPack[ NUMBER_OF_CARDS + 3 ] ;
        System.out.println(myCardsArray.length);
        myCardsArray[0] = new MyCardsPack() ;
        myCardsArray[0].fileName = "hello" ;
        System.out.println(myCardsArray[0].fileName);
        // MyCardsPack m = new MyCardsPack() ;

        try
        {
            File myXmlFile = new File(XML_FILE) ;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance() ;
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder() ;
            Document myDoc = docBuilder.parse(myXmlFile) ;
            myDoc.getDocumentElement().normalize() ;

            System.out.println("Root : " + myDoc.getDocumentElement().getNodeName());

            NodeList nodeList = myDoc.getElementsByTagName("dict") ;
            System.out.println("--");

            for (int i=0; i<nodeList.getLength(); i++)
            {
                Node myNode = nodeList.item(i) ;
                System.out.println((i+1) + " Element : " + myNode.getNodeName());
                System.out.println();

                if ( myNode.getNodeType() == Node.ELEMENT_NODE )
                {
                    myCardsArray[i] = new MyCardsPack() ;

                    Element myElement = (Element) myNode ;
                    System.out.println("Key\t\t\tString") ;
                    System.out.println("~~~\t\t\t~~~~~~") ;

                    //System.out.println("Staff id : " + myElement.getAttribute("id"));
                    int j=0, keyCounter=0, stringCounter=0 ;
                    while ( j < ( myElement.getElementsByTagName("key").getLength() - 1 ) )
                    {
                        String myKey = myElement.getElementsByTagName("key").item(keyCounter).getTextContent() ;
                        System.out.println(j);
                        System.out.print(myKey) ;
                        System.out.print("\t") ;

                        if ( j == 2 )
                        {
                            // two <key> tags are found in the 3rd row (card_type)
                            keyCounter++ ;
                            System.out.print(myElement.getElementsByTagName("key").item(keyCounter).getTextContent()) ;
                        }
                        else
                        {
                            myCardsArray[0].fileName = myElement.getElementsByTagName("string").item(stringCounter).getTextContent() ;
                            System.out.print(myElement.getElementsByTagName("string").item(stringCounter).getTextContent());
                            stringCounter++ ;
                            if ( j == 7 )
                            {
                                // three <string> tags are found in the 8th row (occurrence)
                                while (myElement.getElementsByTagName("string").item(stringCounter).getParentNode().getNodeName() == "array")
                                {
                                    System.out.print(", " + myElement.getElementsByTagName("string").item(stringCounter).getTextContent());
                                    stringCounter++ ;
                                }
                            }
                        }
                        System.out.println("");
                        keyCounter++ ;
                        j++ ;
                    }
                    System.out.println("----------");
                }
            }
            isFileRead = true ;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return isFileRead ;
    }


    public static void readCardDetails2()
    {
        try
        {
            File myXmlFile = new File(XML_FILE) ;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance() ;
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder() ;
            Document myDoc = docBuilder.parse(myXmlFile) ;

            myDoc.getDocumentElement().normalize() ;

            System.out.println("Root : " + myDoc.getDocumentElement().getNodeName());

            NodeList nodeList = myDoc.getElementsByTagName("dict") ;
            System.out.println("--");

            for (int i=0; i<nodeList.getLength(); i++)
            {
                Node myNode = nodeList.item(i) ;
                System.out.println("Element : " + myNode.getNodeName());
                System.out.println("");

                if ( myNode.getNodeType() == Node.ELEMENT_NODE )
                {
                    Element myElement = (Element) myNode ;
                    System.out.println("Key\t\t\tString") ;
                    System.out.println("~~~\t\t\t~~~~~~") ;

                    //System.out.println("Staff id : " + myElement.getAttribute("id"));
                    for (int j=0; j<myElement.getElementsByTagName("key").getLength() ; j++)
                    {
                        System.out.println(myElement.getElementsByTagName("key").item(j).getTextContent()
                                         + "\t"
                                         + myElement.getElementsByTagName("string").item(j).getTextContent());

                    }
                    System.out.println("--");
                }
            }

        }
        catch (Exception e)
        {
           System.out.println(e);
        }
    }


    public static void readStaff()
    {
        try
        {
            File myXmlFile = new File("staff.xml") ;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance() ;
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder() ;
            Document myDoc = docBuilder.parse(myXmlFile) ;

            myDoc.getDocumentElement().normalize() ;

            System.out.println("Root : " + myDoc.getDocumentElement().getNodeName());

            NodeList nodeList = myDoc.getElementsByTagName("staff") ;
            System.out.println("--");

            for (int i=0; i<nodeList.getLength(); i++)
            {
                Node myNode = nodeList.item(i) ;
                System.out.println("Element : " + myNode.getNodeName());

                if (myNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element myElement = (Element) myNode ;

                    System.out.println("Staff id : " + myElement.getAttribute("id"));
                    System.out.println("Name : " + myElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Salary : " + myElement.getElementsByTagName("salary").item(0).getTextContent());
                }
            }



        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}
