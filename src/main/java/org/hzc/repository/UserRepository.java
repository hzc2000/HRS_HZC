package org.hzc.repository;

import org.hzc.entity.Role;
import org.hzc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {






    User getById(long id);
    List<User> getAllByIdIn(List<Long> Ids);
    List<User> getAllById(long id);
    User getByName(String name);
    List<User> getAllByNameIn(List<String> names);
    List<User> getAllByName(String name);

    /*
根据名字进行模糊查询并且忽略大小写
      */
    List<User> getAllByNameLikeIgnoreCase(String name);

    User getByPhone(String phone);
    List<User> getAllByPhoneIn(List<String> phones);
    List<User> getAllByPhone(String phone);
    /*
//根据电话进行模糊查询并且忽略大小写
      */
    List<User> getAllByPhoneLikeIgnoreCase(String phone);
    User getByRole(Role role);
    List<User> getAllByRoleIn(List<Role> roles);
    List<User> getAllByRole(Role role);

    int deleteAllById(long Id);
    int deleteAllByIdIn(List<Long> Ids);

    int deleteAllByName(String name);
    int deleteAllByNameIn(List<String> name);

    int deleteAllByPhone(String name);
    int deleteAllByPhoneIn(List<String> phone);

    int deleteAllByRole(Role role);
    int deleteAllByRoleIn(List<Role> role);



}
