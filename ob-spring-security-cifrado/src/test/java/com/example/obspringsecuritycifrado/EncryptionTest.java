package com.example.obspringsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionTest {
    /**
     * BCrypt que genera su propio salt de 16 bytes
     *
     * El resultado de cifrar con bcrypt será un string de 60 caracteres:
     *
     * $a versión
     * $10 fuerza (valor que va de 4 a 31, por defecto vale 10)
     * Los 22 siguientes caracteres son el salt generado
     */
    @Test
    void bCryptTest() {
        BCryptPasswordEncoder
    }

}
