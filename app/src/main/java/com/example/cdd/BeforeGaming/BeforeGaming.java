package com.example.cdd.BeforeGaming;

import java.util.HashMap;

public class BeforeGaming {

    private static HashMap<String,String> user_name_TO_password=new HashMap<>();

    public BeforeGaming()
    {

    }

    public HashMap<String,String> get_map()
    {
        return user_name_TO_password;
    }

    public boolean is_in(String UserName,String Password)
    {
        if(user_name_TO_password.containsKey(UserName))
            return user_name_TO_password.get(UserName).equals(Password);
        else
            return false;
    }
}

