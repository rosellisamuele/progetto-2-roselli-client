package server;

import java.io.*;
import java.net.*;

public class Server{
    int port = 6789;

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        System.out.println("1 SERVER partito in esecuzione... attesa connessione del client.");
        try{
            server = new ServerSocket(port);
            client = server.accept();

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
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));     
        outVersoClient = new DataOutputStream(client.getOutputStream());

        System.out.println("3 Benvenuto CLIENT, scrivi una frase e questa verrà ritrasmessa ... ");
        
        for(;;){
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta == "FINE"){
                outVersoClient.writeBytes(stringaRicevuta + " (=> Server in chiusura...)"+'\n');
                break;
                
            }else{
                outVersoClient.writeBytes(stringaRicevuta+" (ricevuta e trasmessa) "+'\n');
                System.out.println("6 Echo sul server: "+stringaRicevuta);
            }

            
        }

        System.out.println("6 Echo sul server in chiusura: "+stringaRicevuta);
        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 Chiusura socket "+client);
        client.close();
        
    }

}
