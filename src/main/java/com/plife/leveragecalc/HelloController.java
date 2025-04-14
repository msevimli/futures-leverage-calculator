package com.plife.leveragecalc;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label symbolPrice;

    @FXML
    private Text leverage;

    @FXML
    private Text pProfit;

    @FXML
    private Text pLost;

    @FXML
    private ComboBox symbol;

    @FXML
    private  ComboBox position;

    @FXML
    private TextField riskLevel;

    @FXML
    private TextField margin;

    @FXML
    private TextField sLoss;

    @FXML
    private  TextField tProfit;

    public Item priceData;

    @FXML
    public void initialize() {
        symbolPrice.setText("");
        leverage.setText("");
        pProfit.setText("0.00");
        pLost.setText("0.00");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected  void onCalcButtonClick() {
        double _margin = Double.valueOf(margin.getText()) * Double.valueOf(riskLevel.getText()) / 100;
       if(position.getValue().equals("BUY")) {
           double lev = calculateLeverageLong(Double.valueOf(priceData.getMarkPrice()), Double.valueOf(sLoss.getText()), Double.valueOf(margin.getText()));
           System.out.println(Double.valueOf(priceData.getMarkPrice()));
           System.out.println(Double.valueOf(margin.getText()));
           leverage.setText(String.format("%.2f", lev));
           double profit = calculateProfit(Double.valueOf(margin.getText()),lev,Double.valueOf(tProfit.getText()));
           pProfit.setText(String.format("%.2f", profit));
           double loss = calculateLoss(Double.valueOf(margin.getText()),lev,Double.valueOf(sLoss.getText()));
           pLost.setText(String.format("%.2f",loss));
       } else if( position.getValue().equals("SELL")) {
           double lev = calculateLeverageShort(Double.valueOf(priceData.getMarkPrice()), Double.valueOf(sLoss.getText()), Double.valueOf(margin.getText()));
           leverage.setText(String.format("%.2f", lev));
       }


    }
    @FXML
    protected  void onSymbolChange()  {
        String selectedSymbol = (String) symbol.getValue();
        //symbolPrice.setText(selectedSymbol);
        ApiRequest request = new ApiRequest();
        try {
            ArrayList<Item> items = request.apiReq(selectedSymbol);
            priceData = items.getFirst();;
            symbolPrice.setText(priceData.getMarkPrice());
            sLoss.setText(priceData.getMarkPrice());
            tProfit.setText(priceData.getMarkPrice());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double calculateLeverageLong(double entryPrice, double stopLoss, double margin) {
       // return (margin * entryPrice) / (margin * (entryPrice - stopLoss) / entryPrice);

        return entryPrice / (entryPrice - stopLoss);

    }
    public static double calculateLeverageShort(double entryPrice, double stopLoss, double margin) {
        //return (margin * entryPrice) / (margin * (stopLoss - entryPrice) / entryPrice);
        return entryPrice / (stopLoss - entryPrice);
    }
    public  double calculateProfit(double margin, double leverage, double tProfit) {
        return (((margin / Double.valueOf(priceData.getMarkPrice())) * tProfit)-margin)* leverage;
    }
    public  double calculateLoss(double margin, double leverage, double sLoss) {
        return (margin-(margin / Double.valueOf(priceData.getMarkPrice()) * sLoss)) * leverage;
    }
}