package org.hzc.controller;

import org.hzc.entity.Community;
import org.hzc.entity.House;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.CommunityService;
import org.hzc.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/house")
@Controller
public class HouseController extends BaseController {
    @Autowired
    HouseService houseService;

    @Autowired
    CommunityService communityService;


    @GetMapping
    public String show(Model model, HttpSession session)throws HRSException{
        User user = (User) session.getAttribute("user");
        System.out.println(user.toString());
        if (user != null){
            if (user.getRole().name().equalsIgnoreCase("LANDLORD")){
                return "house";
            } else {
                return "index";
            }
        } else {
            session.setAttribute("ex",new HRSException("用户为空,请检查你是否登录"));
            return "my_error";
        }
    }

    @GetMapping("/house_edit/{id}")
    public String s2(Model model, HttpSession session,@PathVariable long id)throws HRSException{
        User user = (User) session.getAttribute("user");
        if (user.getRole().name().equalsIgnoreCase("LANDLORD")){
            model.addAttribute ("houseId",id);
        }
        return "house_edit";
    }


    @GetMapping("/house_add")
    public String s3(Model model, HttpSession session)throws HRSException{
        User user = (User) session.getAttribute("user");
        if (user.getRole().name().equalsIgnoreCase("LANDLORD")){
            return "/house_add";
        } else {
            return "index";
        }
    }


    @GetMapping("/rentThis")
    public  String rentThis(
            Long houseId
            ,HttpSession session)throws HRSException{
       session.setAttribute("houseId",houseId);
        return "apply_add";
    }

    @GetMapping("/house_edit/getAllCommunity")
    @ResponseBody
    public  List<Community> getAllCommunity()throws HRSException{
        return  communityService.getAll();
    }



    @PostMapping("/house_edit/getOneHouse")
    @ResponseBody
    public House getOneHouse(Integer id)throws HRSException{
        House house = houseService.getById(id);
        return house ;
    }
    @PostMapping("/house_edit/getOneNullHouse")
    @ResponseBody
    public House getOneNullHouse()throws HRSException{
        House house = new House();
        return house ;
    }

    @PostMapping("/house_edit/submit")
    @ResponseBody
    public  Integer submit(@RequestBody(required = true) House house)throws HRSException{
        House i = houseService.create(house);
        return 1 ;
    }



    @PostMapping("/getAllHouseByPage")
    @ResponseBody
    public  List<House> getAllHouseByPage(Integer pageNumber, Integer pageSize)throws HRSException{
        List<House> list = new ArrayList<>();
        System.out.println(pageNumber+","+pageSize);
        list = houseService.get(pageNumber,pageSize).getContent();
        return list;
    }

    @PostMapping("/getTotalPageHouseByPage")
    @ResponseBody
    public  Integer getTotalPageHouseByPage( Integer pageNumber, Integer pageSize)throws HRSException{
        
        System.out.println(pageNumber+","+pageSize);
        return houseService.get(pageNumber,pageSize).getTotalPages();
    }
    @PostMapping("/getTotalSizeHouseByPage")
    @ResponseBody
    public long getTotalSizeHouseByPage(Integer pageNumber, Integer pageSize)throws HRSException{

        System.out.println(pageNumber+","+pageSize);
        return houseService.get(pageNumber,pageSize).getTotalElements();
    }

    @PostMapping("/getAllHousePageByLandlordId")
    @ResponseBody
    public  List<House> getAllHousePageByLandlordId(Integer pageNumber, Integer pageSize, HttpSession session)throws HRSException{
        List<House> list = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        list = houseService.getAllByLandlordId(user.getId(),pageNumber,pageSize).getContent();
        List<House> list2 = new ArrayList<>();
        list.forEach(house -> {
            if(house.getFlag().equals("待出租") || house.getFlag().equals("未发布"))
            {
                list2.add(house);
            }
        });
        return list2;
    }
    @PostMapping("/getHouseTotalPagesByLandlordId")
    @ResponseBody
    public  Integer getHouseTotalPagesByLandlordId( Integer pageNumber, Integer pageSize,HttpSession session)throws HRSException{
        User user = (User) session.getAttribute("user");
        System.out.println(pageNumber+","+pageSize);

        List<House> list =  houseService.getAllByLandlordId(user.getId(),pageNumber,9999).getContent();
        List<House> list2 = new ArrayList<>();
        list.forEach(house -> {
            if(house.getFlag().equals("待出租") || house.getFlag().equals("未发布"))
            {
                list2.add(house);
            }
        });
        int TotalPages = 0;
        if(list2.size()!=0 || pageSize !=0)
        {
            TotalPages =list2.size()/pageSize;
        }
        if(list2.size()%pageSize!=0)
        {
            TotalPages= TotalPages+1;
        }


        return  TotalPages;
    }
    @PostMapping("/getHouseTotalElementsByLandlordId")
    @ResponseBody
    public int getHouseTotalElementsByLandlordId(Integer pageNumber, Integer pageSize,HttpSession session)throws HRSException{
        User user = (User) session.getAttribute("user");
        System.out.println(pageNumber+","+pageSize);
        List<House> list =  houseService.getAllByLandlordId(user.getId(),pageNumber,9999).getContent();
        List<House> list2 = new ArrayList<>();
        list.forEach(house -> {
            if(house.getFlag().equals("待出租") || house.getFlag().equals("未发布"))
            {
                list2.add(house);
            }
        });
        int TotalElements = 0;
        TotalElements= list2.size();
        return  TotalElements;
    }





    @PostMapping("/CreateAllHouse")
    @ResponseBody
    public  Integer CreateAllHouse(List<House> houses)throws HRSException{
        List<House> list = new ArrayList<>();
        list = houseService.createAll(houses);
        list.forEach(str-> System.out.println(str.toString()));
        return  list.size();
    }



    @PostMapping("/SaveOneHouse")
    @ResponseBody
    public  Integer SaveOneHouse(House house)throws HRSException{

        House house1 =   houseService.create(house);

        return  1;
    }

    @PostMapping("/house_add/addOneHouse")
    @ResponseBody
    public  Integer addOneHouse( String flag,
                                 String houseIntroduction,
                                 String unitType,
                                 BigDecimal monthlyRent,
                                 Double area,
                                 String houseAddress,
                                 Long communityId,
                                 String title,
                                 HttpSession session) throws HRSException {
        User landlord = (User) session.getAttribute("user");
        House house = new House();
        house.setFlag(flag);
        house.setHouseIntroduction(houseIntroduction);
        house.setUnitType(unitType);
        house.setMonthlyRent(monthlyRent);
        house.setArea(area);
        house.setHouseAddress(houseAddress);
        Community community = new Community();
        community.setId(communityId);
        house.setCommunity(community);
        house.setTitle(title);
        house.setLandlord(landlord);
        house.setImage("/HRS/images/house1.png");
        houseService.create(house);
        return 1;
    }

    @PostMapping("/deleteAllHouseByIdIn")
    @ResponseBody
    public  Integer deleteAllHouseByIdIn(List<Long> Ids)throws HRSException{

        Integer i = houseService.deleteAllByIdIn(Ids);
        return i;
    }


    @PostMapping("/deleteOneHouseById")
    @ResponseBody
    public  Integer deleteOneHouseById(long id)throws HRSException{
        System.out.println(id);
        houseService.deleteById(id);
        return 0;
    }

}