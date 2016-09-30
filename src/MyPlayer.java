import java.util.ArrayList;

/**
 * Created by Sanjeewa on 29/09/2016.
 *
 * Player related methods
 *
 */

public class MyPlayer
{
    ArrayList<Integer> myCardsPack = new ArrayList<Integer>() ;


    public void myAdd( int pCardNumber )
    {
        this.myCardsPack.add( pCardNumber ) ;
    }

    public int myLength()
    {
        return ( myCardsPack.size() ) ;
    }

    public boolean myIsEmpty()
    {
        return ( myCardsPack.isEmpty() ) ;
    }

    // display the players card numbers (Number of the card  related to the myCardsPackMain)
    public void displayAllCardNumbers()
    {
        for ( int i : myCardsPack )
        {
            System.out.print( i + "," ) ;
        }
        System.out.println( "" ) ;
    }

    // display details of All the cards
    public void displayAllCardDetails()
    {
        int j=1 ;
        for ( int i : myCardsPack )
        {
            System.out.println("Card " + j ) ;
            //System.out.println("Card Number : " + i  ) ;
            //System.out.println( MyConfig.myCardsPackMain[i].fileName ) ;
            MyConfig.myCardsPackMain[i].display() ;

            j++ ;
        }
        System.out.println("");
    }


    public void displayCardCatogory( int pCardIndex, int pCategoryNumber )
    {
        // pCardIndex=>1-3, myCardsPack => 0-2, myCardsPack[2] => 9
        int myCardNumber = myCardsPack.get( pCardIndex-1 ) ;
        MyConfig.myCardsPackMain[ myCardNumber ].displayCategory( pCategoryNumber ) ;
        // myCardsPackMain[1-60]  - has all the details of 60 cards
        // myCardsPack[0-x ]      - has all the players card numbers
    }



}
