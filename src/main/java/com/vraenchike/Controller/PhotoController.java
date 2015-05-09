package com.vraenchike.Controller;

import com.vraenchike.Exception.PhotoAlreadyAddedeException;
import com.vraenchike.Exception.PhotoAlreadyDisliked;
import com.vraenchike.Exception.PhotoAlreadyLiked;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Photo;
import com.vraenchike.Model.User;
import com.vraenchike.Services.ApiUtil.ApiError;
import com.vraenchike.Services.ApiUtil.Status;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Services.EntityServises.PhotoService;
import com.vraenchike.Services.EntityServises.UserService;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Alexeev on 03-May-15.
 */
@Controller
@RequestMapping("/api/photo")
public class PhotoController {
    UserService userService = new UserService();
    PhotoService photoService = new PhotoService();


    @RequestMapping(value = "/like", method = RequestMethod.GET)
    @ResponseBody
    String likePhoto(@RequestParam("url") String url,
                     @RequestParam("ApiType") String apiType,
                     @RequestParam("idApi") String idApi,
                     HttpServletRequest request) throws SQLException, JSONException {

        Status status = new Status();
        status.setCode(0);


        Session session = HibernateUtil.getSessionFactory().openSession();
        String credential = "";
        boolean isuser = true;
        User curUser = UserService.getCurrentUser(session);
        if (curUser == null) {
            credential = request.getRemoteAddr();
            isuser = false;
        } else {
            credential = curUser.getUserLoginInfo().getLogin();
        }
        try {
            Photo photo = photoService.LikePhoto(url,idApi,apiType, credential, isuser);
            status.setMessage(photo.toJSONObject().toString());

        } catch (PhotoAlreadyLiked photoAlreadyLiked) {

            ApiError error = ApiError.THIS_PHOTO_IS_ALREADY_LIKED;

            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        } catch (UserNotAuth userNotAuth) {
            ApiError error = ApiError.USER_NOT_AUTH;
            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        } catch (PhotoAlreadyAddedeException e) {
            return ApiError.PHOTO_IS_FAVORED_ALREADY.toStatus().toJSONObject().toString();
        }
        session.close();
        return status.toJSONObject().toString();
    }


    @RequestMapping(value = "/dislike", method = RequestMethod.GET)
    @ResponseBody
    String dislikePhoto(@RequestParam("url") String url,
                        @RequestParam("ApiType") String apiType,
                        @RequestParam("idApi") String idApi,
                        HttpServletRequest request) throws SQLException, JSONException {

        Status status = new Status();
        status.setCode(0);


        Session session = HibernateUtil.getSessionFactory().openSession();
        String credential = "";
        boolean isuser = true;
        User curUser = UserService.getCurrentUser(session);
        if (curUser == null) {
            credential = request.getRemoteAddr();
            isuser = false;
        } else {
            credential = curUser.getUserLoginInfo().getLogin();
        }
        try {
            Photo photo = photoService.DislikePhoto(url,idApi,apiType,credential, isuser);
            status.setMessage(photo.toJSONObject().toString());

        } catch (PhotoAlreadyDisliked photoAlreadyDisliked) {
            ApiError error = ApiError.THIS_PHOTO_IS_ALREADY_DISLIKED;
            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        } catch (UserNotAuth userNotAuth) {
            ApiError error = ApiError.USER_NOT_AUTH;
            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        } catch (PhotoAlreadyAddedeException e) {
            return ApiError.PHOTO_IS_FAVORED_ALREADY.toStatus().toJSONObject().toString();
        }
        session.close();
        return status.toJSONObject().toString();
    }

}
