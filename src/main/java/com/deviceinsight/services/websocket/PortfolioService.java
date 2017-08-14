package com.deviceinsight.services.websocket;

public interface PortfolioService {
    Portfolio findPortfolio(String username);

    void createPortfolio(TopicItemDAO productDAO);

    void getObjjj(PortfolioPosition newPosition);

    Portfolio getAll();

    void delete(String ticker);
}
