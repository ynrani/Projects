package com.itap.security;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import com.itap.constant.ITAPConstants;
import com.itap.util.JDBCPreparedStatementSelect;

@Component
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator
{

	JDBCPreparedStatementSelect jd = null;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {

		String role = "", username_and_role = "";
		jd = new JDBCPreparedStatementSelect();
		try {
			username_and_role = jd.selectRecordsFromTable(username);
			StringTokenizer st = new StringTokenizer(username_and_role, "-");
			while (st.hasMoreTokens()) {
				username = st.nextToken();
				role = st.nextToken();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();

		if (StringUtils.isNotEmpty(role) && role.equals(ITAPConstants.ROLE_USER)) {
			gas.add(new SimpleGrantedAuthority(ITAPConstants.ROLE_USER));
		} else if (StringUtils.isNotEmpty(role) && role.equals(ITAPConstants.ROLE_ADMIN)) {
			gas.add(new SimpleGrantedAuthority(ITAPConstants.ROLE_ADMIN));
		} else if (StringUtils.isNotEmpty(role) && role.equals(ITAPConstants.ROLE_ENV_OWNR)) {
			gas.add(new SimpleGrantedAuthority(ITAPConstants.ROLE_ENV_OWNR));
		} else {
			gas.add(new SimpleGrantedAuthority(ITAPConstants.ROLE_INVALID));
		}

		return gas;
	}
}
