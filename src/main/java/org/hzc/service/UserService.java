package org.hzc.service;


import org.hzc.entity.Role;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;

import java.util.List;

public interface UserService {

    /*
创建一个用户对象
*/
    User create(User user)throws HRSException;
    /*
  批量创建用户对象
   */
    List<User> createAll(List<User> users)throws HRSException ;
    /*
  通过id修改一个用户对象
   */
    User updateById(long id, User contract)throws HRSException ;
//    /*
//通过id批量修改用户对象
// */
//    List<User> updateAllById(List<Long> Ids, List<User> users)throws HRSException ;


    User getById(long id)throws HRSException ;
    List<User> getAllByIdIn(List<Long> Ids)throws HRSException ;
    List<User> getAllById(long id)throws HRSException ;
    List<User> getAll()throws HRSException ;
    User getByName(String name)throws HRSException ;
    List<User> getAllByNameIn(List<String> names)throws HRSException ;
    List<User> getAllByName(String name)throws HRSException ;

    /*
根据名字进行模糊查询并且忽略大小写
      */
    List<User> getAllByNameLikeIgnoreCase(String name)throws HRSException ;

    User getByPhone(String phone)throws HRSException ;
    List<User> getAllByPhoneIn(List<String> phones)throws HRSException ;
    List<User> getAllByPhone(String phone)throws HRSException ;
    /*
根据电话进行模糊查询并且忽略大小写
      */
    List<User> getAllByPhoneLikeIgnoreCase(String phone)throws HRSException ;
    User getByRole(Role role)throws HRSException ;
    List<User> getAllByRoleIn(List<Role> roles)throws HRSException ;
    List<User> getAllByRole(Role role)throws HRSException ;

    int deleteAllById(long Id)throws HRSException ;
    int deleteAllByIdIn(List<Long> Ids)throws HRSException ;

    int deleteAllByName(String name)throws HRSException ;
    int deleteAllByNameIn(List<String> names)throws HRSException ;

    int deleteAllByPhone(String phone)throws HRSException ;
    int deleteAllByPhoneIn(List<String> phones)throws HRSException ;




    int deleteAllByRole(Role role)throws HRSException ;
    int deleteAllByRoleIn(List<Role> role)throws HRSException ;



}
