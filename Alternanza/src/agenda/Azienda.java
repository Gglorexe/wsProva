/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author Loris
 */
public class Azienda {
    String nome;
    String indirizzo;
    String email;
    String tutor;
    
    public Azienda(String nome, String indirizzo, String email, String tutor){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.email = email;
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", email='" + getEmail() + "'" +
            ", tutor='" + getTutor() + "'" +
            "}";
    }
    
     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
}
