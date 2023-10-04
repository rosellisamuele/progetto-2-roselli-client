package server;

public class App 
{
    public static void main( String[] args )
    {
        Server server = new Server();
        for(;;){ 
            server.attendi();
            try{
                server.comunica();
            }catch(Exception e){}
        }
        
    }
}
