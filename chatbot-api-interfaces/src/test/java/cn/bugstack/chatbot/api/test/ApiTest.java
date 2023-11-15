package cn.bugstack.chatbot.api.test;



import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.http.HttpClient;

public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        get.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218aabc93d118d0-0b3f8fa07b9c45-26031f51-1638720-18aabc93d12d32%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fwww.bing.com%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThhYWJjOTNkMTE4ZDAtMGIzZjhmYTA3YjljNDUtMjYwMzFmNTEtMTYzODcyMC0xOGFhYmM5M2QxMmQzMiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218aabc93d118d0-0b3f8fa07b9c45-26031f51-1638720-18aabc93d12d32%22%7D; abtest_env=product; zsxq_access_token=AB645C45-B460-9437-715B-819BB10C56FF_CB085BCEBDD8FCFA; zsxqsessionid=bf960d5621acc7018d61fabf6ea95597");
        get.addHeader("Content-Type","application/json, text/plain, */*");

        CloseableHttpResponse response = build.execute(get);

        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            System.out.println(EntityUtils.toString(response.getEntity()));
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/588544482148514/comments");
        post.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218aabc93d118d0-0b3f8fa07b9c45-26031f51-1638720-18aabc93d12d32%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fwww.bing.com%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThhYWJjOTNkMTE4ZDAtMGIzZjhmYTA3YjljNDUtMjYwMzFmNTEtMTYzODcyMC0xOGFhYmM5M2QxMmQzMiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218aabc93d118d0-0b3f8fa07b9c45-26031f51-1638720-18aabc93d12d32%22%7D; abtest_env=product; zsxq_access_token=AB645C45-B460-9437-715B-819BB10C56FF_CB085BCEBDD8FCFA; zsxqsessionid=bf960d5621acc7018d61fabf6ea95597");
        post.addHeader("Content-Type","application/json, text/plain, */*");


        String paraJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"你好helloword\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paraJson, ContentType.create("test/json", "UTF-8"));
        post.setEntity( stringEntity);


        CloseableHttpResponse response = build.execute(post);

        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            System.out.println(EntityUtils.toString(response.getEntity()));
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }


    @Test
    public void test_chatGPT() throws IOException {



    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

    String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"1+1等于几\", \"temperature\": 0, \"max_tokens\": 1024}";

    StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

    CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String res = EntityUtils.toString(response.getEntity());
        System.out.println(res);
    } else {
        System.out.println(response.getStatusLine().getStatusCode());
    }

}
}
