package com.deviceinsight.services.websocket;

public class PortfolioPosition {

    private String company;

    private String ticker;

    private double price;

    private String shares;

    private long updateTime;

    private String lastBidder;

    public PortfolioPosition(String company, String ticker, double price, String shares, String lastBidder) {
        this.company = company;
        this.ticker = ticker;
        this.price = price;
        this.shares = shares;

        this.updateTime = System.currentTimeMillis();
        this.lastBidder = lastBidder;
    }

    public PortfolioPosition(PortfolioPosition other, int sharesToAddOrSubtract) {
        this.company = other.company;
        this.ticker = other.ticker;
        this.price = other.price;

        this.lastBidder = other.lastBidder;
        this.price = other.price + 0.01;
        this.price = Math.round(this.price * 100) / 100.0;

        this.shares = other.shares;
        this.updateTime = other.updateTime;
        // this.shares = other.shares + sharesToAddOrSubtract;
        // ebs5grb	this.updateTime = System.currentTimeMillis();
    }

    public PortfolioPosition() {
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShares() {
        return this.shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastBidder() {
        return this.lastBidder;
    }

    public void setLastBidder(String lastBidder) {
        this.lastBidder = lastBidder;
    }

    @Override
    public String toString() {
        return "PortfolioPosition [company=" + this.company + ", ticker=" + this.ticker + ", price=" + this.price
                + ", shares=" + this.shares + "]";
    }

}
