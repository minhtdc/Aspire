package com.example.aspire;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class android_2_func {
    AlertDialog dialog;
    Activity activity;

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public void showLoading(Context context) {

        activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dal_loading, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    public void closeLoading() {
        dialog.dismiss();
    }

    public static boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static boolean isName(String name) {

        if (name.equals("")) {
            return false;
        } else {
            Character c2 = new Character(' ');
            int nSpace = 0;
            for (int i = 0; i < name.length(); i++) {
                Character c1 = name.charAt(i);
                if (c1.equals(c2)) {
                    nSpace++;
                } else {
                    nSpace = 0;
                }

                if (nSpace == 2) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isUsername(String name) {

        if (name.equals("")) {
            return false;
        } else {
            Character c2 = new Character(' ');
            for (int i = 0; i < name.length(); i++) {
                Character c1 = name.charAt(i);
                if (c1.equals(c2)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static String getRandomColor() {
        Random rand = new Random();
        ArrayList<String> listColors = new ArrayList<String>();
        listColors.add("red");
        listColors.add("blue");
        listColors.add("green");
        listColors.add("cyan");
        listColors.add("magenta");
        listColors.add("yellow");
        listColors.add("lightgray");
        listColors.add("darkgray");
        listColors.add("lightgrey");
        listColors.add("darkgrey");
        listColors.add("aqua");
        listColors.add("fuchsia");
        listColors.add("lime");
        listColors.add("maroon");
        listColors.add("navy");
        listColors.add("olive");
        listColors.add("purple");
        listColors.add("silver");
        listColors.add("teal");


        return listColors.get(rand.nextInt(listColors.size() - 2));
    }

    public static String getImgAvatar() {
        Random rand = new Random();
        return String.format("user_%d", rand.nextInt(20));
    }

    public static int getFileImgByName(String name) {
        switch (name) {
            case "user_1":
                return R.drawable.user_1;
            case "user_2":
                return R.drawable.user_2;
            case "user_3":
                return R.drawable.user_3;
            case "user_4":
                return R.drawable.user_4;
            case "user_5":
                return R.drawable.user_5;
            case "user_6":
                return R.drawable.user_6;
            case "user_7":
                return R.drawable.user_7;
            case "user_8":
                return R.drawable.user_8;
            case "user_9":
                return R.drawable.user_9;
            case "user_10":
                return R.drawable.user_10;
            case "user_11":
                return R.drawable.user_11;
            case "user_12":
                return R.drawable.user_12;
            case "user_13":
                return R.drawable.user_13;
            case "user_14":
                return R.drawable.user_14;
            case "user_15":
                return R.drawable.user_15;
            case "user_16":
                return R.drawable.user_16;
            case "user_17":
                return R.drawable.user_17;
            case "user_18":
                return R.drawable.user_18;
            case "user_19":
                return R.drawable.user_19;
            case "user_20":
                return R.drawable.user_20;

            default:
                return 0;
        }
    }

    public static boolean isStrLike(String str_compare, String str_toCompare) {
        str_compare = str_compare.toLowerCase();
        str_toCompare = str_toCompare.toLowerCase();
        try {
            for (int i = 0; i < str_compare.length(); i++) {
                if (!Character.toString(str_compare.charAt(i))
                        .equals(
                                Character.toString(str_toCompare.charAt(i))
                        )
                ) {
                    return false;
                }
            }
        } catch (Exception ex) {
        }
        return true;
    }
}
