package com.ppdai.framework.raptor.refer;

import com.ppdai.framework.raptor.refer.client.Client;
import com.ppdai.framework.raptor.rpc.Request;
import com.ppdai.framework.raptor.rpc.Response;
import com.ppdai.framework.raptor.rpc.URL;

public class DefaultRefer<T> implements Refer<T> {
    private Class<T> interfaceClass;
    private Client client;
    private URL serviceUrl;

    public DefaultRefer(Class<T> interfaceClass, Client client, URL serviceUrl) {
        this.interfaceClass = interfaceClass;
        this.client = client;
        this.serviceUrl = serviceUrl.createCopy();
    }

    @Override
    public Class<T> getInterface() {
        return interfaceClass;
    }

    @Override
    public URL getServiceUrl() {
        return serviceUrl;
    }

    @Override
    public Response call(Request request) {
        return client.sendRequest(request, this.serviceUrl);
    }
}
