import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
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

            os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Youston, we have a problem ! Voici le problème en question : " + e);
        }
    }

    public static void envoie(String envoie, DataOutputStream os) {
        int stop = envoie.length();
        try {
            for (int i = 0; i < stop; i++)
                os.writeChar(envoie.charAt(i));
            os.writeChar(';');
        }catch(IOException e){
            System.out.println("probleme d'envoie")
            e.printStackTrace();
        }
    }

    public static String ecoute(BufferedReader is){
        try{
            String recu = is.readLine();
            return recu;
        }catch(IOException e){
            System.out.println("probleme de reception")
            e.printStackTrace();
        }
    }
}