package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Ivan", "Sidorov", (byte)32);
        userDaoHibernate.saveUser("Dmitriy", "Kruglov", (byte)13);
        userDaoHibernate.saveUser("Oleg", "Kim", (byte)28);
        userDaoHibernate.saveUser("Oksana", "Trav", (byte)14);
        userDaoHibernate.getAllUsers().forEach(System.out::println);
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();

    }
}
