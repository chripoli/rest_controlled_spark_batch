package loc.chripoli.rest_controlled_spark_batch_demo.util;

import loc.chripoli.rest_controlled_spark_batch_demo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which is used as a static user repository for demo purposes.
 *
 * @author chripoli
 */
public class StaticUserRepository {

    public static List<User> getUserList() {

        List<User> userList = new ArrayList<User>();

        userList.add(new User(1, "John", "Doe", 65));
        userList.add(new User(2, "Max", "Muster", 20));
        userList.add(new User(3, "Tom", "Doe", 43));

        return userList;

    }


}
