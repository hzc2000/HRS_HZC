package org.hzc.repository;

import org.hzc.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Deposit getById(long id);
    List<Deposit> getAllByIdIn(List<Long> Ids);
    List<Deposit> getAllById(long id);
    Deposit getByAdministrator_Id(long administratorId);
    List<Deposit> getAllByAdministrator_IdIn(List<Long> administratorIds);
    List<Deposit> getAllByAdministrator_Id(long administratorId);
    Deposit getByTenant_Id(long tenantId);

    int deleteAllById(long Id);
    int deleteAllByIdIn(List<Long> Ids);

    int deleteAllByAdministrator_Id(long administratorId);
    int deleteAllByAdministrator_IdIn(List<Long> administratorIds);

}
