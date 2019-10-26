package com.qf.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qf.service.FindShopService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class FindShopServiceImpl implements FindShopService {

    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Override
    public JSONArray find(String name) {
        JSONArray jsonArray=new JSONArray();
        //搜索的请求对象
        SearchRequest searchRequest=new SearchRequest("qf_shop");
        //设置搜索的类型
        searchRequest.types("doc");
        //搜索元构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //matchQuery
        searchSourceBuilder.query(QueryBuilders.matchQuery("name",name).operator(Operator.OR));

        searchRequest.source(searchSourceBuilder);
        //执行搜索

            try {
                SearchResponse searchResponse=restHighLevelClient.search(searchRequest);
                //搜索匹配结果
                SearchHits hits=searchResponse.getHits();
                SearchHit[] searchHits = hits.getHits();
                for (SearchHit hit:searchHits){
                    JSONObject obj = new JSONObject();
                    String id = hit.getId();
                    Map<String, Object> map = hit.getSourceAsMap();
                    String name1 = (String)map.get("name");
                    String description = (String) map.get("description");
                    String studymodel =(String) map.get("studymodel");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
      
        return null;
    }
}
