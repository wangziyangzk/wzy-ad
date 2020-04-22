package cn.wzy.service;

import cn.wzy.exception.AdException;
import cn.wzy.entity.AdPlan;
import cn.wzy.vo.AdPlanGetRequest;
import cn.wzy.vo.AdPlanRequest;
import cn.wzy.vo.AdPlanResponse;

import java.util.List;

public interface IAdplanService {

    /**
     * 创建推广计划
     * */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 获取推广单元
     * */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * 更新推广计划
     * */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * 删除推广计划
     * */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
