package com.deviceinsight.services.model.dao;

import com.deviceinsight.services.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl() {

    }

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> list() {

        Session sess = sessionFactory.openSession();

        List<User> listUser;
        try {
            /*listUser = (List<User>) sess.createCriteria(User.class)

                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            */

            String hql = "SELECT u from User u WHERE u.isTechnical=0";
            Query query = sess.createQuery(hql);
            listUser = (List<User>) query.list();



            sess.flush();
        } finally {
            if(sess.isOpen()) {
                sess.close();
            }        }

        return listUser;
    }

    @Override
    @Transactional
    public List<User> listOnlyAvailable() {

        Session sess = sessionFactory.openSession();





        List<User> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "SELECT u from User u WHERE u.isTechnical=0 AND u.id NOT IN (SELECT p.employee_id FROM Product p)";
            Query query = s.createQuery(hql);
            listUser = (List<User>) query.list();


            s.flush();
            /*if (listUser != null && !listUser.isEmpty()) {


                return listUser.get(0);
            }*/
        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }


        return listUser;






    }

    public String todaysMonthDate() {
        GregorianCalendar gc = new GregorianCalendar();

        SimpleDateFormat format_time_now1 = new SimpleDateFormat("MM");



        return format_time_now1.format(gc.getTime());
    }
    public String todaysYearDate() {
        GregorianCalendar gc = new GregorianCalendar();

        SimpleDateFormat format_time_now1 = new SimpleDateFormat("YYYY");



        return format_time_now1.format(gc.getTime());
    }

    public String todaysDayDate() {
        GregorianCalendar gc = new GregorianCalendar();

        SimpleDateFormat format_time_now1 = new SimpleDateFormat("dd");



        return format_time_now1.format(gc.getTime());
    }



    @Override
    @Transactional
    public void saveOrUpdate(User user) throws SQLIntegrityConstraintViolationException {
        Session s = sessionFactory.openSession();
        try {


            s.saveOrUpdate(user);
            s.flush();
        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }

    }

    @Override
    @Transactional
    public void delete(int id) {
        Session s = sessionFactory.openSession();
        try {
            User userToDelete = new User();
            userToDelete.setId(id);
            s.delete(userToDelete);
            s.flush();
        } finally {


            if(s.isOpen()) {
                s.close();
            }        }
    }

    @Override
    @Transactional
    public User get(int id) {
        List<User> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from User where id=" + id;
            Query query = s.createQuery(hql);
            listUser = (List<User>) query.list();


            s.flush();
            if (listUser != null && !listUser.isEmpty()) {


                return listUser.get(0);
            }
        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }


        return null;
    }

    @Override
    @Transactional
    public com.deviceinsight.services.model.User findByUsername(String username) {
        List<com.deviceinsight.services.model.User> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from User where username='" + username + "'";
            Query query = s.createQuery(hql);

            listUser = (List<com.deviceinsight.services.model.User>) query.list();

            s.flush();


            if (listUser != null && !listUser.isEmpty()) {
                return listUser.get(0);
            }
        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public com.deviceinsight.services.model.User findByEmail(String username) {
        List<com.deviceinsight.services.model.User> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from User where email='" + username + "'";
            Query query = s.createQuery(hql);

            listUser = (List<com.deviceinsight.services.model.User>) query.list();

            s.flush();
            if (listUser != null && !listUser.isEmpty()) {
                return listUser.get(0);
            }

        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }

        return null;
    }

    @Override
    @Transactional
    public String getRandomUsername() {


        return null;

    }

    @Override
    @Transactional
    public User findByResetPasswordToken(String hashcode) {
        Session s = sessionFactory.openSession();
        List<User> listUser;
        try {

            String hql = "from User where reset_password_token='" + hashcode + "' AND unix_timestamp() < last_token_timestamp + 3600 "; // sql-injection !
            Query query = s.createQuery(hql);

            listUser = (List<User>) query.list();

            s.flush();

            if (listUser != null && !listUser.isEmpty()) {
                return listUser.get(0);
            }

        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }


        return null;
    }

}