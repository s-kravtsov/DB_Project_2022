class Room {
	int room_code;

	Category category;


	Sale[] sales;
	BaseUser[] bidders;

	public Room(int _room_code, int category_code) {

	}

	void conductSale(Sale sale) {
		float top_price = sale.getStartPrice();
		BaseUser top_bidder = null;

		int empty_rounds = 3;

		Bid new_bid = null;
		while (empty_rounds > 0) {
			new_bid = announceRound(sale.getLotDescription(), top_price);
			if (new_bid.getValue() == 0) {
				empty_rounds -= 1;
			}
		}
	}

	Bid announceRound(LotDescription lot_description, float curernt_price) {
		float best_price = 0;
		Bid best_bid = null;
		Bid current_bid = null;
		for (BaseUser bidder : bidders) {
			current_bid = bidder.makeBid(lot_description);
			if (current_bid.getValue() > best_price) {
				best_price = current_bid.getValue();
				best_bid = current_bid;
			}
		}

	}

}
