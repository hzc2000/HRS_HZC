package org.hzc.service;

import lombok.Data;
import org.hzc.entity.Role;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Service
@Transactional
@Data
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private Map<String, Set<String>> permissions;

    @Override
    public User login(String phone, String password, Role roleFormBrower) throws HRSException {

        User user = userService.getByPhone(phone);


        if(roleFormBrower.name() != user.getRole().name()) {
            throw new HRSException("用户角色验证失败");
        }

        if(null == user) {
            throw new HRSException(String.format("没有找到电话为%s的用户", phone));
        }
        if(!bcryptPasswordEncoder.matches(password, user.getPwdHash())){
            throw new HRSException("密码不匹配");
        }
        return user;
    }
    @Override
    public boolean isAuthorized(String action, Role role)throws HRSException {
        // Check permission
        Set<String> permittedRoles = permissions.get(action);

        return permittedRoles != null && permittedRoles.contains(role.name());
    }
}
