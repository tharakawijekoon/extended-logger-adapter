# extended-logger-adapter
Sample Logger adapter for logging events from publishers

## Build

Execute the following command to build the project.

```
mvn clean install
```

## Deploy

Copy and place the built JAR artifact from the <PROJECT_HOME>/target/extended-logger-adapter-1.0.0.jar to the <IS_HOME>/repository/components/dropins directory.

Restart/Start the Identity Server.

Configure the event publishers to use the extendedLogger as shown below.

<IS_HOME>/repository/deployment/server/eventpublishers/IsAnalytics-Publisher-wso2event-AuthenticationData.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<eventPublisher
  name="IsAnalytics-Publisher-wso2event-AuthenticationData"
  statistics="disable" trace="disable" xmlns="http://wso2.org/carbon/eventpublisher">
  <from streamName="org.wso2.is.analytics.stream.OverallAuthentication" version="1.0.0"/>
  <mapping customMapping="disable" type="json" />
  <to eventAdapterType="extendedLogger">
     <property name="uniqueId">overall_auth_data</property>
  </to>
</eventPublisher>
```
<IS_HOME>/repository/deployment/server/eventpublishers/IsAnalytics-Publisher-wso2event-SessionData.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<eventPublisher name="IsAnalytics-Publisher-wso2event-SessionData" statistics="disable" trace="disable" xmlns="http://wso2.org/carbon/eventpublisher">
  <from streamName="org.wso2.is.analytics.stream.OverallSession" version="1.0.0"/>
  <mapping customMapping="disable" type="json"/>
  <to eventAdapterType="extendedLogger">
     <property name="uniqueId">overall_session_data</property>
  </to>
</eventPublisher>
```

## Testing

Login to a service provider, the following logs would be seen in the wso2carbon.log file

```
[2021-03-17 13:36:35,726]  INFO {org.wso2.custom.logger.adapter.ExtendedLoggerEventAdapter} -  Unique ID: overall_auth_data, Event: {"event":{"metaData":{"tenantId":-1234},"payloadData":{"contextId":"f0974398-f270-454f-b824-9c3ce813da02","eventId":"cd7cbe2a-99cd-4b8c-9953-a6181adf2e59","eventType":"step","authenticationSuccess":false,"username":"admin","localUserName":"admin","userStoreDomain":"PRIMARY","tenantDomain":"carbon.super","remoteIp":"127.0.0.1","region":"NOT_AVAILABLE","inboundAuthType":"oidc","serviceProvider":"oidcdebugger","rememberMeEnabled":false,"forceAuthEnabled":false,"passiveAuthEnabled":false,"rolesCommaSeparated":"admin","authenticationStep":"1","identityProvider":"LOCAL","authStepSuccess":true,"stepAuthenticator":"NOT_AVAILABLE","isFirstLogin":false,"identityProviderType":"LOCAL","_timestamp":1615968395726}}}
[2021-03-17 13:36:35,739]  INFO {org.wso2.custom.logger.adapter.ExtendedLoggerEventAdapter} -  Unique ID: overall_session_data, Event: {"event":{"metaData":{"tenantId":-1234},"payloadData":{"sessionId":"28401988be8fd79341ffafcff8f77b3415498cee57991a40361bd801f090fbed","startTimestamp":1615968395737,"renewTimestamp":1615968395737,"terminationTimestamp":1615969295737,"action":1,"username":"admin","userstoreDomain":"PRIMARY","remoteIp":"127.0.0.1","region":"NOT_AVAILABLE","tenantDomain":"carbon.super","serviceProvider":"oidcdebugger","identityProviders":"LOCAL","rememberMeFlag":false,"userAgent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.192 Safari/537.36","_timestamp":1615968395739}}}
[2021-03-17 13:36:35,740]  INFO {org.wso2.custom.logger.adapter.ExtendedLoggerEventAdapter} -  Unique ID: overall_auth_data, Event: {"event":{"metaData":{"tenantId":-1234},"payloadData":{"contextId":"f0974398-f270-454f-b824-9c3ce813da02","eventId":"026f89a1-9e34-4d93-987d-d868aee91109","eventType":"overall","authenticationSuccess":true,"username":"admin","localUserName":"admin","userStoreDomain":"PRIMARY","tenantDomain":"carbon.super","remoteIp":"127.0.0.1","region":"NOT_AVAILABLE","inboundAuthType":"oidc","serviceProvider":"oidcdebugger","rememberMeEnabled":false,"forceAuthEnabled":false,"passiveAuthEnabled":false,"rolesCommaSeparated":"admin","authenticationStep":"1","identityProvider":"LOCAL","authStepSuccess":true,"stepAuthenticator":"BasicAuthenticator","isFirstLogin":true,"identityProviderType":"LOCAL","_timestamp":1615968395740}}}

```


