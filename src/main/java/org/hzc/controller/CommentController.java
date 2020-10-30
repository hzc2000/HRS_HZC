package org.hzc.controller;

import org.hzc.entity.Comment;
import org.hzc.entity.Contract;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.ApplyService;
import org.hzc.service.CommentService;
import org.hzc.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("/comment")
@Controller
public class CommentController extends BaseController {

    @Autowired
    CommentService commentService;

    @Autowired
    ContractService contractService;


    @GetMapping
    public String show(Model model) throws HRSException {
        return "comment";
    }

    @GetMapping("/add")
    public String show2(Model model) throws HRSException {
        return "comment_add";
    }
    @GetMapping("/writeComment")
    public String writeComment(Long contractId, HttpServletRequest req, HttpServletResponse resp,
                                       HttpSession session) throws HRSException {
        if(contractId!=null)
        {
            session.setAttribute("contractId",contractId);
        }

        System.out.println(contractId+"--------writeComment");
        return "comment_add";
    }
    @GetMapping("/browseComment")
    public String browseComment(Long houseId, HttpServletRequest req, HttpServletResponse resp,
                               HttpSession session) throws HRSException {
        if(houseId!=null)
        {
            session.setAttribute("houseId",houseId);
        }
        return "成功";
    }

    @PostMapping("/getAllComment")
    @ResponseBody
    public List<Comment> getAllComment (Integer pageNumber, Integer pageSize, HttpSession session) throws HRSException {
         Long  houseId = (Long) session.getAttribute("houseId");
             return commentService.getAllComment(houseId,pageNumber,pageSize).getContent();
    }
    @PostMapping("/getCommentTotalPages")
    @ResponseBody
    public  Integer getContractTotalPageByLandlordId( Integer pageNumber, Integer pageSize,HttpSession session) throws HRSException {
         Long  houseId = (Long) session.getAttribute("houseId");
             return commentService.getAllComment(houseId,pageNumber,pageSize).getTotalPages();
    }
    @PostMapping("/getCommentTotalElements")
    @ResponseBody
    public Long getContractTotalElementsByLandlordId(Integer pageNumber, Integer pageSize,HttpSession session) throws HRSException {
         Long  houseId = (Long) session.getAttribute("houseId");
             return commentService.getAllComment(houseId,pageNumber,pageSize).getTotalElements();
    }



    @PostMapping("/add/TenantAddOne")
    public void TenantAddOne(
       String  tenantContent,String houseContent,Long contractId, Model model)throws HRSException{

        try {
            Comment comment  = commentService.getByContract_Id(contractId);

          if(comment.getContract().getId().equals(contractId))
          {
              Contract contract = contractService.getById(contractId);
              //set Time
              DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
              String time2 =  LocalDateTime.now().format(df);
              LocalDateTime localDateTime = LocalDateTime.parse(time2,df);
              comment.setTenantContentCreateTime(localDateTime);
              comment.setHouseContentCreateTime(localDateTime);
              //set Content
              comment.setHouseContent(houseContent);
              comment.setTenantContent(tenantContent);
              //set Id
              comment.setHouse(contract.getHouse());
              comment.setTenant(contract.getTenant());
              comment.setLandlord(contract.getLandlord());
              comment.setContract(contract);
              System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
              commentService.updateById(comment.getId(),comment);
          }
        }catch (Exception e)
        {
            System.out.println("出现异常:"+e.getMessage());
            System.out.println("此评论还没有先被房东创建");
            Contract contract = contractService.getById(contractId);
            Comment comment = new Comment();
            //set Time
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time2 =  LocalDateTime.now().format(df);
            LocalDateTime localDateTime = LocalDateTime.parse(time2,df);
            comment.setTenantContentCreateTime(localDateTime);
            comment.setHouseContentCreateTime(localDateTime);
            //set Content
            comment.setHouseContent(houseContent);
            comment.setTenantContent(tenantContent);
            //set Id
            comment.setHouse(contract.getHouse());
            comment.setTenant(contract.getTenant());
            comment.setLandlord(contract.getLandlord());
            comment.setContract(contract);
            System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            commentService.create(comment);
        }


    }
    @PostMapping("/add/LandlordAddOne")
    public void LandlordAddOne(
            String  landlordContent,Long contractId, Model model)throws HRSException{
        try {
            Comment  comment  = commentService.getByContract_Id(contractId);
            if(comment.getContract().getId().equals(contractId))
            {
                System.out.println("contractId="+contractId);
                Contract contract = contractService.getById(contractId);
                comment.setHouse(contract.getHouse());
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String time2 =  LocalDateTime.now().format(df);
                LocalDateTime localDateTime = LocalDateTime.parse(time2,df);
                comment.setLandlordContentCreateTime(localDateTime);
                comment.setLandlordContent(landlordContent);
                comment.setTenant(contract.getTenant());
                comment.setLandlord(contract.getLandlord());
                comment.setContract(contract);
                System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                commentService.updateById(comment.getId(),comment);
            }
        }catch (Exception e)
        {
            System.out.println("出现异常:"+e.getMessage());
            System.out.println("此评论还没有先被租客创建");
            Contract contract = contractService.getById(contractId);
            Comment comment = new Comment();
            comment.setHouse(contract.getHouse());
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time2 =  LocalDateTime.now().format(df);
            LocalDateTime localDateTime = LocalDateTime.parse(time2,df);
            comment.setLandlordContentCreateTime(localDateTime);
            comment.setLandlordContent(landlordContent);
            comment.setTenant(contract.getTenant());
            comment.setLandlord(contract.getLandlord());
            comment.setContract(contract);
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            commentService.create(comment);
        }


    }


}