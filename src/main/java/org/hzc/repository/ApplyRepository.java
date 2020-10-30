package org.hzc.repository;

import org.hzc.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Apply getById(long id);
    List<Apply> getAllByIdIn(List<Long> Ids);
    List<Apply> getAllById(long id);
    Apply getByTenant_Id(long tenantId);
    List<Apply> getAllByTenant_IdIn(List<Long> tenantIds);
    List<Apply> getAllByTenant_Id(long tenantId);
    Apply getByLandlord_Id(long landlordId);
    List<Apply> getAllByLandlord_IdIn(List<Long> landlordIds);
    List<Apply> getAllByLandlord_Id(long landlordId);
    Apply getByHouse_Id(long houseId);
    List<Apply> getAllByHouse_IdIn(List<Long> HouseIds);
    List<Apply> getAllByHouse_Id(long houseId);
    /*
查找大于min小于max的请求
*/
    List<Apply> getAllByOfferPriceBetween(BigDecimal min, BigDecimal max);
    /*
查找大于min(包括min)的请求
*/
    List<Apply> getAllByOfferPriceGreaterThanEqual(BigDecimal min);
    /*
查找小于max(包括max)的请求
*/
    List<Apply> getAllByOfferPriceLessThanEqual(BigDecimal max);
    /*
查找startDate之后,endtDate之前的请求
*/
    List<Apply> getAllByCreateTimeBetween(LocalDate startDate, LocalDate endDate);
    /*
查找startDate之后(包括startDate)的请求
 */
    List<Apply> getAllByCreateTimeLessThanEqual(LocalDate startDate);
    /*
查找endtDate之前(包括endtDate)的请求
     */
    List<Apply> getAllByCreateTimeIsGreaterThanEqual(LocalDate endtDate);
    /*
根据留言进行模糊查询并且忽略大小写
      */
    List<Apply> getAllByLeaveMessageIsLikeIgnoreCase(String message);
    int deleteAllById(long Id);
    int deleteAllByIdIn(List<Long> Ids);

    int deleteAllByTenant_Id(long tenantId);
    int deleteAllByTenant_IdIn(List<Long> tenantIds);

    int deleteAllByLandlord_Id(long landlordId);
    int deleteAllByLandlord_IdIn(List<Long> landlordIds);

    int deleteAllByHouse_Id(long houseId);
    int deleteAllByHouse_IdIn(List<Long> houseIds);

}
