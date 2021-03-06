package com.ams.media;

import java.io.IOException;

public interface IMsgPublisher {
    public void publish(MediaMessage msg) throws IOException;

    public void addSubscriber(IMsgSubscriber subscriber);

    public void removeSubscriber(IMsgSubscriber subscriber);
    
    public void close();
}
