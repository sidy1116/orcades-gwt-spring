package net.orcades.gwt.spring.demo.client;

import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;
import net.customware.gwt.presenter.client.place.PlaceManager;
import net.orcades.gwt.spring.demo.client.mvp.ZozoPresenter;
import net.orcades.gwt.spring.demo.client.mvp.ZozoView;

import com.google.inject.Singleton;

public class ELNSearchModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(PlaceManager.class).in(Singleton.class);
		bind(ZozoPresenter.class).asEagerSingleton();
		bindDisplay(ZozoPresenter.Display.class, ZozoView.class);
	}

}