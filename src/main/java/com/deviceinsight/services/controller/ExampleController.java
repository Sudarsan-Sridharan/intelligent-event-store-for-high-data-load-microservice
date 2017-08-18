package com.deviceinsight.services.controller;

import com.deviceinsight.services.controller.security.MyUserDetailsService;
import com.deviceinsight.services.controller.security.UsernamePasspwdUpgradeRequest;
import com.deviceinsight.services.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExampleController {
    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MyUserDetailsService pp;

    private String tmp;

    @RequestMapping("/secured")
    @ResponseBody
    public String securedContent() {
        return "whooow secured content!!";
    }

/*    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "it works!";
    }*/

    @ResponseBody
    @RequestMapping(value = {"/login/rest"}, method = {RequestMethod.POST})
    public ResponseEntity loginUsername(@RequestBody UsernamePasspwdUpgradeRequest dto, HttpServletRequest request) throws Exception {
        String pw = "demo";//allRequestParams.get("password");
        pw = dto.getPasswd();
        String password_temporary_plain = pw;//password.get(0);
        tmp = password_temporary_plain;
        if (authenticateUserAndSetSession(userDAO.findByUsername(dto.getUsername()), request)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean authenticateUserAndSetSession(com.deviceinsight.services.model.User user, HttpServletRequest request) throws Exception {
        UserDetails details = pp.loadUserByUsername(user.getUsername());
        String username = user.getUsername();
        String password = tmp;
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password,
                details.getAuthorities());
        token.setDetails(new WebAuthenticationDetails(request));
        try {
            Authentication authenticatedUser = auth.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            token.isAuthenticated();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
