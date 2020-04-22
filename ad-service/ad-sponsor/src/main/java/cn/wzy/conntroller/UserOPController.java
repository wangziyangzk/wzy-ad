package cn.wzy.conntroller;

import cn.wzy.exception.AdException;
import cn.wzy.service.impl.UserServiceImpl;
import cn.wzy.vo.CreateUserRequest;
import cn.wzy.vo.CreateUserResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserOPController {
    @Autowired
    private final UserServiceImpl userService;

    public UserOPController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor:createUser -> {}", JSON.toJSONString(request));
        System.err.println(JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
