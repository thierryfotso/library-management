package com.application.library.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.library.entity.Member;
import com.application.library.service.MemberService;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Member user = memberService.getMemberByEmail(username);
		if (Optional.ofNullable(user).isEmpty()) {
			throw new UsernameNotFoundException("user: " + username + " not found");
		}
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
			grantedAuthorities.add(grantedAuthority);
		});
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true,
				true, true, grantedAuthorities);
	}

}
