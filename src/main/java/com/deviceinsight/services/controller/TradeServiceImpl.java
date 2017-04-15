package com.deviceinsight.services.controller;

import com.deviceinsight.services.controller.Trade;
import com.deviceinsight.services.controller.TradeService;
import com.deviceinsight.services.model.TopicItem;
import com.deviceinsight.services.model.Servicequeue;
import com.deviceinsight.services.model.ServicequeuesDAO;
import com.deviceinsight.services.model.TopicItem;
import com.deviceinsight.services.websocket.Portfolio;
import com.deviceinsight.services.websocket.PortfolioPosition;
import com.deviceinsight.services.websocket.PortfolioService;
import com.deviceinsight.services.websocket.TopicItemDAO;
import com.deviceinsight.services.websocket.TopicItemDAO;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Controller
public class TradeServiceImpl implements TradeService {


    private static final Log logger = LogFactory.getLog(TradeServiceImpl.class);
    public static int i = 0;
    private static CopyOnWriteArrayList<TopicItem> jj = new CopyOnWriteArrayList<TopicItem>();
    private final SimpMessageSendingOperations messagingTemplate;
    private final PortfolioService portfolioService;
    private final List<TradeResult> tradeResults = new CopyOnWriteArrayList<>();
    @Autowired
    private ServicequeuesDAO servicequeueDAO;
  /*  @Autowired
    private StatisticsEmployeeDAO statisticsEmployeeDAO;
    @Autowired
    private SettingsDAO settingsDAO;
    @Autowired
    private WorkingareaDAO workingareaDAO;
    @Autowired
    private TablesWorkingareasDAO tablesWorkingareasDAO;
*/    @Autowired
    private TopicItemDAO productDAO;
  /*  @Autowired
    private ServicesessionsWorkingareasDAO servicesessionsWorkingareasDAO;
    @Autowired
    private SendersTablesDAO sendersTablesDAO;
    @Autowired
    private SenderDAO senderDAO;
    private AtomicBoolean brokerAvailable = new AtomicBoolean();
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CustomerBidAgentDAO cbaDAO;
    @Autowired
    private BidhistoryDAO bhDAO;
    @Autowired
    private TablesDAO tablesDAO;
    @Autowired
    private StatisticsDAO statisticsDAO;
*/

    @Autowired
    public TradeServiceImpl(SimpMessageSendingOperations messagingTemplate, PortfolioService portfolioService) {
        this.messagingTemplate = messagingTemplate;
        this.portfolioService = portfolioService;
    }






