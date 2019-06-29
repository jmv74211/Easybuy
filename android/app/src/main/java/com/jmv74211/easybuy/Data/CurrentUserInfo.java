package com.jmv74211.easybuy.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.jmv74211.easybuy.POJO.User;


/*
 *  Author: @jmv74211
 *  Date: 06/2019
 *  Class to load information about current user. That information is saved locally through shared preferences
 *  , written when user login, deleted when user logout, and loaded when method is called.
 * */

public class CurrentUserInfo {

    private static User user;

    private static CurrentUserInfo instance;

    private static Context context;

    public static final String SHARED_PREFS = "userInfoSharedPref";
    public static final String USERNAME_PREF_KEY = "username";
    public static final String EMAIL_PREF_KEY = "email";
    public static final String NAME_PREF_KEY = "name";
    public static final String FIRSTNAME_PREF_KEY = "firstName";
    public static final String SECONDNAME_PREF_KEY = "secondName";

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private CurrentUserInfo() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static CurrentUserInfo getInstance(Context context) {

        CurrentUserInfo.context = context;

        if (instance == null)
            instance = new CurrentUserInfo();

        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public User getCurrentUserInfo() {

       if(user == null)
           loadData();

        return this.user;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setCurrentUserInfo(User user) {
        this.user = user;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void writeData(User user) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME_PREF_KEY, user.getUsername());
        editor.putString(EMAIL_PREF_KEY, user.getEmail());
        editor.putString(NAME_PREF_KEY, user.getName());
        editor.putString(FIRSTNAME_PREF_KEY, user.getFirstName());
        editor.putString(SECONDNAME_PREF_KEY, user.getSecondName());

        editor.apply();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void loadData() {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);

        String username = sharedPreferences.getString(USERNAME_PREF_KEY, "");
        String email = sharedPreferences.getString(EMAIL_PREF_KEY, "");
        String name = sharedPreferences.getString(NAME_PREF_KEY, "");
        String firstName = sharedPreferences.getString(FIRSTNAME_PREF_KEY, "");
        String birthday = sharedPreferences.getString(SECONDNAME_PREF_KEY, "");

        User user = new User(username, email, name, firstName, birthday);

        this.user = user;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME_PREF_KEY,"");
        editor.putString(EMAIL_PREF_KEY, "");
        editor.putString(NAME_PREF_KEY, "");
        editor.putString(FIRSTNAME_PREF_KEY, "");
        editor.putString(SECONDNAME_PREF_KEY, "");

        editor.apply();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void clear(){
        deleteData();
        this.user = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
