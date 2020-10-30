package org.hzc.service;

import lombok.Data;
import org.hzc.entity.Community;
import org.hzc.exception.HRSException;
import org.hzc.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Data
@Transactional
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public Community create(Community community)throws HRSException  {
        return communityRepository.save(community);
    }

    @Override
    public void createAll(List<Community> communities)throws HRSException  {
        communities.forEach(community->communityRepository.save(community));
    }

    @Override
    public Community updateById(long id, Community community)throws HRSException  {
        community.setId(id);
        return communityRepository.save(community);
    }





    @Override
    public Community getById(long id)throws HRSException  {
        return communityRepository.getById(id);
    }

    public List<Community> getAll()throws HRSException  {
        return communityRepository.findAll();
    }


    public List<Community> getAllByIdIn(List<Long> Ids)throws HRSException  {
        return communityRepository.getAllByIdIn(Ids);
    }


    @Override
    public List<Community> getAllById(long id)throws HRSException  {
        return communityRepository.getAllById(id);
    }

    @Override
    public Community getByNameLikeIgnoreCase(String name)throws HRSException  {
        return communityRepository.getByNameLikeIgnoreCase(name);
    }

    @Override
    public List<Community> getAllByNameLikeIgnoreCase(String names)throws HRSException  {
        return communityRepository.getAllByNameLikeIgnoreCase(names);
    }

    @Override
    public Community getByAddressLikeIgnoreCase(String address)throws HRSException  {
        return communityRepository.getByAddressLikeIgnoreCase(address);
    }

    @Override
    public List<Community> getAllByAddressLikeIgnoreCase(String address)throws HRSException  {
        return communityRepository.getAllByAddressLikeIgnoreCase(address);
    }



    @Override
    public int deleteAllById(long Id)throws HRSException {
      return   communityRepository.deleteAllById(Id);
    }

    @Override
    public int deleteAllByIdIn(List<Long> Ids)throws HRSException  {
        return communityRepository.deleteAllByIdIn(Ids);
    }



}
