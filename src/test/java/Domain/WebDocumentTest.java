package Domain;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tutorial.springboot.Domain.WebdocumentInfomation;
import com.tutorial.springboot.api.WebDocumentSearchApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WebDocumentTest {
    WebdocumentInfomation[] webdocumentInfomation = new WebdocumentInfomation[100];
    WebDocumentSearchApi webDocumentSearchApi = new WebDocumentSearchApi();

    @Before
    public void 웹문서설정(){
        String[] WebdocumentResult = new String[100];
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        WebdocumentResult = webDocumentSearchApi.Search("콩고에서도 부정선거");
        for (int i =0; i<4; i++) {
            webdocumentInfomation[i] = gson.fromJson(WebdocumentResult[i], WebdocumentInfomation.class);
        }
    }
    @Test
    public void 중복삭제확인(){
        Assert.assertSame(webdocumentInfomation[0].ItemDeduplication(),4);
    }
}
