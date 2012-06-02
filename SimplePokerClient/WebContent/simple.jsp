<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="com.poker.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
Date d = new Date(session.getLastAccessedTime());
PokerRunner pr = new PokerRunner();

Deck deck = pr.init();
Hand myHand = pr.getHand(deck);

Iterator<Card> handIter = myHand.getCards().iterator();

Card card = null;
%>
<title>Simple JSP</title>

<link rel="stylesheet" type="text/css" href="css/poker.css" />
<script SRC="javascript/CardFunctions.js"></script>
<script SRC="javascript/jquery/jquery-1.7.2.js"></script>
<script>
	//Global Vars

	var canvas = null;
	var context = null;

	$(document).ready(function() {

		canvas = document.getElementById("PlayingTable");
		context = canvas.getContext("2d");
		
// 		CardFunctions.drawCard(225, 300, "A", "HEARTS");
// 		CardFunctions.drawCard(280, 300, "A", "DIAMONDS");
		
// 		CardFunctions.drawCard(130, 150, "K", "HEARTS");
// 		CardFunctions.drawCard(185, 150, "K", "DIAMONDS");
// 		CardFunctions.drawCard(240, 150, "K", "CLUBS");
// 		CardFunctions.drawCard(295, 150, "K", "SPADES");
// 		CardFunctions.drawCard(350, 150, "A", "SPADES");
	});

	var getNewCards = function() {
		//Erases the canvas
		canvas.width = canvas.width;

		$.get("response/PokerClientResponse", function(data) {
			$.each(data, function(index, value) {
				if ($.isArray(value)) {
					var x = 7;
					$.each(value, function(i, cardObj) {
						if(i == 0 || i == 1){
							x = 225 + (55 * i);
							CardFunctions.drawCard(x, 300, cardObj.Value, cardObj.Suit);
						} else {
							x = 130 + (55 * (i - 2));
							CardFunctions.drawCard(x, 150, cardObj.Value, cardObj.Suit);
							x += 55;
						}
					});
				} else {
					CardFunctions.drawText(10, 250, "black", value.HandValue);
				}

			});
		});
	};
</script>

</head>
<body>

	<canvas id="PlayingTable" width="600" height="300"> </canvas>
	<br>
	<input type="button" id="dealButton" value="Deal"
		onclick="getNewCards()" />
</body>
</html>