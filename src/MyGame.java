import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

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
        boolean isNoErrors = false ;

        while (true)
        {
            // Initialise the Card Pack
            if ( !MyInitial.readCardsDetails( true ) )
            {
                break ;
            }

            isNoErrors = true ;
            break ;
        }
        return isNoErrors ;
    }

    // Shuffle the cards
    // Deal the cards 8 each

    // MyPlayer myPlayer1 = new MyPlayer() ;
    //myPlayer1.display();


    // player plays a card
    // if my chance, display the cards, input the selection

}
