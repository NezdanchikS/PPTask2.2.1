package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setUserCar(new Car("modelCar1", 1111));
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setUserCar(new Car("modelCar2", 2222));

        userService.add(user1);
        userService.add(user2);
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
            System.out.println();
        }

        System.out.println("===============");
        System.out.println(userService.getUserByCars("modelCar2", 2222));

        context.close();
    }
}
