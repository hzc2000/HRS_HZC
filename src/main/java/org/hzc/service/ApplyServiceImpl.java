package org.hzc.service;


import lombok.Data;
import org.hzc.entity.Apply;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.repository.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
@Data
@Transactional
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyRepository applyRepository;

    @Override
    public Apply create(Apply user)throws HRSException  {
        return applyRepository.save(user);
    }

    @Override
    public Page<Apply> queryGetPageBylandlordId(Long landlordId, int page, int size)throws HRSException  {
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.DESC, "offerPrice");

       Apply apply = new Apply();
        User landlord = new User();
        landlord.setId(landlordId);
        apply.setLandlord(landlord);
        apply.setFlag("未接受");
        Example<Apply> example = Example.of(apply);

        Page<Apply> applies  = applyRepository.findAll(example,pageable);
        applies.getContent().forEach(str-> System.out.println(str.toString()));
        return applies;
    }



    @Override
    public void createAll(List<Apply> users)throws HRSException {
        users.forEach(user->applyRepository.save(user));
    }

    @Override
    public Apply updateById(long id, Apply user)throws HRSException  {
        user.setId(id);
        return applyRepository.save(user);
    }



    @Override
    public Apply getById(long id)throws HRSException  {
        return applyRepository.getById(id);
    }

    @Override
    public List<Apply> getAllByIdIn(List<Long> Ids)throws HRSException  {
        return applyRepository.getAllByIdIn(Ids);
    }

    @Override
    public List<Apply> getAllById(long id)throws HRSException  {
        return applyRepository.getAllById(id);
    }

    @Override
    public Apply getByTenant_Id(long tenantId)throws HRSException  {
        return applyRepository.getByTenant_Id(tenantId);
    }

    @Override
    public List<Apply> getAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  {
        return applyRepository.getAllByTenant_IdIn(tenantIds);
    }

    @Override
    public List<Apply> getAllByTenant_Id(long tenantId)throws HRSException  {
        return applyRepository.getAllByTenant_Id(tenantId);
    }

    @Override
    public Apply getByLandlord_Id(long landlordId)throws HRSException  {
        return applyRepository.getByLandlord_Id(landlordId);
    }

    @Override
    public List<Apply> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  {
        return applyRepository.getAllByLandlord_IdIn(landlordIds);
    }

    @Override
    public List<Apply> getAllByLandlord_Id(long landlordId)throws HRSException  {
        return applyRepository.getAllByLandlord_Id(landlordId);
    }

    @Override
    public Apply getByHouse_Id(long houseId)throws HRSException  {
        return applyRepository.getByHouse_Id(houseId);
    }

    @Override
    public List<Apply> getAllByHouse_IdIn(List<Long> HouseIds)throws HRSException  {
        return applyRepository.getAllByHouse_IdIn(HouseIds);
    }

    @Override
    public List<Apply> getAllByHouse_Id(long houseId)throws HRSException  {
        return applyRepository.getAllByHouse_Id(houseId);
    }

    @Override
    public List<Apply> getAllByOfferPriceBetween(BigDecimal min, BigDecimal max)throws HRSException  {
        return applyRepository.getAllByOfferPriceBetween(min,max);
    }

    @Override
    public List<Apply> getAllByOfferPriceGreaterThanEqual(BigDecimal min)throws HRSException  {
        return applyRepository.getAllByOfferPriceGreaterThanEqual(min);
    }

    @Override
    public List<Apply> getAllByOfferPriceLessThanEqual(BigDecimal max)throws HRSException  {
        return applyRepository.getAllByOfferPriceLessThanEqual(max);
    }

    @Override
    public List<Apply> getAllByCreateTimeBetween(LocalDate startDate, LocalDate endDate)throws HRSException  {
        return applyRepository.getAllByCreateTimeBetween(startDate,endDate);
    }

    @Override
    public List<Apply> getAllByCreateTimeLessThanEqual(LocalDate startDate)throws HRSException  {
        return applyRepository.getAllByCreateTimeLessThanEqual(startDate);
    }

    @Override
    public List<Apply> getAllByCreateTimeIsGreaterThanEqual(LocalDate endtDate)throws HRSException  {
        return applyRepository.getAllByCreateTimeIsGreaterThanEqual(endtDate);
    }

    @Override
    public List<Apply> getAllByLeaveMessageIsLikeIgnoreCase(String message)throws HRSException  {
        return applyRepository.getAllByLeaveMessageIsLikeIgnoreCase(message);
    }

    @Override
    public int deleteAllById(long Id)throws HRSException  {
        return applyRepository.deleteAllById(Id) ;
    }

    @Override
    public int deleteAllByIdIn(List<Long> Ids)throws HRSException  {
        return applyRepository.deleteAllByIdIn(Ids);
    }

    @Override
    public int deleteAllByTenant_Id(long tenantId)throws HRSException  {
        return applyRepository.deleteAllByTenant_Id(tenantId);
    }

    @Override
    public int deleteAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  {
        return applyRepository.deleteAllByTenant_IdIn(tenantIds);
    }

    @Override
    public int deleteAllByLandlord_Id(long landlordId)throws HRSException  {
        return applyRepository.deleteAllByLandlord_Id(landlordId);
    }

    @Override
    public int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  {
        return applyRepository.deleteAllByLandlord_IdIn(landlordIds);
    }

    @Override
    public int deleteAllByHouse_Id(long houseId)throws HRSException  {
        return applyRepository.deleteAllByHouse_Id(houseId);
    }

    @Override
    public int deleteAllByHouse_IdIn(List<Long> houseIds)throws HRSException  {
        return applyRepository.deleteAllByHouse_IdIn(houseIds);
    }
}
