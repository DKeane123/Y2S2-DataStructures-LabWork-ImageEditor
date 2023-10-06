package controllers;

import com.example.exercise1imageeditor.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class HelloController {

    Image originalImage;
    int width;
    int height;

    public int[] setArray;

    ColorAdjust mainColorAdjust = new ColorAdjust();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    XYChart.Series series1=new XYChart.Series();

    @FXML
    private ImageView MainImageView = new ImageView();
    @FXML
    private Text NameProperty;
    @FXML
    private Text SizeProperty;
    @FXML
    private Slider changeHue;
    @FXML
    private Slider changeSaturation;
    @FXML
    private Slider changeBrightness;
    @FXML
    private MenuItem SelectFile;
    @FXML
    private BarChart<String, Number> barChart = new BarChart<>(xAxis,yAxis);

    public void loadEventHandler(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            MainImageView.setImage(image);
            changePictureInfo();
            originalImage = image;
        }
    }

    public void changePictureInfo() {
        String pictureWidth = String.valueOf(MainImageView.getImage().getWidth());
        String pictureHeight = String.valueOf(MainImageView.getImage().getHeight());
        String pictureName = MainImageView.getImage().getUrl();

        NameProperty.setText(pictureName);
        SizeProperty.setText(pictureWidth + " x " + pictureHeight);
    }

    public void ImageToGrayscale() {
        resetImage();
        WritableImage writableImage = new WritableImage((int) MainImageView.getImage().getWidth(), (int) MainImageView.getImage().getHeight());
        PixelReader pixelReader = MainImageView.getImage().getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        width = (int) MainImageView.getImage().getWidth();
        height = (int) MainImageView.getImage().getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                pixelWriter.setColor(j, i, pixelReader.getColor(j, i).grayscale());
            MainImageView.setImage(writableImage);
        }
    }

    public void ImageToRed() {
        resetImage();
        WritableImage writableImage = new WritableImage((int) MainImageView.getImage().getWidth(), (int) MainImageView.getImage().getHeight());
        PixelReader pixelReader = MainImageView.getImage().getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        width = (int) MainImageView.getImage().getWidth();
        height = (int) MainImageView.getImage().getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color oldColor = pixelReader.getColor(j, i);
                double red = (oldColor.getRed());
                double green = (oldColor.getGreen() * 0);
                double blue = (oldColor.getBlue() * 0);
                Color newColor = Color.color(red, green, blue);
                pixelWriter.setColor(j, i, newColor);
                MainImageView.setImage(writableImage);
            }
        }
    }

    public void ImageToGreen() {
        resetImage();
        WritableImage writableImage = new WritableImage((int) MainImageView.getImage().getWidth(), (int) MainImageView.getImage().getHeight());
        PixelReader pixelReader = MainImageView.getImage().getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        width = (int) MainImageView.getImage().getWidth();
        height = (int) MainImageView.getImage().getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color oldColor = pixelReader.getColor(j, i);
                double red = (oldColor.getRed() * 0);
                double green = (oldColor.getGreen());
                double blue = (oldColor.getBlue() * 0);
                Color newColor = Color.color(red, green, blue);
                pixelWriter.setColor(j, i, newColor);
                MainImageView.setImage(writableImage);

            }
        }
    }

    public void ImageToBlue() {
        resetImage();
        WritableImage writableImage = new WritableImage((int) MainImageView.getImage().getWidth(), (int) MainImageView.getImage().getHeight());
        PixelReader pixelReader = MainImageView.getImage().getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        width = (int) MainImageView.getImage().getWidth();
        height = (int) MainImageView.getImage().getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color oldColor = pixelReader.getColor(j, i);
                double red = (oldColor.getRed() * 0);
                double green = (oldColor.getGreen() * 0);
                double blue = (oldColor.getBlue());
                Color newColor = Color.color(red, green, blue);
                pixelWriter.setColor(j, i, newColor);
                MainImageView.setImage(writableImage);
            }
        }
    }

    public void hue() {
        mainColorAdjust.setHue(changeHue.getValue());
        MainImageView.setEffect(mainColorAdjust);
    }

    public void saturation() {
        mainColorAdjust.setSaturation(changeSaturation.getValue());
        MainImageView.setEffect(mainColorAdjust);
    }

    public void brightness() {
        mainColorAdjust.setBrightness(changeBrightness.getValue());
        MainImageView.setEffect(mainColorAdjust);
    }

    public void plotHistogram() {
        series1.getData().clear();
        barChart.getData().remove(series1);
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Colour")));
        yAxis.setLabel("Frequency");
        series1.setName("Colour Frequencies");

        int[][][] ch = new int[4][4][4];
        PixelReader pixelReader = MainImageView.getImage().getPixelReader();
        width = (int) MainImageView.getImage().getWidth();
        height = (int) MainImageView.getImage().getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int color = pixelReader.getArgb(j, i);
                int alpha = (color & 0xff000000) >> 24;
                int red = (color & 0x00ff0000) >> 16;
                int green = (color & 0x0000ff00) >> 8;
                int blue = color & 0x000000ff;
                ch[red / 64][green / 64][blue / 64]++;

            }
        }
        for (int i = 0; i < ch.length; i++)
            for (int j = 0; j < ch[i].length; j++)
                for (int p = 0; p < ch[i][j].length; p++) {
                    System.out.println("t[" + i + "][" + j + "][" + p + "] = " + ch[i][j][p]);
                    series1.getData().add(new XYChart.Data(i+"/"+j+"/"+p,ch[i][j][p]));
                }
        barChart.getData().add(series1);
    }


    public void resetImage() {
        MainImageView.setImage(originalImage);
        changeHue.setValue(0);
        changeBrightness.setValue(0);
        changeSaturation.setValue(0);
    }

    public void endProgram() {
        HelloApplication.primaryStage.close();
    }

    public void initialize() {
        changeHue.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("Hue: " + changeHue.getValue());
                    hue();
                });
        changeSaturation.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("Saturation: " + changeSaturation.getValue());
                    saturation();
                });
        changeBrightness.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("Brightness: " + changeBrightness.getValue());
                    brightness();
                });
    }

}