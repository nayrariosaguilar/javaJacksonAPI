/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.alumnesapi;

import java.util.List;

/**
 * Interfície DAO
 * 
 * Defineix les operacions de persistència (getAll, getById, getByDni, save, delete).
 * 
 * Facilita la separació de la lògica d'acces a les dades, de la lògica del negoci
 * 

 */
public interface AlumneDAO {
    List<Alumne> getAll();
    Alumne getById(int id);
    Alumne getByDni(String dni);
    void save(Alumne alumne);
    boolean delete(int id);
}
