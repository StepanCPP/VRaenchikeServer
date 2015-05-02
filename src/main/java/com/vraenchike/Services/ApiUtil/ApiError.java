package com.vraenchike.Services.ApiUtil;

/**
 * Created by Artyom on 02.05.2015.
 */
public enum ApiError {


    USER_NOT_FOUND, PHOTO_NOT_FOUND, PLACE_NOT_FOUND,
    NO_DB_CONNECTION, THIS_PLACE_IS_ALREADY_INSIST,
    THIS_USER_IS_ALREADY_INSIST,THIS_PHOTO_IS_ALREADY_INSIST,
    USER_IS_BANNED_ALREADY, PHOTO_IS_BANNED_ALREADY,
    PLACE_IS_BANNED_ALREADY, USER_IS_FAVORED_ALREADY, PHOTO_IS_FAVORED_ALREADY,
    PLACE_IS_FAVORED_ALREADY;

    public String getCode(){
        String toReturn = null;
        switch (this) {

            case USER_NOT_FOUND:{
                toReturn = "01";
                break;
            }
            case PHOTO_NOT_FOUND:{
                toReturn = "02";
                break;
            }
            case PLACE_NOT_FOUND:{
                toReturn = "03";
                break;
            }
            case THIS_PLACE_IS_ALREADY_INSIST:{
                toReturn = "05";
                break;
            }
            case NO_DB_CONNECTION:{
                toReturn = "04";
                break;
            }
            case THIS_PHOTO_IS_ALREADY_INSIST:{
                toReturn = "06";
                break;
            }
            case THIS_USER_IS_ALREADY_INSIST:{
                toReturn = "07";
                break;
            }
            case USER_IS_BANNED_ALREADY:{
                toReturn = "08";
                break;
            }
            case PHOTO_IS_BANNED_ALREADY:{
                toReturn = "09";
                break;
            }
            case PLACE_IS_BANNED_ALREADY:{
                toReturn = "10";
                break;
            }
            case PLACE_IS_FAVORED_ALREADY:{
                toReturn = "11";
                break;
            }
            case USER_IS_FAVORED_ALREADY:{
                toReturn = "12";
                break;
            }
            case PHOTO_IS_FAVORED_ALREADY:{
                toReturn = "13";
                break;
            }



        }
        return toReturn;
    }


}
