/**
 * Noms des étudiants : Gianinetti Lucas & Plancherel Noémie
 */

package ch.heigvd.ser.labo2;

import ch.heigvd.ser.labo2.coups.*;
import org.jdom2.Document;
import org.jdom2.Element;

import java.io.*;
import java.util.List;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


// TODO : Vous avez le droit d'ajouter des instructions import si cela est nécessaire

class Main {

    public static void main(String... args) throws Exception {

        Document document = readDocument(new File("tournois_fse.xml"));

        // TODO : Compléter en une ligne l'initialisation de cette liste d'éléments (c'est la seule ligne que vous pouvez modifier ici.
        Element root = document.getRootElement();
        List<Element> tournois = root.getChildren("tournois"); /* A compléter en utilisant la variable root */;

        writePGNfiles(tournois);

    }

    /**
     * Cette méthode doit parser avec SAX un fichier XML (file) et doit le transformer en Document JDOM2
     * @param file
     */
    private static Document readDocument(File file) throws JDOMException, IOException {

        // TODO : A compléter... (ne doit pas faire plus d'une ligne). Vous êtes autorisés à créer des méthodes utilitaires, mais pas des classes
        SAXBuilder saxBuilder = new SAXBuilder();
        return (Document) saxBuilder.build(file);
    }

    /**
     * Cette méthode permet de retourner le TypePiece d'un attribut pièce récupéré dans le xml
     * @param piece Attribut de deplacement
     * @return TypePiece correspondant à l'attribut
     */
    private static TypePiece getPiece(String piece){
        switch(piece){
            case "Dame":
                return TypePiece.Dame;
            case "Roi":
                return TypePiece.Roi;
            case "Tour":
                return TypePiece.Tour;
            case "Cavalier":
                return TypePiece.Cavalier;
            case "Fou":
                return TypePiece.Fou;
            case "Pion":
                return TypePiece.Pion;
        }
        return null;
    }

    /**
     * Cette méthode permet de retourner le CoupSpecial d'un attribut coup_special récupéré dans le xml
     * @param special Attribut de coup
     * @return CoupSpecial correspondant à l'attribut
     */
    private static CoupSpecial getCoupSpecial(String special){
        switch(special){
            case "echec":
                return CoupSpecial.ECHEC;
            case "mat":
                return CoupSpecial.MAT;
            case "nulle":
                return CoupSpecial.NULLE;
        }
        return null;
    }

    /**
     * Cette méthode permet de retourner le TypeRoque d'un attribut type récupéré dans le xml
     * @param roque Attribut de roque
     * @return TypeRoque correspondant à l'attribut
     */
    private static TypeRoque getRoque(String roque){
        switch(roque){
            case "petit_roque":
                return TypeRoque.PETIT;
            case "grand_rpque":
                return TypeRoque.GRAND;
        }
        return null;
    }

