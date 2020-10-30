package org.hzc.repository;

import org.hzc.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    House getById(long id);
    List<House> getAllByIdIn(List<Long> Ids);
    House getByCommunity_Id(long communityId);

    House getByTitleLikeIgnoreCase(String title);

    List<House> getAllByCommunity_IdIn(List<Long> communityIds);
    List<House> getAllByCommunity_Id(long communityId);
    House getByLandlord_Id(long landlordId);
    List<House> getAllByLandlord_IdIn(List<Long> landlordIds);
    List<House> getAllByLandlord_Id(long landlordId);

    /*
根据房屋标题进行模糊查询并且忽略大小写
      */
    List<House> getAllByTitleLikeIgnoreCase(String title);

    /*
根据房屋在小区里地址进行模糊查询并且忽略大小写
      */
    List<House> getAllByHouseAddressLikeIgnoreCase(String houseAddress);

    /*
根据留言进行模糊查询并且忽略大小写
  */
    List<House> getAllByHouseIntroductionLikeIgnoreCase(String houseIntroduction);


//    List<House> getAllByContractsContains(List<Contract> contracts);



    int deleteAllById(long Id);
    int deleteAllByIdIn(List<Long> Ids);

    int deleteAllByCommunity_Id(long communityId);
    int deleteAllByCommunity_IdIn(List<Long> communityIds);

    int deleteAllByLandlord_Id(long landlordId);
    int deleteAllByLandlord_IdIn(List<Long> landlordIds);

//    int deleteAllByContracts(List<Contract> contractIds);
}
