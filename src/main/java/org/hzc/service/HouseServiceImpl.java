package org.hzc.service;


import lombok.Data;
import org.hzc.entity.House;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Data
@Transactional
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseRepository houseRepository;




    public  Page<House> get(int page, int size)throws HRSException  {
        House house = new House();
        house.setFlag("待出租");
        Example<House> example = Example.of(house);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.DESC, "title");
        Page<House> housePage =houseRepository.findAll(example,pageable);
        return housePage;
    }



    @Override
    public  Page<House> getAllByLandlordId(Long landlordId, int page, int size)throws HRSException  {
        House house = new House();
        User landlord = new User();
        landlord.setId(landlordId);
        house.setLandlord(landlord);
        Example<House> example = Example.of(house);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.DESC, "flag");
        Page<House> page1 = houseRepository.findAll(example,pageable);
        return page1;
    }
    @Override
    public List<House> getAll()throws HRSException  {
        return houseRepository.findAll();
    }


    @Override
    public House getByTitleLikeIgnoreCase(String Title)throws HRSException  {
        return houseRepository.getByTitleLikeIgnoreCase(Title);
    }
    
    @Override
    public House create(House house)throws HRSException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        house.setLastEditTime(LocalDateTime.parse(LocalDateTime.now().format(df),df));//从本地获取当前时间
        return houseRepository.save(house);
    }

    @Override
    public List<House> createAll(List<House> houses)throws HRSException  {
        return  houseRepository.saveAll(houses);
    }

    @Override
    public House updateById(long id, House house)throws HRSException  {
        house.setId(id);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        house.setLastEditTime(LocalDateTime.parse(LocalDateTime.now().format(df),df));//从本地获取当前时间
        return houseRepository.save(house);
    }

    @Override
    public House getById(long id)throws HRSException  {
        return houseRepository.getById(id);
    }


    @Override
    public List<House> getAllByIdIn(List<Long> Ids)throws HRSException  {
        return houseRepository.getAllByIdIn(Ids);
    }




    @Override
    public House getByCommunity_Id(long communityId)throws HRSException  {
        return houseRepository.getByCommunity_Id(communityId);
    }

    @Override
    public List<House> getAllByCommunity_IdIn(List<Long> communityIds)throws HRSException  {
        return houseRepository.getAllByCommunity_IdIn(communityIds);
    }

    @Override
    public List<House> getAllByCommunity_Id(long communityId)throws HRSException  {
        return houseRepository.getAllByCommunity_Id(communityId);
    }

    @Override
    public House getByLandlord_Id(long landlordId)throws HRSException  {
        return houseRepository.getByLandlord_Id(landlordId);
    }

    @Override
    public List<House> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  {
        return houseRepository.getAllByLandlord_IdIn(landlordIds);
    }

    @Override
    public List<House> getAllByLandlord_Id(long landlordId)throws HRSException  {
        return houseRepository.getAllByLandlord_Id(landlordId);
    }


    /*
 根据房屋标题进行模糊查询并且忽略大小写
       */
    @Override
    public List<House> getAllByTitleLikeIgnoreCase(String title)throws HRSException  {
        return houseRepository.getAllByTitleLikeIgnoreCase(title);
    }

    /*
根据房屋在小区里地址进行模糊查询并且忽略大小写
      */
    @Override
    public List<House> getAllByHouseAddressLikeIgnoreCase(String houseAddress)throws HRSException  {
        return houseRepository.getAllByHouseAddressLikeIgnoreCase(houseAddress);
    }
    /*
根据留言进行模糊查询并且忽略大小写
  */
    @Override
    public List<House> getAllByHouseIntroductionLikeIgnoreCase(String houseIntroduction)throws HRSException  {
        return houseRepository.getAllByHouseIntroductionLikeIgnoreCase(houseIntroduction);
    }



    @Override
    public void deleteById(long Id)throws HRSException  {
       houseRepository.deleteById(Id);
    }

    @Override
    public int deleteAllByIdIn(List<Long> Ids)throws HRSException  {
        return houseRepository.deleteAllByIdIn(Ids);
    }

    @Override
    public int deleteAllByCommunity_Id(long communityId)throws HRSException  {
        return houseRepository.deleteAllByCommunity_Id(communityId);
    }

    @Override
    public int deleteAllByCommunity_IdIn(List<Long> communityIds)throws HRSException  {
        return houseRepository.deleteAllByCommunity_IdIn(communityIds);
    }

    @Override
    public int deleteAllByLandlord_Id(long landlordId)throws HRSException  {
        return houseRepository.deleteAllByLandlord_Id(landlordId);
    }

    @Override
    public int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  {
        return houseRepository.deleteAllByLandlord_IdIn(landlordIds);
    }



}
