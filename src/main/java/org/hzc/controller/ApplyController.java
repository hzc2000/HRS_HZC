package org.hzc.controller;


import org.hzc.entity.Apply;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.ApplyService;
import org.hzc.service.HouseService;
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
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/apply")
// 数据需要在会话中保存,注意，是当前控制器临时性存储
//@SessionAttributes("loggedUser")
public class ApplyController extends BaseController {


    @Autowired
    ApplyService applyService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @GetMapping
    public String show1(Model model)throws HRSException{
        return "apply";
    }
    @GetMapping("/add")
    public String show2(Model model)throws HRSException{
        return "apply_add";
    }


    @GetMapping("/add/addOne")
    public String addOne  (
              Long houseId
            , String leaveMessage
            , BigDecimal offerPrice
            , Long tenantId
            , Model model) throws HRSException {
        Apply apply = new Apply();
        apply.setLandlord(houseService.getById(houseId).getLandlord());
        apply.setLeaveMessage(leaveMessage);
        apply.setOfferPrice(offerPrice);
        apply.setTenant(userService.getById(tenantId));
        apply.setHouse(houseService.getById(houseId));
        LocalDateTime date = LocalDateTime.now();
        apply.setCreateTime(date);
        apply.setFlag("未接受");
        applyService.create(apply);
        return "index";
    }

    @PostMapping("/getApply")
    @ResponseBody
    public Apply getApply(Long applyId) throws HRSException {
        return applyService.getById(applyId) ;
    }

    @PostMapping("/getAllByPageByLandlordId")
    @ResponseBody
    public List<Apply> getAllByPageByLandlordId(Integer pageNumber, Integer pageSize, HttpSession session) throws HRSException {
        List<Apply> list = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        list = applyService.queryGetPageBylandlordId(user.getId(),pageNumber,pageSize).getContent();
        return list;
    }
    @PostMapping("/getTotalPageByPageByLandlordId")
    @ResponseBody
    public  Integer getTotalPageByPageByLandlordId( Integer pageNumber, Integer pageSize,HttpSession session) throws HRSException {
        User user = (User) session.getAttribute("user");
        return applyService.queryGetPageBylandlordId(user.getId(),pageNumber,pageSize).getTotalPages();
    }
    @PostMapping("/getTotalSizeByPageByLandlordId")
    @ResponseBody
    public long getTotalSizeByPageByLandlordId(Integer pageNumber, Integer pageSize,HttpSession session)throws HRSException{
        User user = (User) session.getAttribute("user");
        return applyService.queryGetPageBylandlordId(user.getId(),pageNumber,pageSize).getTotalElements();
    }

}