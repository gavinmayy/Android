package com.warrantix.main.common.rest;

import android.os.StrictMode;

import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.pref.MockData;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.rest.model.UsedProduct;
import com.warrantix.main.common.rest.request.PullMessageRequest;
import com.warrantix.main.common.rest.response.GetDealersResponse;
import com.warrantix.main.common.rest.response.GetOrdersResponse;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.common.rest.response.GetProductsResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductsResponse;
import com.warrantix.main.common.rest.response.GetServiceCompaniesResponse;
import com.warrantix.main.common.rest.response.GetUsedProductsResponse;
import com.warrantix.main.common.rest.response.GetServicesResponse;
import com.warrantix.main.common.rest.response.PullMessageResponse;
import com.warrantix.main.common.rest.model.RoleInfo;
import com.warrantix.main.common.rest.request.CustomerLoginRequest;
import com.warrantix.main.common.rest.request.DeviceUpdateGCMTokenRequest;
import com.warrantix.main.common.rest.request.WalletAddCustomerRequest;
import com.warrantix.main.common.rest.response.CustomerLoginResponse;
import com.warrantix.main.common.rest.model.Device;
import com.warrantix.main.common.rest.model.Wallet;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.DeviceResponse;
import com.warrantix.main.common.rest.response.ErrorMessageResponse;
import com.warrantix.main.common.rest.response.WalletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.warrantix.main.R.string.Dealers;
import static com.warrantix.main.R.string.pound_currency;

public class WarrantixWebService {
    public static final String TAG = WarrantixWebService.class.getSimpleName();
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static boolean isDisabled = false;

    private Retrofit client;
    private WarrantixApi mWarrentixApi;
    private String jwtToken = "test";

    private static WarrantixWebService instance = null;
    public static WarrantixWebService getInstance() {
        if (instance == null)
            instance = new WarrantixWebService();

        return instance;
    }

