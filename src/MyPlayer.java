import java.util.*;
import java.util.ArrayList;

/**
 * Created by Sanjeewa on 29/09/2016.
 *
 * Player related methods
 *
 */

public class MyPlayer
{
    ArrayList<Integer> myCardsHand = new ArrayList<Integer>() ;

    public MyPlayer()
    {

        myCardsHand.add(0) ;

    }


    public void display()
    {
        for (int i : myCardsHand )
        {
            System.out.println(i);
        }
        System.out.println("");
    }


}
