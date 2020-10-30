package org.hzc.service;


import lombok.Data;
import org.hibernate.Session;
import org.hzc.entity.Logger;
import org.hzc.entity.House;
import org.hzc.exception.HRSException;
import org.hzc.repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@Data
public class LoggerServiceImpl implements LoggerService   {
    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public void create(Logger logger) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        logger.setCreateTime(LocalDateTime.parse(LocalDateTime.now().format(df), df));//从本地获取当前时间
        System.out.println(logger.toString());

        try {

            loggerRepository.save(logger);

        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }



    @Override
    public Logger updateById(long id, Logger logger){
            logger.setId(id);
            return loggerRepository.save(logger);
        }
    @Override
    public Integer deleteAllById(long id){
        return loggerRepository.deleteAllById(id);
    }


}

