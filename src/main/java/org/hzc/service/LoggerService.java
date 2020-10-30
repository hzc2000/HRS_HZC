package org.hzc.service;


import org.hzc.entity.Logger;


public interface LoggerService {

    void create(Logger logger) ;


    Logger updateById(long id, Logger logger);

    Integer deleteAllById(long id);

}
