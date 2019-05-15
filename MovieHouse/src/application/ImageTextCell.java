
package application;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ImageTextCell extends ListCell<Product> {
    
//    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    
    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null); // doesn't show anything
        } else {
            // create the cell
            HBox hbox = new HBox(8.0); // Gap between controls
            hbox.setAlignment(Pos.CENTER_RIGHT);

            // set cover image
            ImageView coverImageView = new ImageView(new Image(item.getCover_image()));
            coverImageView.setPreserveRatio(false);
            coverImageView.setFitHeight(32.0);
            coverImageView.setFitWidth(32.0);

            // set text
            Label movie_name = new Label(item.getMovie_name());
            movie_name.setFont(Font.font("System", FontWeight.BOLD, 12));
            movie_name.setTextAlignment(TextAlignment.LEFT);
            
            Label genre = new Label(item.getGenre());
            genre.setFont(Font.font("System", 11));
            genre.setTextAlignment(TextAlignment.LEFT);
            
            Label price = new Label();
           // String currencyPrice = currencyFormatter.format(item.getPrice());
            price.setText("₹ "+item.getPrice());
            price.setFont(Font.font("System", FontWeight.BOLD, 12));
                       
            VBox vb = new VBox(2.0);
            
            Pane pane = new Pane(); // for space b/w name and price
            
            vb.getChildren().addAll(movie_name, genre);
            
            hbox.getChildren().addAll(coverImageView, vb, pane, price);
            HBox.setHgrow(pane, Priority.ALWAYS);
            setGraphic(hbox);
            setPrefWidth(USE_PREF_SIZE);
        }
    }
}
