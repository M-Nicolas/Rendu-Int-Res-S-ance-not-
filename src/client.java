import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /**
     * Méthode écrivant la chaine à envoyer au serveur pour reccupérer un Nom.
     * @param surnom
     * @return
     */
    static public String ecrireGetNom(String surnom) {
        return "(getNom)"+surnom;
    }

    /**
     * Méthode écrivant la chaine à envoyer au serveur pour réccupérer un Surnom.
     * @param nom
     * @return
     */
    static public String ecrireGetSurnom(String nom) {
        return "(getSurnom)"+nom;
    }

    /**
     * Méthode écrivant la chaine à envoyer au serveur pour réccupérer la liste de noms.
     * @return
     */
    static public String ecrireGetListeNoms() {
        return "(getListeNoms)";
    }

    /**
     * Méthode écrivant la chaine à envoyer au serveur pour supprimer un nom.
     * @param nom
     * @return
     */
    static public String ecrireRemoveNom(String nom) {
        return "(removeNom)"+nom;
    }

    /**
     * Main du client
     * @param args
     */
    public static void main(String[] args) {
        Socket socket = null;

        DataOutputStream os = null; // output stream
        BufferedReader is = null; // input stream

        try {
            System.out.println("Veuillez tapper l'adresse IP souhaite :");
            Scanner in = new Scanner(System.in);

            String target = in.nextLine();
            socket = new Socket(target, 1313);
            System.out.println("debut client");

            os = new DataOutputStream(socket.getOutputStream());
            is = new BufferedReader(socket.getInputStream());

            /*
            String tavu = checkAnswer(a);

            if ("ok".equals(tavu)) {
                showAction(a);
                showValues(a);
            } else if ("nope". equals(tavu)) {
                System.out.println("Le serveur est pas gentil, il a refusé notre requète...");
            } else {
                System.out.println("Dear, we're in trouble !");
            }
            */

            os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Youston, we have a problem ! Voici le problème en question : " + e);
        }
    }
}