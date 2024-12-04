/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.alumnesapi;

/**
 *
 * @author cri0625
 */
public class Alumne {
    private int id;
    private String dni;
    private String nom;
    private String cognoms;
    private String email;
    private String telefon;
    private int edat;

    public Alumne() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumne(int id, String dni, String nom, String cognoms, String email, String telefon, int edat) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.telefon = telefon;
        this.edat = edat;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getEdat() {
        return edat;
    }
    
}
