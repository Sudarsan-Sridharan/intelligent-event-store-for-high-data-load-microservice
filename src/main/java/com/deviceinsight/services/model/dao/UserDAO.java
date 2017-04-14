package com.deviceinsight.services.model.dao;


import com.deviceinsight.services.model.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserDAO {
    public List<User> list();
    public List<User> listOnlyAvailable();



    public User get(int id);



    public void saveOrUpdate(User user) throws SQLIntegrityConstraintViolationException;

    public void delete(int id);

    public com.deviceinsight.services.model.User findByUsername(String string);

    public com.deviceinsight.services.model.User findByEmail(String string);

    public String getRandomUsername();

    public User findByResetPasswordToken(String hashcode);
}