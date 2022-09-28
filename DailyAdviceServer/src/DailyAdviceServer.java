import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
    String[] adviceList = {"Take smaller bites","Go for the tight jeans",
    "One word: Inappropriate","Just for today, be honest","Gut gemacht! Weiter so...",
    "Beeilen Sie sich, sonst wärst du langsam!"};

    public void go(){
        try{
            ServerSocket serverSock = new ServerSocket(4242);

            while(true){
                Socket sock = serverSock.accept();
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice;
                for (int i=1; i<=2; i++){
                    advice = getAdvice();
                    writer.println(advice);
                    System.out.println(advice);
                }

                writer.close();

            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private String getAdvice(){
        int random = (int)(Math.random()*adviceList.length);
        return adviceList[random];
    }
    public static void main (String[] args){
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
