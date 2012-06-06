package com.poker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class PokerClientResponse
 */
@WebServlet(description = "This Servlet will handle AJAX calls from the web client", urlPatterns = { "/PokerClientResponse" })
public class PokerClientResponse extends HttpServlet {
    private static Logger LOG = Logger.getLogger(PokerClientResponse.class);

    private static final long serialVersionUID = 1L;
    private String var = "";
    private Deck deck = null;
    private Hand myHand = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PokerClientResponse() {
        super();

        setDeck(new Deck());

        var = "Constructor";
        LOG.debug("Constructor Firing");
        LOG.trace("Ending -- PokerClientResponse Constructor");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.trace("Begin -- doGet");
        LOG.debug("In do get VAR == " + var);

        var += " -- Do Get -- ";
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json
        // object to the output stream
        PrintWriter out = response.getWriter();
        // Assuming your json object is **jsonObject**, perform the following,
        // it will return your json object

        HandState currentState = (HandState) request.getSession().getAttribute("handState");

        if (currentState == null || currentState == HandState.NONE) {
            initializeHands(out);
            currentState = HandState.NONE;
        } else {
            dealCommunityCards(out, currentState);
        }
        
        request.getSession().setAttribute("handState", currentState.getNext());
        LOG.trace("Ending -- doGet");
    }

    /**
     * @param out
     */
    private void dealCommunityCards(PrintWriter out, HandState state) {
        LOG.trace("Begin -- dealCommunityCards");

        // There's the BURN CARD!
        getDeck().getNextCard();

        ArrayList<Card> cards = new ArrayList<Card>();

        switch (state) {
            case FLOP:
                cards.add(getDeck().getNextCard());
                cards.add(getDeck().getNextCard());
                cards.add(getDeck().getNextCard());
                break;
            case TURN:
                cards.add(getDeck().getNextCard());
                break;
            case RIVER:
                cards.add(getDeck().getNextCard());
                break;
            default:
                LOG.error("Found unexpected handstate");
        }

        JsonArray parentArray = new JsonArray();

        JsonArray cardArray = new JsonArray();

        Iterator<Card> handIter = cards.iterator();

        JsonObject json = new JsonObject();
        json.addProperty("CardLocation", state.name());
        parentArray.add(json);

        Card card = null;
        while (handIter.hasNext()) {
            card = handIter.next();
            json = new JsonObject();

            json.addProperty("Suit", card.getSuit().toString());
            json.addProperty("Value", card.getValue().displaySymbol());

            getMyHand().addCard(card);
            cardArray.add(json);
        }

        parentArray.add(cardArray);
        json = new JsonObject();
        json.addProperty("HandValue", getMyHand().evaluateHand().name());

        parentArray.add(json);

        out.print(parentArray);
        out.flush();

        LOG.trace("Ending -- dealCommunityCards");
    }

    /**
     * @param out
     */
    private void initializeHands(PrintWriter out) {
        LOG.trace("Begin -- initializeHands");

        getDeck().shuffle();
        setMyHand(new Hand());

        // Get Initial Cards
        getMyHand().addCard(getDeck().getNextCard());
        getMyHand().addCard(getDeck().getNextCard());

        JsonArray parentArray = new JsonArray();

        JsonArray cardArray = new JsonArray();

        Iterator<Card> handIter = getMyHand().getCards().iterator();

        JsonObject json = new JsonObject();
        json.addProperty("CardLocation", "Player");
        parentArray.add(json);

        Card card = null;
        while (handIter.hasNext()) {
            card = handIter.next();
            json = new JsonObject();

            json.addProperty("Suit", card.getSuit().toString());
            json.addProperty("Value", card.getValue().displaySymbol());

            cardArray.add(json);
        }

        parentArray.add(cardArray);
        json = new JsonObject();
        json.addProperty("HandValue", getMyHand().evaluateHand().name());

        parentArray.add(json);

        out.print(parentArray);
        out.flush();
        LOG.trace("Ending -- initializeHands");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("Begin -- doPost");
        // TODO Auto-generated method stub
        LOG.debug("In doPost");
        LOG.trace("Ending -- enclosing_method");
    }

    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * @param deck
     *            the deck to set
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * @return the myHand
     */
    public Hand getMyHand() {
        return myHand;
    }

    /**
     * @param myHand
     *            the myHand to set
     */
    public void setMyHand(Hand myHand) {
        this.myHand = myHand;
    }

    @SuppressWarnings("serial")
    class InvalidHandStateException extends Exception {
    }
}
