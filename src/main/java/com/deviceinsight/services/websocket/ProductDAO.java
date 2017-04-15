package com.deviceinsight.services.websocket;

import com.deviceinsight.services.model.TopicItem;

import java.util.List;

/**
 * Created by ebners on 14.04.17.
 */
public interface ProductDAO {
    public List<TopicItem> list();

    public TopicItem get(int id);

    public void saveOrUpdate(TopicItem user);


    public TopicItem findByTicker(String string);

    public TopicItem findByEmployeeId(int string);

    public TopicItem findByKellnerName(String string);

    public List<TopicItem> getAll();

    void delete(TopicItem id);

    TopicItem findById(String username);

    public String getCurrentItemCount();
}
