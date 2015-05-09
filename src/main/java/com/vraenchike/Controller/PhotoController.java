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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.mapping.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * photo - idApi/ApiType
     * @param photos_json_array json array of photot
     * @return info about photos such as (likes photo,is favorite photo?)
     * @throws JSONException
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    String info(@RequestParam("photos") String photos_json_array) throws JSONException {
        Status status = new Status();
        status.setCode(0);
        HashMap<String,ArrayList<String>> ApiTypeToIdApi = new HashMap<>();
        JSONObject outJsonResult = new JSONObject();

        try {
            JSONArray array = new JSONArray(photos_json_array);
            for(int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);

                String idApi = jsonObject.getString("idApi");
                String ApiType = jsonObject.getString("ApiType");
                ArrayList<String> strings = ApiTypeToIdApi.get(ApiType);
                if(strings==null){
                    ApiTypeToIdApi.put(ApiType,new ArrayList<String>());
                    strings = ApiTypeToIdApi.get(ApiType);
                }
                strings.add(idApi);
            }
            Set<Map.Entry<String, ArrayList<String>>> entries = ApiTypeToIdApi.entrySet();
            Session session = HibernateUtil.getSessionFactory().openSession();
            ArrayList<Photo> outResult = new ArrayList<>();
            for(Map.Entry<String,ArrayList<String>> element : entries){
                Query query = session.createQuery("from Photo where idApiServices in (:ids) and apiServiceType=:type and likes>0");
                query.setParameter("type",element.getKey());
                query.setParameterList("ids",element.getValue());
                List list = query.list();

                //Add to json out result

                outJsonResult.put("likedPhotos",photoTominimizeInfoArray(list));
            }
            User currentUser = UserService.getCurrentUser(session);
            outJsonResult.put("favoritePhotos",
                    photoTominimizeInfoArray(currentUser.getFavoritePhoto(0,1000)));
            status.setMessage(outJsonResult.toString());
            if(session.isOpen())
                session.close();

        } catch (JSONException e) {
            return ApiError.BAD_JSON_REQUEST.toStatus().toJSONObject().toString();
        } catch (SQLException e) {
            return ApiError.USER_NOT_AUTH.toStatus().toJSONObject().toString();
        }
        return status.toJSONObject().toString();
    }
    private JSONArray photoTominimizeInfoArray(Collection list) throws JSONException {
        JSONArray Photos = new JSONArray();
        for(Object o : list){
            Photo p = (Photo) o;
            if(p.getIdApiServices().isEmpty())
                continue;
            JSONObject jsonPhoto = new JSONObject();
            jsonPhoto.put("idApi",p.getIdApiServices());
            jsonPhoto.put("ApiType",p.getApiServiceType());
            jsonPhoto.put("id",p.getId());
            jsonPhoto.put("likes",p.getLikes());
            Photos.put(jsonPhoto);
        }
        return Photos;
    }


}
