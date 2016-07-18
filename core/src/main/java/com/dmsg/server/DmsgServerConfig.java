package com.dmsg.server;

import com.dmsg.exception.ServerConfigException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * Created by cjl on 2016/7/15.
 */
public class DmsgServerConfig extends HashMap<String, Object> {

    public void setHost(String host) throws ServerConfigException {
        if (StringUtils.isEmpty(host)) {
            throw new ServerConfigException("set Host is null");
        }
        this.put("host", host);
    }
    public void setPort(int port) {
        this.put("port", port);
    }

    public void setBufferSize(int i) {

    }


}