package com.acculytixs.mobile.wayuparty.application;

public class WayUPartyConstants {

   // public static final String BASE_URl = TEST_URL:

    public static final String TEST_URL = "http://13.127.110.185:8080";

    public static final String PRODUCTION_URL = "http://aws.wayuparty.com";


    public static final String LIMIT = "20";
    public static final String OFFSET = "0";


    public static final String WAYUPARTY_PREF = "WAYUPARTY_PREF";
    public static final String USER_LANGUAGE = "USER_LANGUAGE";
    public static final String USER_UUID = "USER_UUID";
    public static final String VENDOR_UUID = "VENDOR_UUID";
    public static final String SERVICE_UUID = "SERVICE_UUID";
    public static final String SAVE_USERNAME = "USER_NAME";
    public static final String SAVE_PASSWORD = "PASWWROD";
    public static final String SAVE_RATIO_ENABLED = "RATIO_ENABLED";
    public static final String SAVE_TIME_SLOT= "TIME_SLOT";
    public static final String SAVE_MOBILE_NUM ="MOBILE_NUM";
    public static final String SAVE_EMAIL_NUM="EMAIL";
    public static final String SAVE_VERIFICATION_UUID="VERIFICATION_UUID";



    public static final String URL_GETALLREGISTERED_RESTAURANTSLIST = "/ws/getAllregisteredRestaurantsList";
    public static final String URL_GETVEDORS_INFO = "/ws/getVendorInfo";
    public static final String URL_GETSERVICESLIST_BYVENDORUUID = "/ws/getServicesList";
    public static final String URL_GETSERVICECATEGORYLIST_BYSERVICEUUID = "/ws/getServiceCategoriesList";
    public static final String URL_GETSCATEGORY_SERVICELIST = "/ws/getCategoryServicesList";
    public static final String URL_LOGIN_REGISTERD_USER= "/ws/loginRegisteredUser";
    public static final String URL_GET_USER_CAARTLIST= "/rest/getCartList";
    public static final String URL_ADDTOCART= "/rest/addToCart";
    public static final String URL_REMOVE_CART_ITEM= "/rest/removeCartItem";
    public static final String URL_GET_USER_ORDER_LIST= "/rest/getUserOrdersList";
    public static final String URL_CANCEL_ORDER= "/rest/cancelOrder";
    public static final String URL_ORDERS="/rest/orders";
    public static final String URL_PLACE_ORDER="/rest/placeOrder";
    public static final String URL_UPLOAD_PROFILE_IMAGE="/rest/uploadProfileImage";
    public static final String URL_SAVE_USER_PROFILE="/rest/saveUserProfile";
    public static final String URL_GET_RESCHEDULE_DETAILS="/rest/getRescheduleDetails";
    public static final String URL_RESCHEDULE_ORDER="/rest/rescheduleOrder";

    //EVENTS
    public static final String URL_VENDOR_EVENTS="/ws/getVendorEvents";
    public static final String URL_EVENT_DETAILS="/ws/getEventDetails";
    public static final String URL_EVENT_TIME_SLOTS="/ws/getEventTimeSlots";
    public static final String URL_EVENT_CAATEGORIES="/ws/getEventCategories";
    public static final String URL_EVENT_TICKETS="/ws/getEventTickets";

    public static final String URL_PLACE_EVENT_ORDER="/rest/placeEventOrder";
    public static final String URL_TERMS_CONDITIONS="/ws/vendorTermsAndCondtions";
    public static final String URL_SIGN_UP="/ws/signupUser";
    public static final String URL_VERIFY_EMAIL="/ws/validateEmail";
    public static final String URL_RESET_PASSWORD="/ws/resetLoginUserPassword";

}
