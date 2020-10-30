package org.hzc.service;

import org.hzc.entity.Contract;
import org.hzc.entity.Deposit;
import org.hzc.entity.House;
import org.hzc.exception.HRSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimerTaskService  {
    @Autowired
    ApplyService applyService;
    @Autowired
    DepositService depositService;


    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Autowired
    ContractService contractService;
    @Scheduled(cron="*/50 * * * * *")//每50秒执行一次
    public void run() {
        try {

            System.out.println("Executing plan task ");
            checkContractCompleted();//检查是否有已过期的合同
            System.out.println("Execute planned task completion");
        } catch (Exception e) {
            System.out.println("Failed to execute timed task!");
        }
    }

    public void checkContractCompleted() throws HRSException {

        //要执行的任务逻辑写在这里
        List<Contract>  contractList =  contractService.getAllEffectingContract();//获得所有生效中的合同

        contractList.forEach(contract -> {
            System.out.println(contract.toString());
            if (contract.getContractEndTime().isBefore(LocalDateTime.now())) //如果现在时间大于合同结束时间，合同结束
            {
                contract.setFlag("已完成");
                System.out.println("房屋："+contract.getHouse().getTitle()+"的合同已完成");

                House house = null;
                try {
                    house = houseService.getById(contract.getHouse().getId());
                } catch (HRSException e) {
                    e.printStackTrace();
                }
                house.setFlag("未发布");
                try {
                    houseService.updateById(house.getId(),house);
                } catch (HRSException e) {
                    e.printStackTrace();
                }


                try {
                    contractService.updateById(contract.getId(), contract);
                } catch (HRSException e) {
                    e.printStackTrace();
                }

            }
//            功能:每月从押金里扣钱(未完成  感觉写得不对有点问题)
//            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime contractEndTime =  contract.getContractEndTime();
//            BigDecimal offerPrice =  contract.getApply().getOfferPrice();//每月租金
//            Duration duration = Duration.between(contractEndTime,now);//计算时间差contractEndTime - now
//            Long day =  duration.toDays();
//            Long remainder = day % 30;//余数
//            long month =  (day / 30);//月数
//            Deposit  deposit = depositService.getByTenantId(contract.getTenant().getId());
//            BigDecimal bigDecimal;
//            bigDecimal =  deposit.getDeposit();//未完成，没时间完成这个功能了
//
        });

    }
}