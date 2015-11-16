import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
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

    public static void envoie(String envoie, DataOutputStream os) throws IOException {
        int stop = envoie.length();
        for (int i = 0; i < stop; i++)
            os.writeChar(envoie.charAt(i));
        os.writeChar(';');
    }

    public static String ecoute(BufferedReader is){
        try{
            is.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}