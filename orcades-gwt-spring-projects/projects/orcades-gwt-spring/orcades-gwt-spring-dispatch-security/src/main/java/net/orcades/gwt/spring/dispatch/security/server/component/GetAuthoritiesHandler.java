package net.orcades.gwt.spring.dispatch.security.server.component;

import java.util.ArrayList;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.orcades.gwt.spring.dispatch.security.shared.rpc.GetAuthoritiesAction;
import net.orcades.gwt.spring.dispatch.security.shared.rpc.GetAuthoritiesResult;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetAuthoritiesHandler implements
		ActionHandler<GetAuthoritiesAction, GetAuthoritiesResult> {

	public GetAuthoritiesResult execute(GetAuthoritiesAction action,
			ExecutionContext context) throws ActionException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		ArrayList<String> roles = new ArrayList<String>();
		if (auth != null) {
			for (GrantedAuthority authority : auth.getAuthorities()) {
				roles.add(authority.getAuthority());
			}
		}

		return new GetAuthoritiesResult(roles);
	}

	public Class<GetAuthoritiesAction> getActionType() {
		return GetAuthoritiesAction.class;
	}

	public void rollback(GetAuthoritiesAction action,
			GetAuthoritiesResult result, ExecutionContext context)
			throws ActionException {
		// TODO Auto-generated method stub

	}

}
