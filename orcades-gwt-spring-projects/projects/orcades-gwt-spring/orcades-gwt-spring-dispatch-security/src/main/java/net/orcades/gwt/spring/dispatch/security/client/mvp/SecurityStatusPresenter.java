package net.orcades.gwt.spring.dispatch.security.client.mvp;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.DisplayCallback;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.orcades.gwt.inject.spring.client.mvp.BindedWidgetPresenter;
import net.orcades.gwt.spring.dispatch.security.shared.event.AuthorizationRequiredEvent;
import net.orcades.gwt.spring.dispatch.security.shared.event.AutoritiesGrantedEvent;
import net.orcades.gwt.spring.dispatch.security.shared.event.AutoritiesGrantedEventEventHandler;
import net.orcades.gwt.spring.dispatch.security.shared.event.LogoutEvent;
import net.orcades.gwt.spring.dispatch.security.shared.event.LogoutEventHandler;
import net.orcades.gwt.spring.dispatch.security.shared.rpc.GetAuthoritiesAction;
import net.orcades.gwt.spring.dispatch.security.shared.rpc.GetAuthoritiesResult;
import net.orcades.gwt.spring.dispatch.security.shared.rpc.LogoutAction;
import net.orcades.gwt.spring.dispatch.security.shared.rpc.LogoutResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

public class SecurityStatusPresenter extends
		BindedWidgetPresenter<SecurityStatusPresenter.Display> {

	@Inject
	private DispatchAsync dispatch;

	@Inject
	private SpringSecurityHTMLIDs htmlID;

	private boolean authenticated;

	@Inject
	public SecurityStatusPresenter(Display display, EventBus eventBus) {
		super(display, eventBus);
	}

	public interface Display extends WidgetDisplay {

		public HasValue<String> getStatus();

		public HasClickHandlers getLogInOut();

		public void setEnabled(boolean b);

		public void setAuthorities(ArrayList<String> auths);
	}

	@Override
	public Place getPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onBind() {
		RootPanel.get(htmlID.getId()).add(display.asWidget());

		display.setEnabled(false);

		// Tries to retrieve the current authoritie (reload?)
		dispatch.execute(new GetAuthoritiesAction(),
				new DisplayCallback<GetAuthoritiesResult>(display) {

					@Override
					protected void handleFailure(Throwable e) {
						// TODO Auto-generated method stub
					}

					@Override
					protected void handleSuccess(GetAuthoritiesResult value) {
						eventBus.fireEvent(new AutoritiesGrantedEvent(value
								.getAuthorities()));
					}
				});

		display.getLogInOut().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				if (authenticated) {

					dispatch.execute(new LogoutAction(),
							new DisplayCallback<LogoutResult>(display) {

								@Override
								protected void handleFailure(Throwable e) {
									// TODO Auto-generated method stub

								}

								@Override
								protected void handleSuccess(LogoutResult value) {
									eventBus.fireEvent(new LogoutEvent(
											"logged out"));

								}
							});
				} else {
					eventBus.fireEvent(new AuthorizationRequiredEvent(
							"Login please"));
				}

			}
		});

		eventBus.addHandler(AutoritiesGrantedEvent.TYPE,
				new AutoritiesGrantedEventEventHandler() {

					public void onAuthoritiesGranted(
							AutoritiesGrantedEvent autoritiesGrantedEvent) {
						
						ArrayList<String> auths = autoritiesGrantedEvent
								.getAuthorities();
						display.setAuthorities(auths);
						if (auths.size() > 0) {
							display.setEnabled(true);
							authenticated = true;
						}
					}
				});

		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {

			public void onLogout(LogoutEvent logoutEvent) {
				display.setEnabled(false);
				display.getStatus().setValue(logoutEvent.getMessage());
				display.setAuthorities(null);
				authenticated = false;
			}
		});

	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub

	}

	public void refreshDisplay() {
		// TODO Auto-generated method stub

	}

	public void revealDisplay() {
		// TODO Auto-generated method stub

	}

}
