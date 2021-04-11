/*
 -----------------------------------------------------------------------------------
 Laboratoire : SER - Laboratoire 2
 Fichier     : Roque.java
 Auteur(s)   : Jeremy Zerbib, Guillaume Laubscher, Julien Quartier
 Date        : 14/04/2019
 But         : Cree un roque qui est un type specifique de coup
 Remarque(s) :
 -----------------------------------------------------------------------------------
*/

package ch.heigvd.ser.labo2.coups;

public class Roque extends Coup {

    private final TypeRoque typeRoque;

    /**
     *
     * @param coupSpecial Indique si c'est un coup spécial (echec, mat, null) peut être null
     * @param typeRoque Indique le type de roque (petit ou grand)
     */
    public Roque(CoupSpecial coupSpecial, TypeRoque typeRoque) {

        super(coupSpecial);

        this.typeRoque = typeRoque;

    }

    /**
     *
     * @return Doit retourner la notation PGN du roque comme indiqué dans la donnée
     *
     *         Ici, vous pouvez faire appel directement à l'énumération typeRoque
     */
    @Override
    protected String notationPGNimplem() {
        return typeRoque.notationPGN();
    }

}
