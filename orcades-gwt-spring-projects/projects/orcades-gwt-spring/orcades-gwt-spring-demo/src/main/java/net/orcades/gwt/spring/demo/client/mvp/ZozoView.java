package net.orcades.gwt.spring.demo.client.mvp;

import net.orcades.gwt.spring.demo.client.ui.MyUI;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ZozoView extends Composite implements ZozoPresenter.Display {

	
	private TextBox naine;
	
	private Button buttonSend;
	
	private PushButton login;
	
	private PushButton showList;

	
	private MyUI myUI;
	
	@Inject
	public ZozoView(MyUI myUI) {
		final FlowPanel panel = new FlowPanel();
		this.myUI = myUI;
		initWidget(panel);
		naine = new TextBox();
		naine.setText("<3");
		buttonSend = new Button("send now");
		login = new PushButton("login");
		showList = new PushButton("show list");
		panel.add(naine);
		panel.add(buttonSend);
		panel.add(login);
		panel.add(showList);
		panel.add(myUI);
	}
	
	
	public HasValue<String> loving() {
		return naine;
	}


	public Widget asWidget() {
		return this;
	}


	public void startProcessing() {
		
	}


	public void stopProcessing() {
		
	}


	public HasClickHandlers getSend() {
		return buttonSend;
	}


	public HasClickHandlers getLogin() {
		return login;
	}


	public HasClickHandlers getShowList() {
		return showList;
	}


	

}
