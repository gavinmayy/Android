package com.warrantix.main.common.rest;


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
import com.warrantix.main.common.rest.request.CustomerChangePasswordRequest;
import com.warrantix.main.common.rest.request.CustomerFacebookIDRequest;
import com.warrantix.main.common.rest.request.CustomerForgottenPasswordRequest;
import com.warrantix.main.common.rest.request.CustomerLoginRequest;
import com.warrantix.main.common.rest.request.DeviceUpdateGCMTokenRequest;
import com.warrantix.main.common.rest.request.WalletAddCustomerRequest;
import com.warrantix.main.common.rest.request.WalletRemoveCustomerRequest;
import com.warrantix.main.common.rest.response.CustomerLoginResponse;
import com.warrantix.main.common.rest.model.Device;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.DeviceResponse;
import com.warrantix.main.common.rest.response.ErrorMessageResponse;
import com.warrantix.main.common.rest.model.Wallet;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;
import com.warrantix.main.common.rest.response.WalletResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface WarrantixApi
{

    // ------------------------------------------------
    // DEVICE API Endpoints
    // ------------------------------------------------

    //
    // Retrieves a list of all existing devices from the Warrantix ecosystem
    //
    @GET("devices")
    public Call<List<Device>> getDevices();

    //
    // Creates a new device in the Warrantix ecosystem
    //
    @POST("devices")
    public Call<DeviceResponse> registerDevices(@Body Device device);

    //
    // Removes an existing device from the Warrantix ecosystem
    //
    @DELETE("devices/{id}")
    public Call<ErrorMessageResponse> deleteDevice(@Path("id") String deviceID);

    //
    // Retrieves a device by ID
    //
    @GET("devices/{id}")
    public Call<DeviceResponse> getDeviceById(@Path("id") String deviceID);

    //
    // Updates an existing device
    //
    @PUT("devices/{id}")
    public Call<DeviceResponse> updateDeviceById(@Path("id") String deviceID, @Body Device device);

    //
    // Updates a APN/GCM token of a device
    //
    @POST("devices/{id}/token")
    public Call<DeviceResponse> updateGCMTokenForDevice(@Path("id") String deviceID, @Body DeviceUpdateGCMTokenRequest gcmTokenRequest);

    // ------------------------------------------------
    // CUSTOMER API Endpoints
    // ------------------------------------------------

    //
    // List all customers on the Warrantix ecosystem
    //
    @GET("customers")
    public Call<List<Customer>> getCustomerList();

    //
    // Declares and creates a new customer in the Warrantix ecosystem
    //
    @POST("customers")
    public Call<CustomerResponse> registerCustomer(@Body Customer customer);


    //
    // Removes an existing customer from the Warrantix ecosystem
    //
    @DELETE("customers/{id}")
    public Call<ErrorMessageResponse> deleteCustomer(@Path("id") String deviceID);

    //
    // Retrieves a customer by ID
    //
    @GET("customers/{id}")
    public Call<CustomerResponse> getCustomerById(@Path("id") String customerID);

    //
    // Updates an existing customer
    //
    @PUT("customers/{id}")
    public Call<CustomerResponse> updateCustomerById(@Path("id") String customerID, @Body Customer customer);

    //
    // Customer login
    //
    @POST("customers/login")
    public Call<CustomerLoginResponse> customerLogin(@Body CustomerLoginRequest loginRequest);

    //
    // Change a customer password
    //
    @POST("customers/changePassword")
    public Call<ErrorMessageResponse> customerChangePassword(@Body CustomerChangePasswordRequest changePasswordRequest);

    //
    // Send a reset token link to a customer
    //
    @POST("customers/forgottenPassword")
    public Call<ErrorMessageResponse> customerForgottenPassword(@Body CustomerForgottenPasswordRequest forgottenPasswordRequest);

    //
    // Stores Facebook access token and user_id for a given customer
    //
    @POST("customers/{id}/facebookID")
    public Call<ErrorMessageResponse> customerStoreFacebookID(@Path("id") String customerID,
                                                              @Body CustomerFacebookIDRequest facebookIDRequest);


    //
    // Get a list of friends IDs for a given customer
    //
    @GET("customers/{id}/friends")
    public Call<List<Customer>> getFriendsByCustomer(@Path("id") String customerID);


    // ------------------------------------------------
    // WALLET API Endpoints
    // ------------------------------------------------

    //
    // Retrieves a list of all existing wallets from the Warrantix ecosystem
    //
    @GET("wallets")
    public Call<List<Wallet>> getWalletList();

    //
    // Generate a new wallet and the walletID for a customer (the primary customer)
    //
    @POST("wallets")
    public Call<WalletResponse> registerWallet(@Body Wallet wallet);

    //
    // Removes an existing wallet
    //
    @DELETE("wallets/{id}")
    public Call<ErrorMessageResponse> deleteWallet(@Path("id") String walletID);

    //
    // Retrieves an existing wallet by ID from the Warrantix ecosystem
    //
    @GET("wallets/{id}")
    public Call<WalletResponse> getWalletById(@Path("id") String walletID);

    //
    // Updates an existing wallet
    //
    @PUT("wallets/{id}")
    public Call<WalletResponse> updateWalletById(@Path("id") String walletID, @Body Wallet wallet);

    //
    // Creates a new customer and add it to a wallet household
    //
    @POST("wallets/addCustomer")
    public Call<ErrorMessageResponse> addCustomerToWallet(@Body WalletAddCustomerRequest addCustomerRequest);

    //
    // Removes a customer from a wallet. Customer's warranties will be transferred to the primary customer ID
    //
    @DELETE("wallets/removeCustomer")
    public Call<ErrorMessageResponse> removeCustomerFromWallet(@Body WalletRemoveCustomerRequest removeCustomerRequest);

    // pull the messages
    @POST("messages/pull")
    public Call<List<Message>> getMessages(@Body PullMessageRequest pullMessageRequest);

    // get the usedProducts
    @GET("usedProducts")
    public Call<List<UsedProduct>> getUsedProducts(@Query("brandID") String brandID);

    // get the products
    @GET("products")
    public Call<List<Product>> getProducts();

    // get the product with id
    @GET("products/{id}")
    public Call<Product> getProduct(@Path("id") String productID);

    // get the relatedProducts
    @GET("relatedProducts")
    public Call<List<RelatedProduct>> getRelatedProducts(@Query("type") String type,
                                                         @Query("brandID") String brandID);

    // get the relatedProduct
    @GET("relatedProducts/{id}")
    public Call<RelatedProduct> getRelatedProduct(@Path("id") String productID);

    // get the services
    @GET("services")
    public Call<List<Service>> getServices(@Query("type") String type,
                                           @Query("brandID") String brandID);

    // get the serviceCompanies
    @GET("serviceCompanies")
    public Call<List<ServiceCompany>> getServiceCompanies(@Query("type") String type,
                                                  @Query("brandID") String brandID);


    // get the dealers
    @GET("relatedProducts")
    public Call<List<Dealer>> getDealers();

    // get the dealers
    @GET("orders")
    public Call<List<Order>> getOrders();


}
