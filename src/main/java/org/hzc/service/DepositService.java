package org.hzc.service;


import org.hzc.entity.Deposit;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface DepositService {
    /*
创建一个押金对象
*/
    Deposit create(Deposit deposit)throws HRSException  ;
    /*
  批量创建押金对象
   */
    void createAll(List<Deposit> deposits)throws HRSException  ;
    /*
  通过id修改一个押金对象
   */
    Deposit updateById(long id, Deposit deposit)throws HRSException  ;

    
    Deposit getById(long id)throws HRSException  ;
    List<Deposit> getAllByIdIn(List<Long> Ids)throws HRSException;
    List<Deposit> getAllById(long id)throws HRSException  ;
    Page<Deposit> getAllDeposit(User administrator, int page, int size)throws HRSException  ;
    Deposit getByTenantId(long id)throws HRSException  ;

    int deleteAllById(long Id)throws HRSException  ;
    int deleteAllByIdIn(List<Long> Ids)throws HRSException  ;


}
