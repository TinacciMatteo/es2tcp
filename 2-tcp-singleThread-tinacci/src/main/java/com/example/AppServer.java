package com.example;

import java.io.*;
import java.net.*;

public class AppServer 
{
    ServerSocket server = null;
    Socket socketClient = null;

    int porta = 6789;

    DataInputStream in;
    DataOutputStream out;

    String letto;

    public Socket attendi(){

            try {

                System.out.println("Inizializzo il server...");

                server = new ServerSocket(porta);

                System.out.println("Server pronto in ascolto sulla porta: " + porta);

                socketClient = server.accept();

                System.out.println("Connessione stabilita con un client!");

                in = new DataInputStream(socketClient.getInputStream());
                out = new DataOutputStream(socketClient.getOutputStream());
            
            } catch (IOException e) {
            
                e.printStackTrace();
            }

            return socketClient;
        }

    public void comunica(){
        for (;;) {
            
            try {

                do
                {

                    System.out.println("Aspetto un messaggio dal client...");
                    letto = in.readLine();
                    System.out.print("Messaggio ricevuto: " + letto);
                    String risposta = letto.toUpperCase();
                    System.out.println("Risposta: " + risposta);
                    out.writeBytes(risposta + "\n");

                }while(!letto.equals("bye"));    
                
                    if(letto.equals("bye")){
                        System.out.println("Chiudo la connessione.");
                        socketClient.close();
                    }   
                

            } catch (IOException e) {
            
                e.printStackTrace();
            }
        }
    }
    }

    

    public static void main( String[] args ) throws IOException
    {
        AppServer a =  new AppServer();

        a.attendi();
        a.comunica();
    }
}
