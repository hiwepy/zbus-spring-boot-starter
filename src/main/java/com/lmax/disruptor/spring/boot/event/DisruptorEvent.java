package com.lmax.disruptor.spring.boot.event;

/**
 * Stub for DisruptorEvent.
 */
public class DisruptorEvent {

    private Object source;
    private String routeExpression;

    public DisruptorEvent() {}

    public DisruptorEvent(Object source) {
        this.source = source;
    }

    public Object getSource() { return source; }
    public void setSource(Object source) { this.source = source; }
    public String getRouteExpression() { return routeExpression; }
    public void setRouteExpression(String routeExpression) { this.routeExpression = routeExpression; }
}
