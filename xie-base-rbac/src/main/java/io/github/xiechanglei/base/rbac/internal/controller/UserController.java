package io.github.xiechanglei.base.rbac.internal.controller;

import io.github.xiechanglei.base.common.bean.message.MessageException;
import io.github.xiechanglei.base.common.digest.md5.Md5Helper;
import io.github.xiechanglei.base.common.reflect.FieldHelper;
import io.github.xiechanglei.base.rbac.repo.UserBaseRepository;
import io.github.xiechanglei.base.rbac.token.TokenHandler;
import io.github.xiechanglei.base.rbac.token.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static io.github.xiechanglei.base.rbac.internal.constans.ErrorConstant.USER_LOGIN_FAILED;

@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "io.github.xiechanglei.base.rbac", name = "use-controller", havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(UserBaseRepository.class)
public class UserController {
    private final UserBaseRepository<?> userBaseRepository;

    /**
     * 用户登陆
     */
    @RequestMapping("/rbac/user/login")
    public String login(String username, String password) {
        Object byUsernameAndPassword = userBaseRepository.findByUsernameAndPassword(username, Md5Helper.encode(password));
        if (byUsernameAndPassword == null) {
            throw MessageException.of(USER_LOGIN_FAILED);
        }
        String id = (String) FieldHelper.getValue(byUsernameAndPassword, "id");
        Integer serialNumber = (Integer) FieldHelper.getValue(byUsernameAndPassword, "serialNumber");
        return TokenHandler.encode(new TokenInfo(id, serialNumber,new Date()));
    }

}
