package cn.wzy.service;

import cn.wzy.exception.AdException;
import cn.wzy.vo.CreativeRequest;
import cn.wzy.vo.CreativeResponse;

public interface ICreativeService {
    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
