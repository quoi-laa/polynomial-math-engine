/**
 * Polynomial engine
 * 
 * Polynomial engine to represent and manipulate polynomials
 * where each term is a node in a singly-linked list
 * 
 * @author Huu Khoa Le
 * @version 1.0
 * @since 2024-06-24
 */

public class Polynome {

    protected Noeud premier = null;
    protected Noeud dernier = null;
    protected int nbTermes;

    public Polynome(){
        nbTermes = 0;
    }

    /**
     * Adds a term cx^e to a polynomial
     * Terms are added in descending order of exponent
     *
     * @param coeff: coefficient c of the term to be added
     * @param exposant: exponent e of the term to be added
     */
    public void ajouter(double coeff, int exposant){

        Noeud n = new Noeud(coeff, exposant);
        boolean elementAjoute = false;

        //If polynomial is empty
        if(premier == null){
            premier = n;
            dernier = n;

            nbTermes += 1;
        }

        //Linking term to existing polynomial 
        else{

            //Sum of coefficients of first term if exponents are equal
            if(n.exposant == premier.exposant){
                    premier.coeff = premier.coeff + n.coeff;
            }

            //Linking term at beginning of polynomial
            if(n.exposant > premier.exposant){
                n.prochain = premier;
                premier = n;

                nbTermes += 1;
            }

            else {

                //Support nodes for polynomial traversal
                Noeud terme = premier;
                Noeud elementSuivant = terme.prochain;

                //polynomial traversal
                while((terme != null) && (elementSuivant != null)){

                    //sum of coefficients if exponents are equal and if n is new
                    if((n.exposant == terme.exposant) && elementAjoute == false){

                        terme.coeff = terme.coeff + n.coeff;
                    }

                    //Linking term between two elements
                    if((terme.exposant > n.exposant) && (n.exposant > elementSuivant.exposant)){

                        terme.prochain = n;
                        n.prochain = elementSuivant;

                        //avoids adding a duplicate element
                        elementAjoute = true;

                        nbTermes += 1;
                    }

                    terme = terme.prochain;
                    elementSuivant = terme.prochain;
                }


            //sum of coefficients to last term is exponents are equal
            if(n.exposant == terme.exposant){
                    terme.coeff = terme.coeff + n.coeff;
                }


            //Linking term to end of polynomial
            if((terme.exposant > n.exposant) && (elementSuivant == null)){

                terme.prochain = n;
                n.prochain = null;

                nbTermes += 1;
                }
            }
        }
    }


    /**
     * Addition of a polynomial to another.
     * This function does not modify the current polynomial.
     *
     * @param autre the polynomial to be added
     * @return new polynomial containing the result of the addition
     */
    public Polynome additionner(Polynome autre) {

        Polynome resultat = new Polynome();

        //support nodes for reading polynomial
        Noeud termePolynm1 = premier;
        Noeud termePolynm2 = autre.premier;

        int i = 0, j = 0;

        //linking of 1st polynomial in resultat
        while( i < nbTermes){

            resultat.ajouter(termePolynm1.coeff, termePolynm1.exposant);

            termePolynm1 = termePolynm1.prochain;

            i += 1;
        }

        //linking of 2nd polynomial in resultat
        //sum of coefficients if exponents are equal
        while(j < autre.nbTermes){

            Noeud termeResultat = resultat.premier;

            //traversal of resultat
            while(termeResultat != null){

                if(termePolynm2.exposant == termeResultat.exposant){
                    termeResultat.coeff = termeResultat.coeff + termePolynm2.coeff;
                }

                else{
                    resultat.ajouter(termePolynm2.coeff, termePolynm2.exposant);
                }

                termeResultat = termeResultat.prochain;
            }

            j += 1;
            termePolynm2 = termePolynm2.prochain;
        }
        return resultat;
    }

    /**
     * Multiplication of a polynomial by a constant.
     * This function does not modify the current polynomial.
     *
     * @param c multiplicative constant
     * @return new polynomial containing the result of the multiplication
     */
    public Polynome multiplier(double c) {
        Polynome resultat = new Polynome();

        Noeud terme = premier;

        if(c == 0){
            resultat.ajouter(0, 0);
        }

        else{

            for(int i = 0; i < nbTermes; i++){

                resultat.ajouter(c * terme.coeff, terme.exposant);
    
                terme = terme.prochain;
            }
        }
        return resultat;
    }

    /**
     * Multiplication of a polynomial by another one.
     * This function does not modify the current polynomial.
     *
     * @param autre polynomial to multiply
     * @return new polynomial containing result of multiplication
     */
    public Polynome multiplier(Polynome autre) {

        Noeud termePolynm1 = premier;
        Noeud termePolynm2 = autre.premier;

        Polynome resultat = new Polynome();

        for(int i = 0; i < nbTermes; i++){

            for(int j = 0; j < autre.nbTermes; j++){

                    resultat.ajouter(termePolynm1.coeff * termePolynm2.coeff, termePolynm1.exposant + termePolynm2.exposant);

                    termePolynm2 = termePolynm2.prochain;
                }

            //return to beginning of 2nd polynomial to restart multiplication
            termePolynm2 = autre.premier;

            termePolynm1 = termePolynm1.prochain;

            }

            return resultat;
        }


    /**
     * Returns the derivative as a new polynomial.
     * This function does not modify the current polynomial.
     *
     * @return new polynomial which is the derivative of the current one
     */
    public Polynome derivee() {
        Polynome derivee = new Polynome();
        Noeud terme = premier;

        for(int i = 0; i < nbTermes; i++){

            if(terme.exposant != 0){
                derivee.ajouter(terme.exposant * terme.coeff,  terme.exposant - 1);
            }

            else if(terme.exposant == 0 && derivee.nbTermes == 0){
                derivee.ajouter(0, 0);
            }

            terme = terme.prochain;
        }
        return derivee;

    }

