package com.nekhan.iot.event.processor.constants;

/**
 * Error keys to be sent in message segment of errorDetails response.
 */
public interface LocalizationKey {

    /**
     * The following parameter is missing.
     */
    String MISSING_MANDATORY_PARAMETERS = "missing.mandatory.parameters";

    /**
     * The following device id is incorrect.
     */
    String INVALID_DEVICE_ID_PATTERN = "invalid.device.id.pattern";

    /**
     * The following event type is incorrect.
     */
    String INVALID_EVENT_TYPE_PATTERN = "invalid.event.type.pattern";


}

