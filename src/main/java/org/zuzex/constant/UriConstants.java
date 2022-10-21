package org.zuzex.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UriConstants {
    public static final String F_SLASH = "/";
    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String SHOPS = "/shops";
    public static final String SHOP_ID = "shopId";
    public static final String SHOP_ID_PATH = "/{" + SHOP_ID + "}";
    public static final String SHOP_PATH_V1 = API + V1 + SHOPS;
    public static final String CATEGORIES = "/categories";
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_ID_PATH = "/{" + CATEGORY_ID + "}";
    public static final String CATEGORY_PATH_V1 = API + V1 + CATEGORIES;
    public static final String PRODUCTS = "/products";
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_ID_PATH = "/{" + PRODUCT_ID + "}";
    public static final String ADD_PRODUCT_TO_SHOP = SHOPS + SHOP_ID_PATH + CATEGORIES + CATEGORY_ID_PATH;
    public static final String UPDATE_PRODUCT = PRODUCT_ID_PATH + SHOPS + SHOP_ID_PATH + CATEGORIES + CATEGORY_ID_PATH;
    public static final String PRODUCT_PATH_V1 = API + V1 + PRODUCTS;
    public static final String USERS = "/users";
    public static final String USER_ID = "userId";
    public static final String USER_ID_PATH = "/{" + USER_ID + "}";
    public static final String USER_PATH_V1 = API + V1 + USERS;
}
