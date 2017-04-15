/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deviceinsight.services.controller;

import com.deviceinsight.services.websocket.Portfolio;
import com.deviceinsight.services.websocket.PortfolioPosition;
import com.deviceinsight.services.websocket.PortfolioService;
import com.deviceinsight.services.websocket.ProductDAO;
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
    private final ProductDAO productDAO;

    @Autowired
    public PortfolioController(PortfolioService portfolioService, TradeService tradeService, ProductDAO productDAO) {
        this.portfolioService = portfolioService;
        this.tradeService = tradeService;
        this.productDAO = productDAO;

        portfolioService.createPortfolio(productDAO);
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss > " + icnt);
        logger.debug("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss > " + icnt);


    }

	/*@Autowired
    public void testIt(){
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(new Runnable(){
            public void run() {
                while(true){
                    //System.out.printf("Thread -" + Thread.currentThread().getId());
                	auctionWatch();

                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                }

            }


        });
    }
    */
/*	private void auctionWatch() {
        System.out.println("WATCH!!!!!!!");

	}*/


    @SubscribeMapping("/positions")
    public List<PortfolioPosition> getPositions() throws Exception {
        // logger.debug("Positions for " + principal.getName());
        //this.portfolioService.createPortfolio(productDAO);
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
