import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    ArrayList<ImageView> imageNames = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //initialize starting 9 images, these images represent my hobbies and passions
        ImageView img1 = new ImageView(new Image("Images/chess.jpg"));
        img1.setFitHeight(250);
        img1.setFitWidth(300);

        ImageView img2 = new ImageView(new Image("Images/drone.jpg"));
        img2.setFitHeight(250);
        img2.setFitWidth(300);

        ImageView img3 = new ImageView(new Image("Images/games.jpg"));
        img3.setFitHeight(250);
        img3.setFitWidth(300);

        ImageView img4 = new ImageView(new Image("Images/hockey.jpg"));
        img4.setFitHeight(250);
        img4.setFitWidth(300);

        ImageView img5 = new ImageView(new Image("Images/martial arts.jpg"));
        img5.setFitHeight(250);
        img5.setFitWidth(300);

        ImageView img6 = new ImageView(new Image("Images/piano.jpg"));
        img6.setFitHeight(250);
        img6.setFitWidth(300);

        ImageView img7 = new ImageView(new Image("Images/soccer.jpg"));
        img7.setFitHeight(250);
        img7.setFitWidth(300);

        ImageView img8 = new ImageView(new Image("Images/tennis.jpg"));
        img8.setFitHeight(250);
        img8.setFitWidth(300);

        ImageView img9 = new ImageView(new Image("Images/programming.jpg"));
        img9.setFitHeight(250);
        img9.setFitWidth(300);

        //add the images to the arraylist for future use
        imageNames.add(img1);
        imageNames.add(img2);
        imageNames.add(img3);
        imageNames.add(img4);
        imageNames.add(img5);
        imageNames.add(img6);
        imageNames.add(img7);
        imageNames.add(img8);
        imageNames.add(img9);


        //start the main stage on which the program will run, and pass the arraylist of image names as well
        main(primaryStage, imageNames);

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void main(Stage primaryStage, ArrayList<ImageView> imageNames){
        int width = 1020;
        int height = 950;

        //this is arraylist will be represeting togglebuttons placed behind the imageviews, that way we can select the images
        ArrayList<ToggleButton> toggleButtons = new ArrayList<>();

        //we need to make sure we have the right number of buttons depending on the size of the arraylist
        for(int i = 0; i < imageNames.size(); i++) { //loop through the imagename arraylist
            toggleButtons.add(new ToggleButton("", imageNames.get(i))); //instantiate a new togglebutton
            toggleButtons.get(i).setStyle("-fx-background-color:white"); //make sure its background fits the color of the application
            int a = i;
            toggleButtons.get(i).setOnAction(actionEvent -> {
                if(toggleButtons.get(a).isSelected())   //check if the toggle is on
                    toggleButtons.get(a).setStyle("-fx-background-color:lightskyblue"); //if image is selected, we want to change the outer border to blue
                else //if toggle is false
                    toggleButtons.get(a).setStyle("-fx-background-color:white"); //if the iamge is not selected, we want to change the color back to white
            });
            toggleButtons.get(i).setPrefSize(320,270);
        }


        //we add a few functional buttons here
        Button addImage = new Button("Add Image");
        addImage.setStyle("-fx-background-color:white");

        Button deleteSelectedImages = new Button("Delete Selected Image(s)");
        deleteSelectedImages.setStyle("-fx-background-color:white");

        Button selectAll = new Button("Select All Images");
        selectAll.setStyle("-fx-background-color:white");

        Button deselectAll = new Button("Deselect All Images");
        deselectAll.setStyle("-fx-background-color:white");

        HBox hBox = new HBox(20, addImage, deleteSelectedImages,selectAll, deselectAll); //add the buttons to a horizontal layout box
        hBox.setStyle("-fx-background-color:lightskyblue");
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.setPadding(new Insets(20,0,20,0));

        VBox vBox = new VBox(20,hBox);
        vBox.setStyle("-fx-background-color:white");
        vBox.setPadding(new Insets(0,20,0,20));

        addImage.setOnAction(actionEvent ->  {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll( //make sure we have as many image extensions as possible, we don't need to check whether the user selected an image
                    new FileChooser.ExtensionFilter("Text Files", "*.jpg")
                    ,new FileChooser.ExtensionFilter("HTML Files", "*.png")
                    ,new FileChooser.ExtensionFilter("HTML Files", "*.jpeg"));


            List<File> list = fileChooser.showOpenMultipleDialog(primaryStage);
            try {
                if (list != null) { //we loop through the list of images the user selected
                    for (File file : list) {
                        ImageView temp = new ImageView(file.toURI().toString()); //create an imageview
                        temp.setFitHeight(250);
                        temp.setFitWidth(300);
                        imageNames.add(temp); //add it to the arraylist
                        main(primaryStage, imageNames); //rerun the main method, thus adding the image to the repository
                    }
                }

            }catch (Exception e){}

        });

        deleteSelectedImages.setOnAction(actionEvent -> {
            ImageView[] temp = new ImageView[imageNames.size()]; //because we're dealing with an arraylist, deleting images becomes difficult. It's easier to use a regular array
            for(int x = 0; x < imageNames.size(); x++){ //convert arraylist into a temporary array
               temp[x] = imageNames.get(x);
            }
            for(int x = 0; x<temp.length; x++){ //remove selected images
                if(toggleButtons.get(x).isSelected())
                    temp[x] = null;
            }
            imageNames.removeAll(imageNames); //convert the array back into the arraylist
            for(int x = 0; x < temp.length; x++){
                if(temp[x]!=null)
                    imageNames.add(temp[x]);
            }

            main(primaryStage, imageNames); //rerun the main method, removing the image from the repository
        });

        selectAll.setOnAction(actionEvent -> {
            for(int x = 0; x < toggleButtons.size(); x++) {
                toggleButtons.get(x).setSelected(true); //select all the buttons
                toggleButtons.get(x).setStyle("-fx-background-color: lightskyblue");
            }
        });

        deselectAll.setOnAction(actionEvent -> {
            for(int x = 0; x < toggleButtons.size(); x++) {
                toggleButtons.get(x).setSelected(false); //deselect all the buttons
                toggleButtons.get(x).setStyle("-fx-background-color:white");
            }
        });

        for(int i = 0; i < imageNames.size(); i += 3){ //here we group up the images in groups of 3

            HBox temp = new HBox(10);

            temp.getChildren().add(toggleButtons.get(i));

            if(imageNames.size()-i>=3 || (imageNames.size()-i)==2) { //we verify whether or not there are at least 2 images in the last group
                temp.getChildren().add(toggleButtons.get(i+1));
            }
            if(imageNames.size()-i>=3 || (imageNames.size()-i)==3) { //we verify whether or not there are 3 images in the last group
                temp.getChildren().add(toggleButtons.get(i+2));
            }

            temp.setAlignment(Pos.BASELINE_CENTER);
            vBox.getChildren().add(temp);
            temp.getChildren().removeAll();
        }

        ImageView whiteBackground = new ImageView(new Image("Images/white-background.jpg"));
        whiteBackground.setFitWidth(982);

        if(imageNames.size()==0){ //make sure the background stays white even with no images
            whiteBackground.setFitHeight(840);
            vBox.getChildren().add(whiteBackground);
        }else if(imageNames.size()<=3){ //make sure the background stays white even with no more than 3 images
            whiteBackground.setFitHeight(560);
            vBox.getChildren().add(whiteBackground);
        }else if(imageNames.size()<=6){ //make sure the background stays white even with no more than 6 images
            whiteBackground.setFitHeight(280);
            vBox.getChildren().add(whiteBackground);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        HBox main = new HBox();
        main.getChildren().addAll(vBox, scrollPane);
        main.setStyle("-fx-background-color:white");
        primaryStage.setTitle("Shopify Challenge: Image Repository");
        primaryStage.setScene(new Scene(main, width, height));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("Images/white-background.jpg"));
        primaryStage.show();
    }
}
