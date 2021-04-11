/*
 -----------------------------------------------------------------------------------
 Laboratoire : SER - Laboratoire 2
 Fichier     : TypeRoque.java
 Auteur(s)   : Jeremy Zerbib, Guillaume Laubscher, Julien Quartier
 Date        : 14/04/2019
 But         : Enumeration definissant le type de coup roque
 Remarque(s) :
 -----------------------------------------------------------------------------------
*/



package ch.heigvd.ser.labo2.coups;

public enum TypeRoque implements ConvertissableEnPGN {

    PETIT("O-O"),
    GRAND("O-O-O");

    private final String pgn;

    TypeRoque(String pgn) {
        this.pgn = pgn;
    }

    public String notationPGN() {
        return pgn;
    }

}
