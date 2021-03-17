package org.wso2.custom.logger.adapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapter;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapterConfiguration;
import org.wso2.carbon.event.output.adapter.core.exception.OutputEventAdapterException;
import org.wso2.carbon.event.output.adapter.core.exception.TestConnectionNotSupportedException;
import org.wso2.custom.logger.adapter.internal.util.ExtendedLoggerEventAdapterConstants;

import java.util.Arrays;
import java.util.Map;

public class ExtendedLoggerEventAdapter implements OutputEventAdapter {

    private static final Log log = LogFactory.getLog(ExtendedLoggerEventAdapter.class);
    private OutputEventAdapterConfiguration eventAdapterConfiguration;
    private Map<String, String> globalProperties;

    public ExtendedLoggerEventAdapter(OutputEventAdapterConfiguration eventAdapterConfiguration, Map<String, String> globalProperties) {
        this.eventAdapterConfiguration = eventAdapterConfiguration;
        this.globalProperties = globalProperties;
    }


    @Override
    public void init() throws OutputEventAdapterException {
        //not required
    }

    @Override
    public void testConnect() throws TestConnectionNotSupportedException {
        throw new TestConnectionNotSupportedException("Test connection is not available");
    }

    @Override
    public void connect() {
        //not required
    }

    @Override
    public void publish(Object message, Map<String, String> dynamicProperties) {
        String uniqueIdentification = dynamicProperties.get(ExtendedLoggerEventAdapterConstants.ADAPTER_MESSAGE_UNIQUE_ID);
        if (uniqueIdentification == null || uniqueIdentification.trim().isEmpty()) {
            uniqueIdentification = eventAdapterConfiguration.getName();
        }

        if (message instanceof Object[]) {
            log.info("Unique ID: " + uniqueIdentification + ", Event: " + Arrays.deepToString((Object[]) message));
        } else {
            log.info("Unique ID: " + uniqueIdentification + ", Event: " + message);
        }
    }

    @Override
    public void disconnect() {
        //not required
    }

    @Override
    public void destroy() {
        //not required
    }

    @Override
    public boolean isPolled() {
        return false;
    }
}
