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

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "it works!";
    }


    @ResponseBody
    @RequestMapping(value = {"/login/{username}"}, method = {RequestMethod.POST})
    // public String register(HttpServletRequest request, Model model) throws
    // Exception {
    public ResponseEntity loginUsername(@RequestBody UsernamePasspwdUpgradeRequest dto, @RequestParam MultiValueMap<String, String> allRequestParams, HttpServletRequest request,
                                        Model model) throws Exception {

        //    User user = new User();

        //////////
        System.out.println("---------------------------------------------------");
        System.out.println(dto.getUsername());
        System.out.println(dto.getPasswd());
        System.out.println("---------------------------------------------------");


        String email;// = allRequestParams.get("email").get(0);
        String username;// = allRequestParams.get("username").get(0);



    /*
    admin
    demo
     */

        username = "admin";

        String pw = "demo";//allRequestParams.get("password");


        username = dto.getUsername();
        pw = dto.getPasswd();


        String password_temporary_plain = pw;//password.get(0);

            /*    user.setUsername(username);

                String password = BCrypt.hashpw(password_temporary_plain, BCrypt.gensalt());

                user.setPassword(password);

                user.setEmail(email);

              //  userDAO.saveOrUpdate(user);

                user.setEnabled(true);

                userDAO.saveOrUpdate(user);
*/
        tmp = password_temporary_plain;

        /////////
        if(authenticateUserAndSetSession(userDAO.findByUsername(username), request)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }


        //  return "redirect:/assets/angular/#/app/extra-members-list";
//        return "1";
    }
    private boolean authenticateUserAndSetSession(com.deviceinsight.services.model.User user, HttpServletRequest request) throws Exception {

		/*
		 * String username = user.getUsername(); String password =
		 * user.getPassword(); UsernamePasswordAuthenticationToken token = new
		 * UsernamePasswordAuthenticationToken(username, password);
		 */

        // dao.authenticate(authentication)

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + request.getSession().getCreationTime());

        UserDetails details = pp.loadUserByUsername(user.getUsername());

        String username = user.getUsername();
        String password = tmp;
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password,
                details.getAuthorities());

        // generate session if one doesn't exist
        // request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        // AuthenticationManager authenticationManager = auth.build();

        // token.setAuthenticated(true);
        try {
            Authentication authenticatedUser = auth.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            token.isAuthenticated();
            return true;
        } catch(Exception e) {
            return false;
        }



    }


}