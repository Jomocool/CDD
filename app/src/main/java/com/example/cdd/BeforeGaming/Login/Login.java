package com.example.cdd.BeforeGaming.Login;

import java.util.HashMap;

public class Login {

    private HashMap<String,Integer> m=new HashMap<>();

    public Login()
    {
        m.put("user66",123456);
    }

    public boolean is_in(String UserName,int Password)
    {
        if(m.containsKey(UserName))
            return m.get(UserName)==Password;
        else
            return false;
    }
}

