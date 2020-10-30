package org.hzc.controller;


import org.hzc.entity.Apply;
import org.hzc.entity.Contract;
import org.hzc.entity.House;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.ApplyService;
import org.hzc.service.ContractService;
import org.hzc.service.HouseService;
import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/contract")
// 数据需要在会话中保存,注意，是当前控制器临时性存储
//@SessionAttributes("loggedUser")
public class ContractController extends BaseController {


    @Autowired
    ApplyService applyService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Autowired
    ContractService contractService;
    @GetMapping
    public String show1( HttpSession session) throws Exception {

        return  "contract";
    }
    @RequestMapping("/add")
    public String show2(Model model) throws Exception {
        return "contract_add";
    }
    @RequestMapping("/confirmContract")
    public String show3(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if (user.getRole().name().equals("TENANT")) {
            return "contract_confirm";
        }else{
            return  "index";
        }
    }




    @PostMapping("getContract")
    @ResponseBody
    public Contract completedContract  (Long contractId) throws Exception {
        return contractService.getById(contractId);
    }


    @PostMapping("renewal/addOne")
    @ResponseBody
    public void renewalAddOne  (
            Long contractId,
            String contractEndTime,
            HttpSession session) throws Exception {
       Contract  contract =contractService.getById(contractId);
        LocalDateTime EndTime = LocalDateTime.parse(contractEndTime);
        contract.setContractEndTime(EndTime);
       contract.setFlag("生效中");
       contractService.updateById(contractId,contract);

    }

    @GetMapping("/completedContract")
    public String completedContract  (
            Model model) throws HRSException {
        return "contract_completed";
    }

   @RequestMapping("/renewalContract")
    public String renewalContract  (Long contractId
            ,Model model,
            HttpSession session) throws HRSException {
        if(contractId!=null)
        {
            session.setAttribute("contractId",contractId);
        }

        System.out.println(contractId+"--------renewalContract");
        return "contract_renewal";
    }



    @PostMapping("/confirmContract")
    public String confirmContract  (
            Long contractId ,Model model , HttpSession session) throws Exception {
       Contract contract = contractService.getById(contractId);

        contract.setFlag("生效中");
        contract.setApproveFromTenant("已确认");
        contractService.updateById(contractId,contract);
        House house =  houseService.getById(contract.getHouse().getId());
        house.setFlag("已配租");
        houseService.updateById(house.getId(),house);
        return "contract_confirm";
    }

    @GetMapping("/SignContract")
    public String SignContract  (
            Long applyId
            , HttpSession session) throws HRSException {
        session.setAttribute("applyId",applyId);
        return "contract_add";
    }

    @PostMapping("/add/addOne")
    public String addOne(Long applyId,Long contractTenantId,Long contractLandlordId,Long contractHouseId ,String contractEndTime, String contractContent) throws Exception {
        Contract contract = new Contract();
        User tenant = new User();
        tenant.setId(contractTenantId);
        contract.setTenant(tenant);
        User landlord = new User();
        landlord.setId(contractLandlordId);
        contract.setLandlord(landlord);
        House house = new House();
        house.setId(contractHouseId);
        contract.setHouse(house);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time2 =  LocalDateTime.now().format(df);
        LocalDateTime localDateTime = LocalDateTime.parse(time2,df);
        contract.setContractCreateTime(localDateTime);//从本地获取当前时间
        //String 转 LocalDateTime
        LocalDateTime EndTime = LocalDateTime.parse(contractEndTime);
        contract.setContractEndTime(EndTime);
        contract.setContractContent(contractContent);
        contract.setApproveFromLandlord("已确认");
        contract.setApproveFromTenant("未确认");
        contract.setFlag("未生效");
        Apply apply = applyService.getById(applyId);
        apply.setFlag("已接受");
        contract.setApply(apply);
        applyService.updateById(applyId,apply);
        contractService.create(contract);
        return "index";
    }
    @PostMapping("/getAllContractPageByUserId")
    @ResponseBody
    public List<Contract> getAllContractPageByUserId (Integer pageNumber, Integer pageSize, HttpSession session) throws HRSException {
        User user = (User)  session.getAttribute("user");
        if(user.getRole().name().equals("LANDLORD"))
        {
            return contractService.getAllEffectingContract(user,pageNumber,pageSize).getContent();
        }  else if(user.getRole().name().equals("TENANT"))
        {
            return contractService.getAllEffectingContract(user,pageNumber,pageSize).getContent();
        }else{
            return  null;
        }

    }
    @PostMapping("/getContractTotalPageByUserId")
    @ResponseBody
    public  Integer getContractTotalPageByUserId( Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User user = (User)  session.getAttribute("user");
        if(user.getRole().name().equals("LANDLORD"))
        {
            return contractService.getAllEffectingContract(user,pageNumber,pageSize).getTotalPages();
        }  else if(user.getRole().name().equals("TENANT"))
        {
            return contractService.getAllEffectingContract(user,pageNumber,pageSize).getTotalPages();
        }else{
            return  null;
        }
    }
    @PostMapping("/getContractTotalElementsByUserId")
    @ResponseBody
    public Long getContractTotalElementsByLandlordId(Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User user = (User)  session.getAttribute("user");
        if(user.getRole().name().equals("LANDLORD"))
        {
            return contractService.getAllEffectingContract(user,pageNumber,pageSize).getTotalElements();
        } else if(user.getRole().name().equals("TENANT"))
        {
            return contractService.getAllEffectingContract(user,pageNumber,pageSize).getTotalElements();
        }else{
            return  null;
        }
    }



