package fr.benoitne.libraryweb.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import fr.benoitne.libraryweb.bean.UserBean;
import fr.benoitne.libraryweb.proxy.LibraryProxy;

@Service
public class SpringUserDetailsService implements UserDetailsService {

	@Autowired
	private LibraryProxy libraryProxy;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		UserBean userBean = libraryProxy.loadUserByUsername(userName);
		List<GrantedAuthority> roles = new ArrayList<>();

		if (userBean.getRole().equals("ADMIN")) {
			roles.add(new SimpleGrantedAuthority("ADMIN"));
		} else if (userBean.getRole().equals("USER")) {
			roles.add(new SimpleGrantedAuthority("USER"));
		}

		return new org.springframework.security.core.userdetails.User(userBean.getUserName(), userBean.getPassword(),
				true, true, true, true, roles);
	}

}
