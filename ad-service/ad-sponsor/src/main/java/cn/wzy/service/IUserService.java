package cn.wzy.service;

import cn.wzy.exception.AdException;
import cn.wzy.vo.CreateUserRequest;
import cn.wzy.vo.CreateUserResponse;

public interface IUserService {
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}

