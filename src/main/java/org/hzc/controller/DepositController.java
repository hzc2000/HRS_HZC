package org.hzc.controller;

import org.hzc.entity.Deposit;
import org.hzc.entity.Deposit;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.ApplyService;
import org.hzc.service.DepositService;
import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("/deposit")
@Controller
public class DepositController extends BaseController {

    @Autowired
    DepositService depositService;

    @Autowired
    UserService userService;
    @GetMapping
    public String show(HttpSession session) {
        User administrator = (User)session.getAttribute("user");
        if(administrator.getRole().name().equals("ADMINISTRATOR"))
        {
            return "deposit";
        }else{
            return "index";
        }
    }


    @GetMapping("/add")
    public String administrator_operation(HttpSession session) {
        User administrator = (User) session.getAttribute("user");
        if(administrator.getRole().name().equals("ADMINISTRATOR"))
        {
            return "deposit_add";
        }else
        {
            return  "index";
        }
    }

    @PostMapping("add/addOne")
    @ResponseBody
    public Long depositAddOne  (
            String tenantPhone,
            String landlordPhone,
            Double tenantDeposit,
            Double landlordDeposit,
            HttpSession session) throws Exception {
        User administrator = (User) session.getAttribute("user");
        Deposit deposit = new Deposit();
        deposit.setAdministrator(administrator); 
        deposit.setLandlord(userService.getByPhone(tenantPhone));
        deposit.setTenant(userService.getByPhone(landlordPhone));
        deposit.setTenantDeposit(BigDecimal.valueOf(tenantDeposit));
        deposit.setLandlordDeposit(BigDecimal.valueOf(landlordDeposit));
        depositService.create(deposit);
        return 1l;
    }
    @PostMapping("/getAllDepositPageByAdministratorId")
    @ResponseBody
    public List<Deposit> getAllDepositPageByAdministratorId (Integer pageNumber, Integer pageSize, HttpSession session) throws Exception {
        User administrator = (User)  session.getAttribute("user");
        if(administrator.getRole().name().equals("ADMINISTRATOR"))
        {
            return depositService.getAllDeposit(administrator,pageNumber,pageSize).getContent();
        }else {
            return null;
        }
    }
    @PostMapping("/getDepositTotalPageByAdministratorId")
    @ResponseBody
    public  Integer getDepositTotalPageByAdministratorId( Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User administrator = (User)  session.getAttribute("user");
        if(administrator.getRole().name().equals("ADMINISTRATOR"))
        {
            return depositService.getAllDeposit(administrator,pageNumber,pageSize).getTotalPages();
        }else {
            return null;
        }
    }
    @PostMapping("/getDepositTotalElementsByAdministratorId")
    @ResponseBody
    public Long getDepositTotalElementsByAdministratorId(Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User administrator = (User)  session.getAttribute("user");
        if(administrator.getRole().name().equals("ADMINISTRATOR"))
        {
            return depositService.getAllDeposit(administrator,pageNumber,pageSize).getTotalElements();
        }else {
            return null;
        }
    }
    


}