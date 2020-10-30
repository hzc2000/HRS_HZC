package org.hzc.service;


import org.hzc.entity.Apply;
import org.hzc.exception.HRSException;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ApplyService {
   Page<Apply> queryGetPageBylandlordId(Long landlordId, int page, int size) throws HRSException ;
   /*
   创建一个租用申请
    */
    Apply create(Apply apply)throws HRSException  ;
   /*
 批量创建租用申请
  */
    void createAll(List<Apply> applys)throws HRSException  ;
   /*
 通过id修改一个租用申请
  */
    Apply updateById(long id, Apply apply) throws HRSException ;




   Apply getById(long id)throws HRSException  ;
   List<Apply> getAllByIdIn(List<Long> Ids)throws HRSException  ;
   List<Apply> getAllById(long id)throws HRSException  ;
   Apply getByTenant_Id(long tenantId)throws HRSException  ;
   List<Apply> getAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  ;
   List<Apply> getAllByTenant_Id(long tenantId)throws HRSException  ;
   Apply getByLandlord_Id(long landlordId)throws HRSException  ;
   List<Apply> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;
   List<Apply> getAllByLandlord_Id(long landlordId)throws HRSException  ;
   Apply getByHouse_Id(long houseId)throws HRSException  ;
   List<Apply> getAllByHouse_IdIn(List<Long> HouseIds)throws HRSException  ;
   List<Apply> getAllByHouse_Id(long houseId)throws HRSException  ;
   /*
查找大于min小于max的请求
*/
   List<Apply> getAllByOfferPriceBetween(BigDecimal min, BigDecimal max)throws HRSException;
   /*
查找大于min(包括min)的请求
*/
   List<Apply> getAllByOfferPriceGreaterThanEqual(BigDecimal min)throws HRSException  ;
   /*
查找小于max(包括max)的请求
*/
   List<Apply> getAllByOfferPriceLessThanEqual(BigDecimal max)throws HRSException  ;
   /*
查找startDate之后,endtDate之前的请求
*/
   List<Apply> getAllByCreateTimeBetween(LocalDate startDate, LocalDate endDate)throws HRSException  ;
   /*
查找startDate之后(包括startDate)的请求
*/
   List<Apply> getAllByCreateTimeLessThanEqual(LocalDate startDate)throws HRSException  ;
   /*
查找endtDate之前(包括endtDate)的请求
    */
   List<Apply> getAllByCreateTimeIsGreaterThanEqual(LocalDate endtDate)throws HRSException  ;
   /*
根据留言进行模糊查询并且忽略大小写
     */
   List<Apply> getAllByLeaveMessageIsLikeIgnoreCase(String message)throws HRSException  ;
   int deleteAllById(long Id)throws HRSException  ;
   int deleteAllByIdIn(List<Long> Ids)throws HRSException  ;

   int deleteAllByTenant_Id(long tenantId)throws HRSException  ;
   int deleteAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  ;

   int deleteAllByLandlord_Id(long landlordId)throws HRSException  ;
   int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;

   int deleteAllByHouse_Id(long houseId)throws HRSException  ;
   int deleteAllByHouse_IdIn(List<Long> houseIds)throws HRSException  ;
}
