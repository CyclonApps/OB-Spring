package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // EJEMPLO 1
        // Opción 1. Crear un objeto de forma normal
        //Calculadora service = new Calculadora();

        // Opción 2. Recibir un objeto de Spring
        Calculadora calculadora = (Calculadora) context.getBean("calculadora");

        String texto = calculadora.holaMundo();
        System.out.println(texto);

        Calculadora calculadora2 = (Calculadora) context.getBean("calculadora");
        texto = calculadora2.holaMundo();
        System.out.println(texto);

        // EJEMPLO 2

    }
}