/*
 -----------------------------------------------------------------------------------
 Laboratoire : SER - Laboratoire 2
 Fichier     : Case.java
 Auteur(s)   : Jeremy Zerbib, Guillaume Laubscher, Julien Quartier
 Date        : 14/04/2019
 But         : Cree une case
 Remarque(s) :
 -----------------------------------------------------------------------------------
*/


package ch.heigvd.ser.labo2.coups;

public class Case implements ConvertissableEnPGN {

    private final char colonne;
    private final int ligne;

    /**
     *
     * @param colonne La lettre représentant la colonne (entre A et H)
     * @param ligne La lettre représentant la ligne (entre 1 et 8)
     */
    public Case(char colonne, int ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public int getLigne() {
        return ligne;
    }

    public char getColonne() {
        return colonne;
    }

    /**
     *
     * @return Les coordonnées de la case sous la forme : "a1" (colonne en minuscule suivi du numéro de ligne)
     */
    @Override
    public String notationPGN() {

        return String.format("%c%d", colonne, ligne);

    }

}
