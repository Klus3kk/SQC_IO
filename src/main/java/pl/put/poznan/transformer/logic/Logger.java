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



}
