package com.deviceinsight.services;

public class PanamaEventDto {

    private String eventKey;
    private Long priority;
    private Boolean onCome;
    private Long parentId;
    private Long nodeId;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Boolean getOnCome() {
        return onCome;
    }

    public void setOnCome(Boolean onCome) {
        this.onCome = onCome;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}
