package com.deviceinsight.services.websocket;

/**
 * Created by ebners on 14.04.17.
 */
public interface PortfolioService {
    Portfolio findPortfolio(String username);

    void createPortfolio(TopicItemDAO productDAO);

    void getObjjj(PortfolioPosition newPosition);

    Portfolio getAll();

    void delete(String ticker);
}
