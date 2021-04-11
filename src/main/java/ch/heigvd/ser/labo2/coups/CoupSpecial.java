/*
 -----------------------------------------------------------------------------------
 Laboratoire : SER - Laboratoire 2
 Fichier     : CoupSpecial.java
 Auteur(s)   : Jeremy Zerbib, Guillaume Laubscher, Julien Quartier
 Date        : 14/04/2019
 But         : Enumeration definissant un coup special
 Remarque(s) :
 -----------------------------------------------------------------------------------
*/



package ch.heigvd.ser.labo2.coups;

public enum CoupSpecial implements ConvertissableEnPGN {

    ECHEC("+"),
    MAT("#"),
    NULLE("");

    private final String notation;

    CoupSpecial(String notation) {
        this.notation = notation;
    }

    public String notationPGN() {
        return notation;
    }
}
