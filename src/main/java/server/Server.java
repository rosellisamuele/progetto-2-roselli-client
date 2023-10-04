package server;

import java.io.*;
import java.net.*;

public class Server extends Thread{
    int port = 6789;

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try{
            server = new ServerSocket(port);
            client = server.accept();
            server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server.");
            System.exit(1);
        }
        return client;
    }

    public void comunica() throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        for(;;){
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta == "FINE"){
                outVersoClient.writeBytes(stringaRicevuta + " (=> Server in chiusura...)"+'\n');
                System.out.println("6. Echo sul server in chiusura: "+stringaRicevuta);
                break;
            }else{
                outVersoClient.writeBytes(stringaRicevuta+" (ricevuta e trasmessa"+'\n');
                System.out.println("6. Echo sul server: "+stringaRicevuta);
            }
        }

        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 Chiusura socket "+client);
        client.close();
    }

    public void run(){
        try{
            comunica();
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
    }

}
