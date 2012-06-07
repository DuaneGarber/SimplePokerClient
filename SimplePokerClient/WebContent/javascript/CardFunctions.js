var CardFunctions = {
	
		//All Card Suits Courtesy of http://www.html5canvastutorials.com/labs/html5-canvas-playing-card-suits/
		
		drawSpade : function(x, y, topWidth, spadeHeight, bottomWidth, bottomHeight){
            
            context.beginPath();
            context.moveTo(x, y);
            // top left of spade
            context.bezierCurveTo(x, y + spadeHeight / 2, x - topWidth / 2, y + spadeHeight / 2, x - topWidth / 2, y + spadeHeight);
            // bottom left of spade
            context.bezierCurveTo(x - topWidth / 2, y + spadeHeight * 1.3, x, y + spadeHeight * 1.3, x, y + spadeHeight);
            // bottom right of spade
            context.bezierCurveTo(x, y + spadeHeight * 1.3, x + topWidth / 2, y + spadeHeight * 1.3, x + topWidth / 2, y + spadeHeight);
            // top right of spade
            context.bezierCurveTo(x + topWidth / 2, y + spadeHeight / 2, x, y + spadeHeight / 2, x, y);
            context.closePath();
            context.fillStyle = "black";
            context.fill();
            context.beginPath();
            // bottom of spade
            context.moveTo(x, y + spadeHeight);
            context.quadraticCurveTo(x, y + spadeHeight + bottomHeight, x - bottomWidth / 2, y + spadeHeight + bottomHeight);
            context.lineTo(x + bottomWidth / 2, y + spadeHeight + bottomHeight);
            context.quadraticCurveTo(x, y + spadeHeight + bottomHeight, x, y + spadeHeight);
            context.closePath();
            context.fillStyle = "black";
            context.fill();
        },
        
        drawHeart : function(x, y, width, height){
            context.beginPath();
            context.moveTo(x, y);
            context.bezierCurveTo(x, y - 10, x - width / 2, y - 10, x - width / 2, y);
            context.bezierCurveTo(x - width / 2, y + height / 2, x, y + height / 2, x, y + height);
            context.bezierCurveTo(x, y + height / 2, x + width / 2, y + height / 2, x + width / 2, y);
            context.bezierCurveTo(x + width / 2, y - 10, x, y - 10, x, y);
            
            context.closePath();
            context.fillStyle = "red";
            context.fill();
        },
        
        drawClub : function(x, y, circleRadius, bottomWidth, bottomHeight){
            context.fillStyle = "black";
            
            context.beginPath();
            context.arc(x, y + circleRadius, circleRadius, 0, 2 * Math.PI, false);
            context.fill();
            
            context.beginPath();
            context.arc(x + circleRadius, y + circleRadius * 1.5 + circleRadius, circleRadius, 0, 2 * Math.PI, false);
            context.fill();
            
            context.beginPath();
            context.arc(x - circleRadius, y + circleRadius * 1.5 + circleRadius, circleRadius, 0, 2 * Math.PI, false);
            context.fill();
            
            context.beginPath();
            context.arc(x, y + (circleRadius * 2), circleRadius / 2, 0, 2 * Math.PI, false);
            context.fill();
            
            // bottom of club
            context.moveTo(x, y + circleRadius * 2.6);
            context.quadraticCurveTo(x, y + circleRadius * 2.6 + bottomHeight, x - bottomWidth / 2, y + circleRadius * 2.6 + bottomHeight);
            context.lineTo(x + bottomWidth / 2.6, y + circleRadius * 2.6 + bottomHeight);
            context.quadraticCurveTo(x, y + circleRadius * 2.6 + bottomHeight, x, y + circleRadius * 2.6);
            context.fill();
        },
        
        drawDiamond : function(x, y, width, height){
            context.beginPath();
            context.moveTo(x, y);
            context.lineTo(x - width / 2, y + height / 2);
            context.lineTo(x, y + height);
            context.lineTo(x + width / 2, y + height / 2);
            context.closePath();
            context.fillStyle = "red";
            context.fill();
        },
        
        // END All Card Suits Courtesy of http://www.html5canvastutorials.com/labs/html5-canvas-playing-card-suits/
		
		drawCardBase : function(x, y, width, height){
			context.beginPath();
			context.moveTo(x, y);
			context.lineTo(x + width, y);
			context.lineTo(x + width, y - height);
			context.lineTo(x, y - height);
			context.closePath();
			context.fillStyle = "white";
			context.strokeStyle = "black";
			context.fill();
		
		},
		drawText : function(x, y, color, text) {
			context.fillStyle = color;
			context.font = 'bold 21px Calibri';
			context.textBaseline = 'bottom';
			context.fillText(text, x, y);
		},
		
		drawCard : function(x, y, text, suit) {
			CardFunctions.drawCardBase(x , y, 50, 70);
			var color = "";
			
			switch (suit){
				case "CLUBS":
					CardFunctions.drawClub(x+35, y-68, 4, 10, 8);
					color = "black";
					break;
				case "DIAMONDS":
					CardFunctions.drawDiamond(x+35, y-68, 16, 20);
					color = "red";
					break;
				case "HEARTS":
					CardFunctions.drawHeart(x+35, y-60, 14, 14);
					color = "red";
					break;
				case "SPADES":
					CardFunctions.drawSpade(x+35, y-70, 15, 15, 12, 7);
					color = "black";
					break;
			}
			
			CardFunctions.drawText(x + 5, y - 45, color, text);
			
					
		},
		
		updateHandRank : function(target_id) {
			
			if(target_id != $("td.current").attr("id")){
				$("td.current").removeClass("current").addClass("previous");
			}
			
			$("#" + target_id).addClass("current");
		}
};