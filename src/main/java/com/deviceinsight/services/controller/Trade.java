package com.deviceinsight.services.controller;

public class Trade {

    private String ticker;

    private int shares;

    private TradeAction action;

    private String username;
    private String overwrite;
    private String maximumbids;
    private String maximumprice;
    private String bidmode;

    public String getOverwrite() {
        return overwrite;
    }

    public void setOverwrite(String overwrite) {
        this.overwrite = overwrite;
    }

    public String getMaximumbids() {
        return maximumbids;
    }

    public void setMaximumbids(String maximumbids) {
        this.maximumbids = maximumbids;
    }

    public String getMaximumprice() {
        return maximumprice;
    }

    public void setMaximumprice(String maximumprice) {
        this.maximumprice = maximumprice;
    }

    public String getBidmode() {
        return bidmode;
    }

    public void setBidmode(String bidmode) {
        this.bidmode = bidmode;
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getShares() {
        return this.shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public TradeAction getAction() {
        return this.action;
    }

    public void setAction(TradeAction action) {
        this.action = action;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "[ticker=" + this.ticker + ", shares=" + this.shares + ", action=" + this.action + ", maximumbids=" + maximumbids + ", maximumprice=" + maximumprice + ", bidmode=" + bidmode + ", username="
                + this.username + ", overwrite=" + this.overwrite + "]";
    }

    public enum TradeAction {
        akzeptieren, Sell;
    }

}
