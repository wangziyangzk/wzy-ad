package cn.wzy.client;

import cn.wzy.client.vo.AdPlan;
import cn.wzy.client.vo.AdPlanGetRequest;
import cn.wzy.exception.AdException;
import cn.wzy.vo.CodeMsg;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SponsorClientHystrix implements SponsorClient {
    @Override
    public List<AdPlan> getAdPlans(AdPlanGetRequest request) throws AdException {
        throw new AdException(CodeMsg.EUREKA_CLIENT_AD_SPONSOR_ERROR);
//        return new ArrayList<>().add(CommonResponse.error(CodeMsg.EUREKA_CLIENT_AD_SPONSOR_ERROR));
//        return CommonResponse.error(CodeMsg.EUREKA_CLIENT_AD_SPONSOR_ERROR);
//        return CommonResponse.error(CodeMsg.EUREKA_CLIENT_AD_SPONSOR_ERROR);
    }
}
