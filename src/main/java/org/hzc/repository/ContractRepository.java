package org.hzc.repository;

import org.hzc.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract getById(long id);
    List<Contract> getAllByIdIn(List<Long> Ids);
    List<Contract> getAllById(long id);
    Contract getByTenant_Id(long tenantId);
    List<Contract> getAllByTenant_IdIn(List<Long> tenantIds);
    List<Contract> getAllByTenant_Id(long tenantId);
    Contract getByLandlord_Id(long landlordId);
    List<Contract> getAllByLandlord_IdIn(List<Long> landlordIds);
    List<Contract> getAllByLandlord_Id(long landlordId);
    Contract getByHouse_Id(long houseId);
    List<Contract> getAllByHouse_IdIn(List<Long> HouseIds);
    List<Contract> getAllByHouse_Id(long houseId);

    /*
   查找startDate之后,endtDate之前的请求
*/
    List<Contract> getAllByContractCreateTimeBetween(LocalDate startDate, LocalDate endDate);
    /*
查找startDate之后(包括startDate)的请求
 */
    List<Contract> getAllByContractCreateTimeLessThanEqual(LocalDate startDate);
    /*
查找endtDate之前(包括endtDate)的请求
     */
    List<Contract> getAllByContractCreateTimeIsGreaterThanEqual(LocalDate endtDate);



    /*
         查找startDate之后,endtDate之前的请求
     */
    List<Contract> getAllByContractEndTimeBetween(LocalDate startDate, LocalDate endDate);
    /*
查找startDate之后(包括startDate)的请求
 */
    List<Contract> getAllByContractEndTimeLessThanEqual(LocalDate startDate);
    /*
查找endtDate之前(包括endtDate)的请求
     */
    List<Contract> getAllByContractEndTimeIsGreaterThanEqual(LocalDate endtDate);





    /*
根据租客留言进行模糊查询并且忽略大小写
      */
    List<Contract> getAllByContractContentIsLikeIgnoreCase(String message);



    int deleteAllById(long Id);
    int deleteAllByIdIn(List<Long> Ids);

    int deleteAllByTenant_Id(long tenantId);
    int deleteAllByTenant_IdIn(List<Long> tenantIds);

    int deleteAllByLandlord_Id(long landlordId);
    int deleteAllByLandlord_IdIn(List<Long> landlordIds);

    int deleteAllByHouse_Id(long houseId);
    int deleteAllByHouse_IdIn(List<Long> houseIds);

}
