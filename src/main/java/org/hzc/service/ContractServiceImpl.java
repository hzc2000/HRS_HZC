package org.hzc.service;


import lombok.Data;
import org.hzc.entity.Contract;
import org.hzc.entity.Role;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Data
@Transactional
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract create(Contract user)throws HRSException  {
        return contractRepository.save(user);
    }

    @Override
    public void createAll(List<Contract> users)throws HRSException  {
        users.forEach(user->contractRepository.save(user));
    }

    @Override
    public Contract updateById(long id, Contract user)throws HRSException  {
        user.setId(id);
        return contractRepository.save(user);
    }
    public Page<Contract> getAllCompletedContractPageByTenantId(User user, int page, int size)throws HRSException  {
        Contract contract = new Contract();
        if(user.getRole().name().equals("TENANT"))
        {

            contract.setTenant(user);
        }
        if(user.getRole().name().equals("LANDLORD"))
        {

            contract.setLandlord(user);
        }
        contract.setApproveFromLandlord("已确认");
        contract.setApproveFromTenant("已确认");
        contract.setFlag("已完成");
        System.out.println(contract.getLandlord());
        System.out.println(contract.getTenant());
        Example<Contract> example = Example.of(contract);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.ASC, "id");
        Page<Contract> contractPage =contractRepository.findAll(example,pageable);
        return contractPage;
    }
    public Page<Contract> getAllPageByTenantId(long tenantId, int page, int size)throws HRSException  {
        Contract contract = new Contract();
        User user = new User();
        user.setId(tenantId);
        contract.setTenant(user);
        contract.setApproveFromLandlord("已确认");
        contract.setApproveFromTenant("未确认");
        contract.setFlag("未生效");
        Example<Contract> example = Example.of(contract);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.ASC, "id");
        Page<Contract> contractPage =contractRepository.findAll(example,pageable);
        return contractPage;
    }
    public Page<Contract> getAllApprovedPageByTenantId(long tenantId, int page, int size)throws HRSException  {
        Contract contract = new Contract();
        User tenant = new User();
        tenant.setId(tenantId);
        contract.setTenant(tenant);
        contract.setApproveFromLandlord("已确认");
        contract.setApproveFromTenant("未确认");
        Example<Contract> example = Example.of(contract);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.ASC, "id");
        Page<Contract> contractPage =contractRepository.findAll(example,pageable);
        return contractPage;
    }

    @Override
    public List getAllEffectingContract()throws HRSException  {
        Contract contract = new Contract();
        contract.setFlag("生效中");
        Example example = Example.of(contract);
        Pageable pageable = PageRequest.of(0, 9999999, Sort.Direction.ASC, "id");
        return     contractRepository.findAll(example,pageable).getContent();
    }

    @Override
    public Page<Contract> getAllEffectingContract(User user, int page, int size)throws HRSException {
        Contract contract = new Contract();
        contract.setFlag("生效中");
        if (user.getRole().name().equals("TENANT"))
        {
            contract.setTenant(user);
        }else if(user.getRole().name().equals("LANDLORD"))
        {
            contract.setLandlord(user);
        }

        Example example = Example.of(contract);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.ASC, "id");
        return     contractRepository.findAll(example,pageable);
    }
    @Override
    public Contract getById(long id)throws HRSException  {
        return contractRepository.getById(id);
    }

    @Override
    public List<Contract> getAllByIdIn(List<Long> Ids)throws HRSException  {
        return contractRepository.getAllByIdIn(Ids);
    }

    @Override
    public List<Contract> getAllById(long id)throws HRSException  {
        return contractRepository.getAllById(id);
    }

    @Override
    public Contract getByTenant_Id(long tenantId)throws HRSException  {
        return contractRepository.getByTenant_Id(tenantId);
    }

    @Override
    public List<Contract> getAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  {
        return contractRepository.getAllByTenant_IdIn(tenantIds);
    }

    @Override
    public List<Contract> getAllByTenant_Id(long tenantId)throws HRSException  {
        return contractRepository.getAllByTenant_Id(tenantId);
    }

    @Override
    public Contract getByLandlord_Id(long landlordId)throws HRSException  {
        return contractRepository.getByLandlord_Id(landlordId);
    }

    @Override
    public List<Contract> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  {
        return contractRepository.getAllByLandlord_IdIn(landlordIds);
    }

    @Override
    public List<Contract> getAllByLandlord_Id(long landlordId)throws HRSException  {
        return contractRepository.getAllByLandlord_Id(landlordId);
    }

    @Override
    public Contract getByHouse_Id(long houseId)throws HRSException  {
        return contractRepository.getByHouse_Id(houseId);
    }

    @Override
    public List<Contract> getAllByHouse_IdIn(List<Long> HouseIds)throws HRSException  {
        return contractRepository.getAllByHouse_IdIn(HouseIds);
    }

    @Override
    public List<Contract> getAllByHouse_Id(long houseId)throws HRSException  {
        return contractRepository.getAllByHouse_Id(houseId);
    }


    @Override
    public List<Contract> getAllByContractCreateTimeBetween(LocalDate startDate, LocalDate endDate)throws HRSException  {
        return contractRepository.getAllByContractCreateTimeBetween(startDate,endDate);
    }

    @Override
    public List<Contract> getAllByContractCreateTimeLessThanEqual(LocalDate startDate)throws HRSException  {
        return contractRepository.getAllByContractCreateTimeLessThanEqual(startDate);
    }

    @Override
    public List<Contract> getAllByContractCreateTimeIsGreaterThanEqual(LocalDate endtDate)throws HRSException  {
        return contractRepository.getAllByContractCreateTimeIsGreaterThanEqual(endtDate);
    }

    /*
         查找startDate之后,endtDate之前的请求
     */
    public  List<Contract> getAllByContractEndTimeBetween(LocalDate startDate, LocalDate endDate)throws HRSException  {
        return contractRepository.getAllByContractEndTimeBetween(startDate,endDate);
    }
    /*
       查找startDate之后(包括startDate)的请求
    */
    public List<Contract> getAllByContractEndTimeLessThanEqual(LocalDate startDate)throws HRSException  {
        return contractRepository.getAllByContractEndTimeLessThanEqual(startDate);
    }
    /*
       查找endtDate之前(包括endtDate)的请求
        */
    public List<Contract> getAllByContractEndTimeIsGreaterThanEqual(LocalDate endtDate)throws HRSException  {
        return contractRepository.getAllByContractEndTimeIsGreaterThanEqual(endtDate);
    }


    /*
根据租客留言进行模糊查询并且忽略大小写
      */
    public List<Contract> getAllByContractContentIsLikeIgnoreCase(String message){
        return contractRepository.getAllByContractContentIsLikeIgnoreCase(message);
    }

    @Override
    public int deleteAllById(long Id)throws HRSException  {
        return contractRepository.deleteAllById(Id) ;
    }

    @Override
    public int deleteAllByIdIn(List<Long> Ids)throws HRSException  {
        return contractRepository.deleteAllByIdIn(Ids);
    }

    @Override
    public int deleteAllByTenant_Id(long tenantId)throws HRSException  {
        return contractRepository.deleteAllByTenant_Id(tenantId);
    }

    @Override
    public int deleteAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  {
        return contractRepository.deleteAllByTenant_IdIn(tenantIds);
    }

    @Override
    public int deleteAllByLandlord_Id(long landlordId)throws HRSException  {
        return contractRepository.deleteAllByLandlord_Id(landlordId);
    }

    @Override
    public int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException {
        return contractRepository.deleteAllByLandlord_IdIn(landlordIds);
    }

    @Override
    public int deleteAllByHouse_Id(long houseId)throws HRSException  {
        return contractRepository.deleteAllByHouse_Id(houseId);
    }

    @Override
    public int deleteAllByHouse_IdIn(List<Long> houseIds)throws HRSException  {
        return contractRepository.deleteAllByHouse_IdIn(houseIds);
    }

}
