package online.hotelmanagement.web.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {
	
	protected abstract String getHeaderTitle();

	/**
	 * Get base U.I web title
	 * @return headerTitle
	 */
	@ModelAttribute("headerTitle")
	public String headerTitle() {
		return getHeaderTitle();
	}
}