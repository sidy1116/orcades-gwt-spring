package net.agilent.gwt.spring.demo;

import static org.easymock.EasyMock.*;
import org.junit.Test;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;


public class SimpleSampleTestCase {

	@Test
	public void testFirstMock() {
		final HasClickHandlers clickHandlers = createMock(HasClickHandlers.class);
		expect(clickHandlers.addClickHandler(isA(ClickHandler.class))).andReturn(null);
		
		replay(clickHandlers);

		clickHandlers.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		verify(clickHandlers);
		
	}
	
}
