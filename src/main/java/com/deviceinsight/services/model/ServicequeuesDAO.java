package com.deviceinsight.services.model;


import java.util.List;

public interface ServicequeuesDAO {
///	public List<Sender> list();

    public List<Servicequeue> list();

    public List<Servicequeue> findByServicesession(int string);

    public void saveOrUpdate(Servicequeue sender);

    public String renderByServicesession(int id);

    public void delete(Servicequeue obj);

    String findTickerByAcceptTicket(String g);

    public Servicequeue findByAcceptTicket(String g);

    void deleteByAcceptTicket(String g);

    Servicequeue findByTable(int i);
}