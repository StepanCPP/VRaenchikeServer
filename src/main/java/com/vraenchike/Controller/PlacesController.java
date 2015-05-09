package com.vraenchike.Controller;

import com.vraenchike.Exception.PlaceNotExist;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Place;
import com.vraenchike.Services.ApiUtil.ApiError;
import com.vraenchike.Services.ApiUtil.Status;
import com.vraenchike.Services.EntityServises.PlaceService;
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

/**
 * Created by Alexeev on 04-May-15.
 */
@Controller
@RequestMapping("/api/place")
public class PlacesController {
    PlaceService placeService = new PlaceService();

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    String add(@RequestParam("lng") Double lng,
               @RequestParam("lat") Double lat,
               @RequestParam("radius") Integer radius,
               @RequestParam("name") String name) throws SQLException, JSONException {
        Status status = new Status();
        status.setCode(0);
        try {
            Place place = placeService.AddPlace(lng, lat, radius, name);
            status.setMessage(place.toJSONObject().toString());
        } catch (UserNotAuth userNotAuth) {
            ApiError error = ApiError.USER_NOT_AUTH;
            status.setCode(error.getCode());
            status.setMessage(error.toString());
            return status.toJSONObject().toString();
        }

        return status.toJSONObject().toString();
    }
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    String update(@RequestParam("id") Integer id,
                @RequestParam("lng") Double lng,
               @RequestParam("lat") Double lat,
               @RequestParam("radius") Integer radius,
               @RequestParam("name") String name) throws SQLException, JSONException {
        Status status = new Status();
        status.setCode(0);
        try {
            Place place = placeService.updatePlace(id, lat, lng, radius, name);
            status.setMessage(place.toJSONObject().toString());
        } catch (UserNotAuth userNotAuth) {
            return ApiError.USER_NOT_AUTH
                    .toStatus().toJSONObject().toString();
        } catch (PlaceNotExist placeNotExist) {
            return ApiError.PLACE_NOT_FOUND
                    .toStatus().toJSONObject().toString();
        }

        return status.toJSONObject().toString();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    String all() throws JSONException, SQLException {
        Status status = new Status();
        status.setCode(0);
        try {

            JSONArray array = placeService.GetAllPlaces();


            status.setMessage(array.toString());
        } catch (UserNotAuth userNotAuth) {
            return ApiError.USER_NOT_AUTH
                    .toStatus().toJSONObject().toString();
        }


        return status.toJSONObject().toString();
    }
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    String remove(@RequestParam("id") Integer id) throws JSONException, SQLException {
        Status status = new Status();
        status.setCode(0);

        try {
            Place place = placeService.RemovePlace(id);
            status.setMessage(place.toJSONObject().toString());
        } catch (UserNotAuth userNotAuth) {
            return ApiError.USER_NOT_AUTH
                    .toStatus().toJSONObject().toString();
        } catch (PlaceNotExist placeNotExist) {
            return ApiError.PLACE_NOT_FOUND
                    .toStatus().toJSONObject().toString();
        }

        return status.toJSONObject().toString();
    }

}

