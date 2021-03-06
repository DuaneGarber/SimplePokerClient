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
<title>Simple Poker Client</title>

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
	});

	var getNewCards = function() {
		//Erases the canvas
		
		var location = "";
		var handValue = "";
		
		$.get("response/PokerClientResponse", function(data) {

			location = data[0].CardLocation;
			var cards = data[1];
			handValue = data[2].HandValue;
			if(location == "Player"){
				$('td.current').removeClass('current');
				$('td.previous').removeClass('previous');
				$('#dealButton').val("Get Flop");
				canvas.width = canvas.width;		
				$.each(cards, function(i, cardObj) {
					if(i == 0 || i == 1){
						x = 225 + (55 * i);
						CardFunctions.drawCard(x, 300, cardObj.Value, cardObj.Suit);
					}
				});
				//CardFunctions.drawText(10, 200, "black", handValue);
			} else if(location == "FLOP"){
				$.each(cards, function(i, cardObj) {
					x = 130 + (55 * i);
					CardFunctions.drawCard(x, 150, cardObj.Value, cardObj.Suit);
					x += 55;
				});
				
				//CardFunctions.drawText(10, 220, "black", handValue);
				$('#dealButton').val("Get Turn");
			}
			else if(location == "TURN"){
				$.each(cards, function(i, cardObj) {
					CardFunctions.drawCard(295, 150, cardObj.Value, cardObj.Suit);
				});
				//CardFunctions.drawText(10, 240, "black", handValue);
				$('#dealButton').val("Get River");
			}
			else if(location == "RIVER"){
				$.each(cards, function(i, cardObj) {
					CardFunctions.drawCard(350, 150, cardObj.Value, cardObj.Suit);
				});
				//CardFunctions.drawText(10, 260, "black", handValue);
				$('#dealButton').val("New Hand");
			}
			
			CardFunctions.updateHandRank(handValue.toLowerCase());
		});
	};
</script>

</head>
<body>

	<canvas id="PlayingTable" width="600" height="300"> </canvas>
	<span id="handRanking">
	<table id="tableHandRanking">
		<tr>
			<td id="royal_flush" class="rank"> Royal Flush </td>
		</tr>
		<tr>
			<td id="straight_flush" class="rank"> Straight Flush </td>
		</tr>
		<tr>
			<td id="four_of_a_kind" class="rank"> Four of a Kind </td>
		</tr>
		<tr>
			<td id="full_house" class="rank"> Full House </td>
		</tr>
		<tr>
			<td id="flush" class="rank"> Flush </td>
		</tr>
		<tr>
			<td id="straight" class="rank"> Straight </td>
		</tr>
		<tr>
			<td id="three_of_a_kind" class="rank"> Three Of a Kind </td>
		</tr>
		<tr>
			<td id="two_pair" class="rank"> Two Pair </td>
		</tr>
		<tr>
			<td id="pair" class="rank"> Pair </td>
		</tr>
		<tr>
			<td id="high_card" class="rank"> High Card </td>
		</tr>
		
	</table>
	</span>
	<br>
	<input type="button" id="dealButton" value="Deal"
		onclick="getNewCards()" />
</body>
</html>