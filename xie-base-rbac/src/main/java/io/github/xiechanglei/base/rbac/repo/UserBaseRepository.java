package io.github.xiechanglei.base.rbac.repo;

import java.util.List;

/**
 * 实现此接口，可以自动维护相关接口
 *
 * @param <T> 用户实体
 */
public interface UserBaseRepository<T> {
    /**
     * 根据用户名和密码获取用户信息
     */
    T findByUsernameAndPassword(String username, String password);

    /**
     * 根据用户id 和 serialNumber 获取用户信息
     */
    T findById(String id);

    /**
     * 获取所有用户
     */
    List<T> findAll();


}
