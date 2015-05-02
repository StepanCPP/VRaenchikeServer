package com.vraenchike.Services;

/**
 * VRaenchike created by invalidplane on 28/04/2015.
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vraenchike.Services.DAO.UserDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vraenchike.Services.DAO.UserDAO;
import com.vraenchike.Model.SecurityUserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private UserDAO userDao;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        //TODO
        com.vraenchike.Model.User user =null;//= userDao.findByUserName(username);
        List<GrantedAuthority> authorities=null; // buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.vraenchike.Model.User user to org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.vraenchike.Model.User user, List<GrantedAuthority> authorities) {
      //  return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
        return null;
    }

    private List<GrantedAuthority> buildUserAuthority(Set<SecurityUserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        // Build user's authorities
        for (SecurityUserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }
  
}