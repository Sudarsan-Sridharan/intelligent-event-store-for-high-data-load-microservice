package com.deviceinsight.services;


import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HazelcastConfiguration {

    public static void main(String[] args) {
        Config config = new Config();
        config.getGroupConfig().setName("name");
        config.getGroupConfig().setPassword("pwd");
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("172.17.0.3:5701");

        Hazelcast.newHazelcastInstance(config);
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        System.out.println("#######  CLIENT BEGIN #######");
        HazelcastInstance client = (HazelcastInstance) context.getBean("client");
        IMap<String, String> map = client.getMap("map");
        map.put("city", "Istanbul");
        System.out.println("City: " + map.get("city"));
        System.out.println("#######  CLIENT END #######");

        Hazelcast.shutdownAll();
        HazelcastClient.shutdownAll();
    }
}