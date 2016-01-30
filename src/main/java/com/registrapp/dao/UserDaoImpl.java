package com.registrapp.dao;

import com.registrapp.models.Role;
import com.registrapp.models.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user,Role role) {
        user.setSsoId(new Random(System.currentTimeMillis()).nextInt(1000000) + 10000);
        sessionFactory.getCurrentSession().save(user);
        user.setUser_role_id(2);
    }

    @Override
    public void deleteUser(Integer id) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM user WHERE user.id=?").setParameter(1,id);
        query.executeUpdate();
    }

    @Override
    public User getUserById(Integer id) {
        User userId = (User)sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM user WHERE user.id=?").setParameter(1,id).uniqueResult();
        return userId;
    }

    @Override
    public User getUserByUsername(String username) {
        User userUsername = (User)sessionFactory.getCurrentSession().createQuery("FROM User where firstname = ?").setParameter(0,username).uniqueResult();
        return userUsername;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users= (List<User>)sessionFactory.getCurrentSession().createSQLQuery("SELECT user.firstname, user.lastname, user.email, user_roles.role FROM user INNER JOIN user_roles USING (user_role_id)").list();
        return users;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