    @PostMapping("/getAllContractPageByTenantId")
    @ResponseBody
    public List<Contract> getContract (Integer pageNumber, Integer pageSize, HttpSession session) throws HRSException {
      User tenant = (User)  session.getAttribute("user");
      if(tenant.getRole().name().equals("TENANT"))
      {
          return contractService.getAllPageByTenantId(tenant.getId(),pageNumber,pageSize).getContent();
      }else {
          return null;
      }
    }
    @PostMapping("/getContractTotalPageByTenantId")
    @ResponseBody
    public  Integer getContractTotalPageByTenantId( Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User tenant = (User)  session.getAttribute("user");
        if(tenant.getRole().name().equals("TENANT"))
        {
            return contractService.getAllPageByTenantId(tenant.getId(),pageNumber,pageSize).getTotalPages();
        }else {
            return null;
        }
    }
    @PostMapping("/getContractTotalElementsByTenantId")
    @ResponseBody
    public Long getContractTotalElementsByTenantId(Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User tenant = (User)  session.getAttribute("user");
        if(tenant.getRole().name().equals("TENANT"))
        {
            return contractService.getAllPageByTenantId(tenant.getId(),pageNumber,pageSize).getTotalElements();
        }else {
            return null;
        }
    }



    @PostMapping("/getAllCompletedContractByUserId")
    @ResponseBody
    public  List<Contract> getAllCompletedContractByTenantId(Integer pageNumber, Integer pageSize, HttpSession session) throws Exception {
        User user = (User)  session.getAttribute("user");
        System.out.println("user_----"+user);
        if(user.getRole().name().equals("TENANT"))
        {
            List<Contract>  contractList =  contractService.getAllCompletedContractPageByTenantId(user,pageNumber,pageSize).getContent();
return contractList;
        }
        else if(user.getRole().name().equals("LANDLORD"))
        {
            return contractService.getAllCompletedContractPageByTenantId(user,pageNumber,pageSize).getContent();
        }
            return  null;
    }

    @PostMapping("/getCompletedContractTotalPageByUserId")
    @ResponseBody
    public  Integer getCompletedContractTotalPageByTenantId( Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User user = (User)  session.getAttribute("user");
        if(user.getRole().name().equals("TENANT"))
        {
            return contractService.getAllCompletedContractPageByTenantId(user,pageNumber,pageSize).getTotalPages();
        }
        else if(user.getRole().name().equals("LANDLORD"))
        {
            return contractService.getAllCompletedContractPageByTenantId(user,pageNumber,pageSize).getTotalPages();
        }
        else {
            return null;
        }
    }
    @PostMapping("/getCompletedContractTotalElementsByUserId")
    @ResponseBody
    public Long getCompletedContractTotalElementsByTenantId(Integer pageNumber, Integer pageSize,HttpSession session) throws Exception {
        User user = (User)  session.getAttribute("user");
        if(user.getRole().name().equals("TENANT"))
        {
            return contractService.getAllCompletedContractPageByTenantId(user,pageNumber,pageSize).getTotalElements();
        }
        else if(user.getRole().name().equals("LANDLORD"))
        {
            return contractService.getAllCompletedContractPageByTenantId(user,pageNumber,pageSize).getTotalElements();
        }
        else {
            return null;
        }
    }



//
//    @PostMapping("/getAllCompletedContract")
//    @ResponseBody
//    public  List<Contract> getAllCompletedContract(Integer pageNumber, Integer pageSize, HttpSession session) throws Exception {
//
//        User tenant = (User)  session.getAttribute("user");
//        if(tenant.getRole().name().equals("TENANT"))
//        {
//            List<Contract>  contractList2 = new ArrayList<>();
//            List<Contract>  contractList =  contractService.getAllApprovedPageByTenantId(tenant.getId(),1,999999).getContent();//这么写会租房超过999999有bug，但没人会租这么多房吧
//            contractList.forEach(contract -> {
//               if (contract.getContractEndTime().isBefore(LocalDateTime.now())) //如果现在时间大于合同结束时间，合同结束
//               {
//                   contract.setFlag("已完成");
//                   contractService.updateById(contract.getId(),contract);
//                   contractList2.add(contract);//获得已完成的合同
//               }
//            });
//
//return  contractList2;
//        }else {
//            return null;
//        }
//    }




}