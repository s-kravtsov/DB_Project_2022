function placeBid(el) {
		var sbmt = document.getElementById("sale_code");
		sbmt.value = el.getAttribute("salecode");
		
		var bform = document.getElementById("bid_form");
		bform.className = "BidFormWrapper On"
	}
	function closeForm() {
		var bform = document.getElementById("bid_form");
		bform.className = "BidFormWrapper Off"
	}