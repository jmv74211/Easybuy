package com.jmv74211.easybuy.data;

import android.content.Context;

import com.jmv74211.easybuy.models.Product;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductData {

  private static ProductData instance;
  private Context context;
  private ArrayList<Product> products = new ArrayList<>();

  // -----------------------------------------------------------------------------------------------

  private ProductData(Context context) {
    this.products = new ArrayList<Product>(
      Arrays.asList(
            new Product(1,context.getResources().getString(R.string.peachJam), 1.05f, "440g", 1),
            new Product(2,context.getResources().getString(R.string.semiSkimmedMilk), 0.58f, "1L", 1),
            new Product(3,context.getResources().getString(R.string.margarineWithSalt), 0.8f, "500g", 1),
            new Product(4,context.getResources().getString(R.string.naturalYogurts), 0.74f, "6U", 1),
            new Product(5,context.getResources().getString(R.string.whiteBread), 0.59f, "16U", 2),
            new Product(6,context.getResources().getString(R.string.loafOfBread), 0.45f, "1U", 2),
            new Product(7,context.getResources().getString(R.string.sandwichBread), 1f, "5U", 2),
            new Product(8,context.getResources().getString(R.string.bigEggs), 1.45f, "12U", 2),
            new Product(9,context.getResources().getString(R.string.breadWithTomato), 0.9f, "170g", 2),
            new Product(10,context.getResources().getString(R.string.sugar), 0.69f, "1000g", 3),
            new Product(11,context.getResources().getString(R.string.mayonnaise), 1.4f, "560cl", 4),
            new Product(12,context.getResources().getString(R.string.ketchup), 0.8f, "560cl", 4),
            new Product(13,context.getResources().getString(R.string.salt), 0.21f, "1000g", 4),
            new Product(14,context.getResources().getString(R.string.spaghetti), 0.75f, "1000g", 5),
            new Product(15,context.getResources().getString(R.string.cookedChickpea), 0.54f, "570g", 5),
            new Product(16,context.getResources().getString(R.string.lentilPardina), 1.49f, "1kg", 5),
            new Product(17,context.getResources().getString(R.string.roundRice), 0.79f, "1kg", 5),
            new Product(18,context.getResources().getString(R.string.sunflowerOil), 0.99f, "1L", 4),
            new Product(19,context.getResources().getString(R.string.burgers), 3.35f, "6U", 6),
            new Product(20,context.getResources().getString(R.string.mincedPorkMeat), 2.4f, "500g", 6),
            new Product(21,context.getResources().getString(R.string.hindquarter), 4.21f, "5U", 6),
            new Product(22,context.getResources().getString(R.string.chorizo), 1.97f, "4U", 6),
            new Product(23,context.getResources().getString(R.string.porkTacos), 1.96f, "0.503kg", 6),
            new Product(24,context.getResources().getString(R.string.veal), 3.76f, "0.495kg", 6),
            new Product(25,context.getResources().getString(R.string.foodColoring), 0.59f, "85g", 6),
            new Product(26,context.getResources().getString(R.string.bayLeaf), 0.66f, "12g", 6),
            new Product(27,context.getResources().getString(R.string.serranoHam), 1.99f, "120g", 7),
            new Product(28,context.getResources().getString(R.string.slicedCheese), 1.88f, "8U", 7),
            new Product(29,context.getResources().getString(R.string.cheeseSausages), 1.49f, "4U", 7),
            new Product(30,context.getResources().getString(R.string.turkeyBreast), 1.79f, "200g", 7),
            new Product(31,context.getResources().getString(R.string.pizzaBarbecue), 1.99f, "415g", 7),
            new Product(32,context.getResources().getString(R.string.wedgeCheese), 2.47f, "0.338kg", 7),
            new Product(33,context.getResources().getString(R.string.potatoOmelette), 1.85f, "600g", 7),
            new Product(34,context.getResources().getString(R.string.baconStrips), 1.95f, "250g", 7),
            new Product(35,context.getResources().getString(R.string.chickenNoodleSoup), 0.55f, "71g", 9),
            new Product(36,context.getResources().getString(R.string.friedTomato), 1f, "3U", 9),
            new Product(37,context.getResources().getString(R.string.sweetCorn), 1.19f, "3U", 9),
            new Product(38,context.getResources().getString(R.string.icebergLettuce), 0.8f, "1U", 8),
            new Product(39,context.getResources().getString(R.string.canaryTomato), 0.22f, "1kg", 8),
            new Product(40,context.getResources().getString(R.string.purpleGarlic), 1.2f, "250g", 8),
            new Product(41,context.getResources().getString(R.string.onion), 1.3f, "1kg", 8),
            new Product(42,context.getResources().getString(R.string.newPotato), 4.7f, "5kg", 8),
            new Product(43,context.getResources().getString(R.string.leeks), 1.07f, "0.466kg", 8),
            new Product(44,context.getResources().getString(R.string.muffins), 0.99f, "1U", 3),
            new Product(45,context.getResources().getString(R.string.cabbage), 0.68f, "0.52g", 8),
            new Product(46,context.getResources().getString(R.string.rolledMushrooms), 1.85f, "0.369g", 8),
            new Product(47,context.getResources().getString(R.string.zucchini), 1.59f, "1000g", 8),
            new Product(48,context.getResources().getString(R.string.carrots), 0.69f, "1000g", 8),
            new Product(49,context.getResources().getString(R.string.spinach), 1f, "100g", 8),
            new Product(50,context.getResources().getString(R.string.chard), 1f, "100g", 8),
            new Product(51,context.getResources().getString(R.string.pittedOlive), 1.47f, "500g", 10),
            new Product(52,context.getResources().getString(R.string.cookingCream), 1.32f, "3U", 10),
            new Product(53,context.getResources().getString(R.string.lightTunaInOil), 3.5f, "6U", 10),
            new Product(54,context.getResources().getString(R.string.toiletPaper), 2.07f, "6U", 12),
            new Product(55,context.getResources().getString(R.string.paperNapkins), 0.92f, "100U", 12),
            new Product(56,context.getResources().getString(R.string.tissues), 1f, "100U", 12),
            new Product(57,context.getResources().getString(R.string.airFreshener), 0.95f, "1U", 12),
            new Product(58,context.getResources().getString(R.string.bleach), 0.62f, "2000cc", 12),
            new Product(59,context.getResources().getString(R.string.floorMop), 0.68f, "1500cc", 12),
            new Product(60,context.getResources().getString(R.string.glassCleaner), 1.15f, "1U", 12),
            new Product(61,context.getResources().getString(R.string.rubbishBags), 1.45f, "1U", 12),
            new Product(62,context.getResources().getString(R.string.greenScouringPad), 1f, "3U", 12),
            new Product(63,context.getResources().getString(R.string.dishwasher), 0.89f, "1U", 12),
            new Product(64,context.getResources().getString(R.string.foil), 1.6f, "1U", 12),
            new Product(65,context.getResources().getString(R.string.softener), 1.79f, "1U", 12),
            new Product(66,context.getResources().getString(R.string.deodorantAloeVera), 0.75f, "1U", 13),
            new Product(67,context.getResources().getString(R.string.deodorantSilk), 0.75f, "1U", 13),
            new Product(68,context.getResources().getString(R.string.toothpaste), 1.75f, "100cc", 13),
            new Product(69,context.getResources().getString(R.string.antiDandruffShampoo), 1.8f, "400cc", 13),
            new Product(70,context.getResources().getString(R.string.extraStrongGel), 1.2f, "250cc", 13),
            new Product(71,context.getResources().getString(R.string.antiBreakShampoo), 1.7f, "400cc", 13),
            new Product(72,context.getResources().getString(R.string.handCream), 0.95f, "125cc", 13),
            new Product(73,context.getResources().getString(R.string.whiteBean), 1.69f, "1000g", 5),
            new Product(74,context.getResources().getString(R.string.chips), 0.9f, "160g", 4),
            new Product(75,context.getResources().getString(R.string.groundCoffee), 1.19f, "250g", 3),
            new Product(76,context.getResources().getString(R.string.watermelon), 1f, "1.204g", 8),
            new Product(77,context.getResources().getString(R.string.milkBread), 1.28f, "480g", 2),
            new Product(78,context.getResources().getString(R.string.mineralWater), 0.34f, "1U", 11),
            new Product(79,context.getResources().getString(R.string.colaZero), 0.55f, "1U", 11),
            new Product(80,context.getResources().getString(R.string.showerGel), 1f, "1U", 13),
            new Product(81,context.getResources().getString(R.string.squidsInAmericanSalsa), 1.69f, "3U", 10),
            new Product(82,context.getResources().getString(R.string.chickenCroquette), 0.99f, "500g", 9),
            new Product(83,context.getResources().getString(R.string.trunks), 2.19f, "600g", 10),
            new Product(84,context.getResources().getString(R.string.melon), 1.31f, "1/2U", 8),
            new Product(85,context.getResources().getString(R.string.greenPepper), 0.29f, "1U", 8),
            new Product(86,context.getResources().getString(R.string.peaches), 1.59f, "1U", 8),
            new Product(87,context.getResources().getString(R.string.oranges), 0.68f, "2U", 8),
            new Product(88,context.getResources().getString(R.string.pittedOlives), 1.6f, "500g", 10),
            new Product(89,context.getResources().getString(R.string.riceWithVegetables), 0.95f, "1U", 9),
            new Product(90,context.getResources().getString(R.string.potatoStirFry), 1.47f, "1U", 9),
            new Product(91,context.getResources().getString(R.string.hamburgerBuns), 0.79f, "4U", 2),
            new Product(92,context.getResources().getString(R.string.orangeJuice), 1f, "1U", 11),
            new Product(93,context.getResources().getString(R.string.avocados), 1.99f, "5U", 8),
            new Product(94,context.getResources().getString(R.string.turkeyDice), 1.89f, "2U", 7),
            new Product(95,context.getResources().getString(R.string.mandarins), 1.99f, "1KG", 8),
            new Product(96,context.getResources().getString(R.string.apples), 1.59f, "1KG", 8),
            new Product(97,context.getResources().getString(R.string.strawberries), 1.35f, "1U", 8),
            new Product(98,context.getResources().getString(R.string.quinceJelly), 0.89f, "1U", 7),
            new Product(99,context.getResources().getString(R.string.freshCheese), 1.49f, "6U", 1),
            new Product(100,context.getResources().getString(R.string.chops), 1.93f, "4U", 6),
            new Product(101,context.getResources().getString(R.string.choppedRibs), 3.01f, "1U", 6),
            new Product(102,context.getResources().getString(R.string.bananas), 0.57f, "3U", 8),
            new Product(103,context.getResources().getString(R.string.saladTomatoes), 2.12f, "1KG", 8),
            new Product(104,context.getResources().getString(R.string.tunaInBrine), 3.5f, "1U", 10),
            new Product(105,context.getResources().getString(R.string.lettuce), 1f, "3U", 8),
            new Product(106,context.getResources().getString(R.string.handSoap), 0.8f, "1U", 13),
            new Product(107,context.getResources().getString(R.string.turkeyBologna), 1.19f, "1U", 7),
            new Product(108,context.getResources().getString(R.string.cleanBathrooms), 1.3f, "1U", 12),
            new Product(109,context.getResources().getString(R.string.crushedTomato), 0.74f, "1U", 4),
            new Product(110,context.getResources().getString(R.string.chickenThighs), 2.75f, "1U", 6)
      )
    );
  }

  // -----------------------------------------------------------------------------------------------

  public static ProductData getInstance(Context context) {

    if (instance == null)
      instance = new ProductData(context);

    return instance;
  }

  // -----------------------------------------------------------------------------------------------

  public ArrayList<Product> getAllproducts(){
    return products;
  }

  // -----------------------------------------------------------------------------------------------

  public ArrayList<Product> getProducts(int sectionId){
    ArrayList<Product> products = new ArrayList<>();

    for(Product p: this.products){
      if(p.getSection() == sectionId){
        products.add(p);
      }
    }

    return products;
  }

  // -----------------------------------------------------------------------------------------------

  public Product getProduct(int id){
    for(Product p : products){
      if(p.getId() == id){
        return new Product(p);
      }
    }

    return new Product();
  }

  // -----------------------------------------------------------------------------------------------

  public void reloadData(Context context){
    instance = null;
    instance = getInstance(context);
  }

  // -----------------------------------------------------------------------------------------------

}
