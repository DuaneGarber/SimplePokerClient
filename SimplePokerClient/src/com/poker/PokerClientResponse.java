package com.poker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private String var = "Original";
	private Deck deck = null;
	private Hand myHand = null;
	private Hand communityHand = null;
	
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Begin -- doGet");
		LOG.debug("In do get VAR == " + var);

		var += " -- Do Get -- ";
		HttpSession session = request.getSession();
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json
		// object to the output stream
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following,
		// it will return your json object
		
		Object obj = session.getAttribute("handState");
		if (obj == null || obj == "newHand"){
			LOG.debug("New Hand or null");
			
			getDeck().shuffle();
			setMyHand(new Hand());
			setCommunityHand(new Hand());
			
			session.setAttribute("handState", "Flop");
			//Get Initial Cards
			getMyHand().addCard(getDeck().getNextCard());
			getMyHand().addCard(getDeck().getNextCard());
			sendMyHand(out);
			
		} else {
			session.setAttribute("handState", null);
			//Get Initial Cards
			getCommunityHand().addCard(getDeck().getNextCard());
			getCommunityHand().addCard(getDeck().getNextCard());
			getCommunityHand().addCard(getDeck().getNextCard());
			getCommunityHand().addCard(getDeck().getNextCard());
			getCommunityHand().addCard(getDeck().getNextCard());
			
			getMyHand().addHand(getCommunityHand());
			
			sendCommunityHand(out);
		}
		
//		JsonArray arrayObj = new JsonArray();
//
//		 
//		
//		PokerRunner pr = new PokerRunner();
//
//		Deck deck = pr.init();
//		Hand myHand = pr.getHand(deck);
//
//		Iterator<Card> handIter = myHand.getCards().iterator();
//
//		Card card = null;
//
//
//		// LOG.debug("GSON = " + gson.toJson(myHand));
//
//		JsonArray parentArray = new JsonArray();
//
//		JsonArray cardArray = new JsonArray();
//		JsonObject json = null;
//		while (handIter.hasNext()) {
//			card = handIter.next();
//			json = new JsonObject();
//
//			json.addProperty("Suit", card.getSuit().toString());
//			json.addProperty("Value", card.getValue().displaySymbol());
//
//			cardArray.add(json);
//
//			// json.addProperty("Card" , card.getValue().displaySymbol());
//			// json.addProperty("Suit", card.getSuit().name());
//
//			arrayObj.add(json);
//		}
//
//		json = new JsonObject();
//		json.addProperty("HandValue", myHand.evaluateHand().name());
//		parentArray.add(cardArray);
//		parentArray.add(json);
//
//		out.print(parentArray);
//		out.flush();

		LOG.trace("Ending -- doGet");
	}

	/**
	 * @param out
	 */
	private void sendCommunityHand(PrintWriter out) {
		LOG.trace("Begin -- sendCommunityHand");
		JsonArray parentArray = new JsonArray();
		
		JsonArray cardArray = new JsonArray();
		
		
		Iterator<Card> handIter = getCommunityHand().getCards().iterator();
		
		JsonObject json = new JsonObject();
		json.addProperty("CardLocation", "Community");
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
		
		LOG.trace("Ending -- sendCommunityHand");
	}

	/**
	 * @param out
	 */
	private void sendMyHand(PrintWriter out) {
		LOG.trace("Begin -- sendMyHand");
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
		LOG.trace("Ending -- sendMyHand");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
	 * @param deck the deck to set
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
	 * @param myHand the myHand to set
	 */
	public void setMyHand(Hand myHand) {
		this.myHand = myHand;
	}

	/**
	 * @return the community
	 */
	public Hand getCommunityHand() {
		return communityHand;
	}

	/**
	 * @param community the community to set
	 */
	public void setCommunityHand(Hand community) {
		this.communityHand = community;
	}

}
