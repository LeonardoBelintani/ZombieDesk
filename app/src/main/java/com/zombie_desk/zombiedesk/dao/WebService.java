package com.zombie_desk.zombiedesk.dao;

import com.zombie_desk.zombiedesk.model.User;

/**
 * Created by Lab. Desenvolvimento on 27/03/2017.
 */

public class WebService {
    static final String URL_BASE = "lexgalante.esy.es";
    public static String urlUserAcess(User user)
    {
        return "http://lexgalante.esy.es/zombiews/user/acess.php?username="+ user.getUsername() +"&password=" + user.getPassword();
    }


}
