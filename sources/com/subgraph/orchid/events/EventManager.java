package com.subgraph.orchid.events;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/events/EventManager.class */
public class EventManager {
    private final List<EventHandler> handlers = new ArrayList();

    public void addListener(EventHandler eventHandler) {
        synchronized (this) {
            this.handlers.add(eventHandler);
        }
    }

    public void fireEvent(Event event) {
        EventHandler[] eventHandlerArr;
        synchronized (this) {
            eventHandlerArr = new EventHandler[this.handlers.size()];
            this.handlers.toArray(eventHandlerArr);
        }
        int length = eventHandlerArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            eventHandlerArr[i2].handleEvent(event);
            i = i2 + 1;
        }
    }

    public void removeListener(EventHandler eventHandler) {
        synchronized (this) {
            this.handlers.remove(eventHandler);
        }
    }
}
