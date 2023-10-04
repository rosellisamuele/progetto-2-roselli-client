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
        System.out.println("SERVER disponibile... attesa connessione del client.");
        try{
            if(server == null){
                server = new ServerSocket(port);
            }

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


        for(;;){
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta == "FINE"){
               
                outVersoClient.writeBytes(stringaModificata + " (=> Server in chiusura...)"+'\n');
                System.out.println("Echo sul server in chiusura: "+stringaRicevuta);
                break;
                
            }else{
                stringaModificata = stringaRicevuta.toUpperCase();
                outVersoClient.writeBytes(stringaModificata+" (ricevuta e trasmessa) "+'\n');
                System.out.println("Echo sul server: "+stringaRicevuta);
            }

            
        }

        System.out.println("Echo sul server in chiusura: "+stringaRicevuta);
        System.out.println("Chiusura socket "+client);
        client.close();
        
    }

}
