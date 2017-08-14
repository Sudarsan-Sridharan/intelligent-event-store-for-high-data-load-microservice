package com.deviceinsight.services.websocket;

import com.deviceinsight.services.model.TopicItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    public static Map<String, Portfolio> portfolioLookup = new HashMap<>();

    public PortfolioServiceImpl() {
    }

    public void getObjjj(PortfolioPosition vvv) {
        Portfolio hhh = this.portfolioLookup.get("fabrice");
        hhh.addPosition(vvv);
        this.portfolioLookup.put("fabrice", hhh);
    }

    public Portfolio findPortfolio(String username) {
        Portfolio portfolio = this.portfolioLookup.get(username);
        if (portfolio == null) {
            throw new IllegalArgumentException(username);
        }
        return portfolio;
    }

    public void createPortfolio(TopicItemDAO productDAO) {
        Portfolio portfolio = new Portfolio(productDAO);
        List<TopicItem> k = productDAO.getAll();
        int i = 0;
        for (TopicItem p : k) {
            TopicItem kkk = productDAO.findByTicker(p.getIdentifier());
            String byServicesession = null;
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
            i++;
        }
        this.portfolioLookup.put("fabrice", portfolio);
    }

    @Override
    public Portfolio getAll() {
        return this.portfolioLookup.get("fabrice");
    }

    @Override
    public void delete(String ticker) {
        Portfolio hhh = this.portfolioLookup.get("fabrice");
        hhh.removePosition(ticker);
        this.portfolioLookup.put("fabrice", hhh);
    }
}
