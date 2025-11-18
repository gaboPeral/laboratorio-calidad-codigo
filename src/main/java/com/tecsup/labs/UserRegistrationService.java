package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de registro de usuarios.
 * Esta versión corrige problemas de calidad intencionales.
 */
public class UserRegistrationService {

    private String lastErrorMessage = "";
    
    // Lista de usuarios con genéricos
    private List<User> users = new ArrayList<>();
    
    private static final int MIN_PASSWORD_LENGTH = 8;

    public UserRegistrationService() {
        System.out.println("Constructor llamado correctamente.");
    }

    /**
     * Registra un nuevo usuario.
     * @param username nombre de usuario
     * @param password contraseña
     * @param email correo electrónico
     * @return true si el usuario se registra, false en caso contrario
     */
    public boolean registerUser(String username, String password, String email) {

        // Validaciones
        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario no puede estar vacío.";
            return false;
        }

        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es muy corta o nula.";
            return false;
        }

        if (email == null || !email.contains("@") || !email.contains(".")) {
            lastErrorMessage = "El correo electrónico no es válido.";
            return false;
        }

        // Evitar duplicados
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                lastErrorMessage = "El usuario ya existe.";
                return false;
            }
        }

        // Guardar usuario con manejo de excepción específico
        try {
            saveUser(username, password, email);
        } catch (IllegalArgumentException e) {
            lastErrorMessage = e.getMessage();
            return false;
        }

        System.out.println("Usuario registrado correctamente: " + username);
        return true;
    }

    private void saveUser(String username, String password, String email) {
        // Simula guardar en lista
        if ("error".equals(username)) {
            throw new IllegalArgumentException("Nombre de usuario no permitido.");
        }

        users.add(new User(username, password, email));
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Método ejemplo con nombre claro y eficiente.
     */
    public int getStringLength(String s) {
        if (s == null) return -1;
        return s.length();
    }

    // Clase interna para representar un usuario
    private static class User {
        private String username;
        private String password;
        private String email;

        public User(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }
    }
}
