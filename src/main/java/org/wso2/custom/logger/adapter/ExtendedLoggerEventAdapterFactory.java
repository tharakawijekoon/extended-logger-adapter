package org.wso2.custom.logger.adapter;

import org.wso2.carbon.event.output.adapter.core.*;
import org.wso2.custom.logger.adapter.internal.util.ExtendedLoggerEventAdapterConstants;

import java.util.*;

public class ExtendedLoggerEventAdapterFactory extends OutputEventAdapterFactory {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("org.wso2.carbon.event.output.adapter.logger.i18n.Resources", Locale.getDefault());

    @Override
    public String getType() {
        return ExtendedLoggerEventAdapterConstants.ADAPTER_TYPE_LOGGER;
    }

    @Override
    public List<String> getSupportedMessageFormats() {
        List<String> supportedMessageFormats = new ArrayList<String>();
        supportedMessageFormats.add(MessageType.TEXT);
        supportedMessageFormats.add(MessageType.XML);
        supportedMessageFormats.add(MessageType.JSON);
        return supportedMessageFormats;
    }

    @Override
    public List<Property> getStaticPropertyList() {
        return null;
    }

    @Override
    public List<Property> getDynamicPropertyList() {
        List<Property> dynamicPropertyList = new ArrayList<Property>();

        // set unique identification
        Property uniqueIdentifierProperty = new Property(ExtendedLoggerEventAdapterConstants.ADAPTER_MESSAGE_UNIQUE_ID);
        uniqueIdentifierProperty.setDisplayName(
                resourceBundle.getString(ExtendedLoggerEventAdapterConstants.ADAPTER_MESSAGE_UNIQUE_ID));
        uniqueIdentifierProperty.setHint(resourceBundle.getString(ExtendedLoggerEventAdapterConstants.ADAPTER_MESSAGE_UNIQUE_ID_HINT));

        dynamicPropertyList.add(uniqueIdentifierProperty);

        return dynamicPropertyList;
    }

    @Override
    public String getUsageTips() {
        return null;
    }

    @Override
    public OutputEventAdapter createEventAdapter(OutputEventAdapterConfiguration eventAdapterConfiguration, Map<String, String> globalProperties) {
        return new ExtendedLoggerEventAdapter(eventAdapterConfiguration, globalProperties);
    }
}
