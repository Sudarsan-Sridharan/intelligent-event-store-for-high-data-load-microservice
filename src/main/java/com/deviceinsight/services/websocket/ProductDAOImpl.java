package com.deviceinsight.services.websocket;

import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.websocket.ProductDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//////////////////// USE THIS! import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;


    public ProductDAOImpl() {

    }

    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Product> list() {
        Session s = sessionFactory.openSession();
        List<Product> listUser;
        try {
            listUser = (List<Product>) s.createCriteria(Product.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            s.flush();
        } finally {
            if(s.isOpen()) {
                s.close();
            }

        }
        return listUser;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Product user) {
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
    public void delete(Product id) {

        Session ss = sessionFactory.openSession();
        try {
            ss.delete(id);


            ss.flush();
        } finally {
            if(ss.isOpen()) {
                ss.close();
            }

        }

    }

    @Override
    @Transactional
    public Product get(int id) {
        List<Product> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Product where identifier=" + id;
            Query query = s.createQuery(hql);
            listUser = (List<Product>) query.list();
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
    public Product findByTicker(String username) {
        List<Product> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Product where identifier='" + username + "'";
            Query query = s.createQuery(hql);


            listUser = (List<Product>) query.list();

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
    public Product findByEmployeeId(int id) {

        Session s = sessionFactory.openSession();
        List<Product> listUser;
        try {
            String hql = "from Product where employee_id='" + (int) id + "'";
            Query query = s.createQuery(hql);


            listUser = (List<Product>) query.list();

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
    public Product findByKellnerName(String username) {
        List<Product> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Product where title='" + username + "'";
            Query query = s.createQuery(hql);

            listUser = (List<Product>) query.list();

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
    public Product findById(String username) {
        List<Product> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Product where id='" + username + "'";
            Query query = s.createQuery(hql);


            listUser = (List<Product>) query.list();

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
    public List<Product> getAll() {
        List<Product> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Product WHERE isActive=1 ORDER BY id";
            Query query = s.createQuery(hql);

            listUser = (List<Product>) query.list();

            s.flush();

        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }
        return listUser;

    }


    @Override
    public String getCurrentItemCount() {
        String s2 = "";

        Session s = sessionFactory.openSession();
        try {
            String sql = "SELECT COUNT(*) FROM products p WHERE p.finished=0 AND isActive=1";
            Query query = s.createSQLQuery(sql);
            List<Number> counts = (List<Number>) query.list();
            long count = counts.get(0).longValue();
            s2 = "" + count;
            s.flush();

        } finally {
            s.close();

        }
        return s2;
    }

}