    /*@ResponseBody
    @RequestMapping(value = {"/versioninfo"}, method = RequestMethod.GET)
    public VersionInfo showVersionInfo(HttpServletRequest req, Model model) throws IOException {

        VersionInfo versionInfo = new VersionInfo();
        versionInfo.setVersion("testversion");


        return versionInfo;
    }
*/







/*


    @RequestMapping(value = {"/333/{id}"}, method = RequestMethod.GET)
    public void spieslpriasassasddsnzipPage(@PathVariable("id") String id, HttpServletRequest req, Model model) throws IOException {


        Setting sa = settingsDAO.get(1);
        String booleanIntStringPairingModes = sa.getValue();

        List<Table> k = tablesDAO.list();

        String out = "<input type=\"text\" id=\"identifier\" name=\"identifier\" /><div id=\"senderid\">" + id + "</div><input type=\"submit\" onclick=\"assignsender()\" />";

        String optionsStr = "";
        for (Table item : k) {

            optionsStr += "<option value=\"" + item.getId() + "\" class=\"ng-binding ng-scope\">" + item.getTitle() + "</option>";

        }


        out = "\t<div class=\"row\">\n" +
                "\t\t<div class=\"col-md-7\">\n" +
                "\t\t\t<div class=\"panel panel-default\">\n" +
                "\t\t\t\t<div style=\"text-align:left; float:left; margin-right:25px;\">\n" +
                "\t\t\t\t\t<img src=\"/assets/img/sender.png\" style=\"height:150px\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div style=\"float:left\"><h2 style=\"color:#65ba13\">New sender found! <font style=\"color:#000\">#" + id + "</font></h2>\n" +
                "\t\t\t\t\t<div style=\"display:none\" id=\"senderid\">" + id + "</div>\n" +
                "\t\t\t\t\t<select id=\"identifier\" name=identifier\" ng-model=\"selectedItem\" style=\"-webkit-appearance: none; -moz-appearance: none; border: 0 !important; color: #ccc; -webkit-border-radius: 5px; border-radius: 5px; font-size: 14px; padding: 10px; cursor: pointer; background: #fafafa url('http://www.kevinfremon.com/wp-content/uploads/2013/11/drop-down-arrow.png') no-repeat right center; background-size: 40px 37px; width:auto; width:225px;\" class=\"ng-pristine ng-valid\">\n" +
                "\t\t\t\t\t\t<option selected=\"selected\" value=\"\">Choose table to assign ...</option>\n" +
                optionsStr +
                "\t\t\t\t\t</select>\n" +
                "\t\t\t\t\t<input type=\"submit\" class=\"btn btn-block btn-secondary\" onclick=\"assignsender()\" value=\"Confirm\" style=\"width:100px; margin-top:5px;\" >\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<br style=\"clear:both\" /></div>\n" +
                "\n" +
                "\t\t</div>\n" +
                "\t\t\t</div>";
        if (booleanIntStringPairingModes.equals("1")) {
            messagingTemplate.convertAndSend("/senders", out);

            sa.setValue("0");
            settingsDAO.saveOrUpdate(sa);
        } else {


        }


    }


*/




/*
    @ResponseBody
    @RequestMapping(value = {"/api/v1/shifts"}, method = {RequestMethod.POST})
    public String kjkjk(@RequestParam MultiValueMap<String, String> allRequestParams, HttpServletRequest req, Model model, @RequestBody ShiftDto shiftDto) throws PayPalRESTException, IOException, MessagingException, SQLIntegrityConstraintViolationException {
        boolean create = false;
        SingleWorkingAreaShift[] reviews = new Gson().fromJson(shiftDto.getShift(), SingleWorkingAreaShift[].class);
        List<SingleWorkingAreaShift> asList = Arrays.asList(reviews);
        String uuid = UUID.randomUUID().toString();
        for (SingleWorkingAreaShift swas2 : asList) {
            if (swas2.getWorkingarea() != null && swas2.getTablegroup() != null) {
                create = true;
            }
        }
        if (create) {
            Product pro = new Product();
            pro.setIdentifier(uuid);
            pro.setIsActive(1);
            pro.setIsUpdated(0);
            pro.setCredit(0f);
            pro.setBase_price(0f);
            pro.setEnds_on_price(0f);
            pro.setFinished(0);
            User user = userDAO.get(shiftDto.getEmployeeId());
            pro.setFirst_name("" + user.getUsername());
            pro.setLast_bidder("" + user.getLast_name());
            pro.setEmployee_id(shiftDto.getEmployeeId());
            pro.setImagePath(user.getAvatar_url_gravatar_by_email());
//            pro.setImagePath("/assets/media/avatars/no-product-photo.png");
            productDAO.saveOrUpdate(pro);
            PortfolioPosition ppa =
                    new PortfolioPosition(
                            "<img style=\"margin-right:15px; width:75px\" src=\""+user.getAvatar_url_gravatar_by_email()+ "\" />"
                                    + "&nbsp;" + pro.getFirst_name(),
                            uuid, new Double("" + 12),
                            "<div id=\"expireMessage_" + uuid + "\"></div>",
                            "");
            portfolioService.getObjjj(ppa);
        }
        Product ppp1 = productDAO.findByTicker(uuid);
        for (SingleWorkingAreaShift swas : asList) {
            if (swas.getWorkingarea() != null && swas.getTablegroup() != null) {
                ServicesessionsWorkingareas servicesessionsWorkingareas = new ServicesessionsWorkingareas();
                servicesessionsWorkingareas.setTablegroup(Integer.parseInt(swas.getTablegroup()));
                Workingarea workingareasae = workingareaDAO.get(Integer.parseInt(swas.getWorkingarea()));
                servicesessionsWorkingareas.setWorkingarea(workingareasae);
                servicesessionsWorkingareas.setServicesession(ppp1);
                servicesessionsWorkingareasDAO.saveOrUpdate(servicesessionsWorkingareas);
                create = true;
            }
        }
        if (create) {
            Map<String, String> params;
            RestTemplate restTemplate;
            String uri;
            uri = "http://localhost:8080/222";
            params = new HashMap<String, String>();
            restTemplate = new RestTemplate();
            restTemplate.getForObject(uri, String.class, params);
        }
        return "s";
    }

*/


/*

    @ResponseBody
    @RequestMapping(value = {"/222"}, method = RequestMethod.GET)
    public void spieslprisddsnzipPage(HttpServletRequest req, Model model) throws IOException {
        Trade t1 = new Trade();
        t1.setAction(TradeAction.akzeptieren);
        t1.setTicker("32c9effc-d158-4cbb-ad2a-b8720f716607");
        t1.setShares(0);
        t1.setMaximumbids("");
        t1.setMaximumprice("");
        t1.setBidmode("1");
        t1.setOverwrite("");
        executeTrade(t1);
    }

    @RequestMapping(value = {"/clear"}, method = RequestMethod.GET)
    public String clearPage(@RequestParam MultiValueMap<String, String> allRequestParams, Model model) throws IOException {
        Trade t1 = new Trade();
        t1.setAction(TradeAction.akzeptieren);
        t1.setTicker("32c9effc-d158-4cbb-ad2a-b8720f716607");
        t1.setShares(0);
        t1.setMaximumbids("");
        t1.setMaximumprice("");
        t1.setBidmode("1");
        t1.setOverwrite("");
        clearTrade(allRequestParams.get("ticker").get(0));
        return "/auktionsregeln";
    }


    @RequestMapping(value = {"/clear5"}, method = RequestMethod.GET)
    public String wwclearPage(@RequestParam MultiValueMap<String, String> allRequestParams, Model model) throws IOException {
        Trade t1 = new Trade();
        t1.setAction(TradeAction.akzeptieren);
        t1.setTicker("32c9effc-d158-4cbb-ad2a-b8720f716607");
        t1.setShares(0);
        t1.setMaximumbids("");
        t1.setMaximumprice("");
        t1.setBidmode("1");
        t1.setOverwrite("");
        clearTrade5(allRequestParams.get("ticker").get(0));
        return "/auktionsregeln";
    }

*/
    public String currentDateTime() {
        GregorianCalendar gc = new GregorianCalendar();

        SimpleDateFormat format_time_now1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        return format_time_now1.format(gc.getTime());
    }


