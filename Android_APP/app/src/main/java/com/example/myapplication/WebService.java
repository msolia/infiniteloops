package com.example.myapplication;

import com.example.pojo.Model;
import com.example.pojo.Pojo;
import com.example.pojo.UserModel;

import org.ksoap2.transport.HttpTransportSE;

import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;



/**
 * Created by madsoliy on 1/22/2015.
 */

public class WebService {

    String URL = "http://10.132.162.76:3000/service/users/";

    public boolean softLogin(String us,String pass)
    {

        ObjectMapper mapper = new ObjectMapper();
        URL url;
        try {

           url = new URL(URL+us);

            Model pojo = mapper.readValue(url, Model.class);

            if(pojo.getResult()[0].getUsername().equals(us) && pojo.getResult()[0].getPassword().equals(pass))
            {
                return true;
            }

        } catch (Exception e) {

            return false;
        }

        return false;
    }
}
