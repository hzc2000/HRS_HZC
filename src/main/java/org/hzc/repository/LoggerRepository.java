package org.hzc.repository;


import org.hzc.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {
    Integer deleteAllById(long id);



}
