/**
 * Singly-linked list node
 * 
 * Custom implementation of a node in a singly-linked
 * 
 * @author Huu Khoa Le
 * @version 1.0
 * @since 2024-06-24
 */

public class Noeud {
    
    protected double coeff;
    protected int exposant;
    protected Noeud prochain;

    //Empty constructor
    public Noeud(){
    }

    public Noeud(double coeff, int exp){

        this.coeff = coeff;
        this.exposant = exp;
        this.prochain = null;

    }
}