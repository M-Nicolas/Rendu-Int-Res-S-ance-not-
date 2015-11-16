import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /**
     * Méthode écrivant la chaine à envoyer au serveur pour écrire un Nom.
     * @param surnom
     * @return
     */
    static public String ecrireSetNom(String surnom) {
        return "(setNom)"+surnom;
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

    static public String createRequest() {
        System.out.println("Veuillez selectionner l'action à faire :\n1. Méthode SetNom.\n2. Méthode getSurnom." +
                "\n3. Méthode GetListe.\n4. Méthode RemoveNom.");

        Scanner in = new Scanner(System.in);

        String number = in.nextLine();

        if ("1".equals(number)) {
            System.out.println("Veuillez tapper le nom à ajouter :");
            in = new Scanner(System.in);

            String surname = in.nextLine();
            in.close();
            return ecrireSetNom(surname);
        } else if ("2".equals(number)) {
            System.out.println("Veuillez tapper le nom à partir duquel chercher les surnoms :");
            in = new Scanner(System.in);

            String name = in.nextLine();
            in.close();
            return ecrireGetSurnom(name);
        } else if ("3".equals(number)) {
            return ecrireGetListeNoms();
        } else if ("4".equals(number)) {
            System.out.println("Veuillez tapper le nom à supprimer :");
            in = new Scanner(System.in);

            String name = in.nextLine();
            in.close();
            return ecrireRemoveNom(name);
        } else{
            in.close();
            return error();
        }
    }

    static public String error() {
        System.out.println("Veuillez choisir une valeur entre 1 et 2");
        return createRequest();
    }

    /**
     * Main du client
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("client_0");

        Socket socket = null;

        DataOutputStream os = null; // output stream
        BufferedReader is = null; // input stream

        try {
            //System.out.println("Veuillez tapper l'adresse IP souhaite :");
            //Scanner in = new Scanner(System.in);

            //String target = in.nextLine();
            socket = new Socket("10.212.116.160", 1903);
            System.out.println("debut client");

            os = new DataOutputStream(socket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String request = createRequest();

            System.out.println("on va envoye :" + request);

            envoie(request, os);

            System.out.println("on a envoye !");

            System.out.println(ecoute(is));

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
            System.out.println("probleme d'envoie");
            e.printStackTrace();
        }
    }

    public static String ecoute(BufferedReader is){
        try{
            String recu = is.readLine();
            return recu;
        }catch(IOException e){
            System.out.println("probleme de reception");
            e.printStackTrace();
        }
        return "ERROR rien n'a été reçu";
    }
}