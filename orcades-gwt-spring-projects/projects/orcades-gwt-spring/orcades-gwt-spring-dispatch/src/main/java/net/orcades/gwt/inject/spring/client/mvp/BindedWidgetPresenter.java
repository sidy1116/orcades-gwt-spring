package net.orcades.gwt.inject.spring.client.mvp;

import com.google.inject.Inject;

import net.customware.gwt.presenter.client.BasicPresenter;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.orcades.gwt.inject.spring.shared.event.ApplicationStartEvent;
import net.orcades.gwt.inject.spring.shared.event.ApplicationStartEventHandler;

public abstract class BindedWidgetPresenter<D extends WidgetDisplay> extends
		BasicPresenter<D> {

	@Inject
	public BindedWidgetPresenter(D display, EventBus eventBus) {
		super(display, eventBus);

		eventBus.addHandler(ApplicationStartEvent.TYPE,
				new ApplicationStartEventHandler() {

					public void onApplicationStart(ApplicationStartEvent event) {
						bind();
					}
				});
	}

}