 /*   @RequestMapping(value = {"/druecker"}, method = RequestMethod.GET)
    public void spieslpsdrisddsnzipPage(@RequestParam MultiValueMap<String, String> allRequestParams, Model model) throws IOException, SQLIntegrityConstraintViolationException {
        Trade t1 = new Trade();
        t1.setAction(TradeAction.akzeptieren);
        t1.setTicker("32c9effc-d158-4cbb-ad2a-b8720f716607");
        t1.setShares(0);
        t1.setMaximumbids("sdsds");
        t1.setMaximumprice("sdsd");
        t1.setBidmode("1");
        t1.setOverwrite("");
        updateTrade(t1, allRequestParams.get("sender").get(0), allRequestParams.get("button").get(0), allRequestParams.get("kellner").get(0), allRequestParams.get("battery").get(0));
    }

    @RequestMapping(value = {"/toggleNightMode"}, method = RequestMethod.GET)
    public void toggleNightMode(@RequestParam MultiValueMap<String, String> allRequestParams, Model model) throws IOException, SQLIntegrityConstraintViolationException {

        Setting setting = settingsDAO.get(4);
        if(setting.getValue().equals("off")) {
            setting.setValue("on");
        } else if(setting.getValue().equals("on")) {
            setting.setValue("off");
        } else {
            setting.setValue("off");
        }

        settingsDAO.saveOrUpdate(setting);


        messagingTemplate.convertAndSend("/topic/settings." + "toggleNightMode",
                "{\"ticker\":\"" + "on" + "\",\"price\":" + "999999" + ",\"lastBidder\":\""
                        + "" + "\"" + ",\"isFinished\":\"" + "1" + "\"" + "} ");
    }


    @RequestMapping(value = {"/confirmticket"}, method = RequestMethod.GET)
    public void spieslpsdrisddsjjjnzipPage(@RequestParam MultiValueMap<String, String> allRequestParams, Model model) throws IOException, SQLIntegrityConstraintViolationException {
        Trade t1 = new Trade();
        t1.setAction(TradeAction.akzeptieren);
        t1.setTicker("32c9effc-d158-4cbb-ad2a-b8720f716607");
        t1.setShares(0);
        t1.setMaximumbids("sdsds");
        t1.setMaximumprice("sdsd");
        t1.setBidmode("1");
        t1.setOverwrite("");
        confirmTicket(allRequestParams.get("id").get(0));
    }
*/

