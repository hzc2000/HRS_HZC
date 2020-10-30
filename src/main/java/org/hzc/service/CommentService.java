package org.hzc.service;


import org.hzc.entity.Comment;
import org.hzc.exception.HRSException;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    /*
创建一个评论
 */
    Comment create(Comment comment)throws HRSException  ;
    /*
  批量创建评论
   */
    void createAll(List<Comment> comments)throws HRSException  ;
    /*
  通过id修改一个评论
   */
    Comment updateById(long id, Comment comment)throws HRSException  ;


  Page<Comment> getAllComment(Long houseId, int page, int size)throws HRSException  ;

    Comment getById(long id)throws HRSException  ;
    List<Comment> getAllByIdIn(List<Long> Ids)throws HRSException  ;
    List<Comment> getAllById(long id)throws HRSException  ;
    Comment getByTenant_Id(long tenantId)throws HRSException  ;
    List<Comment> getAllByTenant_IdIn(List<Long> tenantIds)throws HRSException;
    List<Comment> getAllByTenant_Id(long tenantId)throws HRSException  ;
    Comment getByLandlord_Id(long landlordId)throws HRSException  ;
    List<Comment> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;
    List<Comment> getAllByLandlord_Id(long landlordId)throws HRSException  ;
    Comment getByHouse_Id(long houseId)throws HRSException  ;
    List<Comment> getAllByHouse_IdIn(List<Long> HouseIds)throws HRSException  ;
    List<Comment> getAllByHouse_Id(long houseId)throws HRSException  ;


    Comment getByContract_Id(Long contractId)throws HRSException  ;
    int deleteAllById(long Id)throws HRSException  ;
    int deleteAllByIdIn(List<Long> Ids)throws HRSException  ;

    int deleteAllByTenant_Id(long tenantId)throws HRSException  ;
    int deleteAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  ;

    int deleteAllByLandlord_Id(long landlordId)throws HRSException  ;
    int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  ;

    int deleteAllByHouse_Id(long houseId)throws HRSException  ;
    int deleteAllByHouse_IdIn(List<Long> houseIds)throws HRSException  ;

    int deleteAllByContract_Id(long contractId)throws HRSException  ;
    int deleteAllByContract_IdIn(List<Long> contractIds)throws HRSException  ;
}
