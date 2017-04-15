/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deviceinsight.services.websocket;

import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.ServicequeuesDAO;
import com.deviceinsight.services.websocket.Portfolio;
import com.deviceinsight.services.websocket.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rob Winch
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {

    // user -> Portfolio
    public static Map<String, Portfolio> portfolioLookup = new HashMap<>();

    @Autowired
    private ServicequeuesDAO servicequeuesDAO;


    public PortfolioServiceImpl() {



	/*	Portfolio portfolio = new Portfolio();
        List<Product> k = productDAO.getAll();
		int i = 0;
		for (Product p : k) {
			portfolio.addPosition(
					// ticker, price, shares, lastBidder);
					new PortfolioPosition(
							"<img style=\"width:110px\" src=\"" + p.getImagePath() + "\" />" + "&nbsp;"
									+ p.getFirst_name(),
							p.getIdentifier(), 0.0, "<div id=\"expireMessage_" + p.getIdentifier() + "\"></div>", ""));*/
			/*
			 * new PortfolioPosition( "<img style=\"width:110px\" src=\"" +
			 * p.getImagePath() + "\" />" + "&nbsp;" + p.getFirst_name() + "",
			 * p.getIdentifier(), (float) p.getCredit() / 100,
			 * "<div id=\"expireMessage_" + p.getIdentifier() + "\"></div>",
			 * p.getLast_bidder());
			 *
			 */


		/*
		 * portfolio.addPosition(new PortfolioPosition("Citrix Systems, Inc.",
		 * "1", 24.30, 75, "test")); portfolio.addPosition(new
		 * PortfolioPosition("Dell Inc.", "2", 13.44, 50, "test"));
		 * portfolio.addPosition(new PortfolioPosition("Microsoft", "3", 34.15,
		 * 33, "test")); portfolio.addPosition(new PortfolioPosition("Oracle",
		 * "4", 31.22, 45, "test"));
		 *
		 */
        // this.portfolioLookup.put("fabrice", portfolio);

    }


    public void getObjjj(PortfolioPosition vvv) {

        Portfolio hhh = this.portfolioLookup.get("fabrice");


        System.out.println("new ticket");
        hhh.addPosition(vvv);


        this.portfolioLookup.put("fabrice", hhh);
        System.out.println("*****************" + this.portfolioLookup.get("fabrice").getPositions().size());
    }

    public Portfolio findPortfolio(String username) {
        Portfolio portfolio = this.portfolioLookup.get(username);
        if (portfolio == null) {
            throw new IllegalArgumentException(username);
        }
        return portfolio;
    }

    public void createPortfolio(ProductDAO productDAO) {
        Portfolio portfolio = new Portfolio(productDAO);
        List<Product> k = productDAO.getAll();
        int i = 0;
        for (Product p : k) {

            Product kkk = productDAO.findByTicker(p.getIdentifier());
            //FIX-APRIL USE UUID!!  String byServicesession = servicequeuesDAO.renderByServicesession(kkk.getId());
            String byServicesession=null;
            if (p.getFinished() == 1) {


                portfolio.addPosition(
                        // ticker, price, shares, lastBidder);
                        new PortfolioPosition(
                                "<img style=\"margin-right:15px; width:75px\" src=\"" + p.getImagePath() + "\" />" + "&nbsp;"
                                        + p.getFirst_name(),
                                p.getIdentifier(), p.getCredit(), "<font style=\"color:#ff0000\"></font>", byServicesession));


            } else {
                portfolio.addPosition(
                        // ticker, price, shares, lastBidder);
                        new PortfolioPosition(
                                "<img style=\"margin-right:15px; width:75px\" src=\"" + p.getImagePath() + "\" />" + "&nbsp;"
                                        + p.getFirst_name(),
                                p.getIdentifier(), p.getCredit(), "<div id=\"expireMessage_" + p.getIdentifier() + "\"></div>", byServicesession));
            }
			/*
			 * new PortfolioPosition( "<img style=\"width:110px\" src=\"" +
			 * p.getImagePath() + "\" />" + "&nbsp;" + p.getFirst_name() + "",
			 * p.getIdentifier(), (float) p.getCredit() / 100,
			 * "<div id=\"expireMessage_" + p.getIdentifier() + "\"></div>",
			 * p.getLast_bidder());
			 *
			 */
            i++;
        }

        this.portfolioLookup.put("fabrice", portfolio);


    }


    @Override
    public Portfolio getAll() {
        // TODO Auto-generated method stub
        return this.portfolioLookup.get("fabrice");
    }


    @Override
    public void delete(String ticker) {
        // TODO Auto-generated method stub

        Portfolio hhh = this.portfolioLookup.get("fabrice");

        hhh.removePosition(ticker);


//		this.portfolioLookup.remove(ticker);


		/*hhh.removePosition(ticker);
	*/
        this.portfolioLookup.put("fabrice", hhh);


    }


}
