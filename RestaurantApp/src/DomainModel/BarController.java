package DomainModel;
import java.util.ArrayList;
import java.util.Observable;

public class BarController extends RestaurantPageController{

	public BarController(Observable obs) {
		obs.addObserver(this);
		orderRecords = new ArrayList<OrderRecord>();
	}
	
	@Override
	public void getOrder(Order order) {
		for(OrderRecord ord : order.getRecords()) {
			if(ord.getDish().getKindOfDish() == KindOfDish.DESSERT ||
			   ord.getDish().getKindOfDish() == KindOfDish.DRINK) {
				orderRecords.add(ord);
			}
		}
	}
}
