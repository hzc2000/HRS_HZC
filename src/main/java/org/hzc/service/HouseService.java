package org.hzc.service;


import org.hzc.entity.House;
import org.hzc.exception.HRSException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseService {
    Page<House> get(int page, int size)throws HRSException  ;

    Page<House> getAllByLandlordId(Long landlordId, int page, int size)throws HRSException  ;
    /*
创建一个押金对象
*/
    House create(House house)throws HRSException  ;
    /*
  批量创建押金对象
   */
    List<House> createAll(List<House> houses)throws HRSException  ;
    /*
  通过id修改一个押金对象
   */
    House updateById(long id, House house)throws HRSException  ;
    House getByTitleLikeIgnoreCase(String Title)throws HRSException  ;
  List<House> getAll()throws HRSException  ;

    House getById(long id)throws HRSException  ;
    List<House> getAllByIdIn(List<Long> Ids)throws HRSException  ;
    House getByCommunity_Id(long communityId)throws HRSException  ;
    List<House> getAllByCommunity_IdIn(List<Long> communityIds)throws HRSException  ;
    List<House> getAllByCommunity_Id(long communityId)throws HRSException  ;
    House getByLandlord_Id(long landlordId)throws HRSException  ;
    List<House> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;
    List<House> getAllByLandlord_Id(long landlordId)throws HRSException  ;

    /*
根据房屋标题进行模糊查询并且忽略大小写
      */
    List<House> getAllByTitleLikeIgnoreCase(String title)throws HRSException  ;

    /*
根据房屋在小区里地址进行模糊查询并且忽略大小写
      */
    List<House> getAllByHouseAddressLikeIgnoreCase(String houseAddress)throws HRSException  ;

    /*
根据留言进行模糊查询并且忽略大小写
  */
    List<House> getAllByHouseIntroductionLikeIgnoreCase(String houseIntroduction)throws HRSException  ;

//    /*
// 返回所有已租出的房屋
//   */
//    List<House> getAllByRentFlagIsTrue()throws HRSException  ;
//
//    /*
// 返回所有未租出的房屋
//   */
//    List<House> getAllByRentFlagIsFalse()throws HRSException  ;
//
//    List<House> getAllByContractsIn(List<Contract> contracts)throws HRSException  ;









    void deleteById(long Id)throws HRSException  ;
    int deleteAllByIdIn(List<Long> Ids)throws HRSException;

    int deleteAllByCommunity_Id(long communityId)throws HRSException  ;
    int deleteAllByCommunity_IdIn(List<Long> communityIds)throws HRSException  ;

    int deleteAllByLandlord_Id(long landlordId)throws HRSException  ;
    int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;

//    int deleteAllByContracts(List<Contract> contractIds)throws HRSException  ;
}
