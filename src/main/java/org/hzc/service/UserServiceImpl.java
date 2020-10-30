package org.hzc.service;


import lombok.Data;
import org.hzc.entity.Role;
import org.hzc.entity.User;
import org.hzc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Data
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /** * 排序和分页查询 * @param currentPage * @param pageSize * @param sortColumn * @return */


    
    @Override
    public User create(User user) {
        String pwdHash = passwordEncoder.encode(user.getPassword());
        user.setPwdHash(pwdHash);
        return userRepository.save(user);
    }
    public List<User> createAll(List<User> users) {
        List<String> pwdHashes = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        users.forEach(user -> passwords.add(user.getPassword()));
        passwords.forEach(password-> pwdHashes.add(passwordEncoder.encode(password)));
for(int i=0;i<users.size();i++)
{
    User user = users.get(i);
    user.setPwdHash(pwdHashes.get(i));
    users.set(i,user);
}
        return userRepository.saveAll(users);
    }


    @Override
    public User updateById(long id, User user) {
        user.setId(id);
        String pwdHash = passwordEncoder.encode(user.getPassword());
        user.setPwdHash(pwdHash);
        return userRepository.save(user);
    }
//
//    @Override
//    public List<User> updateAllById(List<Long> Ids, List<User> users) {
//        Ids.forEach(id-> users.forEach(user->user.setId(id)));
//        List<String> pwdHashes = new ArrayList<>();
//        List<String> passwords = new ArrayList<>();
//        users.forEach(user -> passwords.add(user.getPassword()));
//        passwords.forEach(password-> pwdHashes.add(passwordEncoder.encode(password)));
//        for(int i=0;i<users.size();i++)
//        {
//            User user = users.get(i);
//            user.setPwdHash(pwdHashes.get(i));
//            users.set(i,user);
//        }
//      return userRepository.saveAll(users);
//    }





    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAllByIdIn(List<Long> Ids) {
        return userRepository.getAllByIdIn(Ids);
    }

    @Override
    public List<User> getAllById(long id) {
        return userRepository.getAllById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByName(String name) {
        return userRepository.getByName(name);
    }

    @Override
    public List<User> getAllByNameIn(List<String> names) {
        return userRepository.getAllByNameIn(names);
    }

    @Override
    public List<User> getAllByName(String name) {
        return userRepository.getAllByName(name);
    }
    @Override
    public  List<User> getAllByNameLikeIgnoreCase(String name){
        return userRepository.getAllByNameLikeIgnoreCase(name);
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.getByPhone(phone);
    }

    @Override
    public List<User> getAllByPhoneIn(List<String> phones) {
        return userRepository.getAllByPhoneIn(phones);
    }

    @Override
    public List<User> getAllByPhone(String phone) {
        return userRepository.getAllByPhone(phone);
    }
    @Override
    public  List<User> getAllByPhoneLikeIgnoreCase(String phone){
        return userRepository.getAllByPhoneLikeIgnoreCase(phone);
    }
    @Override
    public User getByRole(Role role) {
        return userRepository.getByRole(role);
    }

    @Override
    public List<User> getAllByRoleIn(List<Role> roles) {
        return userRepository.getAllByRoleIn(roles);
    }

    @Override
    public List<User> getAllByRole(Role role) {
        return userRepository.getAllByRole(role);
    }


    @Override
    public int deleteAllById(long Id) {
        return userRepository.deleteAllById(Id) ;
    }

    @Override
    public int deleteAllByIdIn(List<Long> Ids) {
        return userRepository.deleteAllByIdIn(Ids);
    }

    @Override
    public int deleteAllByName(String name) {
        return userRepository.deleteAllByName(name);
    }

    @Override
    public int deleteAllByNameIn(List<String> names) {
        return userRepository.deleteAllByNameIn(names);
    }

    @Override
    public int deleteAllByPhone(String phone) {
        return userRepository.deleteAllByPhone(phone);
    }

    @Override
    public int deleteAllByPhoneIn(List<String> phones) {
        return userRepository.deleteAllByPhoneIn(phones);
    }

    @Override
    public int deleteAllByRole(Role role) {
        return userRepository.deleteAllByRole(role);
    }

    @Override
    public int deleteAllByRoleIn(List<Role> roles) {
        return userRepository.deleteAllByRoleIn(roles);
    }



}
