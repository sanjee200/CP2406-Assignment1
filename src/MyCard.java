/**
 * Created by Sanjeewa on 27/09/2016.
 *
 * Card Details
 *      myCardsPackMain[]
 */

public class MyCard
{

    public String fileName ;
    public String imageName ;
    public String cardType ;
    public String title ;
    public String chemistry ;
    public String classification ;
    public String crystalSystem ;
    public String occurrence ;
    public String hardness ;
    public String specificGravity ;
    public String cleavage ;
    public String crustalAbundance ;
    public String economicValue ;

    public String subTitle ;                // only for 6 trump cards
    public double hardnessDouble ;          // String of double hardness
    public double specificGravityDouble ;   // String of double specific gravity

    public String[] myCategory = {
          ""
        , "Hardness"
        , "Specific Gravity"
        , "Cleavage"
        , "Crustal Abundance"
        , "Economic Value"
    } ;


    public void set(String pKey, String pString )
    {
        if      ( pKey.equals( "fileName" ) )        fileName = pString ;
        else if ( pKey.equals( "imageName") )        imageName = pString ;
        else if ( pKey.equals( "card_type") )        cardType = pString ;
        else if ( pKey.equals( "title") )            title = pString ;
        else if ( pKey.equals( "chemistry") )        chemistry = pString ;
        else if ( pKey.equals( "classification") )   classification = pString ;
        else if ( pKey.equals( "crystal_system") )   crystalSystem = pString ;
        else if ( pKey.equals( "occurrence") )       occurrence = pString ;
        else if ( pKey.equals( "cleavage") )         cleavage = pString ;
        else if ( pKey.equals( "crustal_abundance")) crustalAbundance = pString ;
        else if ( pKey.equals( "economic_value") )   economicValue = pString ;
        else if ( pKey.equals( "subtitle") )         subTitle = pString ;
        else if ( pKey.equals( "hardness") )
        {
            hardness = pString ;
            hardnessDouble = Double.parseDouble( MyCommon.findRest( pString, "- " ) ) ;
        }
        else if ( pKey.equals( "specific_gravity") )
        {
            specificGravity = pString ;
            specificGravityDouble = Double.parseDouble( MyCommon.findRest( pString, "- " ) ) ;
        }
    }

    public void displayAll()
    {
        System.out.println( "File Name :\t\t" +        fileName ) ;
        System.out.println( "Image Name :\t\t" +       imageName ) ;
        System.out.println( "Card Type :\t\t" +        cardType ) ;
        System.out.println( "Title :\t\t" +            title ) ;
        System.out.println( "Chemistry :\t\t" +        chemistry    ) ;
        System.out.println( "Classification :\t\t" +   classification ) ;
        System.out.println( "CrystalSystem :\t\t" +    crystalSystem ) ;
        System.out.println( "Occurrence :\t\t" +       occurrence) ;
        System.out.println( "Hardness  :\t\t" +        hardness ) ;
        System.out.println( "Specific Gravity :\t\t" + specificGravity ) ;
        System.out.println( "Cleavage :\t\t" +         cleavage ) ;
        System.out.println( "Crustal Abundance :\t\t"+ crustalAbundance ) ;
        System.out.println( "Economic Value :\t\t" +   economicValue ) ;
        System.out.println( "Sub Title  :\t\t" +       subTitle ) ;
    }

    public void display()
    {
        System.out.println( "File Name\t\t\t: "      + fileName ) ;
        System.out.println( "Card Type\t\t\t: "      + cardType ) ;
        System.out.println( "Title\t\t\t\t: "        + title ) ;
        System.out.println( "Hardness \t\t\t: "      + hardness ) ;
        System.out.println( "Specific Gravity\t: "   + specificGravity ) ;
        System.out.println( "Cleavage\t\t\t: "       + cleavage ) ;
        System.out.println( "Crustal Abundance\t: "  + crustalAbundance ) ;
        System.out.println( "Economic Value\t\t: "   + economicValue ) ;
        System.out.println( "Card Type\t\t\t: "      + cardType ) ;
        if ( subTitle != null)
        {
            System.out.println( "Sub Title \t\t\t: "     + subTitle ) ;
        }
        System.out.println( "---------------------" ) ;
    }

    public void displayCategory( int pCategoryNumber )
    {
        System.out.println( "File Name : " + fileName ) ;
        System.out.println( "Title     : " + title ) ;
        System.out.println( "Category  : " + myCategory[ pCategoryNumber ] ) ;
        System.out.println( "Top Value : " + getCategoryString( pCategoryNumber ) ) ;
    }

    // double number
    public double getCategoryTopValue( int pCategoryNumber )
    {
        double myTopValue = 0.0 ;
        switch (pCategoryNumber)
        {
            case 1 :    // hardness
                myTopValue = hardnessDouble ;
                break ;
            case 2 :
                myTopValue = specificGravityDouble ;
                break ;
            default :
                break ;
        }
        return myTopValue ;
    }

    public String getCategoryString( int pCategoryNumber )
    {
        String myString = "" ;
        switch (pCategoryNumber)
        {
            case 1 :
                myString = hardness ;   // Double.toString( hardnessDouble ) ;
                break ;
            case 2 :
                myString = specificGravity ; // Double.toString( specificGravityDouble ) ;
                break ;
            case 3 :
                myString = cleavage ;
                break ;
            case 4 :
                myString = crustalAbundance ;
                break ;
            case 5 :
                myString = economicValue ;
                break ;
            default :
                break ;
        }

        return myString ;
    }


    public boolean isCategoryHigher( int pCategoryNumber, double pCurrentValue  )
    {
        boolean myFound = false ;
        switch ( pCategoryNumber )
        {
            case 1 :    // hardness
                myFound = hardnessDouble > pCurrentValue ;
                break ;
            case 2 :    // Specific Gravity
                myFound = specificGravityDouble > pCurrentValue ;
                break ;
            default :
                break ;
        }
        return ( myFound ) ;
    }

    public boolean isCategoryHigher2( int pCategoryNumber, String pCurrentValue  )
    {
        boolean myFound = false ;
        switch ( pCategoryNumber )
        {
            case 3 :    // Cleavage
                myFound = true ; // hardnessDouble > pCurrentValue ;
                break ;
            case 4 :    // Crustal Abundance
                myFound = true ; // specificGravityDouble > pCurrentValue ;
                break ;
            case 5 :    // Economic Value
                myFound = true ; // specificGravityDouble > pCurrentValue ;
                break ;
            default :
                break ;
        }
        return ( myFound ) ;
    }

}
