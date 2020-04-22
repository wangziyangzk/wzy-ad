package cn.wzy.service;

import cn.wzy.exception.AdException;
import cn.wzy.vo.AdUnitRequest;
import cn.wzy.vo.AdUnitResponse;
import cn.wzy.vo.unit_condition.*;

public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    //限制维度
    AdUnitKeywordResponse createUntiKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    //推广单元与创意
    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}
