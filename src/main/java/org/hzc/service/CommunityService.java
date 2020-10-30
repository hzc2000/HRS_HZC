package org.hzc.service;


import org.hzc.entity.Community;
import org.hzc.exception.HRSException;

import java.util.List;

public interface CommunityService {
    /*
创建一个小区
 */
    Community create(Community community)throws HRSException  ;
    /*
  批量创建小区
   */
    void createAll(List<Community> communities)throws HRSException  ;
    /*
  通过id修改一个小区
   */
    Community updateById(long id, Community community)throws HRSException;
    List<Community> getAll()throws HRSException  ;

    Community getById(long id)throws HRSException  ;
    List<Community> getAllByIdIn(List<Long> Ids)throws HRSException  ;
    List<Community> getAllById(long id)throws HRSException  ;
    Community getByNameLikeIgnoreCase(String name)throws HRSException  ;
    List<Community> getAllByNameLikeIgnoreCase(String names)throws HRSException  ;
    Community getByAddressLikeIgnoreCase(String address)throws HRSException  ;
    List<Community> getAllByAddressLikeIgnoreCase(String address)throws HRSException  ;
    int deleteAllById(long Id)throws HRSException  ;
    int deleteAllByIdIn(List<Long> Ids)throws HRSException  ;
//    int deleteAllByHouses(List<House> houseIds)throws HRSException  ;
}
