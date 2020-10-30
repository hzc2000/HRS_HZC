package org.hzc.service;


import lombok.Data;
import org.hzc.entity.Comment;
import org.hzc.entity.House;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Data
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment)throws HRSException  {
        return commentRepository.save(comment);
    }

    @Override
    public void createAll(List<Comment> comments)throws HRSException  {
        comments.forEach(comment->commentRepository.save(comment));
    }

    @Override
    public Comment updateById(long id, Comment comment)throws HRSException  {
        comment.setId(id);
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> getAllComment(Long houseId, int page, int size)
    {
        Comment comment = new Comment();
        House house = new House();
        house.setId(houseId);
        comment.setHouse(house);
        Example<Comment> example = Example.of(comment);
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.DESC, "id");
        Page<Comment> page1 = commentRepository.findAll(example,pageable);
        return page1;
    }

    @Override
    public Comment getById(long id)throws HRSException  {
        return commentRepository.getById(id);
    }

    @Override
    public List<Comment> getAllByIdIn(List<Long> Ids)throws HRSException  {
        return commentRepository.getAllByIdIn(Ids);
    }

    @Override
    public List<Comment> getAllById(long id)throws HRSException  {
        return commentRepository.getAllById(id);
    }

    @Override
    public Comment getByTenant_Id(long tenantId)throws HRSException  {
        return commentRepository.getByTenant_Id(tenantId);
    }

    @Override
    public List<Comment> getAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  {
        return commentRepository.getAllByTenant_IdIn(tenantIds);
    }

    @Override
    public List<Comment> getAllByTenant_Id(long tenantId)throws HRSException  {
        return commentRepository.getAllByTenant_Id(tenantId);
    }

    @Override
    public Comment getByLandlord_Id(long landlordId)throws HRSException  {
        return commentRepository.getByLandlord_Id(landlordId);
    }

    @Override
    public List<Comment> getAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  {
        return commentRepository.getAllByLandlord_IdIn(landlordIds);
    }

    @Override
    public List<Comment> getAllByLandlord_Id(long landlordId)throws HRSException  {
        return commentRepository.getAllByLandlord_Id(landlordId);
    }

    @Override
    public Comment getByHouse_Id(long houseId)throws HRSException  {
        return commentRepository.getByHouse_Id(houseId);
    }

    @Override
    public List<Comment> getAllByHouse_IdIn(List<Long> HouseIds)throws HRSException  {
        return commentRepository.getAllByHouse_IdIn(HouseIds);
    }

    @Override
    public List<Comment> getAllByHouse_Id(long houseId)throws HRSException  {
        return commentRepository.getAllByHouse_Id(houseId);
    }

    @Override
    public  Comment getByContract_Id(Long contractId)throws HRSException  {
        return commentRepository.getByContract_Id(contractId);
    }





    @Override
    public int deleteAllById(long Id)throws HRSException  {
        return commentRepository.deleteAllById(Id) ;
    }

    @Override
    public int deleteAllByIdIn(List<Long> Ids)throws HRSException  {
        return commentRepository.deleteAllByIdIn(Ids);
    }

    @Override
    public int deleteAllByTenant_Id(long tenantId)throws HRSException  {
        return commentRepository.deleteAllByTenant_Id(tenantId);
    }

    @Override
    public int deleteAllByTenant_IdIn(List<Long> tenantIds)throws HRSException  {
        return commentRepository.deleteAllByTenant_IdIn(tenantIds);
    }

    @Override
    public int deleteAllByLandlord_Id(long landlordId)throws HRSException  {
        return commentRepository.deleteAllByLandlord_Id(landlordId);
    }

    @Override
    public int deleteAllByLandlord_IdIn(List<Long> landlordIds)throws HRSException  {
        return commentRepository.deleteAllByLandlord_IdIn(landlordIds);
    }

    @Override
    public int deleteAllByHouse_Id(long houseId)throws HRSException  {
        return commentRepository.deleteAllByHouse_Id(houseId);
    }

    @Override
    public int deleteAllByHouse_IdIn(List<Long> houseIds)throws HRSException   {
        return commentRepository.deleteAllByHouse_IdIn(houseIds);
    }
    @Override
    public int deleteAllByContract_Id(long contractId)throws HRSException {

        return commentRepository.deleteAllByContract_Id(contractId);
    }

    @Override
    public int deleteAllByContract_IdIn(List<Long> contractIds)throws HRSException  {
        return commentRepository.deleteAllByContract_IdIn(contractIds);
    }


}

