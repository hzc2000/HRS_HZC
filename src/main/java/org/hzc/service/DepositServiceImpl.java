package org.hzc.service;


import lombok.Data;
import org.hzc.entity.Deposit;
import org.hzc.entity.House;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@Data
@Transactional
public class DepositServiceImpl implements DepositService {
    @Autowired
    private DepositRepository depositRepository;

    @Override
    public Deposit create(Deposit deposit)throws HRSException  {
        return depositRepository.save(deposit);
    }

    @Override
    public void createAll(List<Deposit> deposits)throws HRSException  {
        deposits.forEach(deposit->depositRepository.save(deposit));
    }

    @Override
    public Deposit updateById(long id, Deposit deposit)throws HRSException  {
        deposit.setId(id);
        return depositRepository.save(deposit);
    }

    @Override
    public Page<Deposit> getAllDeposit(User administrator, int page, int size){
        Deposit deposit = new Deposit();
        deposit.setAdministrator(administrator);
        Example<Deposit> example = Example.of(deposit);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.DESC, "id");
        return depositRepository.findAll(example,pageable);
    }


    @Override
    public Deposit getById(long id)throws HRSException  {
        return depositRepository.getById(id);
    }

    @Override
    public Deposit getByTenantId(long id)throws HRSException  {
        return depositRepository.getByTenant_Id(id);
    }

    @Override
    public List<Deposit> getAllByIdIn(List<Long> Ids)throws HRSException  {
        return depositRepository.getAllByIdIn(Ids);
    }

    @Override
    public List<Deposit> getAllById(long id)throws HRSException  {
        return depositRepository.getAllById(id);
    }





    @Override
    public int deleteAllById(long Id)throws HRSException {
        return depositRepository.deleteAllById(Id) ;
    }

    @Override
    public int deleteAllByIdIn(List<Long> Ids)throws HRSException  {
        return depositRepository.deleteAllByIdIn(Ids);
    }


}
