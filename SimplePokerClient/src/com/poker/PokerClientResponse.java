package com.poker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class PokerClientResponse
 */
@WebServlet(description = "This Servlet will handle AJAX calls from the web client", urlPatterns = { "/PokerClientResponse" })
public class PokerClientResponse extends HttpServlet {
	private static Logger LOG = Logger.getLogger(PokerClientResponse.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PokerClientResponse() {
        super();
        // TODO Auto-generated constructor stub
        LOG.trace("Ending -- PokerClientResponse Constructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Begin -- doGet");
		LOG.debug("In do get");
		
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		PokerRunner pr = new PokerRunner();

		Deck deck = pr.init();
		Hand myHand = pr.getHand(deck);

		Iterator<Card> handIter = myHand.getCards().iterator();

		Card card = null;
		StringBuilder jsonString = new StringBuilder("[");
		
		LOG.debug("About to build string");
		
        while ( handIter.hasNext() ){
        	card = handIter.next();
        	jsonString.append("[" + card.getValue().displaySymbol() + ", " + card.getSuit() + " ]");
        	
        }
		jsonString.append("]");
		
		LOG.debug("Final String = " + jsonString.toString());
		out.print(jsonString.toString());
		out.flush();
		
		// TODO Auto-generated method stub
		LOG.trace("Ending -- doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Begin -- doPost");
		// TODO Auto-generated method stub
		LOG.debug("In doPost");
		LOG.trace("Ending -- enclosing_method");
	}

}
