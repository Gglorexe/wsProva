/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbwsconsumerget;

import java.util.Scanner;

/**
 *
 * @author scuola
 */
public class NBWSConsumerGet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        WSConsumer webService = new WSConsumer();
        System.out.println("Inserisci il nome: ");
        Scanner tastiera = new Scanner(System.in);
        String nome = tastiera.nextLine();
        if (!nome.equals("")) {
            System.out.println("Desideri anche la descrizione del contatto?:[true/false]:");
            boolean descr = tastiera.nextBoolean();
            if(descr)
                webService.getUtente(nome, true);
            else
                webService.getUtente(nome, false);
            //webService.printResult();
        }

    }
}
