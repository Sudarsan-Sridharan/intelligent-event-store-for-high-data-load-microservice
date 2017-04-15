package com.deviceinsight.services.model;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/////// USE THIS import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ServicequeuesDAOImpl implements ServicequeuesDAO {
    @Autowired
    private SessionFactory sessionFactory;


    public ServicequeuesDAOImpl() {

    }


    public ServicequeuesDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Servicequeue servicequeue) {
        Servicequeue o;
// here - important flush after save otherwise it won't work
        Session s = sessionFactory.openSession();
        try {
            o = renderByServicesession(servicequeue.getServicesession().getId(), servicequeue.getTable().getId());


            if (o != null) {
                o.setAction(servicequeue.getAction());

                s.saveOrUpdate(o);
            } else {

                s.saveOrUpdate(servicequeue);

            }
            s.flush();
        } finally {

            if(s.isOpen()) {
                s.close();
            }
        }


    }

    @Override
    @Transactional
    public void delete(Servicequeue id) {

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
    public String findTickerByAcceptTicket(String acceptTicket) {
        Session s = sessionFactory.openSession();
        List<String> h;
        try {
            String sql = "select p.identifier from servicequeues sq, products p where sq.accept_ticket='" + acceptTicket + "' AND sq.servicesession_id=p.id";
            Query query = s.createSQLQuery(sql);


            h = query.list();
            s.flush();

        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }
        return h.get(0);

     /*   @SuppressWarnings("unchecked")
        List<Servicequeue> listSender = (List<Servicequeue>) query.list();





        s.flush();
        s.close();


        if (listSender != null && !listSender.isEmpty()) {
            return listSender.get(0);
        }*/


    }

    @Override
    @Transactional
    public Servicequeue findByAcceptTicket(String acceptTicket) {
        Session s = sessionFactory.openSession();
        List<Servicequeue> listSender;
        try {
            String hql = "from Servicequeue where accept_ticket='" + acceptTicket + "'";
            Query query = s.createQuery(hql);


            listSender = (List<Servicequeue>) query.list();

            s.flush();


        } finally {
            if(s.isOpen()) {
                s.close();
            }

        }
        if (listSender != null && !listSender.isEmpty()) {
            return listSender.get(0);
        }

        return null;



    }

    @Override
    @Transactional
    public void deleteByAcceptTicket(String at) {


        Session s = sessionFactory.openSession();
        try {
            String hql = "delete from Servicequeue where accept_ticket='" + at + "'";
            Query query = s.createQuery(hql);


            query.executeUpdate();
 /*       @SuppressWarnings("unchecked")
        List<Servicequeue> listSender = (List<Servicequeue>) query.list();
*/
            s.flush();
        } finally {


            if(s.isOpen()) {
                s.close();
            }
        }


    }

    @Override
    @Transactional
    public Servicequeue findByTable(int table) {
        Session s = sessionFactory.openSession();
        List<Servicequeue> listSender;
        try {
            String hql = "from Servicequeue where table_id='" + table + "'";
            Query query = s.createQuery(hql);


            listSender = (List<Servicequeue>) query.list();

            s.flush();


        } finally {
            if(s.isOpen()) {
                s.close();
            }

        }
        if (listSender != null && !listSender.isEmpty()) {
            return listSender.get(0);
        }

        return null;


    }

    @Override
    @Transactional
    public List<Servicequeue> list() {
        List<Servicequeue> listUser;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Servicequeue";
            Query query = s.createQuery(hql);


            listUser = (List<Servicequeue>) query.list();
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
    public List<Servicequeue> findByServicesession(int workingarea) {
        List<Servicequeue> listSender;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Servicequeue where servicesession_id='" + workingarea + "'";
            Query query = s.createQuery(hql);

            listSender = (List<Servicequeue>) query.list();

            s.flush();
        } finally {

            if(s.isOpen()) {
                s.close();
            }
        }


        return listSender;


    }


    @Override
    @Transactional
    public String renderByServicesession(int workingarea) {
        List<Servicequeue> listSender = new LinkedList<>();
        Session s = sessionFactory.openSession();
        String h = "";


        try {
            String hql = "from Servicequeue where servicesession_id='" + workingarea + "'";
            Query query = s.createQuery(hql);

            try {
                listSender = (List<Servicequeue>) query.list();
            } catch (HibernateException e1) {
                System.out.println("!!!!!!!!!!!!!!! 1");
            } catch (Exception e2) {
                System.out.println("!!!!!!!!!!!!!!! 2");
            }


            s.flush();


            for (Servicequeue a : listSender) {


                if (((String) "" + a.getAction()).equals("0")) {
                    h += "<div onClick='confirm(" + a.getAccept_ticket() + ")' style='float:left; background-color:#8bc832; color:#fff; padding:10px; margin-right:5px; width:133px; height:100px; text-align:center'><font style='font-size:40px'>" + a.getTable().getTitle() + "</font><br /><font style='font-size:14px'>Service</font></div>";
                } else if (((String) "" + a.getAction()).equals("1")) {
                    h += "<div onClick='confirm(" + a.getAccept_ticket() + ")' style='float:left; background-color:#b72222; color:#fff; padding:10px; margin-right:5px; width:133px; height:100px; text-align:center'><font style='font-size:40px'>" + a.getTable().getTitle() + "</font><br /><font style='font-size:14px'>Pay</font></div>";
                }


            }

        } finally {
            if(s.isOpen()) {
                s.close();
            }
        }

        return h;

    }


    @Transactional
    private Servicequeue renderByServicesession(int servicesession, int table) {
        List<Servicequeue> listSender;
        Session s = sessionFactory.openSession();
        try {
            String hql = "from Servicequeue where servicesession_id='" + servicesession + "' AND table_id='" + table + "'";
            Query query = s.createQuery(hql);


            listSender = (List<Servicequeue>) query.list();


            s.flush();


        } finally {
            if(s.isOpen()) {
                s.close();
            }

        }

        if (listSender != null && !listSender.isEmpty()) {
            return listSender.get(0);
        }


        return null;

    }


}