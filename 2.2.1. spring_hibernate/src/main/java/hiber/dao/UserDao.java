package hiber.dao;

import hiber.model.User;

import java.util.Calendar;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   List<User> listUsersByModelAndSeries(String model, int series);
}
