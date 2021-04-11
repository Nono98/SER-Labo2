/*
 -----------------------------------------------------------------------------------
 Laboratoire : SER - Laboratoire 2
 Fichier     : TypePiece.java
 Auteur(s)   : Jeremy Zerbib, Guillaume Laubscher, Julien Quartier
 Date        : 14/04/2019
 But         : Enumeration de toutes les pieces possibles
 Remarque(s) :
 -----------------------------------------------------------------------------------
*/



package ch.heigvd.ser.labo2.coups;

public enum TypePiece implements ConvertissableEnPGN {

    Tour("R"),
    Cavalier("N"),
    Fou("B"),
    Roi("K"),
    Dame("Q"),
    Pion("");

    private final String pgn;

    TypePiece(String pgn) {

        this.pgn = pgn;

    }

    public String notationPGN() {

        return pgn;

    }
}
