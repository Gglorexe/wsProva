/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbwsconsumerget;

/**
 *
 * @author scuola
 */
public class NBWSConsumerGet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WSConsumer webService = new WSConsumer();
        
        webService.get("dencomune", "Mariano Comense");
        webService.printResult();
        
        webService.get("regione", "lombardia");
        webService.printResult();
    }
    
}
