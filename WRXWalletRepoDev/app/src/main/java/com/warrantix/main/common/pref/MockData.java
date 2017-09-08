package com.warrantix.main.common.pref;

import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Deal;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.MsrpCurrency;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.Rate;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.model.Review;
import com.warrantix.main.common.rest.model.RoleInfo;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.rest.model.UsedProduct;

import java.util.ArrayList;
import java.util.List;

public class MockData{

    public static final List<Product> mockProducts = new ArrayList<>();
    public static final List<Review> mockReviews = new ArrayList<>();
    public static final List<Rate> mockRates = new ArrayList<>();
    public static final List<RelatedProduct> mockRelateProducts = new ArrayList<>();
    public static final List<ServiceCompany> mockServiceCompanies = new ArrayList<>();
    public static final List<Message> mockReminderMessages = new ArrayList<>();
    public static final List<Dealer> mockDealers = new ArrayList<>();
    public static final List<Order> mockOrders = new ArrayList<>();
    public static final List<UsedProduct> mockUsedProducts = new ArrayList<>();
    public static final List<Service> mockServices = new ArrayList<>();

    public static List<Product> createProducts (){

        mockProducts.clear();

        Product product0 = new Product();
        product0.setId("575a963055b8439f41aac0ff");
        product0.setName("NXG");
        product0.setCreatedAt("2016-06-10");
        product0.setUpdatedAt("2016-06-10");
        product0.setBrandID("575a96308fd9bf7b11f938e1");
        product0.setClass_("150cc");
        product0.setCategory("scooter");
        product0.setModel("Hero MotoCorp");
        product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
        product0.setSerialNumberStart("string");
        product0.setSerialNumberEnd("string");
        product0.setLoading("string");
        product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
        product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
        product0.setImageThmb("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        product0.setMpn("string");
        product0.setProductionDate("2016-06-10");
        product0.setReleaseDate("2016-06-10");
        product0.setSku("690");
        product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
        product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
        product0.setMsrp(828);
            MsrpCurrency msrpCurrency0 = new MsrpCurrency();
            msrpCurrency0.setMsrpCurrency("INR");
        product0.setMsrpCurrency(msrpCurrency0);
        product0.setMsrpCurrencySymb("&#8377;");
        product0.setFeatured(false);
        product0.setPopular(false);
            createReviews();
            List<Review> reviews0 = new ArrayList<>();
            reviews0.add(mockReviews.get(0));
            reviews0.add(mockReviews.get(1));
        product0.setReviews(reviews0);
            createRates();
            List<Rate> rates0 = new ArrayList<>();
            rates0.add(mockRates.get(0));
            rates0.add(mockRates.get(1));
        product0.setRate(rates0);

        mockProducts.add(product0);

        Product product1 = new Product();
        product1.setImageThmb(product0.getImageThmb());
        product1.setClass_(product0.getClass_());
        product1.setLoading(product0.getLoading());
        product1.setManualImageThmb(product0.getManualImageThmb());
        product1.setManualUrl(product0.getManualUrl());
        product1.setMpn(product0.getMpn());
        product1.setMsrpCurrency(product0.getMsrpCurrency());
        product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product1.setPopular(product0.getPopular());
        product1.setProductionDate(product0.getProductionDate());
        product1.setRate(product0.getRate());
        product1.setReleaseDate(product0.getReleaseDate());
        product1.setReviews(product0.getReviews());
        product1.setSerialNumberEnd(product0.getSerialNumberEnd());
        product1.setSerialNumberStart(product0.getSerialNumberStart());
        product1.setUpdatedAt(product0.getUpdatedAt());
        product1.setId("575a96305764b195b7c0f784");
        product1.setName("Ambition");
        product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product1.setCategory("off-road");
        product1.setSku("279");
        product1.setModel("WT 650 CF, Codrej");
        product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product1.setMsrp(940);
            createReviews();
            List<Review> reviews1 = new ArrayList<>();
            reviews1.add(mockReviews.get(2));
            reviews1.add(mockReviews.get(3));
        product1.setReviews(reviews1);
            createRates();
            List<Rate> rates1 = new ArrayList<>();
            rates1.add(mockRates.get(2));
            rates1.add(mockRates.get(3));
        product1.setRate(rates1);

        mockProducts.add(product1);

        Product product2 = new Product();
        product2.setImageThmb(product0.getImageThmb());
        product2.setClass_(product0.getClass_());
        product2.setLoading(product0.getLoading());
        product2.setManualImageThmb(product0.getManualImageThmb());
        product2.setManualUrl(product0.getManualUrl());
        product2.setMpn(product0.getMpn());
        product2.setMsrpCurrency(product0.getMsrpCurrency());
        product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product2.setPopular(product0.getPopular());
        product2.setProductionDate(product0.getProductionDate());
        product2.setRate(product0.getRate());
        product2.setReleaseDate(product0.getReleaseDate());
        product2.setReviews(product0.getReviews());
        product2.setSerialNumberEnd(product0.getSerialNumberEnd());
        product2.setSerialNumberStart(product0.getSerialNumberStart());
        product2.setUpdatedAt(product0.getUpdatedAt());
        product2.setId("575a9630d5725ffc91c74eab");
        product2.setName("Splendor");
        product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
        product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
        product2.setCategory("scooter");
        product2.setSku("471");
        product2.setModel("Hero MotoCorp");
        product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product2.setMsrp(718);
            createReviews();
            List<Review> reviews2 = new ArrayList<>();
            reviews2.add(mockReviews.get(4));
            reviews2.add(mockReviews.get(5));
        product2.setReviews(reviews2);
            createRates();
            List<Rate> rates2 = new ArrayList<>();
            rates2.add(mockRates.get(4));
            rates2.add(mockRates.get(5));
        product2.setRate(rates2);

        mockProducts.add(product2);

        Product product3 = new Product();
        product3.setImageThmb(product0.getImageThmb());
        product3.setClass_(product0.getClass_());
        product3.setLoading(product0.getLoading());
        product3.setManualImageThmb(product0.getManualImageThmb());
        product3.setManualUrl(product0.getManualUrl());
        product3.setMpn(product0.getMpn());
        product3.setMsrpCurrency(product0.getMsrpCurrency());
        product3.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product3.setPopular(product0.getPopular());
        product3.setProductionDate(product0.getProductionDate());
        product3.setRate(product0.getRate());
        product3.setReleaseDate(product0.getReleaseDate());
        product3.setReviews(product0.getReviews());
        product3.setSerialNumberEnd(product0.getSerialNumberEnd());
        product3.setSerialNumberStart(product0.getSerialNumberStart());
        product3.setUpdatedAt(product0.getUpdatedAt());
        product3.setId("575a9630ac9474317edd8aab");
        product3.setName("Ambition");
        product3.setDescription("Officia nostrud aute eu irure magna deserunt occaecat mollit non nostrud sint irure. Velit occaecat Lorem proident officia do nisi pariatur eiusmod proident veniam");
        product3.setShortDescription("Ut enim magna nostrud ullamco.");
        product3.setCategory("cruiser");
        product3.setModel("WT 650 CF, Codrej");
        product3.setSku("499");
        product3.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product3.setMsrp(354);
            createReviews();
            List<Review> reviews3 = new ArrayList<>();
            reviews3.add(mockReviews.get(6));
            reviews3.add(mockReviews.get(7));
        product3.setReviews(reviews3);
            createRates();
            List<Rate> rates3 = new ArrayList<>();
            rates3.add(mockRates.get(6));
            rates3.add(mockRates.get(7));
        product3.setRate(rates3);

        mockProducts.add(product3);

        Product product4 = new Product();
        product4.setImageThmb(product0.getImageThmb());
        product4.setClass_(product0.getClass_());
        product4.setLoading(product0.getLoading());
        product4.setManualImageThmb(product0.getManualImageThmb());
        product4.setManualUrl(product0.getManualUrl());
        product4.setMpn(product0.getMpn());
        product4.setMsrpCurrency(product0.getMsrpCurrency());
        product4.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product4.setPopular(product0.getPopular());
        product4.setProductionDate(product0.getProductionDate());
        product4.setRate(product0.getRate());
        product4.setReleaseDate(product0.getReleaseDate());
        product4.setReviews(product0.getReviews());
        product4.setSerialNumberEnd(product0.getSerialNumberEnd());
        product4.setSerialNumberStart(product0.getSerialNumberStart());
        product4.setUpdatedAt(product0.getUpdatedAt());
        product4.setId("575a963098d67ff6c6e38cc9");
        product4.setName("Ambition");
        product4.setDescription("Eiusmod proident qui est cillum adipisicing minim culpa sunt minim duis dolor ea. Laboris elit elit consectetur tempor non pariatur nisi fugiat elit cupidatat in ea.");
        product4.setShortDescription("Enim ex est dolore commodo dolor occaecat.");
        product4.setCategory("scooter");
        product4.setSku("323");
        product4.setModel("Hero MotoCorp");
        product4.setFeatureAndDetails("Culpa et sit ipsum et nostrud consectetur ea velit sunt. Culpa voluptate occaecat duis mollit id occaecat culpa eu duis ex cillum incididunt ullamco consectetur.");
        product4.setMsrp(147);
            createReviews();
            List<Review> reviews4 = new ArrayList<>();
            reviews4.add(mockReviews.get(8));
            reviews4.add(mockReviews.get(9));
        product4.setReviews(reviews4);
            createRates();
            List<Rate> rates4 = new ArrayList<>();
            rates4.add(mockRates.get(8));
            rates4.add(mockRates.get(9));
        product4.setRate(rates4);

        mockProducts.add(product4);

        Product product5 = new Product();
        product5.setImageThmb(product0.getImageThmb());
        product5.setClass_(product0.getClass_());
        product5.setLoading(product0.getLoading());
        product5.setManualImageThmb(product0.getManualImageThmb());
        product5.setManualUrl(product0.getManualUrl());
        product5.setMpn(product0.getMpn());
        product5.setMsrpCurrency(product0.getMsrpCurrency());
        product5.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product5.setPopular(product0.getPopular());
        product5.setProductionDate(product0.getProductionDate());
        product5.setRate(product0.getRate());
        product5.setReleaseDate(product0.getReleaseDate());
        product5.setReviews(product0.getReviews());
        product5.setSerialNumberEnd(product0.getSerialNumberEnd());
        product5.setSerialNumberStart(product0.getSerialNumberStart());
        product5.setUpdatedAt(product0.getUpdatedAt());
        product5.setId("575a96301d43624062a44c2c");
        product5.setName("Ambition");
        product5.setDescription("Officia fugiat irure dolor in nulla esse culpa aliquip minim labore Lorem nostrud ea. Ut adipisicing exercitation est eu consectetur aliquip anim commodo deserunt sunt culpa aliquip sint.");
        product5.setShortDescription("Eu aute ut adipisicing consectetur reprehenderit voluptate pariatur.");
        product5.setCategory("cruiser");
        product5.setSku("418");
        product5.setModel("WT 650 CF, Codrej");
        product5.setFeatureAndDetails("Esse excepteur labore reprehenderit minim ullamco aliqua laboris sint sit est id. Fugiat dolore aliqua qui ut occaecat sint.");
        product5.setMsrp(531);
            createReviews();
            List<Review> reviews5 = new ArrayList<>();
            reviews5.add(mockReviews.get(10));
            reviews5.add(mockReviews.get(11));
        product5.setReviews(reviews5);
            createRates();
            List<Rate> rates5 = new ArrayList<>();
            rates5.add(mockRates.get(10));
            rates5.add(mockRates.get(11));
        product5.setRate(rates5);

        mockProducts.add(product5);

        Product product6 = new Product();
        product6.setImageThmb(product0.getImageThmb());
        product6.setClass_(product0.getClass_());
        product6.setLoading(product0.getLoading());
        product6.setManualImageThmb(product0.getManualImageThmb());
        product6.setManualUrl(product0.getManualUrl());
        product6.setMpn(product0.getMpn());
        product6.setMsrpCurrency(product0.getMsrpCurrency());
        product6.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product6.setPopular(product0.getPopular());
        product6.setProductionDate(product0.getProductionDate());
        product6.setRate(product0.getRate());
        product6.setReleaseDate(product0.getReleaseDate());
        product6.setReviews(product0.getReviews());
        product6.setSerialNumberEnd(product0.getSerialNumberEnd());
        product6.setSerialNumberStart(product0.getSerialNumberStart());
        product6.setUpdatedAt(product0.getUpdatedAt());
        product6.setId("575a963012d141a611c9a358");
        product6.setName("Ambition");
        product6.setDescription("Est laborum minim sit laboris cupidatat labore do. Dolor in veniam ut ad enim mollit esse non deserunt occaecat.");
        product6.setShortDescription("Est do ullamco ex eiusmod labore nisi nisi ipsum sunt est reprehenderit ea enim.");
        product6.setCategory("scooter");
        product6.setSku("722");
        product6.setModel("Hero MotoCorp");
        product6.setFeatureAndDetails("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
        product6.setMsrp(647);
            createReviews();
            List<Review> reviews6 = new ArrayList<>();
            reviews6.add(mockReviews.get(12));
            reviews6.add(mockReviews.get(13));
        product6.setReviews(reviews6);
            createRates();
            List<Rate> rates6 = new ArrayList<>();
            rates6.add(mockRates.get(12));
            rates6.add(mockRates.get(13));
        product6.setRate(rates6);

        mockProducts.add(product6);

        Product product7 = new Product();
        product7.setImageThmb(product0.getImageThmb());
        product7.setClass_(product0.getClass_());
        product7.setLoading(product0.getLoading());
        product7.setManualImageThmb(product0.getManualImageThmb());
        product7.setManualUrl(product0.getManualUrl());
        product7.setMpn(product0.getMpn());
        product7.setMsrpCurrency(product0.getMsrpCurrency());
        product7.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product7.setPopular(product0.getPopular());
        product7.setProductionDate(product0.getProductionDate());
        product7.setRate(product0.getRate());
        product7.setReleaseDate(product0.getReleaseDate());
        product7.setReviews(product0.getReviews());
        product7.setSerialNumberEnd(product0.getSerialNumberEnd());
        product7.setSerialNumberStart(product0.getSerialNumberStart());
        product7.setUpdatedAt(product0.getUpdatedAt());
        product7.setId("575a963030a86a88e818d703");
        product7.setName("Splendor");
        product7.setDescription("Aliqua cillum aute deserunt Lorem mollit elit ipsum ut nulla sit enim tempor esse. Ea laborum proident ad irure tempor reprehenderit enim excepteur aliqua.");
        product7.setShortDescription("Adipisicing anim aliquip elit dolor sint.");
        product7.setCategory("off-road");
        product7.setSku("77");
        product7.setModel("WT 650 CF, Codrej");
        product7.setFeatureAndDetails("Mollit tempor voluptate tempor laboris ex aute tempor minim enim officia sint consectetur do. Proident Lorem nostrud incididunt duis dolor aliquip est amet.");
        product7.setMsrp(865);
            createReviews();
            List<Review> reviews7 = new ArrayList<>();
            reviews7.add(mockReviews.get(14));
            reviews7.add(mockReviews.get(15));
        product7.setReviews(reviews7);
            createRates();
            List<Rate> rates7 = new ArrayList<>();
            rates7.add(mockRates.get(14));
            rates7.add(mockRates.get(15));
        product7.setRate(rates7);

        mockProducts.add(product7);

        Product product8 = new Product();
        product8.setImageThmb(product0.getImageThmb());
        product8.setClass_(product0.getClass_());
        product8.setLoading(product0.getLoading());
        product8.setManualImageThmb(product0.getManualImageThmb());
        product8.setManualUrl(product0.getManualUrl());
        product8.setMpn(product0.getMpn());
        product8.setMsrpCurrency(product0.getMsrpCurrency());
        product8.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product8.setPopular(product0.getPopular());
        product8.setProductionDate(product0.getProductionDate());
        product8.setRate(product0.getRate());
        product8.setReleaseDate(product0.getReleaseDate());
        product8.setReviews(product0.getReviews());
        product8.setSerialNumberEnd(product0.getSerialNumberEnd());
        product8.setSerialNumberStart(product0.getSerialNumberStart());
        product8.setUpdatedAt(product0.getUpdatedAt());
        product8.setId("575a96309655c54d320fe883");
        product8.setName("Splendor");
        product8.setDescription("Laborum do id occaecat exercitation occaecat quis esse aute nostrud est aliqua. Labore ea cillum ut pariatur.");
        product8.setShortDescription("Incididunt cupidatat et consectetur commodo laboris est non.");
        product8.setCategory("scooter");
        product8.setSku("701");
        product8.setModel("Hero MotoCorp");
        product8.setFeatureAndDetails("Exercitation adipisicing adipisicing aute sit Lorem tempor labore anim deserunt deserunt mollit officia et. Occaecat id sit ut cupidatat duis nostrud esse amet eu.");
        product8.setMsrp(117);
            createReviews();
            List<Review> reviews8 = new ArrayList<>();
            reviews8.add(mockReviews.get(16));
            reviews8.add(mockReviews.get(17));
        product8.setReviews(reviews8);
            createRates();
            List<Rate> rates8 = new ArrayList<>();
            rates8.add(mockRates.get(16));
            rates8.add(mockRates.get(17));
        product8.setRate(rates8);

        mockProducts.add(product8);

        Product product9 = new Product();
        product9.setImageThmb(product0.getImageThmb());
        product9.setClass_(product0.getClass_());
        product9.setLoading(product0.getLoading());
        product9.setManualImageThmb(product0.getManualImageThmb());
        product9.setManualUrl(product0.getManualUrl());
        product9.setMpn(product0.getMpn());
        product9.setMsrpCurrency(product0.getMsrpCurrency());
        product9.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product9.setPopular(product0.getPopular());
        product9.setProductionDate(product0.getProductionDate());
        product9.setRate(product0.getRate());
        product9.setReleaseDate(product0.getReleaseDate());
        product9.setReviews(product0.getReviews());
        product9.setSerialNumberEnd(product0.getSerialNumberEnd());
        product9.setSerialNumberStart(product0.getSerialNumberStart());
        product9.setUpdatedAt(product0.getUpdatedAt());
        product9.setId("575a9630588a0334c9e1278b");
        product9.setName("Splendor");
        product9.setDescription("Sunt qui id nostrud voluptate eu aliquip fugiat reprehenderit reprehenderit. Cillum in occaecat non pariatur.");
        product9.setShortDescription("Consectetur quis fugiat amet occaecat sit Lorem aliqua aliquip eu magna proident non.");
        product9.setCategory("cruiser");
        product9.setSku("172");
        product9.setModel("WT 650 CF, Codrej");
        product9.setFeatureAndDetails("Incididunt ex qui sint nostrud laborum nulla magna incididunt dolor ex laborum voluptate exercitation reprehenderit. Non fugiat tempor nulla sit laboris laboris ea ex minim laborum.");
        product9.setMsrp(37);
            createReviews();
            List<Review> reviews9 = new ArrayList<>();
            reviews9.add(mockReviews.get(18));
            reviews9.add(mockReviews.get(19));
        product9.setReviews(reviews9);
            createRates();
            List<Rate> rates9 = new ArrayList<>();
            rates9.add(mockRates.get(18));
            rates9.add(mockRates.get(19));
        product9.setRate(rates9);

        mockProducts.add(product9);


        return mockProducts;
    }

    public static Product getProduct (String productId){
        Product selProduct = new Product();
        createProducts();
        for (int i = 0; i < mockProducts.size(); i++){
            Product product = mockProducts.get(i);
            String pId = product.getId();
            if (pId.equals(productId)){
                selProduct = product;
                break;
            }
        }
        return selProduct;
    }

    // create the mock review list
    public static List<Review> createReviews (){

        mockReviews.clear();

        Review review0 = new Review();
        review0.setCustomerID("575a9630846b3c68b1e0271c");
        review0.setReview("Aliqua anim ullamco veniam laborum ut minim deserunt tempor adipisicing ad velit occaecat.");

        mockReviews.add(review0);

        Review review1 = new Review();
        review1.setCustomerID("575a9630f7baf774b1f23eab");
        review1.setReview("Sunt nisi reprehenderit sunt veniam minim.");

        mockReviews.add(review1);

        Review review2 = new Review();
        review0.setCustomerID("575a9630d7e236aabef5edf8");
        review0.setReview("Occaecat ut consequat aliquip esse et aliquip excepteur ex aute dolor ex proident.");

        mockReviews.add(review2);

        Review review3 = new Review();
        review1.setCustomerID("575a96300d28d4c813cf8f39");
        review1.setReview("Duis consequat deserunt consequat ipsum proident officia quis nulla occaecat minim sint est.");

        mockReviews.add(review3);

        Review review4 = new Review();
        review0.setCustomerID("575a9630e79f8820f81d7734");
        review0.setReview("Irure sit quis est amet tempor voluptate sint in Lorem.");

        mockReviews.add(review4);

        Review review5 = new Review();
        review1.setCustomerID("575a9630ce81abdf60bce8c2");
        review1.setReview("Sint sit laborum mollit nisi anim non velit in incididunt velit do consequat aliquip.");

        mockReviews.add(review5);

        Review review6 = new Review();
        review0.setCustomerID("575a9630c7058aadf6a5c378");
        review0.setReview("Commodo duis quis ullamco nostrud esse consectetur nostrud dolore incididunt eiusmod quis anim nulla.");

        mockReviews.add(review6);

        Review review7 = new Review();
        review1.setCustomerID("575a963004bf1c11caac1476");
        review1.setReview("Ipsum cillum deserunt nostrud incididunt officia duis labore commodo non tempor eiusmod nisi labore officia.");

        mockReviews.add(review7);

        Review review8 = new Review();
        review0.setCustomerID("575a963048fcac2eafdfe5a0");
        review0.setReview("Amet eu non adipisicing anim.");

        mockReviews.add(review8);

        Review review9 = new Review();
        review1.setCustomerID("575a9630684b80ccb988fac4");
        review1.setReview("Ex consequat mollit elit exercitation voluptate esse do.");

        mockReviews.add(review9);

        Review review10 = new Review();
        review0.setCustomerID("575a9630c9a45bc11906cb41");
        review0.setReview("Cillum et est id sunt eu sit laborum tempor.");

        mockReviews.add(review10);

        Review review11 = new Review();
        review1.setCustomerID("575a963059f5ab18abe390a7");
        review1.setReview("Duis enim cupidatat quis voluptate anim elit adipisicing magna magna ullamco.");

        mockReviews.add(review11);

        Review review12 = new Review();
        review0.setCustomerID("575a96309ba7f342df1853d4");
        review0.setReview("Minim consequat non ea dolore ipsum eiusmod excepteur cillum.");

        mockReviews.add(review12);

        Review review13 = new Review();
        review1.setCustomerID("575a96306e2f722886d88fcc");
        review1.setReview("Est nostrud anim ullamco proident qui irure duis commodo.");

        mockReviews.add(review13);

        Review review14 = new Review();
        review0.setCustomerID("575a9630c814f9f6d4f958ca");
        review0.setReview("Et voluptate duis ea nulla quis id labore cupidatat incididunt.");

        mockReviews.add(review14);

        Review review15 = new Review();
        review1.setCustomerID("575a96309239a3744d88e7f2");
        review1.setReview("Veniam esse anim pariatur ullamco fugiat aute qui ut deserunt cillum do occaecat duis.");

        mockReviews.add(review15);

        Review review16 = new Review();
        review0.setCustomerID("575a9630776cb78962ee6ddc");
        review0.setReview("Proident ex minim ad mollit sit deserunt eiusmod duis ut dolor in aliqua pariatur.");

        mockReviews.add(review16);

        Review review17 = new Review();
        review1.setCustomerID("575a963056a2b4caf53f41d9");
        review1.setReview("Quis mollit duis consequat cillum qui id et id qui.");

        mockReviews.add(review17);

        Review review18 = new Review();
        review0.setCustomerID("575a963020ff6a87d38b26c0");
        review0.setReview("Et eiusmod Lorem ex voluptate duis consectetur aliqua pariatur commodo irure est.");

        mockReviews.add(review18);

        Review review19 = new Review();
        review1.setCustomerID("575a9630e65754590e3da669");
        review1.setReview("Aliqua deserunt velit magna mollit reprehenderit dolore veniam nisi cillum non cupidatat fugiat eu.");

        mockReviews.add(review19);

        return mockReviews;
    }

    // create the mock rate list
    public static List<Rate> createRates (){

        mockRates.clear();

        Rate rate0 = new Rate();
        rate0.setCustomerID("575a9630422bf290a78a7800");
        rate0.setReview(2);

        mockRates.add(rate0);

        Rate rate1 = new Rate();
        rate1.setCustomerID("575a9630ca1b10fc04c86c8e");
        rate1.setReview(4);

        mockRates.add(rate1);

        Rate rate2 = new Rate();
        rate0.setCustomerID("575a96302cb3185a04d5e736");
        rate0.setReview(3);

        mockRates.add(rate2);

        Rate rate3 = new Rate();
        rate1.setCustomerID("575a9630dbeed0d14d488724");
        rate1.setReview(4);

        mockRates.add(rate3);

        Rate rate4 = new Rate();
        rate0.setCustomerID("575a96301156273575b11d66");
        rate0.setReview(5);

        mockRates.add(rate4);

        Rate rate5 = new Rate();
        rate1.setCustomerID("575a9630327673c121947213");
        rate1.setReview(1);

        mockRates.add(rate5);

        Rate rate6 = new Rate();
        rate0.setCustomerID("575a96303938f8f8254bc58b");
        rate0.setReview(2);

        mockRates.add(rate6);

        Rate rate7 = new Rate();
        rate1.setCustomerID("575a96307c8749e29b6fcc92");
        rate1.setReview(3);

        mockRates.add(rate7);

        Rate rate8 = new Rate();
        rate0.setCustomerID("575a963063c0ada8c7dcba63");
        rate0.setReview(5);

        mockRates.add(rate8);

        Rate rate9 = new Rate();
        rate1.setCustomerID("575a9630dd24190874cfd2f4");
        rate1.setReview(1);

        mockRates.add(rate9);

        Rate rate10 = new Rate();
        rate0.setCustomerID("575a963018bfd060fe937703");
        rate0.setReview(4);

        mockRates.add(rate10);

        Rate rate11 = new Rate();
        rate1.setCustomerID("575a9630684844de664b5e94");
        rate1.setReview(3);

        mockRates.add(rate11);

        Rate rate12 = new Rate();
        rate0.setCustomerID("575a96301cbfcd3a79fa4006");
        rate0.setReview(1);

        mockRates.add(rate12);

        Rate rate13 = new Rate();
        rate1.setCustomerID("575a9630f39f9be55021e8dd");
        rate1.setReview(5);

        mockRates.add(rate13);

        Rate rate14 = new Rate();
        rate0.setCustomerID("575a963007f28f386c5d5bf8");
        rate0.setReview(2);

        mockRates.add(rate14);

        Rate rate15 = new Rate();
        rate1.setCustomerID("575a9630d507ef057bd659c7");
        rate1.setReview(1);

        mockRates.add(rate15);

        Rate rate16 = new Rate();
        rate0.setCustomerID("575a96304927b0e60092db2f");
        rate0.setReview(5);

        mockRates.add(rate16);

        Rate rate17 = new Rate();
        rate1.setCustomerID("575a963094d3433e17331828");
        rate1.setReview(1);

        mockRates.add(rate17);

        Rate rate18 = new Rate();
        rate0.setCustomerID("575a96302c3331706a52fa6e");
        rate0.setReview(2);

        mockRates.add(rate18);

        Rate rate19 = new Rate();
        rate1.setCustomerID("575a96308c64a9048891a81f");
        rate1.setReview(2);

        mockRates.add(rate19);

        return mockRates;
    }

    public static List<RelatedProduct> createRelatedProducts() {

        mockRelateProducts.clear();

        RelatedProduct relatedProduct0 = new RelatedProduct();
        relatedProduct0.setFeatured(true);
        relatedProduct0.setProductID("575a963055b8439f41aac0ff");
        relatedProduct0.setDescription("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
        relatedProduct0.setMsrpCurrencySymb("aeiou");
        relatedProduct0.setSerialNumberEnd("aeiou");
        relatedProduct0.setCreatedAt("2000-01-23T04:56:07.000+0000");
        relatedProduct0.setProductionDate("2000-01-23T04:56:07.000+0000");
        relatedProduct0.setBrandID("aeiou");
        relatedProduct0.setMsrp(1.3579);
        relatedProduct0.setModel("Hero Motocyles");
        relatedProduct0.setManualUrl("aeiou");
        relatedProduct0.setSku("aeiou");
        relatedProduct0.setClass_("aeiou");
        relatedProduct0.setPopular(true);
        relatedProduct0.setUpdatedAt("2000-01-23T04:56:07.000+0000");
        relatedProduct0.setSerialNumberStart("aeiou");
        relatedProduct0.setReleaseDate("2000-01-23T04:56:07.000+0000");
        relatedProduct0.setMpn("aeiou");
        relatedProduct0.setShortDescription("aeiou");
        relatedProduct0.setFeatureAndDetails("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
        relatedProduct0.setLoading("aeiou");
        relatedProduct0.setImageThmb("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        relatedProduct0.setName("Flip Cover - Galaxy 56  Edge");
        relatedProduct0.setId("aeiou");
        relatedProduct0.setCategory("category0");
            MsrpCurrency msrpCurrency = new MsrpCurrency();
            msrpCurrency.setMsrpCurrency("aeiou");
        relatedProduct0.setMsrpCurrency(msrpCurrency);

        mockRelateProducts.add(relatedProduct0);

        RelatedProduct relatedProduct1 = new RelatedProduct();
        relatedProduct1.setFeatured(relatedProduct0.getFeatured());
        relatedProduct1.setDescription(relatedProduct0.getDescription());
        relatedProduct1.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
        relatedProduct1.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
        relatedProduct1.setCreatedAt(relatedProduct0.getCreatedAt());
        relatedProduct1.setProductionDate(relatedProduct0.getProductionDate());
        relatedProduct1.setBrandID(relatedProduct0.getBrandID());
        relatedProduct1.setMsrp(relatedProduct0.getMsrp());
        relatedProduct1.setModel("Bajaj Pulsar 135/150/180");
        relatedProduct1.setManualUrl(relatedProduct0.getManualUrl());
        relatedProduct1.setSku(relatedProduct0.getSku());
        relatedProduct1.setShortDescription(relatedProduct0.getShortDescription());
        relatedProduct1.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
        relatedProduct1.setLoading(relatedProduct0.getLoading());
        relatedProduct1.setImageThmb(relatedProduct0.getImageThmb());
        relatedProduct1.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
        relatedProduct1.setId("aeiou1");
        relatedProduct1.setName("aeiou1");
        relatedProduct1.setProductID("575a96305764b195b7c0f784");
        relatedProduct1.setCategory("category1");

        mockRelateProducts.add(relatedProduct1);

        RelatedProduct relatedProduct2 = new RelatedProduct();
        relatedProduct2.setFeatured(relatedProduct0.getFeatured());
        relatedProduct2.setDescription(relatedProduct0.getDescription());
        relatedProduct2.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
        relatedProduct2.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
        relatedProduct2.setCreatedAt(relatedProduct0.getCreatedAt());
        relatedProduct2.setProductionDate(relatedProduct0.getProductionDate());
        relatedProduct2.setBrandID(relatedProduct0.getBrandID());
        relatedProduct2.setMsrp(relatedProduct0.getMsrp());
        relatedProduct2.setModel("Bullet(Classic)");
        relatedProduct2.setManualUrl(relatedProduct0.getManualUrl());
        relatedProduct2.setSku(relatedProduct0.getSku());
        relatedProduct2.setShortDescription(relatedProduct0.getShortDescription());
        relatedProduct2.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
        relatedProduct2.setLoading(relatedProduct0.getLoading());
        relatedProduct2.setImageThmb(relatedProduct0.getImageThmb());
        relatedProduct2.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
        relatedProduct2.setId("aeiou2");
        relatedProduct2.setName("aeiou2");
        relatedProduct2.setProductID("575a9630d5725ffc91c74eab");
        relatedProduct2.setCategory("category2");

        mockRelateProducts.add(relatedProduct2);

        RelatedProduct relatedProduct3 = new RelatedProduct();
        relatedProduct3.setFeatured(relatedProduct0.getFeatured());
        relatedProduct3.setDescription(relatedProduct0.getDescription());
        relatedProduct3.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
        relatedProduct3.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
        relatedProduct3.setCreatedAt(relatedProduct0.getCreatedAt());
        relatedProduct3.setProductionDate(relatedProduct0.getProductionDate());
        relatedProduct3.setBrandID(relatedProduct0.getBrandID());
        relatedProduct3.setMsrp(relatedProduct0.getMsrp());
        relatedProduct3.setModel("Arai Helmets");
        relatedProduct3.setManualUrl(relatedProduct0.getManualUrl());
        relatedProduct3.setSku(relatedProduct0.getSku());
        relatedProduct3.setShortDescription(relatedProduct0.getShortDescription());
        relatedProduct3.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
        relatedProduct3.setLoading(relatedProduct0.getLoading());
        relatedProduct3.setImageThmb(relatedProduct0.getImageThmb());
        relatedProduct3.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
        relatedProduct3.setId("aeiou3");
        relatedProduct3.setName("aeiou3");
        relatedProduct3.setProductID("575a9630ac9474317edd8aab");
        relatedProduct3.setCategory("category1");

        mockRelateProducts.add(relatedProduct3);

        RelatedProduct relatedProduct4 = new RelatedProduct();
        relatedProduct4.setFeatured(relatedProduct0.getFeatured());
        relatedProduct4.setDescription(relatedProduct0.getDescription());
        relatedProduct4.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
        relatedProduct4.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
        relatedProduct4.setCreatedAt(relatedProduct0.getCreatedAt());
        relatedProduct4.setProductionDate(relatedProduct0.getProductionDate());
        relatedProduct4.setBrandID(relatedProduct0.getBrandID());
        relatedProduct4.setMsrp(relatedProduct0.getMsrp());
        relatedProduct4.setModel(relatedProduct0.getModel());
        relatedProduct4.setManualUrl(relatedProduct0.getManualUrl());
        relatedProduct4.setSku(relatedProduct0.getSku());
        relatedProduct4.setShortDescription(relatedProduct0.getShortDescription());
        relatedProduct4.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
        relatedProduct4.setLoading(relatedProduct0.getLoading());
        relatedProduct4.setImageThmb(relatedProduct0.getImageThmb());
        relatedProduct4.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
        relatedProduct4.setId("aeiou4");
        relatedProduct4.setName("aeiou4");
        relatedProduct4.setProductID("575a963098d67ff6c6e38cc9");
        relatedProduct4.setCategory("category0");

        mockRelateProducts.add(relatedProduct4);

        return mockRelateProducts;
    }

    public static List<ServiceCompany> createServiceCompanies (){

        mockServiceCompanies.clear();

        ServiceCompany serviceCompany0 = new ServiceCompany();
        serviceCompany0.setName("Hero MotoCorp");
        serviceCompany0.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        serviceCompany0.setDescription("Comprehensive, Starts form Rs. 4000 p.a");
        serviceCompany0.setId("575acf2274550a837e5d9c92");
        serviceCompany0.setCreatedAt("2016-05-25");
        serviceCompany0.setUpdatedAt("2016-05-25");
            Contact contact0 = new Contact();
            contact0.setFirstName("Matthews");
            contact0.setLastName("Barlow");
            contact0.setTel("+91 (900) 445-3807");
            contact0.setSsn("Gale");
            contact0.setAddress("757 Gallatin Place");
            contact0.setCity("Frystown");
            contact0.setZip(86351);
            contact0.setCountry("Faroe Islands");
            contact0.setContactEmail("galebarlow@visualix.com");
            contact0.setWebsite("Silvia.com");
            contact0.setLatitude(66.206727);
            contact0.setLongitude(85.51726);
        serviceCompany0.setContact(contact0);

        mockServiceCompanies.add(serviceCompany0);

        ServiceCompany serviceCompany1 = new ServiceCompany();
        serviceCompany1.setName("Codrej");
        serviceCompany1.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        serviceCompany1.setDescription("Comprehensive, Starts form Rs. 3800 p.a");
        serviceCompany1.setId("575acf2275674a353e016eb1");
        serviceCompany1.setCreatedAt("2016-05-25");
        serviceCompany1.setUpdatedAt("2016-05-25");
            Contact contact1 = new Contact();
            contact1.setFirstName("Elliott");
            contact1.setLastName("Sloan");
            contact1.setTel("+91 (987) 519-2511");
            contact1.setSsn("Esmeralda");
            contact1.setAddress("689 Surf Avenue");
            contact1.setCity("Fedora");
            contact1.setZip(42554);
            contact1.setCountry("Swaziland");
            contact1.setContactEmail("esmeraldasloan@visualix.com");
            contact1.setWebsite("Miranda.com");
            contact1.setLatitude(74.325343);
            contact1.setLongitude(27.392789);
        serviceCompany0.setContact(contact1);

        mockServiceCompanies.add(serviceCompany1);

        ServiceCompany serviceCompany2 = new ServiceCompany();
        serviceCompany2.setName("Hero MotoCorp1");
        serviceCompany2.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        serviceCompany2.setDescription("Comprehensive, Starts form Rs. 4500 p.a");
        serviceCompany2.setId("575acf22b15e97e514ee09c9");
        serviceCompany2.setCreatedAt("2016-05-25");
        serviceCompany2.setUpdatedAt("2016-05-25");
            Contact contact2 = new Contact();
            contact2.setFirstName("Alexandra");
            contact2.setLastName("Lamb");
            contact2.setTel("+91 (990) 522-2052");
            contact2.setSsn("Debbie");
            contact2.setAddress("209 Celeste Court");
            contact2.setCity("Kapowsin");
            contact2.setZip(31776);
            contact2.setCountry("Gabon");
            contact2.setContactEmail("debbielamb@visualix.com");
            contact2.setWebsite("Slater.com");
            contact2.setLatitude(84.120678);
            contact2.setLongitude(63.463442);
        serviceCompany0.setContact(contact2);

        mockServiceCompanies.add(serviceCompany2);

        ServiceCompany serviceCompany3 = new ServiceCompany();
        serviceCompany3.setName("Codrej");
        serviceCompany3.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        serviceCompany3.setDescription("Comprehensive, Starts form Rs. 3500 p.a");
        serviceCompany3.setId("575acf22971c7d6ccacf371f");
        serviceCompany3.setCreatedAt("2016-05-25");
        serviceCompany3.setUpdatedAt("2016-05-25");
            Contact contact3 = new Contact();
            contact3.setFirstName("Beach");
            contact3.setLastName("Shields");
            contact3.setTel("+91 (950) 402-3848");
            contact3.setSsn("Holt");
            contact3.setAddress("406 Clay Street");
            contact3.setCity("Escondida");
            contact3.setZip(45367);
            contact3.setCountry("Ethiopia");
            contact3.setContactEmail("holtshields@visualix.com");
            contact3.setWebsite("Penelope.com");
            contact3.setLatitude(60.64305);
            contact3.setLongitude(69.668411);
        serviceCompany0.setContact(contact3);

        mockServiceCompanies.add(serviceCompany3);

        ServiceCompany serviceCompany4 = new ServiceCompany();
        serviceCompany4.setName("Hero MotoCorp2");
        serviceCompany4.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        serviceCompany4.setDescription("Comprehensive, Starts form Rs. 3000 p.a");
        serviceCompany4.setId("575acf22c0f1ccff86fd9c74");
        serviceCompany4.setCreatedAt("2016-05-25");
        serviceCompany4.setUpdatedAt("2016-05-25");
            Contact contact4 = new Contact();
            contact4.setFirstName("Roslyn");
            contact4.setLastName("Jefferson");
            contact4.setTel("+91 (968) 506-2024");
            contact4.setSsn("Moore");
            contact4.setAddress("374 Verona Place");
            contact4.setCity("Bridgetown");
            contact4.setZip(39468);
            contact4.setCountry("Reunion");
            contact4.setContactEmail("moorejefferson@visualix.com");
            contact4.setWebsite("Keller.com");
            contact4.setLatitude(62.11774);
            contact4.setLongitude(72.249163);
        serviceCompany0.setContact(contact4);

        mockServiceCompanies.add(serviceCompany4);

        ServiceCompany serviceCompany5 = new ServiceCompany();
        serviceCompany5.setName("Codrej");
        serviceCompany5.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        serviceCompany5.setDescription("Comprehensive, Starts form Rs. 5000 p.a");
        serviceCompany5.setId("575acf22b5e0387fba50fa1e");
        serviceCompany5.setCreatedAt("2016-05-25");
        serviceCompany5.setUpdatedAt("2016-05-25");
            Contact contact5 = new Contact();
            contact5.setFirstName("Regina");
            contact5.setLastName("Gaines");
            contact5.setTel("+91 (927) 498-3942");
            contact5.setSsn("Debra");
            contact5.setAddress("260 Aviation Road");
            contact5.setCity("Freelandville");
            contact5.setZip(10893);
            contact5.setCountry("Bosnia and Herzegovina");
            contact5.setContactEmail("debragaines@visualix.com");
            contact5.setWebsite("Rhoda.com");
            contact5.setLatitude(20.673356);
            contact5.setLongitude(33.945107);
        serviceCompany0.setContact(contact5);

        mockServiceCompanies.add(serviceCompany5);

        ServiceCompany serviceCompany6 = new ServiceCompany();
        serviceCompany6.setName("Hero MotoCorp3");
        serviceCompany6.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
        serviceCompany6.setDescription("Comprehensive, Starts form Rs. 4300 p.a");
        serviceCompany6.setId("575acf228194ac5d99933b3d");
        serviceCompany6.setCreatedAt("2016-05-25");
        serviceCompany6.setUpdatedAt("2016-05-25");
            Contact contact6 = new Contact();
            contact6.setFirstName("Lowe");
            contact6.setLastName("Goff");
            contact6.setTel("+91 (992) 438-2760");
            contact6.setSsn("Tina");
            contact6.setAddress("441 Kansas Place");
            contact6.setCity("Blackgum");
            contact6.setZip(58621);
            contact6.setCountry("Canada");
            contact6.setContactEmail("tinagoff@visualix.com");
            contact6.setWebsite("Guy.com");
            contact6.setLatitude(32.919691);
            contact6.setLongitude(44.907874);
        serviceCompany0.setContact(contact6);

        mockServiceCompanies.add(serviceCompany6);

        return mockServiceCompanies;
    }

    public static List<Message> createReminderMessages (){

        mockReminderMessages.clear();

        Message message0 = new Message();
        message0.setId("2");
        message0.setCreatedAt("2016-06-03");
        message0.setUpdatedAt("2016-06-03");
            RoleInfo to = new RoleInfo();
            to.setId("c1");
            to.setRole("consumer");
        message0.setTo(to);
            RoleInfo from = new RoleInfo();
            from.setId("r1");
            from.setRole("reminder");
        message0.setFrom(from);
            String reminderContent0 = "{'name' : 'Hero Motocorp', 'imageThumb': 'https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png', 'sub': 'lorem Ipsum is simply dummy text of the printing', 'description':'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a aliquet lorem, vitae laoreet odio. Nullam eu neque et nunc cursus commodo.Vivamus at magna eleifend, luctus augue at, ullamcorper augue.'}";
        message0.setContent(reminderContent0);

        mockReminderMessages.add(message0);

        Message message1 = new Message();
        message1.setId("3");
        message1.setCreatedAt("2016-06-03");
        message1.setUpdatedAt("2016-06-03");
        message0.setTo(to);
            RoleInfo from1 = new RoleInfo();
            from1.setId("r2");
            from1.setRole("reminder");
        message1.setFrom(from1);
        String reminderContent1 = "{'name' : 'Godrej', 'imageThumb': 'https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png', 'sub': 'lorem Ipsum is simply dummy text of the printing', 'description':'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a aliquet lorem, vitae laoreet odio. Nullam eu neque et nunc cursus commodo. Vivamus at magna eleifend, luctus augue at, ullamcorper augue.'}";
        message1.setContent(reminderContent1);

        mockReminderMessages.add(message1);

        return mockReminderMessages;
    }

    public static List<Dealer> createDealer (){

        Dealer dealer0 = new Dealer();
        dealer0.setId("dealer0");
        dealer0.setName("dealer0");
        dealer0.setDescription("Stadium Corssroads, navrangpura");
        dealer0.setBrandID("brandId");
        dealer0.setCreatedAt("2016-06-03");
        dealer0.setUpdatedAt("2016-06-03");
        dealer0.setRate(4);
        dealer0.setImagelUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
            Contact contact0 = new Contact();
            contact0.setFirstName("Matthews");
            contact0.setLastName("Barlow");
            contact0.setTel("+91 (900) 445-3807");
            contact0.setSsn("Gale");
            contact0.setAddress("757 Gallatin Place");
            contact0.setCity("Frystown");
            contact0.setZip(86351);
            contact0.setCountry("Faroe Islands");
            contact0.setContactEmail("galebarlow@visualix.com");
            contact0.setWebsite("Silvia.com");
            contact0.setLatitude(66.206727);
            contact0.setLongitude(85.51726);
        dealer0.setContact(contact0);
            Deal deal0 = new Deal();
            deal0.setProductID("575a963055b8439f41aac0ff");
            deal0.setBrandID("brandId");
            deal0.setPrice(53000);
            deal0.setShortDescription("15% off");
            List<Deal> deals0 = new ArrayList<>();
            deals0.add(deal0);
        dealer0.setDeals(deals0);

        mockDealers.add(dealer0);

        Dealer dealer1 = new Dealer();
        dealer1.setId("dealer1");
        dealer1.setName("dealer1");
        dealer1.setDescription("Shamal Corssroads, Satellite");
        dealer1.setBrandID("brandId");
        dealer1.setCreatedAt("2016-06-03");
        dealer1.setUpdatedAt("2016-06-03");
        dealer1.setRate(4);
        dealer1.setImagelUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
            Contact contact1 = new Contact();
            contact1.setFirstName("Elliott");
            contact1.setLastName("Sloan");
            contact1.setTel("+91 (987) 519-2511");
            contact1.setSsn("Esmeralda");
            contact1.setAddress("689 Surf Avenue");
            contact1.setCity("Fedora");
            contact1.setZip(42554);
            contact1.setCountry("Swaziland");
            contact1.setContactEmail("esmeraldasloan@visualix.com");
            contact1.setWebsite("Miranda.com");
            contact1.setLatitude(74.325343);
            contact1.setLongitude(27.392789);
        dealer1.setContact(contact1);
            Deal deal1 = new Deal();
            deal1.setProductID("575a9630d5725ffc91c74eab");
            deal1.setBrandID("brandId");
            deal1.setPrice(60000);
            deal1.setShortDescription("20% off");
            List<Deal> deals1 = new ArrayList<>();
            deals1.add(deal1);
        dealer1.setDeals(deals1);
        mockDealers.add(dealer1);

        return mockDealers;
    }

    public static List<Order> createOrders(){

        mockOrders.clear();

        Order order0 = new Order();
        order0.setId("order0");
        order0.setProductID("575a963055b8439f41aac0ff");
        order0.setCartID("cart0");
            Contact contact0 = new Contact();
            contact0.setFirstName("Matthews");
            contact0.setLastName("Barlow");
            contact0.setTel("+91 (900) 445-3807");
            contact0.setSsn("Gale");
            contact0.setAddress("757 Gallatin Place");
            contact0.setCity("Frystown");
            contact0.setZip(86351);
            contact0.setCountry("Faroe Islands");
            contact0.setContactEmail("galebarlow@visualix.com");
            contact0.setWebsite("Silvia.com");
            contact0.setLatitude(66.206727);
            contact0.setLongitude(85.51726);
        order0.setContact(contact0);
        order0.setCurrency("INR");
        order0.setCreatedAt("2016-06-03");
        order0.setCustomerID("c1");
        order0.setDeliveryCharges(50);
        order0.setDeliveryDate("2016-06-03");
        order0.setQuantity(1);
        order0.setStatus("On the Way");
        order0.setTotalAmount(100);
        order0.setUnitPrice(30);
        order0.setUpdatedAt("2016-06-03");

        mockOrders.add(order0);

        Order order1 = new Order();
        order1.setId("order1");
        order1.setProductID("575a9630d5725ffc91c74eab");
        order1.setCartID("cart1");
            Contact contact1 = new Contact();
            contact1.setFirstName("Elliott");
            contact1.setLastName("Sloan");
            contact1.setTel("+91 (987) 519-2511");
            contact1.setSsn("Esmeralda");
            contact1.setAddress("689 Surf Avenue");
            contact1.setCity("Fedora");
            contact1.setZip(42554);
            contact1.setCountry("Swaziland");
            contact1.setContactEmail("esmeraldasloan@visualix.com");
            contact1.setWebsite("Miranda.com");
            contact1.setLatitude(74.325343);
            contact1.setLongitude(27.392789);
        order1.setContact(contact1);
        order1.setCurrency("INR");
        order1.setCreatedAt("2016-06-03");
        order1.setCustomerID("c1");
        order1.setDeliveryCharges(40);
        order1.setDeliveryDate("2016-06-03");
        order1.setQuantity(2);
        order1.setStatus("Delivered Wednesday");
        order1.setTotalAmount(100);
        order1.setUnitPrice(40);
        order1.setUpdatedAt("2016-06-03");

        mockOrders.add(order1);

        return mockOrders;
    }

    public static RelatedProduct getRelatedProduct(String _Id){

        RelatedProduct selRelatedProduct = new RelatedProduct();
        createRelatedProducts();
        for (int i = 0; i < mockRelateProducts.size()-1; i++){
            RelatedProduct relatedProduct = mockRelateProducts.get(i);
            if (relatedProduct.getId().equals(_Id)){
                selRelatedProduct = relatedProduct;
            }
        }
        return  selRelatedProduct;
    }

    public static List<UsedProduct> createUsedProducts(){

        mockUsedProducts.clear();

        UsedProduct usedProduct0 = new UsedProduct();
        usedProduct0.setId("575aa374d0a3dddda3ccb2d1");
            MsrpCurrency msrpCurrency = new MsrpCurrency();
            msrpCurrency.setMsrpCurrency("INR");
        usedProduct0.setMsrpCurrency(msrpCurrency);
        usedProduct0.setMsrp(100000);
        usedProduct0.setBrandID("heroId");
        usedProduct0.setCreatedAt("2016/06/10");
        usedProduct0.setUpdatedAt("2016/06/10");
        usedProduct0.setCustomerID("575aa374d06f7df6578904d6");
        usedProduct0.setProductID("575a963055b8439f41aac0ff");
        usedProduct0.setWarrantyID("2016/06/10");

        mockUsedProducts.add(usedProduct0);

        UsedProduct usedProduct1 = new UsedProduct();
        usedProduct1.setId("575aa374dbdbf1be41895293");
        usedProduct1.setMsrpCurrency(msrpCurrency);
        usedProduct1.setMsrp(100000);
        usedProduct1.setBrandID("heroId");
        usedProduct1.setCreatedAt("2016/06/10");
        usedProduct1.setUpdatedAt("2016/06/10");
        usedProduct1.setCustomerID("575aa3746a675ace7bc03e4c");
        usedProduct1.setProductID("575a96305764b195b7c0f784");
        usedProduct1.setWarrantyID("2016/06/10");

        mockUsedProducts.add(usedProduct1);

        UsedProduct usedProduct2 = new UsedProduct();
        usedProduct2.setId("575aa37428dd5e00c221e7a4");
        usedProduct2.setMsrpCurrency(msrpCurrency);
        usedProduct2.setMsrp(100000);
        usedProduct2.setBrandID("heroId");
        usedProduct2.setCreatedAt("2016/06/10");
        usedProduct2.setUpdatedAt("2016/06/10");
        usedProduct2.setCustomerID("575aa37475a3cfdef25965b4");
        usedProduct2.setProductID("575a9630d5725ffc91c74eab");
        usedProduct2.setWarrantyID("2016/06/10");

        mockUsedProducts.add(usedProduct2);

        UsedProduct usedProduct3 = new UsedProduct();
        usedProduct3.setId("575aa374c4fd4f639ed44b2f");
        usedProduct3.setMsrpCurrency(msrpCurrency);
        usedProduct3.setMsrp(100000);
        usedProduct3.setBrandID("heroId");
        usedProduct3.setCreatedAt("2016/06/10");
        usedProduct3.setUpdatedAt("2016/06/10");
        usedProduct3.setCustomerID("575aa3747b1c545c44b3da9f");
        usedProduct3.setProductID("575a9630ac9474317edd8aab");
        usedProduct3.setWarrantyID("2016/06/10");

        mockUsedProducts.add(usedProduct3);

        UsedProduct usedProduct4 = new UsedProduct();
        usedProduct4.setId("575aa374229d425914c94610");
        usedProduct4.setMsrpCurrency(msrpCurrency);
        usedProduct4.setMsrp(100000);
        usedProduct4.setBrandID("heroId");
        usedProduct4.setCreatedAt("2016/06/10");
        usedProduct4.setUpdatedAt("2016/06/10");
        usedProduct4.setCustomerID("575aa3742fcb7ea983ff4576");
        usedProduct4.setProductID("575a963098d67ff6c6e38cc9");
        usedProduct4.setWarrantyID("2016/06/10");

        mockUsedProducts.add(usedProduct4);

        return mockUsedProducts;
    }

    public static List<Service> createServices(){

        mockServices.clear();

        Service service0 = new Service();
        service0.setId("575ace1ea6b98c418f0cb3b4");
        service0.setCreatedAt("2016-06-10");
        service0.setUpdatedAt("2016-06-10");
        service0.setBrandID("heroId");
        service0.setType("insurance");
        service0.setName("IFFCO TOKIO");
        service0.setServiceCompanyID("575acf2274550a837e5d9c92");
        service0.setPrice(390);
        service0.setCurrency("CAD");
        service0.setDeal(176);
        service0.setDescription("Veniam amet commodo sit non ipsum quis labore aliquip ad qui adipisicing exercitation.");
        service0.setDuration("10");

        mockServices.add(service0);

        Service service1 = new Service();
        service1.setId("575ace1eb10c0ee37376bb04");
        service1.setCreatedAt("2016-06-10");
        service1.setUpdatedAt("2016-06-10");
        service1.setBrandID("heroId");
        service1.setType("insurance");
        service1.setName("Tata AIG Insurance");
        service1.setServiceCompanyID("575acf2275674a353e016eb1");
        service1.setPrice(794);
        service1.setCurrency("EUR");
        service1.setDeal(736);
        service1.setDescription("Ex sit et aliqua fugiat nostrud eu nulla ut sunt commodo aliqua quis laborum non.");
        service1.setDuration("3");

        mockServices.add(service1);

        Service service2 = new Service();
        service2.setId("575ace1e651e7584f70664a8");
        service2.setCreatedAt("2016-06-10");
        service2.setUpdatedAt("2016-06-10");
        service2.setBrandID("heroId");
        service2.setType("insurance");
        service2.setName("IFFCO TOKIO");
        service2.setServiceCompanyID("575acf22b15e97e514ee09c9");
        service2.setPrice(165);
        service2.setCurrency("USD");
        service2.setDeal(508);
        service2.setDescription("Aute commodo adipisicing ut in sit cillum anim in consequat.");
        service2.setDuration("5");

        mockServices.add(service2);

        Service service3 = new Service();
        service3.setId("575ace1e1f5849436f5398c2");
        service3.setCreatedAt("2016-06-10");
        service3.setUpdatedAt("2016-06-10");
        service3.setBrandID("heroId");
        service3.setType("insurance");
        service3.setName("Tata AIG Insurance");
        service3.setServiceCompanyID("575acf22971c7d6ccacf371f");
        service3.setPrice(540);
        service3.setCurrency("AUD");
        service3.setDeal(615);
        service3.setDescription("Veniam Lorem minim anim irure sunt labore ullamco qui qui laboris.");
        service3.setDuration("10");

        mockServices.add(service3);

        Service service4 = new Service();
        service4.setId("575ace1e6b97709ffa66326d");
        service4.setCreatedAt("2016-06-10");
        service4.setUpdatedAt("2016-06-10");
        service4.setBrandID("heroId");
        service4.setType("insurance");
        service4.setName("Tata AIG Insurance");
        service4.setServiceCompanyID("575acf22c0f1ccff86fd9c74");
        service4.setPrice(541);
        service4.setCurrency("GBP");
        service4.setDeal(662);
        service4.setDescription("Et aute aliquip laboris minim duis eu consectetur est.");
        service4.setDuration("4");

        mockServices.add(service4);

        Service service5 = new Service();
        service5.setId("575ace1e50dcf4edb271a736");
        service5.setCreatedAt("2016-06-10");
        service5.setUpdatedAt("2016-06-10");
        service5.setBrandID("heroId");
        service5.setType("insurance");
        service5.setName("Relince General");
        service5.setServiceCompanyID("575acf22b5e0387fba50fa1e");
        service5.setPrice(548);
        service5.setCurrency("CAD");
        service5.setDeal(196);
        service5.setDescription("Elit id sint eiusmod esse ullamco sint incididunt nostrud occaecat elit sint sunt.");
        service5.setDuration("5");

        mockServices.add(service5);

        Service service6 = new Service();
        service6.setId("575ace1e21cf57f81a22d96f");
        service6.setCreatedAt("2016-06-10");
        service6.setUpdatedAt("2016-06-10");
        service6.setBrandID("heroId");
        service6.setType("insurance");
        service6.setName("Relince General");
        service6.setServiceCompanyID("575acf228194ac5d99933b3d");
        service6.setPrice(530);
        service6.setCurrency("CAD");
        service6.setDeal(710);
        service6.setDescription("Eu qui sit voluptate voluptate incididunt voluptate consectetur minim id duis irure non amet consequat.");
        service6.setDuration("5");

        mockServices.add(service6);

        return mockServices;
    }

}
