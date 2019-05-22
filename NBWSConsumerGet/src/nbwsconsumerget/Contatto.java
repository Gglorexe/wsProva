/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbwsconsumerget;

/**
 *
 * @author Loris
 */
public class Contatto {
    String nome;
    String cognome;
    String numero;
    String descrizione;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
   
    @Override
    public String toString(){
        String s = "";
        s = "Nome: " + nome + " Cognome: " + cognome + " Numero: " + numero 
                + " Descrizione: " + descrizione;
        return s;
    }
    
}
