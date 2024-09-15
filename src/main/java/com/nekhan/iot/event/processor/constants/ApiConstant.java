package com.nekhan.iot.event.processor.constants;

/**
 * This class contains constants used across the API.
 * <p>
 * It provides a centralized location for all constant values, making it easier to manage and maintain.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * String apiUrl = ApiConstant.BASE_URL;
 * }
 * </pre>
 * </p>
 *
 * @author Neha Khan
 * @version 1.0
 */
public final class ApiConstant {

    /**
     * Version of the API.
     */
    public static final String EVENT_VERSION = "/v1";

    /**
     * The base URL for the EventProcessorController API.
     */
    public static final String PATH_EVENTS = EVENT_VERSION + "/events";

    /**
     * Event entity table name in database.
     */
    public static final String EVENT_ENTITY_TABLE_NAME = "iot_event";

    /**
     * UnderScore.
     */
    public static final String UNDERSCORE = "_";

    /**
     * ID of the event entity.
     */
    public static final String ID = "id";

    /**
     * Event entity sequence name.
     */
    public static final String EVENT_ENTITY_SEQUENCE_NAME = EVENT_ENTITY_TABLE_NAME + UNDERSCORE + ID
            + UNDERSCORE + "seq";

    /**
     * Contains description of events.
     */
    public static final String EVENT_DATA_TABLE_NAME = "iot_event_data";

    /**
     * Event entity sequence name.
     */
    public static final String EVENT_DATA_SEQUENCE_NAME = EVENT_DATA_TABLE_NAME + UNDERSCORE + ID
            + UNDERSCORE + "seq";

    /**
     * Device Id description of the iot device.
     */
    public static final String DEVICE_ID_DESCRIPTION = "Device Id of the iot device. Maximum chars = 20";

    /**
     * DeviceId validation expression.
     */
    public static final String DEVICE_ID_REGEXP = "^[A-Za-z0-9]{1,20}";

    /**
     * Event Type description of the iot device.
     */
    public static final String EVENT_TYPE_DESCRIPTION = "Event Type of the iot device. Maximum chars = 20";

    /**
     * Event validator expression.
     */
    public static final String EVENT_TYPE_REGEXP = "^[A-Za-z]{1,20}";

    /**
     * Time at which data was recieved.
     */
    public static final String EVENT_TIMESTAMP_DESCRIPTION = "Time at which data is recieved.";

    /**
     * Temperature of IOT device.
     */
    public static final String EVENT_TEMPERATURE_DESCRIPTION = "iot device temperature in terms of celsius.";

    /**
     * Humidity of IOT device.
     */
    public static final String EVENT_HUMIDITY_DESCRIPTION = "iot device humidity.";

    /**
     * Battery Level of IOT device.
     */
    public static final String BATTERY_LEVEL_DESCRIPTION = "iot device battery level in terms of percentage.";

    /**
     * Summary of store event API.
     */
    public static final String SUMMARY_STORE_EVENT_API = "API to store incoming events.";

    /**
     * System user.
     */
    public static final String SYSTEM_USER = "system";

    /**
     * Configuration to set max number of requests per minute.
     */
    public static final String MAX_NUMBER_OF_REQUESTS_PER_MINUTE = "max.number.of.requests.per.minute";

    private ApiConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
