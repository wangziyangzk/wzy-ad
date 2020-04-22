package cn.wzy.service.impl;

import cn.wzy.exception.AdException;
import cn.wzy.vo.CodeMsg;
import cn.wzy.constant.CommonStatus;
import cn.wzy.dao.AdPlanRepository;
import cn.wzy.dao.AdUserRepository;
import cn.wzy.entity.AdPlan;
import cn.wzy.entity.AdUser;
import cn.wzy.service.IAdplanService;
import cn.wzy.utils.CommonUtils;
import cn.wzy.vo.AdPlanGetRequest;
import cn.wzy.vo.AdPlanRequest;
import cn.wzy.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements IAdplanService {

    private final AdPlanRepository adPlanRepository;
    private final AdUserRepository adUserRepository;
    @Autowired
    public AdPlanServiceImpl(AdPlanRepository adPlanRepository, AdUserRepository adUserRepository) {
        this.adPlanRepository = adPlanRepository;
        this.adUserRepository = adUserRepository;
    }

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(CodeMsg.REQUEST_PARAM_ERROR);
        }
        //确保广联的User存在的
        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(CodeMsg.CAN_NOT_FIND_RECORD);
        }

        //同名推广计划
        AdPlan oldPlan = adPlanRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldPlan != null) {
            throw new AdException(CodeMsg.SAME_NAME_UNIT_ERROR);
        }

        AdPlan newPlan = adPlanRepository.save(new AdPlan(
                request.getUserId(), request.getPlanName(),
                CommonUtils.parseStringDate(request.getStartDate()),
                CommonUtils.parseStringDate(request.getEndDate())
        ));

        return new AdPlanResponse(newPlan.getId(),newPlan.getPlanName());

    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(CodeMsg.REQUEST_PARAM_ERROR);
        }

        return adPlanRepository.findAllByIdInAndUserId(
                request.getIds(), request.getUserId()
        );
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(CodeMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(CodeMsg.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate())
            );
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate())
            );
        }

        plan.setUpdateTime(new Date());
        plan = adPlanRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(CodeMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(CodeMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);
    }
//测试方法
    public List<AdPlan> findAll() {
        return adPlanRepository.findAll();
    }
    public void adExceptionTest() throws AdException {
        throw new AdException(CodeMsg.REQUEST_PARAM_ERROR);
    }
}
