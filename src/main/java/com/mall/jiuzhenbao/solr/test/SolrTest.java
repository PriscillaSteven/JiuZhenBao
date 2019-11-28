package com.mall.jiuzhenbao.solr.test;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {
    String baseURL = "http://solr.com:8080/solr";
    SolrServer solrServer = new HttpSolrServer(baseURL);

    @Test
    public void testSolr() throws Exception{
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id","001");
        doc.setField("name","Priscilla");
        solrServer.add(doc);
        solrServer.commit();
    }
}
