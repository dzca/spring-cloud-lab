package com.mfc.profile.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateTokenHandler implements Handler {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void validate() {
		logger.error("HANDLER CALLED!");
		System.out.println("HANDLER CALLED!");
	}

}
