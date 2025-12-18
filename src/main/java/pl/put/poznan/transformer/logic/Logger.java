package pl.put.poznan.transformer.logic;

import org.slf4j.LoggerFactory;

/**
 * Logger wrapper for SQC application.
 * Provides logging with support for DEBUG, INFO, WARN and ERROR levels.
 *
 * <p>Example usage</p>
 * <pre>
 *     Logger logger = new Logger(MyClass.class);
 *     logger.info("Processing scenario: {}", scenarioTitle);
 *     logger.debug("Step count: {}", count);
 *     logger.warn("Missing actor in step");
 *     logger.error("Failed to parse JSON", exception);
 * </pre>
 *
 * @author Łukasz Bielaszewski
 * @version 0.1.0
 */

public class Logger {
    private org.slf4j.Logger logger;
    private String className;

    /**
     * Creates a new Logger instance for the specified class.
     *
     * @param clazz -> the class that will use the logger
     */
    public Logger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
        this.className = clazz.getSimpleName();
    }

    /**
     * Logs an informational message.
     *
     * @param message -> the message to log
     */
    public void info(String message) {
        logger.info(message);
    }

    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void debug(String message, Object... args) {
        logger.debug(message, args);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Object... args) {
        logger.error(message, args);
    }

    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public void entering(String methodName) {
        logger.debug("Entering {}.{}", className, methodName);
    }

    public void exiting(String methodName) {
        logger.debug("Exiting {}.{}", className, methodName);
    }
}
