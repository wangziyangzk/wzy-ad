package cn.wzy.service.impl;

import cn.wzy.exception.AdException;
import cn.wzy.vo.CodeMsg;
import cn.wzy.dao.CreativeRepository;
import cn.wzy.entity.Creative;
import cn.wzy.service.ICreativeService;
import cn.wzy.vo.CreativeRequest;
import cn.wzy.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements ICreativeService {
    private final CreativeRepository creativeRepository;
@Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }


    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(CodeMsg.REQUEST_PARAM_ERROR);
        }
        Creative newcreative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(newcreative.getName(), newcreative.getId());
    }
}
