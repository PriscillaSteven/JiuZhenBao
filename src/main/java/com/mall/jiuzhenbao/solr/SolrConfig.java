package com.mall.jiuzhenbao.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {
    @Value("${solr.url}")
    private String baseURL;

    @Bean
    public SolrServer solrServer(){
        return new HttpSolrServer(baseURL);
    }
}
