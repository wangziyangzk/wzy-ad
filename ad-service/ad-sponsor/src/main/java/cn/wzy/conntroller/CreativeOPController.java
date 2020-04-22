package cn.wzy.conntroller;

import cn.wzy.exception.AdException;
import cn.wzy.service.impl.CreativeServiceImpl;
import cn.wzy.vo.CreativeRequest;
import cn.wzy.vo.CreativeResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Qinyi.
 */
@Slf4j
@RestController
public class CreativeOPController {

    private final CreativeServiceImpl creativeService;

    @Autowired
    public CreativeOPController(CreativeServiceImpl creativeService) {
        this.creativeService = creativeService;
    }

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException {
        log.info("ad-sponsor: createCreative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
