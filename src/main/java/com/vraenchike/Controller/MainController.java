package com.vraenchike.Controller;

import com.vraenchike.Model.*;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Services.EntityServises.UserService;
import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Alexeev on 06-Apr-15.
 */
@Controller
@RequestMapping("/")
public class MainController {

    /*
    *Просто выдадим страницу и передадим 1 параметр(смотреть web/WEB-INF/pages/index.jsp)
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String mainPageDefault(ModelMap modelMap)
    {
        modelMap.addAttribute("text","helloWorld");

        return "index";
    }

    /**
     * МОжно передавать параметры вот так
     *try http://localhost:8080/pagenumber/123
     */
    @RequestMapping(value = "/pagenumber/{pageno}", method = RequestMethod.GET)
    public String mainPage(ModelMap modelMap,@PathVariable(value = "pageno") String pageno)
    {


        modelMap.addAttribute("text",pageno);


        User u = new User();
        u.setUser_name("Albert");
        u.setLastPhotoView(new Date());
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setLogin("asd");
        userLoginInfo.setPass("asdqwe");


        u.setUserLoginInfo(userLoginInfo);
        userLoginInfo.setUser(u);

        try {
            DAOFactory.getInstance().getUserDAO().addUser(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "index";
    }



    /*
    * Можно передать GET запросом
    * Перемножим 2 числа
    * http://localhost:8080//api/sumtwonumber?number1=13&number2=10
    *
    * Интересно! @ResponseBody - говорит что мы вернем текст(данные) а не html страницу
    * то что надо для апи!
     */
    @RequestMapping(value = "/api/sumtwonumber",method = RequestMethod.GET)
    @ResponseBody
    String getAllMeasure(@RequestParam("number1")String number,@RequestParam("number2")String number2) {
        int val =0;
        try {
            val = Integer.parseInt(number) * Integer.parseInt(number2);
        }catch (Exception e){
            return "bad numbers";
        }
        return val+"";//toString
    }


    @RequestMapping(value = "/api/save-test-model",method = RequestMethod.GET)
    @ResponseBody
    String saveTestModel(@RequestParam("url") String url) throws SQLException {
        Photo p =new Photo();
        p.setUrl(url);

       DAOFactory.getInstance().getPhotoDAO().addPhoto(p);
        // JSONObject jsonObject = new JSONObject()
        return "saved!";
    }

    public static void main(String[] args) throws SQLException {
        UserService us = new UserService();
        //Photo p = new Photo("betka.com",0,1);
     //   DAOFactory.getInstance().getPhotoDAO().addPhoto(p);
       // us.AddFavoritePhoto(3,"betka.com12");
        us.RemoveFavoritePhoto(3,"betka.com1");

        // Collection allUsers = DAOFactory.getInstance().getUserDAO().getAllUsers();
      //  for(Object usr : allUsers){
        //    System.out.println(((User)usr));



        }

    }


