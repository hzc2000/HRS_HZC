package org.hzc.service;

import org.hzc.entity.Role;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;

public interface SecurityService {
    User login(String phone, String password, Role role) throws HRSException;
    public boolean isAuthorized(String action, Role role)throws HRSException ;

}