    public WarrantixWebService()
    {
        if (isDisabled == true)
            return;

//        Gson gSon = new GsonBuilder().setPrettyPrinting().create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
                                      @Override
                                      public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                                          okhttp3.Request original = chain.request();
                                          okhttp3.Request request = original.newBuilder()
                                                  .header("authorization", jwtToken)
                                                  .method(original.method(), original.body())
                                                  .build();
                                          return chain.proceed(request);
                                      }
                                  });

        OkHttpClient okHttpClient = httpClient.build();

        client = new Retrofit.Builder()
                .baseUrl(Api.SERVER)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(/*gSon*/))
                .build();

        mWarrentixApi = client.create(WarrantixApi.class);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        BusProvider.get().register(this);
    }

    //
    //
    //
    public synchronized ErrorMessageResponse sendAddFamilyToWalletRequest(final String walletID, final Customer customer) {
        WalletAddCustomerRequest request = new WalletAddCustomerRequest();
        request.setCustomerID(customer.getId());
        request.setWalletID(walletID);

        Call<ErrorMessageResponse> call = mWarrentixApi.addCustomerToWallet(request);
        try {
            Response<ErrorMessageResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                ErrorMessageResponse errorResponse = new ErrorMessageResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send update customer information to backend service
    //
    public synchronized CustomerResponse sendPutCustomerByIdRequest(final String customerID, final Customer customer) {

        Call<CustomerResponse> call = mWarrentixApi.updateCustomerById(customerID, customer);
        try {
            Response<CustomerResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                CustomerResponse errorResponse = new CustomerResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //
    // send Get Customer information request to backend service
    //
    public synchronized CustomerResponse sendGetCustomerByIDRequest(final String customerID) {

        Call<CustomerResponse> call = mWarrentixApi.getCustomerById(customerID);
        try {
            Response<CustomerResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                CustomerResponse errorResponse = new CustomerResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send Get Wallet information request to backend service
    //
    public synchronized WalletResponse sendGetWalletByIDRequest(final String walletID) {

        Call<WalletResponse> call = mWarrentixApi.getWalletById(walletID);
        try {
            Response<WalletResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                WalletResponse errorResponse = new WalletResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //
    // send Get Device information request to backend service
    //
    public synchronized DeviceResponse sendGetDeviceByIDRequest(final String deviceID) {

        Call<DeviceResponse> call = mWarrentixApi.getDeviceById(deviceID);
        try {
            Response<DeviceResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                DeviceResponse errorResponse = new DeviceResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //
    // send GCM token registration request to backend service
    //
    public synchronized DeviceResponse sendGCMTokenRegistrationRequest(final String deviceID, final String gcmToken) {

        DeviceUpdateGCMTokenRequest gcmTokenRequest = new DeviceUpdateGCMTokenRequest();
        gcmTokenRequest.setToken(gcmToken);

        Call<DeviceResponse> call = mWarrentixApi.updateGCMTokenForDevice(deviceID, gcmTokenRequest);
        try {
            Response<DeviceResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                DeviceResponse errorResponse = new DeviceResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    //
    // send device registration request to backend service
    //
    public synchronized DeviceResponse sendDeviceRegistrationRequest(final String mobileNumber, final String customerID) {
        Device newDevice = new Device();
        newDevice.setTel(mobileNumber);
        newDevice.setOwner(customerID);

        Call<DeviceResponse> call = mWarrentixApi.registerDevices(newDevice);
        try {
            Response<DeviceResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                DeviceResponse errorResponse = new DeviceResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send wallet registration request to backend service
    //
    public synchronized WalletResponse sendWalletRegistrationRequest(final String customerID) {
        Wallet newWallet = new Wallet();
        newWallet.setPrimaryID(customerID);

        Call<WalletResponse> call = mWarrentixApi.registerWallet(newWallet);
        try {
            Response<WalletResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                WalletResponse errorResponse = new WalletResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send customer login request to backend service
    //
    public synchronized CustomerLoginResponse sendCustomerLoginRequest(final String username, final String password) {
        CustomerLoginRequest loginRequest = new CustomerLoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call<CustomerLoginResponse> call = mWarrentixApi.customerLogin(loginRequest);
        try {
            Response<CustomerLoginResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                jwtToken = response.body().getJWT();
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                CustomerLoginResponse errorResponse = new CustomerLoginResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //
    // send customer registration request to WALLET backend service
    //
    public synchronized CustomerResponse sendCustomerRegistrationRequest(final String customerName, final String email, final String password, final String mobileNumber) {
        Customer mainCustomer = new Customer();
        Contact mainContact = new Contact();
        mainContact.setTel(mobileNumber);

        mainCustomer.setContact(mainContact);
        mainCustomer.setUsername(email);
        mainCustomer.setPassword(password);

        Call<CustomerResponse> call = mWarrentixApi.registerCustomer(mainCustomer);
        try
        {
            Response<CustomerResponse> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                return response.body();
            }
            else {
                okhttp3.Response raw = response.raw();

                CustomerResponse errorResponse = new CustomerResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized PullMessageResponse getMessages(String type) {

        String acType = type;

        RoleInfo to = new RoleInfo();
        to.setRole("consumer");
        to.setId("c1");

//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
//        String strDate = "Current Date : " + mdformat.format(calendar.getTime());

        final PullMessageRequest acPullMessageRequest = new PullMessageRequest();
        acPullMessageRequest.setTo(to);
        acPullMessageRequest.setSince("2016-06-01");
        acPullMessageRequest.setType(acType);

        if (acType.equals("b2c")) {
            Call<List<Message>> call = mWarrentixApi.getMessages(acPullMessageRequest);
            try {
                Response<List<Message>> response = call.execute();
                if ((response != null) && (response.body() != null)) {
                    PullMessageResponse pullMessageResponse = new PullMessageResponse();
                    pullMessageResponse.setMessages(response.body());
                    return pullMessageResponse;
                } else {
                    okhttp3.Response raw = response.raw();
                    PullMessageResponse errorResponse = new PullMessageResponse();
                    errorResponse.setCode(raw.code());
                    errorResponse.setMessage(raw.message());
                    return errorResponse;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else if(acType.equals("reminder")){

            List<Message> reminderMessages = MockData.createReminderMessages();
            PullMessageResponse pullMessageResponse = new PullMessageResponse();
            pullMessageResponse.setMessages(reminderMessages);
            return pullMessageResponse;
        } else {
            PullMessageResponse pullMessageResponse = new PullMessageResponse();
            return pullMessageResponse;
        }

    }

    public synchronized GetProductsResponse getProducts(String brandID) {

        String acBrandID = brandID;

        Call<List<Product>> call = mWarrentixApi.getProducts();
        try {
            Response<List<Product>> response = call.execute();
            if ((response != null) && (response.body() != null)) {
                GetProductsResponse getProductsResponse = new GetProductsResponse();
                getProductsResponse.setProducts(response.body());
                return getProductsResponse;
            } else {
                okhttp3.Response raw = response.raw();
                GetProductsResponse errorResponse = new GetProductsResponse();
                errorResponse.setCode(raw.code());
                errorResponse.setMessage(raw.message());
                return errorResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public synchronized GetUsedProductsResponse getUsedProducts(String brandID) {

        String acBrandID = brandID;

//        Call<List<UsedProduct>> call = mWarrentixApi.getUsedProducts(acBrandID);
//        try {
//            Response<List<UsedProduct>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetUsedProductsResponse getUsedProductsResponse = new GetUsedProductsResponse();
//                getUsedProductsResponse.setUsedProducts(response.body());
//                return getUsedProductsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetUsedProductsResponse errorResponse = new GetUsedProductsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        List<UsedProduct> usedProducts = MockData.createUsedProducts();
        GetUsedProductsResponse getUsedProductResponse = new GetUsedProductsResponse();
        if (usedProducts.size() != 0 ){
            getUsedProductResponse.setUsedProducts(usedProducts);
        } else {
            getUsedProductResponse.setCode(400);
            getUsedProductResponse.setMessage("There is not Product with ");
        }

        return getUsedProductResponse;

    }

    public synchronized GetRelatedProductsResponse getRelatedProducts(String type, String brandID) {

        String acType = type;
        String acBrandID = brandID;

//        Call<List<RelatedProduct>> call = mWarrentixApi.getRelatedProducts(acType,acBrandID);
//        try {
//            Response<List<RelatedProduct>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetRelatedProductsResponse getRelatedProductsResponse = new GetRelatedProductsResponse();
//                getRelatedProductsResponse.setRelatedProducts(response.body());
//                return getRelatedProductsResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetRelatedProductsResponse errorResponse = new GetRelatedProductsResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        List<RelatedProduct> relatedProducts = MockData.createRelatedProducts();
        GetRelatedProductsResponse getRelatedProductsResponse = new GetRelatedProductsResponse();
        if (relatedProducts.size() != 0 ){
           getRelatedProductsResponse.setRelatedProducts(relatedProducts);
        } else {
            getRelatedProductsResponse.setCode(400);
            getRelatedProductsResponse.setMessage("There is not Product with ");
        }

        return getRelatedProductsResponse;

    }

    public synchronized GetDealersResponse getDealersResponse() {

//        Call<List<Dealer>> call = mWarrentixApi.getDealers();
//        try {
//            Response<List<Dealer>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetDealersResponse getDealersResponse= new GetDealersResponse();
//                getDealersResponse.setDealers(response.body());
//                return getDealersResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetDealersResponse errorResponse = new GetDealersResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        List<Dealer> dealers = MockData.createDealer();
        GetDealersResponse getDealersResponse= new GetDealersResponse();
        if (dealers.size() != 0 ){
            getDealersResponse.setDealers(dealers);
        } else {
            getDealersResponse.setCode(400);
            getDealersResponse.setMessage("There is not Product with ");
        }

        return getDealersResponse;

    }

    public synchronized GetServicesResponse getServices(String type, String brandID) {

//        String acType = type;
//        String acBrandID = brandID;
//
//        Call<List<Service>> call = mWarrentixApi.getServices(acType, acBrandID);
//        try {
//            Response<List<Service>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetServicesResponse getServicesResponse = new GetServicesResponse();
//                getServicesResponse.setServices(response.body());
//                return getServicesResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetServicesResponse errorResponse = new GetServicesResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        List<Service> services = MockData.createServices();
        GetServicesResponse getServicesResponse= new GetServicesResponse();
        if (services.size() != 0 ){
            getServicesResponse.setServices(services);
        } else {
            getServicesResponse.setCode(400);
            getServicesResponse.setMessage("There is not Product with ");
        }

        return getServicesResponse;

    }

    public synchronized GetServiceCompaniesResponse getServiceCompanies(String type, String brandID) {

        String acType = type;
        String acBrandID = brandID;

//        Call<List<ServiceCompany>> call = mWarrentixApi.getServiceCompanies(acType, acBrandID);
//        try {
//            Response<List<ServiceCompany>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetServiceCompaniesResponse getServiceCompaniesResponse = new GetServiceCompaniesResponse();
//                getServiceCompaniesResponse.setServiceCompanies(response.body());
//                return getServiceCompanyResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetServiceCompaniesResponse errorResponse = new GetServiceCompaniesResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        List<ServiceCompany> serviceCompanies = MockData.createServiceCompanies();
        GetServiceCompaniesResponse getServiceCompaniesResponse = new GetServiceCompaniesResponse();
        if (serviceCompanies.size() != 0 ){
            getServiceCompaniesResponse.setServiceCompanies(serviceCompanies);
        } else {
            getServiceCompaniesResponse.setCode(400);
            getServiceCompaniesResponse.setMessage("There is not ServiceCompanies");
        }

        return getServiceCompaniesResponse;
    }

    public synchronized GetProductResponse getProduct(String productID) {

        String acProductID = productID;
// api call
//        Call<Product> call = mWarrentixApi.getProduct(acProductID);
//        try {
//            Response<Product> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetProductResponse getProductResponse = new GetProductResponse();
//                getProductResponse.setProduct(response.body());
//                return getProductResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetProductResponse errorResponse = new GetProductResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        Product product = MockData.getProduct(productID);
        GetProductResponse getProductResponse = new GetProductResponse();
        if (product != null){
            getProductResponse.setProduct(product);
        } else {
            getProductResponse.setCode(400);
            getProductResponse.setMessage("There is not Product with "+productID);
        }

        return getProductResponse;
    }

    public synchronized GetRelatedProductResponse getRelatedProduct(String _Id) {

//        GetRelatedProductResponse getRelatedProductResponse = new GetRelatedProductResponse();
//        Call<RelatedProduct> call = mWarrentixApi.getRelatedProduct(productID);
//        try {
//            Response<RelatedProduct> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                getRelatedProductResponse.setRelatedProduct(response.body());
//                return getRelatedProductResponse;
//            }
//            else {
//                okhttp3.Response raw = response.raw();
//                GetRelatedProductResponse errorResponse = new GetRelatedProductResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }


        RelatedProduct relatedProduct = MockData.getRelatedProduct(_Id);
        GetRelatedProductResponse getRelatedProductResponse = new GetRelatedProductResponse();
        getRelatedProductResponse.setRelatedProduct(relatedProduct);

        return getRelatedProductResponse;

    }

    public synchronized GetOrdersResponse getOrdersResponse() {

//        Call<List<Order>> call = mWarrentixApi.getOrders();
//        try {
//            Response<List<Order>> response = call.execute();
//            if ((response != null) && (response.body() != null)) {
//                GetOrdersResponse getOrdersResponse = new GetOrdersResponse();
//                getOrdersResponse.setOrders(response.body());
//                return getOrdersResponse;
//            } else {
//                okhttp3.Response raw = response.raw();
//                GetOrdersResponse errorResponse = new GetOrdersResponse();
//                errorResponse.setCode(raw.code());
//                errorResponse.setMessage(raw.message());
//                return errorResponse;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        List<Order> orders = MockData.createOrders();
        GetOrdersResponse getOrdersResponse = new GetOrdersResponse();
        if (orders.size() != 0 ){
            getOrdersResponse.setOrders(orders);
        } else {
            getOrdersResponse.setCode(400);
            getOrdersResponse.setMessage("There is not Product with ");
        }

        return getOrdersResponse;

    }

}
