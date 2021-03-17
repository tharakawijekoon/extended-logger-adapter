package org.wso2.custom.logger.adapter.internal.ds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapterFactory;
import org.wso2.custom.logger.adapter.ExtendedLoggerEventAdapterFactory;

@Component(
        name = "output.Logger.AdapterService.component",
        immediate = true)
public class ExtendedLoggerEventAdapterServiceDS {
    private static final Log log = LogFactory.getLog(ExtendedLoggerEventAdapterServiceDS.class);

    @Activate
    protected void activate(ComponentContext context) {

        try {
            OutputEventAdapterFactory extendedLoggerEventAdapterFactory= new ExtendedLoggerEventAdapterFactory();
            context.getBundleContext().registerService(OutputEventAdapterFactory.class.getName(),
                    extendedLoggerEventAdapterFactory, null);
            if (log.isDebugEnabled()) {
                log.debug("Successfully deployed the output logger adapter service");
            }
        } catch (RuntimeException e) {
            log.error("Can not create the output logger adapter service ", e);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        log.debug("ExtendedLoggerEventAdapter bundle deactivated.");
    }
}
