package org.hzc.service;


import org.hzc.entity.Contract;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ContractService {
    /*
创建一个合同
*/
    Contract create(Contract contract)throws HRSException  ;
    /*
  批量创建合同
   */
    void createAll(List<Contract> contracts)throws HRSException  ;
    /*
  通过id修改一个合同
   */
    Contract updateById(long id, Contract contract)throws HRSException  ;

     List<Contract> getAllEffectingContract()throws HRSException  ;
    Page<Contract> getAllCompletedContractPageByTenantId(User user, int page, int size)throws HRSException  ;
    Page<Contract> getAllPageByTenantId(long tenantId, int page, int size)throws HRSException  ;
    Page<Contract> getAllEffectingContract(User landlord, int page, int size)throws HRSException  ;
     Page<Contract> getAllApprovedPageByTenantId(long tenantId, int page, int size)throws HRSException  ;
    Contract getById(long id)throws HRSException  ;
    List<Contract> getAllByIdIn(List<Long> Ids)throws HRSException  ;
    List<Contract> getAllById(long id)throws HRSException  ;
    Contract getByTenant_Id(long tenantId)throws HRSException  ;
    List<Contract> getAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  ;
    List<Contract> getAllByTenant_Id(long tenantId)throws HRSException  ;
    Contract getByLandlord_Id(long landlordId)throws HRSException  ;
    List<Contract> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;
    List<Contract> getAllByLandlord_Id(long landlordId)throws HRSException  ;
    Contract getByHouse_Id(long houseId)throws HRSException  ;
    List<Contract> getAllByHouse_IdIn(List<Long> HouseIds)throws HRSException  ;
    List<Contract> getAllByHouse_Id(long houseId)throws HRSException  ;

    /*
   查找startDate之后,endtDate之前的请求
*/
    List<Contract> getAllByContractCreateTimeBetween(LocalDate startDate, LocalDate endDate)throws HRSException  ;
    /*
查找startDate之后(包括startDate)的请求
 */
    List<Contract> getAllByContractCreateTimeLessThanEqual(LocalDate startDate)throws HRSException  ;
    /*
查找endtDate之前(包括endtDate)的请求
     */
    List<Contract> getAllByContractCreateTimeIsGreaterThanEqual(LocalDate endtDate)throws HRSException  ;



    /*
         查找startDate之后,endtDate之前的请求
     */
    List<Contract> getAllByContractEndTimeBetween(LocalDate startDate, LocalDate endDate)throws HRSException  ;
    /*
查找startDate之后(包括startDate)的请求
 */
    List<Contract> getAllByContractEndTimeLessThanEqual(LocalDate startDate)throws HRSException  ;
    /*
查找endtDate之前(包括endtDate)的请求
     */
    List<Contract> getAllByContractEndTimeIsGreaterThanEqual(LocalDate endtDate)throws HRSException  ;





    /*
根据租客留言进行模糊查询并且忽略大小写
      */
    List<Contract> getAllByContractContentIsLikeIgnoreCase(String message)throws HRSException  ;



    int deleteAllById(long Id)throws HRSException  ;
    int deleteAllByIdIn(List<Long> Ids)throws HRSException;

    int deleteAllByTenant_Id(long tenantId)throws HRSException  ;
    int deleteAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  ;

    int deleteAllByLandlord_Id(long landlordId)throws HRSException  ;
    int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;

    int deleteAllByHouse_Id(long houseId)throws HRSException  ;
    int deleteAllByHouse_IdIn(List<Long> houseIds)throws HRSException  ;
}
