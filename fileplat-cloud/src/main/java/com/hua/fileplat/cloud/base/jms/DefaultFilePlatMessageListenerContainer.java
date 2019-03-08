package com.hua.fileplat.cloud.base.jms;

import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class DefaultFilePlatMessageListenerContainer extends DefaultMessageListenerContainer {
    public DefaultFilePlatMessageListenerContainer() {
        setSessionTransacted(true);
    }
}
