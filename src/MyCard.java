/**
 * Created by Sanjeewa on 27/09/2016.
 *
 * Card Details
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
    public double hardness ;
    public double specificGravity ;
    public String cleavage ;
    public String crustalAbundance ;
    public String economicValue ;
    public String subTitle ;        // only for 6 trump cards


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
            String myString = MyCommon.findRest( pString, "- " ) ;
            hardness = Double.parseDouble( myString ) ;
        }
        else if ( pKey.equals( "specific_gravity") )
        {
            String myString = MyCommon.findRest( pString, "- " ) ;
            specificGravity = Double.parseDouble( myString ) ;
        }
    }

    public void display()
    {
        System.out.println( "File Name : " +        fileName ) ;
        System.out.println( "Image Name : " +       imageName ) ;
        System.out.println( "Card Type : " +        cardType ) ;
        System.out.println( "Title : " +            title ) ;
        System.out.println( "Chemistry : " +        chemistry    ) ;
        System.out.println( "Classification : " +   classification ) ;
        System.out.println( "CrystalSystem : " +    crystalSystem ) ;
        System.out.println( "Occurrence : " +       occurrence) ;
        System.out.println( "Hardness  : " +        hardness ) ;
        System.out.println( "Specific Gravity : " + specificGravity ) ;
        System.out.println( "Cleavage : " +         cleavage ) ;
        System.out.println( "Crustal Abundance : "+ crustalAbundance ) ;
        System.out.println( "Economic Value : " +   economicValue ) ;
        System.out.println( "Sub Title  : " +       subTitle ) ;
    }

}
