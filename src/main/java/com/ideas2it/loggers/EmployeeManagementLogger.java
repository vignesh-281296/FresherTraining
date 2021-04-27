package com.ideas2it.loggers;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * custom logger class
 * @author vignesh r
 */
public class EmployeeManagementLogger {

	private Logger logger;
	
	public EmployeeManagementLogger(Class<?> className) {
		logger = LogManager.getLogger(className);
	}
	
	/**
	 * It is used to call log info
	 * @param info
	 */
	public void logInfo(Object info) {
		logger.info(info);
	}
	
	/**
	 * It is used to call log error
	 * @param error
	 */
	public void logError(Object error) {
		logger.error(error);
	}
	
	/**
	 * It is used to call log warning
	 * @param warning
	 */
	public void logWarning(Object warning) {
		logger.warn(warning);
	}
	/**
	 * It is used to call log fatal
	 * @param fatal
	 */
	public void logFatal(Object fatal) {
		logger.fatal(fatal);
	}
    
	/**
	 * It is used to call log debug
	 * @param debug
	 */
	public void logDebug(Object debug) {
		logger.debug(debug);
	}
}
