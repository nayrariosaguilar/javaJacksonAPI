package cat.proven.alumnesapi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import cat.proven.alumnesapi.Alumne;
import cat.proven.alumnesapi.AlumneDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* creació de la tabla:

SQL:

CREATE DATABASE escola;

USE escola;

CREATE TABLE IF NOT EXISTS alumnes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL,
    cognoms VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefon VARCHAR(20) NOT NULL,
    edat INT NOT NULL
);


INSERT INTO alumnes (dni, nom, cognoms, email, telefon, edat) 
VALUES ('00000000T', 'JOSEP', 'FUENTES ROBLES', 'josep.robles@gmail.com', '666777888', 45);

INSERT INTO alumnes (dni, nom, cognoms, email, telefon, edat) 
VALUES ('12345678Z', 'MARIA', 'ARNALDOS VICENTE', 'maria.vicente@gmail.com', '666555444', 33);
*/




/**
 * Implementació DAO 
 * Conté la implementació de les operacions definides a la interfície
 * 
 * Realitza les operacions SQL per interactuar amb la base de dades MySQL
 * Inicialitza la taula alumnes si no existeix.
 * 
 * @author mcast386
 */
public class AlumneDAOImpl implements AlumneDAO {
    private Connection connexio;

    public AlumneDAOImpl() {
        try {
            String url = "jdbc:mysql://localhost:3306/escola";
            String usuari = "root";
            String contrasenya = "";
            connexio = DriverManager.getConnection(url, usuari, contrasenya);
            inicialitzarBaseDeDades();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void inicialitzarBaseDeDades() {
        try (Statement stmt = connexio.createStatement()) {
            String crearTaula = "CREATE TABLE IF NOT EXISTS alumnes (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "dni VARCHAR(20) NOT NULL UNIQUE, " +
                                "nom VARCHAR(255) NOT NULL, " +
                                "cognoms VARCHAR(255) NOT NULL, " +
                                "email VARCHAR(255) NOT NULL, " +
                                "telefon VARCHAR(20) NOT NULL, " +
                                "edat INT NOT NULL)";
            stmt.execute(crearTaula);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Alumne> getAll() {
        List<Alumne> alumnes = new ArrayList<>();
        try (Statement stmt = connexio.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM alumnes");
            while (rs.next()) {
                alumnes.add(new Alumne(
                    rs.getInt("id"),
                    rs.getString("dni"),
                    rs.getString("nom"),
                    rs.getString("cognoms"),
                    rs.getString("email"),
                    rs.getString("telefon"),
                    rs.getInt("edat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnes;
    }

    @Override
    public Alumne getById(int id) {
        try (PreparedStatement stmt = connexio.prepareStatement("SELECT * FROM alumnes WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Alumne(
                    rs.getInt("id"),
                    rs.getString("dni"),
                    rs.getString("nom"),
                    rs.getString("cognoms"),
                    rs.getString("email"),
                    rs.getString("telefon"),
                    rs.getInt("edat")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Alumne getByDni(String dni) {
        try (PreparedStatement stmt = connexio.prepareStatement("SELECT * FROM alumnes WHERE dni = ?")) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Alumne(
                    rs.getInt("id"),
                    rs.getString("dni"),
                    rs.getString("nom"),
                    rs.getString("cognoms"),
                    rs.getString("email"),
                    rs.getString("telefon"),
                    rs.getInt("edat")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Alumne alumne) {
        try (PreparedStatement stmt = connexio.prepareStatement(
            "INSERT INTO alumnes (dni, nom, cognoms, email, telefon, edat) VALUES (?, ?, ?, ?, ?, ?)"
        )) {
            stmt.setString(1, alumne.getDni());
            stmt.setString(2, alumne.getNom());
            stmt.setString(3, alumne.getCognoms());
            stmt.setString(4, alumne.getEmail());
            stmt.setString(5, alumne.getTelefon());
            stmt.setInt(6, alumne.getEdat());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try (PreparedStatement stmt = connexio.prepareStatement("DELETE FROM alumnes WHERE id = ?")) {
            stmt.setInt(1, id);
            int filesAfectades = stmt.executeUpdate();
            return filesAfectades > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}