    /**
     * Returns the polynomial as a string.
     *
     * @return string formatted polynomial
     */
    @Override
    public String toString(){

        String equation = "";

        String strCoeff = "";
        String strX = "x";
        String strExpos = "";

        Noeud terme =  premier;

        //for each term, define how it will be displayed
        for(int i = 0; i < nbTermes; i++){

            if(terme.coeff % 1 == 0){
                strCoeff = (int) terme.coeff + "";
            }
            else{
                strCoeff = terme.coeff + "";
            }


            if(terme.coeff == 1 && terme.exposant == 1){
                strCoeff = "";
                strX = "x";
                strExpos = "";
            }
            else if(terme.coeff == -1){
                strCoeff = "-";
                strX = "x";
            }

            if(terme.exposant == 0){
                strX = "";
                strExpos = "";
            }
            else{
                strExpos = "^" + terme.exposant;
            }

            if((terme.coeff != 0 && terme.exposant == 1) &&  terme.exposant == 1){
                strExpos = "";
            }

            //concatenating the final equation
            equation = equation + strCoeff + strX + strExpos;

            //+ sign in between terms
            if(nbTermes - i != 1){
                equation = equation + " + ";
            }
            terme = terme.prochain;
        }

        return equation;
    }

    /**
     * Utility function to test the class
     *
     * @param test result of unit test
     * @param message description of test
     */
    public static void assertTest(boolean test, String message, String result) {
        if(test) {
            System.out.println("\n=== OK: " + message + " \n[ Result: " + result + " ]");
        } else {
            System.out.println("\n=== ERREUR: " + message);
        }
    }

    public static void main(String[] args) {

        String title = "=== POLYNOMIAL: ";

        Polynome p = new Polynome();

        p = new Polynome();

        p.ajouter(10, 0);
        assertTest(p.toString().equals("10"), "Single term (constant)", p.toString());

        p = new Polynome();
        p.ajouter(10, 1);
        assertTest(p.toString().equals("10x"), "Single term (exponent = 1)", p.toString());

        p = new Polynome();
        p.ajouter(10, 2);
        assertTest(p.toString().equals("10x^2"), "Single term (exponent = 2)", p.toString());

        p.ajouter(5, 4);
        assertTest(p.toString().equals("5x^4 + 10x^2"), "Two terms", p.toString());

        p.ajouter(3, 2);
        assertTest(p.toString().equals("5x^4 + 13x^2"), "Adding to an existing term", p.toString());

        p.ajouter(7, 0);
        assertTest(p.toString().equals("5x^4 + 13x^2 + 7"), "Adding a constant", p.toString());

        p.ajouter(-22, 3);
        assertTest(p.toString().equals("5x^4 + -22x^3 + 13x^2 + 7"), "Adding a negative coefficient", p.toString());


        Polynome derivee = p.derivee();
        assertTest(derivee.toString().equals("20x^3 + -66x^2 + 26x"), 
        "1st derivative (%s)".formatted(p.toString()), derivee.toString());

        derivee = p.derivee().derivee().derivee().derivee();
        assertTest(derivee.toString().equals("120"), 
        "4th derivative (%s)".formatted(p.toString()), derivee.toString());

        derivee = p.derivee().derivee().derivee().derivee().derivee()
                   .derivee().derivee().derivee().derivee().derivee();
        assertTest(derivee.toString().equals("0"), 
        "10th derivative (%s)".formatted(p.toString()), derivee.toString());


        Polynome p1 = new Polynome();
        p1.ajouter(2.1, 6);     
        p1.ajouter(6, 0);
        
        p1.ajouter(6, 86);
        assertTest(p1.toString().equals("6x^86 + 2.1x^6 + 6"), "Adding to beginning", p1.toString());    
        

        p1.ajouter(3, 9);
        assertTest(p1.toString().equals("6x^86 + 3x^9 + 2.1x^6 + 6"), "Adding between two terms", p1.toString());    

        p1.ajouter(6.5, 12);
        assertTest(p1.toString().equals("6x^86 + 6.5x^12 + 3x^9 + 2.1x^6 + 6"), 
        "Adding between terms", p1.toString());

        Polynome p2 = new Polynome();
        p2.ajouter(5, 6);   p2.ajouter(-8, 86);

        Polynome p3 = p1.additionner(p2);
        assertTest(p3.toString().equals("-66x^86 + 6.5x^12 + 3x^9 + 27.1x^6 + 6"), 
        "Adding two polynomials: ( %s ) + ( %s )".formatted(p1.toString(), p2.toString()), p3.toString());

        double c = 6.5;
        Polynome p4 = p3.multiplier(c);
        assertTest(p4.toString().equals("-429x^86 + 42.25x^12 + 19.5x^9 + 176.15x^6 + 39"), 
        "Multiplying by a constant: ( %s ) * %1.1f".formatted(p3.toString(), c), p4.toString());

        Polynome p5 = new Polynome();
        p5 = p4.multiplier(0);
        assertTest(p5.toString().equals("0"), "Multiplying by zero: ( %s ) * 0".formatted(p4.toString()), p5.toString());

        Polynome p6 = new Polynome();
        p6.ajouter(10, 2);
        p6.ajouter(-20, 0);

        Polynome p7 = new Polynome();
        p7.ajouter(75, 4);   p7.ajouter(5.5, 2);
        p7.ajouter(3, 1);
        

        Polynome p8 = p6.multiplier(p7);
        assertTest(p8.toString().equals("750x^6 + -1445x^4 + 30x^3 + -110x^2 + -60x"), 
        "Multiplying two polynomials: (%s) * (%s)".formatted(p6.toString(), p7.toString()), p8.toString());
    }
}