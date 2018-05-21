package com.ppdai.framework.raptor.demo.client;

import com.ppdai.framework.raptor.proto.Cat;
import com.ppdai.framework.raptor.proto.HelloRequest;
import com.ppdai.framework.raptor.proto.MoreService;
import com.ppdai.framework.raptor.proto.Simple;
import com.ppdai.framework.raptor.spring.annotation.RaptorClient;
import okio.ByteString;
import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;

@RestController
public class SayHelloController {

    @RaptorClient
    private Simple simple;

    @RaptorClient
    private MoreService moreService;

    private HelloRequest getTestRequest() {
        HelloRequest helloRequest = new HelloRequest();
        helloRequest.setCorpus(HelloRequest.Corpus.UNIVERSAL);
        helloRequest.setSnippets(Arrays.asList("snippets1", "snippets2"));
        helloRequest.setCats(Arrays.asList(new Cat("black"), new Cat("white")));
        helloRequest.setResult(new HelloRequest.Result("http://ppdai.com", HelloRequest.Result.Corpus.NEWS));
        helloRequest.setTbytes(ByteString.encodeUtf8("拍拍贷"));
        helloRequest.setTDouble(1.01);
        helloRequest.setTFloat(1.02f);
        helloRequest.setTfixed32(23);
        helloRequest.setTfixed64(23232222332332323L);
        helloRequest.setTint32(123);
        helloRequest.setTint64(3232132555244324324L);
        helloRequest.setTsfixed32(213);
        helloRequest.setTsfixed64(323232323223232L);
        helloRequest.setTunit32(123);
        helloRequest.setTunit64(323213232133223L);
        helloRequest.setTbool(true);

        helloRequest.setRepFixed32(Arrays.asList(1, 2, 3));
        helloRequest.setRepString(Arrays.asList("str1", "str2"));

        HelloRequest.Result result = new HelloRequest.Result("url1", HelloRequest.Result.Corpus.NEWS);
        helloRequest.setRepResult(Arrays.asList(result, result, result));

        helloRequest.setMapInt32Int32(MapUtils.putAll(new HashMap<>(), new Object[]{1, 2, 3, 4}));
        helloRequest.setMapStringMessage(MapUtils.putAll(new HashMap<>(), new Object[]{"c1", result, "c2", result}));
        helloRequest.setMapStringEnum(MapUtils.putAll(new HashMap<>(), new Object[]{"c1", HelloRequest.Corpus.IMAGES, "c2", HelloRequest.Corpus.PRODUCTS}));
        return helloRequest;
    }

    @RequestMapping("/sayhello")
    public Object sayHello(@RequestParam("name") String name) {
        HelloRequest helloRequest = getTestRequest();
        helloRequest.setName(name);

        return simple.sayHello(helloRequest);
    }


    @RequestMapping("get")
    public Object testGet1() {
        HelloRequest helloRequest = getTestRequest();
        return moreService.testGet1(helloRequest);
    }

    @RequestMapping("get2")
    public Object testGet2() {
        HelloRequest helloRequest = getTestRequest();
        return moreService.testGet2(helloRequest);
    }

    @RequestMapping("post1")
    public Object testPost1() {
        HelloRequest helloRequest = getTestRequest();
        return moreService.testPost1(helloRequest);
    }

    @RequestMapping("put1")
    public Object testPut1() {
        HelloRequest helloRequest = getTestRequest();
        return moreService.testPut1(helloRequest);
    }

    @RequestMapping("delete")
    public Object testDelete1() {
        HelloRequest helloRequest = getTestRequest();
        return moreService.testDelete1(helloRequest);
    }
}
