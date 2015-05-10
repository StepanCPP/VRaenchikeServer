package com.vraenchike.Controller;

import com.vraenchike.Exception.PhotoAlreadyAddedeException;
import com.vraenchike.Exception.PhotoNotFoundException;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Photo;
import com.vraenchike.Services.ApiUtil.ApiError;
import com.vraenchike.Services.ApiUtil.Status;
import com.vraenchike.Services.EntityServises.PhotoService;
import com.vraenchike.Services.EntityServises.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Alexeev on 03-May-15.
 */
@Controller
@RequestMapping("/api/photo/favorite")
public class PhotoFavoriteController {
    UserService userService = new UserService();
    PhotoService photoService = new PhotoService();
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    String addPhoto(@RequestParam("url") String url,
                    @RequestParam("ApiType") String apiType,
                    @RequestParam("idApi") String idApi) throws SQLException, JSONException {
        Status status = new Status();
        status.setCode(0);
        try {
            Photo photo = userService.AddFavoritePhoto(url,idApi,apiType);
            status.setMessage(photo.toJSONObject().toString());

        } catch (UserNotAuth userNotAuth) {
           return ApiError.USER_NOT_AUTH.toStatus()
                   .toJSONObject().toString();
        } catch (PhotoAlreadyAddedeException e) {
          return  ApiError.PHOTO_FAVORITE_ALREADY.toStatus()
                    .toJSONObject().toString();
        }
        return status.toJSONObject().toString();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    String removePhoto(@RequestParam("id") Integer id) throws SQLException, JSONException {
        Status status = new Status();
        status.setCode(0);
        try {
            Photo photo = userService.RemoveFavoritePhoto(id);
            status.setMessage(photo.toJSONObject().toString());

        } catch (UserNotAuth userNotAuth) {
            ApiError error = ApiError.USER_NOT_AUTH;
            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        } catch (PhotoNotFoundException e) {
            ApiError error = ApiError.PHOTO_NOT_FOUND;
            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        }
        return status.toJSONObject().toString();
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    String allPhoto(@RequestParam("count") Integer count,
                    @RequestParam("offset") Integer offset) throws SQLException, JSONException {
        Status status = new Status();
        status.setCode(0);
        try {
            JSONArray array = photoService.GetFavoritePhotos(offset, count);
            status.setMessage(array.toString());
        } catch (UserNotAuth userNotAuth) {
            ApiError error = ApiError.USER_NOT_AUTH;
            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        }
        return status.toJSONObject().toString();
    }
}
