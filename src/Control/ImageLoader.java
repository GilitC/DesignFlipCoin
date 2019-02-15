package Control;

import Model.Product;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

public class ImageLoader extends Task<ImageView> {
	
	static Integer id = 0;
	private Product _product;
	
	public ImageLoader (Product product)
	{
		_product = product;
	}

	@Override
	protected ImageView call() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(150 * id++);
		return new ImageView(_product.getPicture().toString());
	}
	
}
