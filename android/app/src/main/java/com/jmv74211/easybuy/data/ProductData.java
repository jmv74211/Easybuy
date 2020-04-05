package com.jmv74211.easybuy.data;

import android.content.Context;

import com.jmv74211.easybuy.models.Product;


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
        new Product(1, "Mermelada de melocotón", 1.05f, "440g", 1),
        new Product(2, "Leche semidesnatada", 0.58f, "1L", 1),
        new Product(3, "Margarina con sal", 0.8f, "500g", 1),
        new Product(4, "Yogures naturales azúcar caña", 0.74f, "6U", 1),
        new Product(5, "Pan blanco", 0.59f, "16U", 2),
        new Product(6, "Barra de pan", 0.45f, "1U", 2),
        new Product(7, "Bocadillos", 1f, "5U", 2),
        new Product(8, "Huevos grandes", 1.45f, "12U", 2),
        new Product(9, "Pan con tomate", 0.9f, "170g", 2),
        new Product(10, "Azúcar", 0.69f, "1000g", 3),
        new Product(11, "Mayonesa", 1.4f, "560cl", 4),
        new Product(12, "Ketchup", 0.8f, "560cl", 4),
        new Product(13, "Sal normal", 0.21f, "1000g", 4),
        new Product(14,"Spaghetti", 0.75f, "1000g", 5),
        new Product(15,"Garbanzo cocido", 0.54f, "570g", 5),
        new Product(16,"Lenteja pardina", 1.49f, "1kg", 5),
        new Product(17,"Arroz redondo", 0.79f, "1kg", 5),
        new Product(18,"Aceite girasol", 0.99f, "1L", 4),
        new Product(19,"Hamburguesas burguer vacuno cerdo", 3.35f, "6U", 6),
        new Product(20,"Carne picada cerdo", 2.4f, "500g", 6),
        new Product(21,"Cuarto trasero familiar", 4.21f, "5U", 6),
        new Product(22,"Chorizo oreado", 1.97f, "4U", 6),
        new Product(23,"Cerdo a tacos", 1.96f, "0.503kg", 6),
        new Product(24,"Vacuno a tacos", 3.76f, "0.495kg", 6),
        new Product(25,"Colorante alimentario", 0.59f, "85g", 6),
        new Product(26,"Laurel hoja", 0.66f, "12g", 6),
        new Product(27,"Jamón serrano", 1.99f, "120g", 7),
        new Product(28,"Queso lonchas semicurado", 1.88f, "8U", 7),
        new Product(29,"Salchichas frankfurt queso", 1.49f, "4U", 7),
        new Product(30,"Pechuga de pavo", 1.79f, "200g", 7),
        new Product(31,"Pizza barbacoa/bacon", 1.99f, "415g", 7),
        new Product(32,"Queso cuña", 2.47f, "0.338kg", 7),
        new Product(33,"Tortilla patata", 1.85f, "600g", 7),
        new Product(34,"Cintas de bacon", 1.95f, "250g", 7),
        new Product(35,"Sopa pollo fideos", 0.55f, "71g", 9),
        new Product(36,"Tomate frito brick", 1f, "3U", 9),
        new Product(37,"Maíz dulce", 1.19f, "3U", 9),
        new Product(38,"Lechuga iceberg", 0.8f, "1U", 8),
        new Product(39,"Tomate canario", 0.22f, "1kg", 8),
        new Product(40,"Ajo morado", 1.2f, "250g", 8),
        new Product(41,"Cebolla", 1.3f, "1kg", 8),
        new Product(42,"Patata nueva", 4.7f, "5kg", 8),
        new Product(43,"Puerros", 1.07f, "0.466kg", 8),
        new Product(44,"Magdalenas", 0.99f, "1U", 3),
        new Product(45,"Repollo liso cortado", 0.68f, "0.52g", 8),
        new Product(46,"Champiñones laminados", 1.85f, "0.369g", 8),
        new Product(47,"Calabacín", 1.59f, "1000g", 8),
        new Product(48,"Zanahoria bolsa", 0.69f, "1000g", 8),
        new Product(49,"Espinacas", 1f, "100g", 8),
        new Product(50,"Acelgas", 1f, "100g", 8),
        new Product(51,"Aceituna con hueso", 1.47f, "500g", 10),
        new Product(52,"Nata para cocinar", 1.32f, "3U", 10),
        new Product(53,"Atún claro aceite", 3.5f, "6U", 10),
        new Product(54,"Papel higiénico doble", 2.07f, "6U", 12),
        new Product(55,"Servilletas", 0.92f, "100U", 12),
        new Product(56,"Pañuelos mentol", 1f, "100U", 12),
        new Product(57,"Ambientador passion floral", 0.95f, "1U", 12),
        new Product(58,"Lejía normal", 0.62f, "2000cc", 12),
        new Product(59,"Fregasuelos pino", 0.68f, "1500cc", 12),
        new Product(60,"Cristales multiusos", 1.15f, "1U", 12),
        new Product(61,"Bolsas de basura", 1.45f, "1U", 12),
        new Product(62,"Estropajo verde", 1f, "3U", 12),
        new Product(63,"Lavavajillas", 0.89f, "1U", 12),
        new Product(64,"Papel aluminio", 1.6f, "1U", 12),
        new Product(65,"Suavizante azul", 1.79f, "1U", 12),
        new Product(66,"Rollon aloe vera", 0.75f, "1U", 13),
        new Product(67,"Rollon seda", 0.75f, "1U", 13),
        new Product(68,"Colgate triple acción", 1.75f, "100cc", 13),
        new Product(69,"Champú anticaspa/grasa", 1.8f, "400cc", 13),
        new Product(70,"Gel extrafuerte", 1.2f, "250cc", 13),
        new Product(71,"Champú antirotura", 1.7f, "400cc", 13),
        new Product(72,"Crema manos aloe", 0.95f, "125cc", 13),
        new Product(73,"Alubia blanca", 1.69f, "1000g", 5),
        new Product(74,"Patatas fritas salsa", 0.9f, "160g", 4),
        new Product(75,"Café molido natural", 1.19f, "250g", 3),
        new Product(76,"Sandía", 1f, "1.204g", 8),
        new Product(77,"Pan de leche", 1.28f, "480g", 2),
        new Product(78,"Agua mineral", 0.34f, "1U", 11),
        new Product(79,"Cola zero", 0.55f, "1U", 11),
        new Product(80,"Gel-champú ducha", 1f, "1U", 13),
        new Product(81,"Calamar en salsa americana", 1.69f, "3U", 10),
        new Product(82,"Croquetas de pollo", 0.99f, "500g", 9),
        new Product(83,"Tronquitos", 2.19f, "600g", 10),
        new Product(84,"Melón", 1.31f, "1/2U", 8),
        new Product(85,"Pimiento freír", 0.29f, "1U", 8),
        new Product(86,"Bandeja de melocotones", 1.59f, "1U", 8),
        new Product(87,"Naranjas", 0.68f, "2U", 8),
        new Product(88,"Aceitunas sin hueso", 1.6f, "500g", 10),
        new Product(89,"Arroz con verduras", 0.95f, "1U", 9),
        new Product(90,"Salteado de patatas", 1.47f, "1U", 9),
        new Product(91,"Pan de hamburguesa", 0.79f, "4U", 2),
        new Product(92,"Zumo de naranja", 1f, "1U", 11),
        new Product(93,"Aguacates", 1.99f, "5U", 8),
        new Product(94,"Dados de pavo", 1.89f, "2U", 7),
        new Product(95,"Mandarinas", 1.99f, "1KG", 8),
        new Product(96,"Manzanas", 1.59f, "1KG", 8),
        new Product(97,"Fresas", 1.35f, "1U", 8),
        new Product(98,"Carne de membrillo", 0.89f, "1U", 7),
        new Product(99,"Quesitos de Burgos", 1.49f, "6U", 1),
        new Product(100,"Chuletas de aguja", 1.93f, "4U", 6),
        new Product(101,"Costillas troceadas", 3.01f, "1U", 6),
        new Product(102,"Plátanos", 0.57f, "3U", 8),
        new Product(103,"Tomates de ensalada", 2.12f, "1KG", 8),
        new Product(104,"Atún al natural", 3.5f, "1U", 10),
        new Product(105,"Cogotes de lechuga", 1f, "3U", 8),
        new Product(106,"Jabón de manos", 0.8f, "1U", 13),
        new Product(107,"Mortadela de pavo", 1.19f, "1U", 7),
        new Product(108,"Limpiabaños", 1.3f, "1U", 12)
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

  public ArrayList<Product> getproducts(int sectionId){

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

}
