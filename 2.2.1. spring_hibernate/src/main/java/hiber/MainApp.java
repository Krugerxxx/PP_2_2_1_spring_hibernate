package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        for (int i = 1; i < 5; i++) {
            User us = new User("Петр" + i, "Петров" + i, "sfd@fes" + i);
            us.setCar(new Car("BMW" + i, i));
            userService.add(us);
        }


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println((Optional.ofNullable(user.getCar())).isPresent() ? user.getCar().toString() : "Нет автомобиля");
            System.out.println();
        }

        users = userService.getUsersByModelAndSeries(new Car("BMW3", 3));
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println((Optional.ofNullable(user.getCar())).isPresent() ? user.getCar().toString() : "Нет автомобиля");
            System.out.println();
        }

        context.close();
    }
}
