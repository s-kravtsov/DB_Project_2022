function placeBid(el) {
		var sbmt = document.getElementById("sale_code");
		sbmt.value = el.getAttribute("salecode");
		
		var quantity = document.getElementById("quantity");
		quantity.value = el.getAttribute("quantity");
		quantity.type = "hidden";
		
		var quantity_label = document.getElementById("quantity_label");
		quantity_label.style.display = "none";
		
		var amount = document.getElementById("amount");
		amount.value = el.getAttribute("amount");
		amount.type = "hidden";
		
		var amount_label = document.getElementById("amount_label");
		amount_label.innerText = el.getAttribute("amount");;
		
		var bform = document.getElementById("bid_form");
		bform.className = "BidFormWrapper On"
}

function closeForm() {
	var bform = document.getElementById("bid_form");
	bform.className = "BidFormWrapper Off"
}