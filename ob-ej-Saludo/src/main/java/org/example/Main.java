package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        // Get application context
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // Get the Saludo bean
        Saludo saludo = (Saludo) context.getBean("saludo");

        // Execute imprimirSaludo method
        saludo.imprimirSaludo();

        // execute imprimirSaludo of NotificationService of the notificationService attribute in UserService
        UserService userService = (UserService) context.getBean("userService");
        userService.notificationService.imprimirSaludo();
    }
}