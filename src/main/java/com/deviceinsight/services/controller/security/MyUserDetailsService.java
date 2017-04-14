package com.deviceinsight.services.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import com.deviceinsight.services.model.dao.UserDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO UserDAO;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        com.deviceinsight.services.model.User user = null;
        if (username.contains("@")) {
            user = UserDAO.findByEmail(username); // NC-29-11-2015 ... findbyusername
        } else {
            user = UserDAO.findByUsername(username); // NC-29-11-2015 ... findbyusername
        }
        /*
		 * UserRole j = new UserRole(); j.setRole("fabrice");
		 * j.setUserRoleId(1);
		 * 
		 * Set<UserRole> r = new TreeSet<>(); r.add(j);
		 * 
		 */
        Set<UserRole> ur = new HashSet<>();
        UserRole o = new UserRole();
        o.setRole("ADMIN");
        o.setUser(user);
        o.setUserRoleId(1);
        ur.add(o);
        ////////////	List<GrantedAuthority> authorities = buildUserAuthority(ur);
        // List<GrantedAuthority> authorities = buildUserAuthority(r);


        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();


        if (user.getUserRole().equals("1")) {
            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(
                    "ROLE_ADMIN");
            authorities.add(adminAuthority);
        } else {

            SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(
                    "ROLE_USER");
            authorities.add(userAuthority);
        }


        // if (details.getRole().equals("user"))
        //authorities.add(userAuthority);
        //else if (details.getRole().equals("admin")) {


        //}

        // List<GrantedAuthority> authorities =
        // buildUserAuthority(user.getUserRole());
        List<GrantedAuthority> r = new ArrayList<GrantedAuthority>(authorities);
        return buildUserForAuthentication(user, r);

    }

    // Converts org.springframework.samples.portfolio.persistence.model.User
    // user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.deviceinsight.services.model.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}