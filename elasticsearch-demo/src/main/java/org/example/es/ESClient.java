package org.example.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.example.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * ES客户端
 *
 * @author Mr.Lan
 * @create: 2022-01-25 23:04
 */
public class ESClient {
    private static final String ES_SERVER_ADDRESS = "192.168.0.108";
    private static final int ES_SERVER_PORT = 9200;
    private static final String ES_CONNECT_WAY = "http";

   static Logger logger = LoggerFactory.getLogger(ESClient.class);

    public static void main(String[] args) throws IOException {
        //创建ES客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost(ES_SERVER_ADDRESS, ES_SERVER_PORT, ES_CONNECT_WAY)));
       // logger.info("sfsdfsdfsdfsdf");
       // createIndex(esClient,"user");
        //selectIndex(esClient,"user");
        //deleteIndex(esClient,"user");
        //insertDocument(esClient,"user");
        updateDocument(esClient,"user");
        //selectDocument(esClient,"user");
        //deleteDocument(esClient,"user");
        //关闭连接
        esClient.close();
    }

    /**
     * 创建索引
     * @param client
     * @param indexName
     */
    public static void createIndex(RestHighLevelClient client,String indexName) throws IOException {
        CreateIndexRequest req = new CreateIndexRequest(indexName);
        CreateIndexResponse createIndexResponse = client.indices().create(req, RequestOptions.DEFAULT);

        //响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作："+acknowledged);
    }

    /**
     * 查询索引
     * @param client
     * @param indexName
     */
    public static void selectIndex(RestHighLevelClient client,String indexName) throws IOException {
        GetIndexRequest req = new GetIndexRequest(indexName);
        GetIndexResponse getIndexResponse = client.indices().get(req, RequestOptions.DEFAULT);

        //响应状态
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
    }

    /**
     * 删除索引
     * @param client
     * @param indexName
     */
    public static void deleteIndex(RestHighLevelClient client,String indexName) throws IOException {
        DeleteIndexRequest req = new DeleteIndexRequest(indexName);
        AcknowledgedResponse response = client.indices().delete(req, RequestOptions.DEFAULT);

        //响应状态
        boolean acknowledged = response.isAcknowledged();
        System.out.println("索引操作："+acknowledged);
    }

    /**
     * 插入数据
     * @param client
     * @param indexName
     * @throws IOException
     */
    public static void insertDocument(RestHighLevelClient client,String indexName) throws IOException {
        IndexRequest req = new IndexRequest();
        req.index(indexName).id("1001");

        User user = new User();
        user.setName("测试名");
        user.setAge(18);
        user.setSex("男");

        //向ES插入数据，必须转换json格式
        String json = JSON.toJSONString(user);
        req.source(json, XContentType.JSON);

        IndexResponse response = client.index(req,RequestOptions.DEFAULT);

        System.out.println(response.getResult());

    }

    /**
     * 更新数据
     * @param client
     * @param indexName
     * @throws IOException
     */
    public static void updateDocument(RestHighLevelClient client,String indexName) throws IOException {
        UpdateRequest req = new UpdateRequest();
        req.index(indexName).id("1001");

        //修改数据
        req.doc(XContentType.JSON,"sex","女6");

        UpdateResponse response = client.update(req,RequestOptions.DEFAULT);

        System.out.println(response.getResult());

    }

    /**
     * 查询数据
     * @param client
     * @param indexName
     * @throws IOException
     */
    public static void selectDocument(RestHighLevelClient client,String indexName) throws IOException {
        GetRequest req = new GetRequest();
        req.index(indexName).id("1001");

        GetResponse response = client.get(req,RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());

    }

    /**
     * 删除数据
     * @param client
     * @param indexName
     * @throws IOException
     */
    public static void deleteDocument(RestHighLevelClient client,String indexName) throws IOException {
        DeleteRequest req = new DeleteRequest();
        req.index(indexName).id("1001");

        DeleteResponse response = client.delete(req,RequestOptions.DEFAULT);

        System.out.println(response.toString());

    }
}
