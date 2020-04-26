package cn.wzy.controller;

import cn.wzy.annotation.IgnoreResponseDataAdvice;
import cn.wzy.client.SponsorClient;
import cn.wzy.client.vo.AdPlan;
import cn.wzy.client.vo.AdPlanGetRequest;
import cn.wzy.exception.AdException;
import cn.wzy.search.ISearch;
import cn.wzy.search.vo.SearchRequest;
import cn.wzy.search.vo.SearchResponse;
import cn.wzy.vo.CommonResponse;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class SearchController {

    //实现检索服务
    @Resource(name = "searchImpl")
    private ISearch search;
    @PostMapping("/fetchAds")
    public SearchResponse fetchAds(@RequestBody SearchRequest request) {
        log.info("fetchAds requset Object-> {}", JSON.toJSONString(request));
        return search.fetchAds(request);
    }

    //基于feign实现微服务调用
    private final SponsorClient sponsorClient;

    @PostMapping("/get/adPlans")
    public List<AdPlan> getAdPlans(@RequestBody AdPlanGetRequest request) throws AdException {
        log.info("ad-search: getAdPlans: -> {}", JSON.toJSON(request));
        return sponsorClient.getAdPlans(request);
    }

    //基于Ribbon实现微服务调用
    private final RestTemplate restTemplate;

    @Autowired
    public SearchController(ISearch search, SponsorClient sponsorClient, RestTemplate restTemplate) {
        this.search = search;
        this.sponsorClient = sponsorClient;
        this.restTemplate = restTemplate;
    }

    @IgnoreResponseDataAdvice
    @HystrixCommand(fallbackMethod = "这里有断路器-例子")
    @PostMapping("/getAdPlansByRibbon")
    public CommonResponse<List<AdPlan>> getAdPlansByRibbon(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlanByRibbon: -> {}", JSON.toJSON(request));
        return restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan",
                request, CommonResponse.class).getBody();
    }
}
