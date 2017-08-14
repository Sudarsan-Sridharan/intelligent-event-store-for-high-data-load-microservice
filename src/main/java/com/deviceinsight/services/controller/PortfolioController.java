package com.deviceinsight.services.controller;

import com.deviceinsight.services.websocket.Portfolio;
import com.deviceinsight.services.websocket.PortfolioPosition;
import com.deviceinsight.services.websocket.PortfolioService;
import com.deviceinsight.services.websocket.TopicItemDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class PortfolioController {

    private static final Log logger = LogFactory.getLog(PortfolioController.class);
    private static int icnt = 0;
    private final PortfolioService portfolioService;
    private final TradeService tradeService;
    private final TopicItemDAO productDAO;

    @Autowired
    public PortfolioController(PortfolioService portfolioService, TradeService tradeService, TopicItemDAO productDAO) {
        this.portfolioService = portfolioService;
        this.tradeService = tradeService;
        this.productDAO = productDAO;
        portfolioService.createPortfolio(productDAO);
    }

    @SubscribeMapping("/positions")
    public List<PortfolioPosition> getPositions() throws Exception {
        Portfolio portfolio = this.portfolioService.findPortfolio("fabrice");
        return portfolio.getPositions();
    }

    @MessageMapping("/trade")
    public void executeTrade(Trade trade, Principal principal) {
        trade.setUsername(principal.getName());
        logger.debug("Trade: " + trade);
        this.tradeService.executeTrade(trade);
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
