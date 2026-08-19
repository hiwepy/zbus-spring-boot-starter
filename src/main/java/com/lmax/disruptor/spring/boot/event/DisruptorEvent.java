package com.lmax.disruptor.spring.boot.event;

/**
 * Stub for DisruptorEvent.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DisruptorEvent {

    private Object source;
    private String routeExpression;

    public DisruptorEvent() {}

    public DisruptorEvent(Object source) {
        this.source = source;
    }

    /**
     * Returns the source.
     *
     * @return the source
     */
    public Object getSource() { return source; }
    /**
     * Sets the source.
     *
     * @param source the source
     */
    public void setSource(Object source) { this.source = source; }
    /**
     * Returns the route expression.
     *
     * @return the route expression
     */
    public String getRouteExpression() { return routeExpression; }
    /**
     * Sets the route expression.
     *
     * @param routeExpression the route expression
     */
    public void setRouteExpression(String routeExpression) { this.routeExpression = routeExpression; }
}
