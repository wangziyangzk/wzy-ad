package cn.wzy.client;

import cn.wzy.client.vo.AdPlan;
import cn.wzy.client.vo.AdPlanGetRequest;
import cn.wzy.exception.AdException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "eureka-client-ad-sponsor",fallback = SponsorClientHystrix.class)
public interface SponsorClient {
    @RequestMapping(value = "/ad-sponsor/get/adPlan",method = RequestMethod.POST)
    List<AdPlan> getAdPlans(@RequestBody AdPlanGetRequest request) throws AdException;
}
