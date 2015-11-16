
public class Client {
    public static void main(String[] args) {
        Socket socket = null;

        DataOutputStream os = null; // output stream
        BufferedReader is = null; // input stream

        try {
            socket = new Socket("", 1313);
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