    /**
     * Cette méthode doit générer un fichier PGN pour chaque partie de chaque tournoi recu en paramètre comme indiqué dans la donnée
     *
     * Le nom d'un fichier PGN doit contenir le nom du tournoi ainsi que le numéro de la partie concernée
     *
     * Nous vous conseillons d'utiliser la classe PrinterWriter pour écrire dans les fichiers PGN
     *
     * Vous devez utiliser les classes qui sont dans le package coups pour générer les notations PGN des coups d'une partie
     *
     * @param tournois Liste des tournois pour lesquelles écrire les fichiers PGN
     *
     *                 (!!! Un fichier par partie, donc cette méthode doit écrire plusieurs fichiers PGN !!!)
     */
    private static void writePGNfiles(List<Element> tournois) {

        int nb_partie = 0;
        int nb_tour = 0;

        try {

            // Tournois
            for (int cnt_tournois = 0; cnt_tournois < tournois.size(); ++cnt_tournois) {
                List<Element> tournoi = tournois.get(cnt_tournois).getChildren();

                // Tournoi
                for (int cnt_tournoi = 0; cnt_tournoi < tournoi.size(); ++cnt_tournoi) {
                    List<Element> parties = tournoi.get(cnt_tournoi).getChildren();
                    String path = "out/" + tournoi.get(cnt_tournoi).getAttributeValue("nom");

                    // Parties
                    for (int cnt_parties = 0; cnt_parties < parties.size(); ++cnt_parties) {
                        List<Element> partie = parties.get(cnt_parties).getChildren();

                        // Partie
                        for (int cnt_partie = 0; cnt_partie < partie.size(); ++cnt_partie) {
                            // Création du fichier
                            PrintWriter file = new PrintWriter(new FileWriter(path + "_" + (++nb_partie)));
                            List<Element> coups = partie.get(cnt_partie).getChildren();

                            // Coups
                            for (int cnt_coups = 0; cnt_coups < coups.size(); ++cnt_coups) {
                                List<Element> coup = coups.get(cnt_coups).getChildren();

                                // Déplacement/Roque
                                for (int cnt_coup = 0; cnt_coup < coup.size(); ++cnt_coup) {

                                    TypePiece pieceDeplacee;
                                    TypePiece elimination = null;
                                    TypePiece promotion = null;
                                    CoupSpecial coupSpecial = null;
                                    Case depart = null;
                                    Case arrivee;

                                    // Ecriture du numéro de tour
                                    if (cnt_coup % 2 == 0) {
                                        ++nb_tour;
                                        file.print(nb_tour + " ");
                                    } else
                                        file.print(" ");

                                    // Si le coup est un déplacement
                                    if (coup.get(cnt_coup).getChild("deplacement") != null) {
                                        Element deplacement = coup.get(cnt_coup).getChild("deplacement");

                                        // Récupération du coup joué par un joueur
                                        pieceDeplacee = getPiece(deplacement.getAttributeValue("piece"));
                                        if (deplacement.getAttributeValue("elimination") != null)
                                            elimination = getPiece(deplacement.getAttributeValue("elimination"));
                                        if (deplacement.getAttributeValue("promotion") != null)
                                            promotion = getPiece(deplacement.getAttributeValue("promotion"));
                                        if (coup.get(cnt_coup).getAttributeValue("coup_special") != null)
                                            coupSpecial = getCoupSpecial(coup.get(cnt_coup).getAttributeValue("coup_special"));
                                        if (deplacement.getAttributeValue("case_depart") != null)
                                            depart = new Case(deplacement.getAttributeValue("case_depart").charAt(0),
                                                    Integer.parseInt(String.valueOf(deplacement.getAttributeValue
                                                            ("case_depart").charAt(1))));
                                        arrivee = new Case(deplacement.getAttributeValue("case_arrivee").charAt(0),
                                                Integer.parseInt(String.valueOf(deplacement.getAttributeValue
                                                        ("case_arrivee").charAt(1))));

                                        Deplacement depl = new Deplacement(pieceDeplacee, elimination, promotion,
                                                coupSpecial, depart, arrivee);

                                        // Ecriture dans le fichier du coup joué en notation PGN
                                        file.print(depl.notationPGNimplem());

                                        // Si le coup est un coup spécial, on écrit la notationPGN dans le fichier
                                        if (coupSpecial != null)
                                            file.print(coupSpecial.notationPGN());
                                    }
                                    // Si le coup est un Roque
                                    else {
                                        if (coup.get(cnt_coup).getChild("roque") != null) {
                                            Element roque = coup.get(cnt_coup).getChild("roque");
                                            if (roque.getAttributeValue("type") != null)
                                                file.print(getRoque(roque.getAttributeValue("type")).notationPGN());
                                        }
                                    }
                                    if (cnt_coup % 2 != 0) file.print("\n");
                                }
                            }
                            file.flush();
                            file.close();
                            nb_tour = 0;
                        }

                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}