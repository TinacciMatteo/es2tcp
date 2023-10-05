package com.example;

import java.io.*;
import java.net.*;

public class AppClient {

    Socket mioSocket = null;

    int porta = 6789;

    BufferedReader in;
    DataOutputStream out;

    BufferedReader tastiera;

    String messaggio;
    
    public Socket connetti()
    {
        try {

            System.out.println("Pronto a connettermi al server...");

            Socket mioSocket = new Socket(InetAddress.getLocalHost(), porta);

            System.out.println("Connesso!");

            in = new BufferedReader(mioSocket.getInputStream());

            out = new DataOutputStream(mioSocket.getOutputStream());

        } catch (UnknownHostException e) {
           
            System.err.println("Host sconosciuto");

        } catch (Exception e) {
            
            e.printStackTrace();

            System.err.println("Impossibile stabilire connessione");
        }

        return mioSocket;
    }

    public void comunica(){

        try {

            do
            {

                tastiera = new BufferedReader( new InputStreamReader(System.in));
                System.out.println("Messaggio da inviare al server, se vuoi chiudere la connessione scrivi bye");
                messaggio = tastiera.readLine();
                System.out.println("Invio:" + messaggio);
                out.writeBytes(messaggio + "\n");
                System.out.println("In attesa di una risposta...");
                String ricevuta = in.readLine();
                System.out.println("Risposta del server:" + ricevuta);

            }while(!messaggio.equals("bye"));

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

   

    public static void main( String[] args ) 
    {
        AppClient c = new AppClient();
        c.connetti();
        c.comunica();
    }
}
