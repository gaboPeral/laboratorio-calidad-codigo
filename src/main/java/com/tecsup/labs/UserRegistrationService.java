package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de registro de usuarios con varios problemas de calidad
 * intencionales para el laboratorio.
 */
public class UserRegistrationService {

    // Mala práctica: campo público y mutable
    public String lastErrorMessage = "";

    // Mala práctica: lista sin genéricos
    private List users = new ArrayList();

    // Mala práctica: número mágico
    private static final int MIN_PASSWORD_LENGTH = 8;

    // Constructor con lógica innecesaria
    public UserRegistrationService() {
        // Comentario engañoso: aquí no se valida nada aún
        System.out.println("Constructor llamado");
        if (users == null) { // Esta condición nunca se cumple
            users = new ArrayList();
        }
    }

    /**
     * Registra un nuevo usuario.
     * Retorna true si se registra, false en caso contrario.
     */
    public boolean registerUser(String username, String password, String email) {
        // Posible NullPointerException: no se valida si username es null
        if (username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario está vacío.";
            return false;
        }

        // Código duplicado: validación de longitud escrita dos veces
        if (password == null) {
            lastErrorMessage = "La contraseña es null.";
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es muy corta.";
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) { // Duplicado intencional
            System.out.println("Advertencia: contraseña corta.");
        }

        // Mala lógica: condición incorrecta para validar email
        if (!email.contains("@") && !email.contains(".")) {
            lastErrorMessage = "El correo electrónico no parece válido.";
            // En realidad, debería ser una condición más estricta
        }

        // Manejo de excepciones deficiente
        try {
            // Simulación de acceso a base de datos
            saveUser(username, password, email);
        } catch (Exception e) {
            // Mala práctica: capturar Exception general y no registrar nada
            lastErrorMessage = "Error desconocido al guardar el usuario.";
            return false;
        }

        // Usuarios duplicados no se validan
        System.out.println("Usuario registrado: " + username);
        return true;
    }

    private void saveUser(String username, String password, String email) throws Exception {
        // Simula guardar el usuario en una lista
        users.add(username); // Mala práctica: solo se guarda el nombre
        if (username.equals("error")) {
            // Excepción artificial para que las herramientas lo detecten
            throw new Exception("Nombre de usuario no permitido.");
        }
    }

    // Método con nombre poco claro y sin comentarios
    public int x(String s) {
        if (s == null) {
            return -1;
        }
        // Uso ineficiente de String
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            result = result + s.charAt(i);
        }
        return result.length();
    }
}
