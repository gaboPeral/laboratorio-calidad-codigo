package com.tecsup.labs;

public class Main {

    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();

        // Casos de prueba simples
        service.registerUser("juan", "123", "juan@correo"); // Contraseña muy corta
        System.out.println(service.getLastErrorMessage());

        service.registerUser(null, "12345678", "correo-sin-arroba"); // username null
        System.out.println(service.getLastErrorMessage());

        service.registerUser("error", "12345678", "error@correo.com"); // fuerza excepción
        System.out.println(service.getLastErrorMessage());
    }
}