    /**
     * In real application a trade is probably executed in an external system,
     * i.e. asynchronously.
     */
    public void executeTrade(Trade trade) {
        String tticker = "1";
        String tnewprice = "121212";
        String tnewbidder = "12121212";
        Portfolio portfolio = this.portfolioService.findPortfolio("fabrice");
        String ticker = trade.getTicker();
        int sharesToTrade = trade.getShares();
        PortfolioPosition newPosition = null;
        String toBeAdded = "{\"company\":\"<img style=\\\"margin-right:15px; width:110px\\\" src=\\\"/assets/no-product-photo.png\\\" />&nbsp;John\",\"ticker\":\"bbbbbbbb-970a-4c37-8c6e-9850b43a9f31\",\"price\":44.439998626708984,\"shares\":\"<div id=\\\"expireMessage_32c9effc-d158-4cbb-ad2a-b8720f716607\\\"></div>\",\"updateTime\":1451933885198,\"lastBidder\":\"\"}";
        String resres = "";
        resres += "[";
        List<PortfolioPosition> k = portfolio.getPositions();
        String persisted = "";
        int io = 0;
        int size = k.size();
        for (PortfolioPosition ll : k) {
            io++;
            String tcomp = ll.getCompany();
            String details = tcomp.replace("\"", "\\\"");
            TopicItem p1m = productDAO.findByTicker(ll.getTicker());
            persisted += "{\"company\":\"" + details + "\",\"ticker\":\"" + ll.getTicker() + "\",\"price\":44.439998626708984,\"shares\":\"<div id=\\\"expireMessage_32c9effc-d158-4cbb-ad2a-b8720f716607\\\"></div>\",\"updateTime\":1451933885198,\"lastBidder\":\"" + servicequeueDAO.renderByServicesession(p1m.getId()) + "\"}";
            if (size != io) {
                persisted += ",";
            }
        }
        resres += persisted;
        resres += "]";
        messagingTemplate.convertAndSend("/app/positions", resres);
        TopicItem product = productDAO.findByTicker(ticker);
    }

