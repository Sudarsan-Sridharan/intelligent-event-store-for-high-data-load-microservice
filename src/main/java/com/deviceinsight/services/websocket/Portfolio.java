package com.deviceinsight.services.websocket;

import com.deviceinsight.services.model.TopicItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

    private final Map<String, PortfolioPosition> positionLookup = new LinkedHashMap<String, PortfolioPosition>();
    private TopicItemDAO productDAO;

    public Portfolio(TopicItemDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<PortfolioPosition> getPositions() {
        return new ArrayList<PortfolioPosition>(positionLookup.values());
    }

    public void addPosition(PortfolioPosition position) {
        this.positionLookup.put(position.getTicker(), position);
    }

    public PortfolioPosition getPortfolioPosition(String ticker) {
        return this.positionLookup.get(ticker);
    }

    public Map<String, PortfolioPosition> getObj() {
        return this.positionLookup;
    }

    public PortfolioPosition buy(String ticker, int sharesToBuy) {
        PortfolioPosition position = this.positionLookup.get(ticker);
        if (position == null) {
            return null;
        }
        long cu = System.currentTimeMillis();
        position.setUpdateTime(cu);
        TopicItem product = productDAO.findByTicker(ticker);
        this.positionLookup.put(ticker, position);
        if (product.getFinished() != 1) {
            position = new PortfolioPosition(position, sharesToBuy);
            return position;
        }
        return null;
    }

    public PortfolioPosition getPosition(String ticker) {
        PortfolioPosition position = this.positionLookup.get(ticker);
        if (position == null) {
            return null;
        }
        return position;
    }

    public PortfolioPosition sell(String ticker, int sharesToSell) {
        PortfolioPosition position = this.positionLookup.get(ticker);
        this.positionLookup.put(ticker, position);
        return position;
    }

    public void removePosition(String ticker) {
        PortfolioPosition position = this.positionLookup.get(ticker);
        this.positionLookup.remove(ticker);
    }

}
