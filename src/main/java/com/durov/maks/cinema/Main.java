package com.durov.maks.cinema;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.durov.maks.cinema.config.AppConfig;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

    }
}
