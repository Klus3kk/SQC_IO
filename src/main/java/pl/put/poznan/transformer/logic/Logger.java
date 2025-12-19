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
     * @param clazz the class that will use the logger
     */
    public Logger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
        this.className = clazz.getSimpleName();
    }

    /**
     * Logs an informational message.
     *
     * @param message the message to log
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Logs informational message with parameters.
     * @param message the message temple
     * @param args the arguments to substitute into the message
     */
    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    /**
     * Logs a debug message.
     * @param message message the message to log
     */
    public void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs a debug message with parameters.
     *
     * @param message the message template
     * @param args the arguments to substitute into the message
     */
    public void debug(String message, Object... args) {
        logger.debug(message, args);
    }

    /**
     * Long a warning message.
     *
     * @param message the message to log
     */
    public void warn(String message) {
        logger.warn(message);
    }

    /**
     * Logs a warning message with parameters.
     *
     * @param message the message template
     * @param args the arguments to subsitute into the message
     */
    public void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    /**
     * Logs an error message.
     *
     * @param message the message to log
     */
    public void error(String message) {
        logger.error(message);
    }

    /**
     * Logs an error message with parameters.
     *
     * @param message the message template
     * @param args the argumnets to substitute into the message
     */
    public void error(String message, Object... args) {
        logger.error(message, args);
    }

    /**
     * Logs an error message with parameters.
     *
     * @param message the message to log
     * @param throwable the exception that occured
     */
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    /**
     * Logs entry into a method (debug level).
     *
     * @param methodName the name of the method being entered
     */
    public void entering(String methodName) {
        logger.debug("Entering {}.{}", className, methodName);
    }

    /**
     * Logs exit from a method (debug level).
     *
     * @param methodName the name of the method being exited
     */
    public void exiting(String methodName) {
        logger.debug("Exiting {}.{}", className, methodName);
    }

    /**
     * Logs exit from a method with a return value (debug level).
     *
     * @param methodName the name of the method being exited
     * @param result result the return value
     */
    public void exiting(String methodName, Object result) {
        logger.debug("Exiting {}.{} with result: {}", className, methodName, result);
    }
}
