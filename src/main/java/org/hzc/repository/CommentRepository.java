package org.hzc.repository;

import org.hzc.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment getById(long id);
    List<Comment> getAllByIdIn(List<Long> Ids);
    List<Comment> getAllById(long id);
    Comment getByTenant_Id(long tenantId);
    List<Comment> getAllByTenant_IdIn(List<Long> tenantIds);
    List<Comment> getAllByTenant_Id(long tenantId);
    Comment getByLandlord_Id(long landlordId);
    List<Comment> getAllByLandlord_IdIn(List<Long> landlordIds);
    List<Comment> getAllByLandlord_Id(long landlordId);
    Comment getByHouse_Id(long houseId);
    List<Comment> getAllByHouse_IdIn(List<Long> HouseIds);
    List<Comment> getAllByHouse_Id(long houseId);
    Comment getByContract_Id(Long contractId);
    int deleteAllById(long Id);
    int deleteAllByIdIn(List<Long> Ids);

    int deleteAllByTenant_Id(long tenantId);
    int deleteAllByTenant_IdIn(List<Long> tenantIds);

    int deleteAllByLandlord_Id(long landlordId);
    int deleteAllByLandlord_IdIn(List<Long> landlordIds);

    int deleteAllByHouse_Id(long houseId);
    int deleteAllByHouse_IdIn(List<Long> houseIds);

    int deleteAllByContract_Id(long contractId);
    int deleteAllByContract_IdIn(List<Long> contractIds);
}
