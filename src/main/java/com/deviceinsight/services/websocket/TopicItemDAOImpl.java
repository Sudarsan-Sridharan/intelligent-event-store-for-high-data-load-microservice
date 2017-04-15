package com.deviceinsight.services.websocket;

import com.deviceinsight.services.model.TopicItem;
import com.deviceinsight.services.websocket.TopicItemDAO;
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
public class TopicItemDAOImpl implements TopicItemDAO {
    @Autowired
    private SessionFactory sessionFactory;


    public TopicItemDAOImpl() {

    }

    public TopicItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<TopicItem> list() {
        Session s = sessionFactory.openSession();
        List<TopicItem> listUser;
        try {
            listUser = (List<TopicItem>) s.createCriteria(TopicItem.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
    public void saveOrUpdate(TopicItem user) {
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
    public void delete(TopicItem id) {

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
    public TopicItem get(int id) {
        List<TopicItem> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from TopicItem where identifier=" + id;
            Query query = s.createQuery(hql);
            listUser = (List<TopicItem>) query.list();
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
    public TopicItem findByTicker(String username) {
        List<TopicItem> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from TopicItem where identifier='" + username + "'";
            Query query = s.createQuery(hql);


            listUser = (List<TopicItem>) query.list();

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
    public TopicItem findByEmployeeId(int id) {

        Session s = sessionFactory.openSession();
        List<TopicItem> listUser;
        try {
            String hql = "from TopicItem where employee_id='" + (int) id + "'";
            Query query = s.createQuery(hql);


            listUser = (List<TopicItem>) query.list();

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
    public TopicItem findByKellnerName(String username) {
        List<TopicItem> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from TopicItem where title='" + username + "'";
            Query query = s.createQuery(hql);

            listUser = (List<TopicItem>) query.list();

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
    public TopicItem findById(String username) {
        List<TopicItem> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from TopicItem where id='" + username + "'";
            Query query = s.createQuery(hql);


            listUser = (List<TopicItem>) query.list();

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
    public List<TopicItem> getAll() {
        List<TopicItem> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from TopicItem WHERE isActive=1 ORDER BY id";
            Query query = s.createQuery(hql);

            listUser = (List<TopicItem>) query.list();

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
            String sql = "SELECT COUNT(*) FROM topic_items p WHERE p.finished=0 AND isActive=1";
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