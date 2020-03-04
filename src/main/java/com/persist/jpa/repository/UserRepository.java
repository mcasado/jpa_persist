package com.persist.jpa.repository;

import com.persist.jpa.entity.User;
import com.persist.jpa.entity.UserAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public User saveUser(User u) {
        getCurrentSession().saveOrUpdate(u);
        return u;
    }

    public List<User> getUsers(String accountId) {
        Query query = getCurrentSession().createQuery("SELECT User u where u.accountId = :id", User.class);
        query.setParameter("id", accountId);
        return query.list();
    }

    public List<User> saveUsers(List<User> users) {

        for (User u : users) {
            if (canAccessUser(u.getAccountId())) {
                saveUser(u);
            }
        }

        return users;
    }

    public UserAccess getUserAccess(String accountId, String username) {
        Query query = getCurrentSession().createQuery("SELECT UserAccess ua where ua.accountId = :id and ua.username = :username", UserAccess.class);
        query.setParameter("id", accountId);
        query.setParameter("username", username);
        List<UserAccess> results = query.list();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public Boolean canAccessUser(String accountId) {

        UserAccess ua = getUserAccess(accountId, ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername());

        if (ua != null && ua.getPermissions().indexOf("READ") > -1) {
            return true;
        }

        return false;
    }

    protected Session getCurrentSession() {
        return sessionFactory
                .withOptions()
                .openSession();
    }
}
