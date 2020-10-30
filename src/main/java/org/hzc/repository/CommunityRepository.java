package org.hzc.repository;

import org.hzc.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Community getById(long id);
    List<Community> getAllByIdIn(List<Long> Ids);
    List<Community> getAllById(long id);
    Community getByNameLikeIgnoreCase(String name);
    List<Community> getAllByNameLikeIgnoreCase(String names);
    Community getByAddressLikeIgnoreCase(String address);
    List<Community> getAllByAddressLikeIgnoreCase(String address);
    int deleteAllById(long Id);
    int deleteAllByIdIn(List<Long> Ids);
//    int deleteAllByHouses(List<House> houses);
}
