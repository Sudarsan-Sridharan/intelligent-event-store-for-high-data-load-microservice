package com.deviceinsight.services.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.searchbox.client.AbstractJestClient;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;

@Service
public class JestClientService implements Serializable {
    private static final long serialVersionUID = 1L;
    JestClient client=null;

    @Value("${jest.elasticsearch.host}")
    String host;

    @Value("${jest.elasticsearch.port}")
    String port;

    @Value("${jest.elasticsearch.index}")
    String indexName;
    /**
     *
     */
    public JestClient getClient() {
        if (this.client==null){
            //GsonFireBuilder fireBuilder = new Jsonbuilder();
            //fireBuilder.enableExposeMethodResult();

            GsonBuilder builder = new GsonBuilder();// fireBuilder.createGsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();

            final Gson gson = builder.setDateFormat(AbstractJestClient.ELASTIC_SEARCH_DATE_FORMAT).create();
            JestClientFactory factory = new JestClientFactory();
            factory.setHttpClientConfig(new HttpClientConfig
                    .Builder("http://"+this.host+":"+this.port)
.multiThreaded(true)
                    .readTimeout(20000)
                    .gson(gson)
                    .build());
            this.client = factory.getObject();

        }

        return this.client;
    }


}