    public void clearTrade(String ticker) {
        TopicItem p0 = productDAO.findByTicker(ticker);
        messagingTemplate.convertAndSend("/topic/price.stock." + ticker,
                "{\"ticker\":\"" + ticker + "\",\"price\":" + p0.getCredit() + ",\"lastBidder\":\""
                        + "" + "\"" + ",\"isFinished\":\"" + p0.getFinished() + "\"" + "} ");
        p0.setLast_bidder("");
        TopicItem po = productDAO.findByTicker(ticker);
        String pId = po.getIdentifier();
        List<Servicequeue> lpro = servicequeueDAO.findByServicesession(po.getId());
        for (Servicequeue k : lpro) {
            servicequeueDAO.delete(k);
        }
        PortfolioPosition ppa =
                new PortfolioPosition(
                        "<img style=\"width:110px\" src=\"" + p0.getImagePath() + "\" />"
                                + "&nbsp;" + p0.getTitle(),
                        p0.getIdentifier(), new Double("" + p0.getCredit()),
                        "<div id=\"expireMessage_" + p0.getIdentifier() + "\"></div>",
                        p0.getLast_bidder());
        portfolioService.getObjjj(ppa);
        productDAO.saveOrUpdate(p0);
        portfolioService.delete(ticker);
        TopicItem deleteProduct = productDAO.findByTicker(ticker);
        productDAO.delete(deleteProduct);
        Map<String, String> params;
        RestTemplate restTemplate;
        String uri;
        uri = "http://localhost:8080/222";
        params = new HashMap<String, String>();
        restTemplate = new RestTemplate();
        restTemplate.getForObject(uri, String.class, params);
    }
/*
    public void clearTrade5(String ticker) {
        Product p0 = productDAO.findByTicker(ticker);
        messagingTemplate.convertAndSend("/topic/price.stock." + ticker,
                "{\"ticker\":\"" + ticker + "\",\"price\":" + p0.getCredit() + ",\"lastBidder\":\""
                        + "" + "\"" + ",\"isFinished\":\"" + p0.getFinished() + "\"" + "} ");
        p0.setLast_bidder("");
        PortfolioPosition ppa =
                new PortfolioPosition(
                        "<img style=\"width:110px\" src=\"" + p0.getImagePath() + "\" />"
                                + "&nbsp;" + p0.getTitle(),
                        p0.getIdentifier(), new Double("" + p0.getCredit()),
                        "<div id=\"expireMessage_" + p0.getIdentifier() + "\"></div>",
                        p0.getLast_bidder());

        portfolioService.getObjjj(ppa);
        productDAO.saveOrUpdate(p0);
    }
*/

/*
    public void updateTrade(Trade trade, String sender, String button, String kellner, String battery) throws SQLIntegrityConstraintViolationException {


        Setting sa = settingsDAO.get(1);
        String booleanIntStringPairingModes = sa.getValue();

        if (booleanIntStringPairingModes.equals("1")) {
            System.out.println("**** IN PAIRING MODE **** ... new sender detected " + sender);
        } else {


        }


        SendersTables st = sendersTablesDAO.findByUuid(sender);


        Sender senderExiststs = senderDAO.findByUuid(sender);
        if (senderExiststs == null) {
            Map<String, String> params;
            RestTemplate restTemplate;
            String uri;
            uri = "http://localhost:8080/333/" + sender;
            params = new HashMap<String, String>();
            restTemplate = new RestTemplate();
            restTemplate.getForObject(uri, String.class, params);

        } else {

            senderExiststs.setLast_seen(currentDateTime());
            senderDAO.saveOrUpdate(senderExiststs);
        }


        TablesWorkingareas tw = tablesWorkingareasDAO.findByTable("" + st.getTable().getId()); // optional delete it 5 3 16 // TODO release check fk table group!!
        Table tableee = tablesWorkingareasDAO.findByTable2("" + st.getTable().getId()); // optional delete it 5 3 16
        if (tw != null) {
            ServicesessionsWorkingareas sswa3 = servicesessionsWorkingareasDAO.findByWorkingareaAndTableGroup("" + tw.getWorkingarea().getId(), "" + tw.getTablegroup().getId());
            ServicesessionsWorkingareas sswa;// = servicesessionsWorkingareasDAO.findByWorkingarea("" + tw.getWorkingarea().getId());
            sswa = sswa3;
            String tttticker = sswa.getServicesession().getIdentifier();
            Portfolio all = portfolioService.getAll();
            PortfolioPosition portfolioPosition = all.getPortfolioPosition(tttticker);
            String beforeText;
            if (portfolioPosition != null)
                beforeText = portfolioPosition.getLastBidder();
            else
                beforeText = "";
            Sender senderUsed = senderDAO.findByUuid(sender);
            senderUsed.setVoltage(Integer.parseInt(battery));
            int batteryStatus = (Integer.parseInt(battery) - 2400) / 6;
            if (batteryStatus < 0) batteryStatus = 0;
            if (batteryStatus > 100) batteryStatus = 100;
            senderUsed.setBattery(batteryStatus);
            senderDAO.saveOrUpdate(senderUsed);
            String text = "";
            Servicequeue sq = new Servicequeue();
            Product sessionEmployee = productDAO.findByTicker(tttticker);
            sq.setServicesession(sessionEmployee);

            org.springframework.samples.portfolio.persistence.model.Table tableNo = tableee; //////////////tw.getTable();
            sq.setAction(Integer.parseInt(button));
            sq.setTable(tableNo);
            int zufallszahl = (int) ((Math.random() * 30) + 1);
            sq.setAccept_ticket("" + zufallszahl);
            sq.setCreated(System.currentTimeMillis()/1000);
            if (Integer.parseInt(button) == 2) {
                Servicequeue s = servicequeueDAO.findByTable(tableNo.getId());
                servicequeueDAO.delete(s);

                s.getServicesession();

                int getAction = s.getAction();


                SimpleDateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date today = new Date();
                String result = formatter.format(today);




                long time=System.currentTimeMillis()/1000-s.getCreated();


                StatisticsEmployee statisticsEmployee = statisticsEmployeeDAO.findByUserIdAndDate("" + sessionEmployee.getEmployee_id(), result);
                int action = Integer.parseInt(button);

                if (statisticsEmployee == null) {
                    if(getAction==0) {
                        statisticsEmployee = new StatisticsEmployee();
                        statisticsEmployee.setCounter_service(1);
                        statisticsEmployee.setDay(result);
                        statisticsEmployee.setEmployee_id("" + sessionEmployee.getEmployee_id());
                        statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
                    }
                    if(getAction==1) {
                        statisticsEmployee = new StatisticsEmployee();
                        statisticsEmployee.setCounter_pay(1);
                        statisticsEmployee.setDay(result);
                        statisticsEmployee.setEmployee_id("" + sessionEmployee.getEmployee_id());
                        statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
                    }
                } else {
                    int counterService = statisticsEmployee.getCounter_service();
                    int counterPay = statisticsEmployee.getCounter_pay();
                    counterService++;
                    counterPay++;
                    if(getAction==0) {
                        statisticsEmployee.setCounter_service(counterService);
                        statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
                    }
                    if(getAction==1) {
                        statisticsEmployee.setCounter_pay(counterPay);
                        statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
                    }
                }








                User waiter = userDAO.get(sessionEmployee.getEmployee_id());


                Long newAvgService = (((waiter.getService_counter()*waiter.getAverage_time_service())+time)/(waiter.getService_counter()+1));
                Long newAvgPay = (((waiter.getPay_counter()*waiter.getAverage_time_pay())+time)/(waiter.getPay_counter()+1));


                if(getAction==0) {
                    waiter.setAverage_time_service(new Long(Integer.parseInt(newAvgService.toString())));
                    waiter.setService_counter(waiter.getService_counter() + 1);
                }


                if(getAction==1) {
                    waiter.setAverage_time_pay(new Long(Integer.parseInt(newAvgPay.toString())));
                    waiter.setPay_counter(waiter.getPay_counter() + 1);
                }

                userDAO.saveOrUpdate(waiter);













                Statistic statistics = statisticsDAO.findByDate(result);

                if (statistics == null) {
                    if(getAction==0) {
                        statistics = new Statistic();
                        statistics.setCounter_service(1);
                        statistics.setDay(result);
                        statistics.setAverage_time_service(time);
                        statistics.setAverage_time_pay(0l);
                        statisticsDAO.saveOrUpdate(statistics);
                    }
                    if(getAction==1) {
                        statistics = new Statistic();
                        statistics.setCounter_pay(1);
                        statistics.setDay(result);
                        statistics.setAverage_time_pay(time);
                        statistics.setAverage_time_service(0l);
                        statisticsDAO.saveOrUpdate(statistics);
                    }
                } else {


                    Long service = (((statistics.getCounter_service()*statistics.getAverage_time_service())+time)/(statistics.getCounter_service()+1));
                    Long pay = (((statistics.getCounter_pay()*statistics.getAverage_time_pay())+time)/(statistics.getCounter_pay()+1));


                    int counterService = statistics.getCounter_service();
                    int counterPay = statistics.getCounter_pay();
                    counterService++;
                    counterPay++;
                    if(getAction==0) {
                        statistics.setCounter_service(counterService);
                        statistics.setAverage_time_service(service);
                        statisticsDAO.saveOrUpdate(statistics);
                    }
                    if(getAction==1) {
                        statistics.setCounter_pay(counterPay);
                        statistics.setAverage_time_pay(pay);
                        statisticsDAO.saveOrUpdate(statistics);
                    }
                }












            } else {
                servicequeueDAO.saveOrUpdate(sq);
            }
            if (button.equals("0"))
                text = "<div style='float:left; background-color:#8bc832; color:#fff; padding:10px; margin-right:5px; width:133px; height:100px; text-align:center'><font style='font-size:40px'>" + tableNo.getTitle() + "</font><br /><font style='font-size:14px'>Service" + " <br />(" + tw.getWorkingarea().getId() + ")" + "</font></div>";
            if (button.equals("1"))
                text = "<div style='float:left; background-color:#8bc832; color:#fff; padding:10px; margin-right:5px; width:133px; height:100px; text-align:center'><font style='font-size:40px'>" + tableNo.getTitle() + "</font><br /><font style='font-size:14px'>Pay" + " <br />(" + tw.getWorkingarea().getId() + ")" + "</font></div>";
            portfolioPosition.setLastBidder(beforeText + text);
            Product kkk = productDAO.findByTicker(tttticker);
            String byServicesession = servicequeueDAO.renderByServicesession(kkk.getId());
            portfolioPosition.setLastBidder(byServicesession);
            portfolioService.getObjjj(portfolioPosition);
            messagingTemplate.convertAndSend("/topic/price.stock." + tttticker,
                    "{\"ticker\":\"" + tttticker + "\",\"price\":" + "100" + ",\"lastBidder\":\""
                            + servicequeueDAO.renderByServicesession(sswa.getServicesession().getId()) + "\"" + ",\"isFinished\":\"" + "1" + "\"" + "} ");
            Product pppp = productDAO.findByTicker(tttticker);
            pppp.setLast_bidder(beforeText + text);
            productDAO.saveOrUpdate(pppp);
        }
    }

    public void confirmTicket(String accept_ticket) throws SQLIntegrityConstraintViolationException {
        String tttticker = servicequeueDAO.findTickerByAcceptTicket(accept_ticket);
        Portfolio all = portfolioService.getAll();
        PortfolioPosition portfolioPosition = all.getPortfolioPosition(tttticker);


        Servicequeue performedSq = servicequeueDAO.findByAcceptTicket(accept_ticket);
        System.out.println(performedSq.getAction() + "------------------");


        servicequeueDAO.deleteByAcceptTicket(accept_ticket);
        String beforeText;
        if (portfolioPosition != null)
            beforeText = portfolioPosition.getLastBidder();
        else
            beforeText = "";
        String text = "";
        Product sessionEmployee = productDAO.findByTicker(tttticker);


        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String result = formatter.format(today);




        int action = performedSq.getAction();


        long time=System.currentTimeMillis()/1000-performedSq.getCreated();


        //Statistic stats = statisticsDAO.get(1);



        //statisticsDAO.saveOrUpdate(stats);

        //  System.out.println(stats.getValue());


        StatisticsEmployee statisticsEmployee = statisticsEmployeeDAO.findByUserIdAndDate("" + sessionEmployee.getEmployee_id(), result);

        if (statisticsEmployee == null) {
            if(action==0) {
                statisticsEmployee = new StatisticsEmployee();
                statisticsEmployee.setCounter_service(1);
                statisticsEmployee.setDay(result);
                statisticsEmployee.setEmployee_id("" + sessionEmployee.getEmployee_id());
                statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
            }
            if(action==1) {
                statisticsEmployee = new StatisticsEmployee();
                statisticsEmployee.setCounter_pay(1);
                statisticsEmployee.setDay(result);
                statisticsEmployee.setEmployee_id("" + sessionEmployee.getEmployee_id());
                statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
            }
        } else {
            int counterService = statisticsEmployee.getCounter_service();
            int counterPay = statisticsEmployee.getCounter_pay();
            counterService++;
            counterPay++;
            if(action==0) {
                statisticsEmployee.setCounter_service(counterService);
                statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
            }
            if(action==1) {
                statisticsEmployee.setCounter_pay(counterPay);
                statisticsEmployeeDAO.saveOrUpdate(statisticsEmployee);
            }
        }





        User waiter = userDAO.get(sessionEmployee.getEmployee_id());


        Long newAvgService = (((waiter.getService_counter()*waiter.getAverage_time_service())+time)/(waiter.getService_counter()+1));
        Long newAvgPay = (((waiter.getPay_counter()*waiter.getAverage_time_pay())+time)/(waiter.getPay_counter()+1));


        if(action==0) {
            waiter.setAverage_time_service(new Long(Integer.parseInt(newAvgService.toString())));
            waiter.setService_counter(waiter.getService_counter() + 1);
        }


        if(action==1) {
            waiter.setAverage_time_pay(new Long(Integer.parseInt(newAvgPay.toString())));
            waiter.setPay_counter(waiter.getPay_counter() + 1);
        }

        userDAO.saveOrUpdate(waiter);













        Statistic statistics = statisticsDAO.findByDate(result);

        if (statistics == null) {
            if(action==0) {
                statistics = new Statistic();
                statistics.setCounter_service(1);
                statistics.setDay(result);
                statistics.setAverage_time_service(time);
                statistics.setAverage_time_pay(0l);
                statisticsDAO.saveOrUpdate(statistics);
            }
            if(action==1) {
                statistics = new Statistic();
                statistics.setCounter_pay(1);
                statistics.setDay(result);
                statistics.setAverage_time_pay(time);
                statistics.setAverage_time_service(0l);
                statisticsDAO.saveOrUpdate(statistics);
            }
        } else {


            Long service = (((statistics.getCounter_service()*statistics.getAverage_time_service())+time)/(statistics.getCounter_service()+1));
            Long pay = (((statistics.getCounter_pay()*statistics.getAverage_time_pay())+time)/(statistics.getCounter_pay()+1));


            int counterService = statistics.getCounter_service();
            int counterPay = statistics.getCounter_pay();
            counterService++;
            counterPay++;
            if(action==0) {
                statistics.setCounter_service(counterService);
                statistics.setAverage_time_service(service);
                statisticsDAO.saveOrUpdate(statistics);
            }
            if(action==1) {
                statistics.setCounter_pay(counterPay);
                statistics.setAverage_time_pay(pay);
                statisticsDAO.saveOrUpdate(statistics);
            }
        }






        Product kkk = productDAO.findByTicker(tttticker);
        String byServicesession = servicequeueDAO.renderByServicesession(kkk.getId());
        portfolioPosition.setLastBidder(byServicesession);
        portfolioService.getObjjj(portfolioPosition);
        messagingTemplate.convertAndSend("/topic/price.stock." + tttticker,
                "{\"ticker\":\"" + tttticker + "\",\"price\":" + "100" + ",\"lastBidder\":\""
                        + byServicesession + "\"" + ",\"isFinished\":\"" + "1" + "\"" + "} ");
        Product pppp = productDAO.findByTicker(tttticker);
        pppp.setLast_bidder(beforeText + text);
        productDAO.saveOrUpdate(pppp);
    }
*/
    public String now() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat format_time_now1 = new SimpleDateFormat("dd.MM.YYYY");
        SimpleDateFormat format_time_now2 = new SimpleDateFormat("HH:mm:ss");
        return format_time_now1.format(gc.getTime()) + ", " + format_time_now2.format(gc.getTime()) + " Uhr";
    }

    public String now2() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat format_time_now1 = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat format_time_now2 = new SimpleDateFormat("HH:mm:ss");
        return format_time_now1.format(gc.getTime()) + " " + format_time_now2.format(gc.getTime());
    }

    public void sendTradeNotificationsOriginal() {
    }

    public int getRandomNumber(int pMaximum) {
        return (int) ((Math.random() * pMaximum) + 1);
    }

    private static class TradeResult {
        private final String user;
        private final PortfolioPosition position;

        public TradeResult(String user, PortfolioPosition position) {
            this.user = user;
            this.position = position;
        }
    }

}
