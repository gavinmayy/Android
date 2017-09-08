package com.warrantix.main.manager;

import android.util.Log;

import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.bus.BusProvider;
import com.warrantix.main.common.event.ProductSuccessEvent;
import com.warrantix.main.common.event.ProductsSuccessEvent;
import com.warrantix.main.common.event.RelatedProductSuccessEvent;
import com.warrantix.main.common.event.RelatedProductsSuccessEvent;
import com.warrantix.main.common.event.UserAccountFailedEvent;
import com.warrantix.main.common.event.UserAccountSuccessEvent;
import com.warrantix.main.common.pref.WarrantixPreference;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.Customer;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.response.CustomerLoginResponse;
import com.warrantix.main.common.rest.model.Wallet;
import com.warrantix.main.common.rest.response.CustomerResponse;
import com.warrantix.main.common.rest.response.DeviceResponse;
import com.warrantix.main.common.rest.response.GetDealersResponse;
import com.warrantix.main.common.rest.response.GetOrdersResponse;
import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.common.rest.response.GetProductsResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductsResponse;
import com.warrantix.main.common.rest.response.GetServiceCompaniesResponse;
import com.warrantix.main.common.rest.response.GetServicesResponse;
import com.warrantix.main.common.rest.response.WalletResponse;
import com.warrantix.main.common.rest.response.GetUsedProductsResponse;

import java.util.ArrayList;
import java.util.List;


public class BackendManager {
    private static final String TAG = BackendManager.class.getSimpleName();

    private static BackendManager instance = null;

    public static BackendManager getInstance() {
        if (instance == null)
            instance = new BackendManager();

        return instance;
    }

    public BackendManager() {

        BusProvider.get().register(this);
    }

    //
    // synchronize all information in Customer model
    //
    private Customer synchronizeCustomer(String customerID) {

        // get customer information
        CustomerResponse response = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(customerID);
        if ((response == null) || ( response.getCode() != 0))
            return null;

        // get child devices
        if (response.getDeviceID() != null) {
            for (int i=0; i<response.getDeviceID().size(); i++) {
                String mainDeviceID = response.getDeviceID().get(i);
                DeviceResponse mainDeviceResponse = WarrantixWebService.getInstance().sendGetDeviceByIDRequest(mainDeviceID);
                if ((mainDeviceResponse == null) || (mainDeviceResponse.getCode() != 0)) {
                    continue;
                }

                response.addDevice(mainDeviceID, mainDeviceResponse);
            }
        }

        return response;
    }

    //
    // synchronize all information in Wallet Model
    //
    private Wallet synchronizeWallet(String walletID) {

        // get wallet information
        WalletResponse response = WarrantixWebService.getInstance().sendGetWalletByIDRequest(walletID);
        if ((response == null) || (response.getCode() != 0))
            return null;

        // if family members exists,
        ArrayList<String> houseHolds = (ArrayList<String>) response.getHousehold();
        if (houseHolds == null)
            return response;

        for (int i=0; i<houseHolds.size(); i++) {
            String customerID = houseHolds.get(i);

            CustomerResponse familyCustomerRespone = WarrantixWebService.getInstance().sendGetCustomerByIDRequest(customerID);
            if ((familyCustomerRespone == null) || (familyCustomerRespone.getCode() != 0)) {
                continue;
            }

            Customer familyCustomer = synchronizeCustomer(familyCustomerRespone.getId());
            if (familyCustomer == null)
                continue;

            response.addFamily(customerID, familyCustomer);
        }

        return response;
    }

    // update main customer
    public void updateMainCustomer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (WarrantixPreference.warrantixConfig.hasMainCustomer() != true)
                    return;

                //
                // Get the main customer
                //
                Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();
                CustomerResponse response = WarrantixWebService.getInstance().sendPutCustomerByIdRequest(mainCustomer.getId(), mainCustomer);
                if (response == null) {
                    UserAccountFailedEvent failedEvent = new UserAccountFailedEvent("Failed to update customer");
                    BusProvider.get().post(failedEvent);
                }
                else if (response.getCode() != 0) {
                    UserAccountFailedEvent failedEvent = new UserAccountFailedEvent(response.getMessage());
                    BusProvider.get().post(failedEvent);
                }
                else {
                    UserAccountSuccessEvent successEvent = new UserAccountSuccessEvent();
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    // synchronize DB
    public void synchronizeDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (WarrantixPreference.warrantixConfig.hasMainCustomer() != true)
                    return;

                //
                // Get the main customer
                //
                Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();
                mainCustomer = BackendManager.getInstance().synchronizeCustomer(mainCustomer.getId());
                if (mainCustomer == null)
                    return;

                WarrantixPreference.warrantixConfig.setMainCustomer(mainCustomer);

                //
                // Get the main Wallet ID from main customer
                //
                Wallet mainWallet = WarrantixPreference.warrantixConfig.getMainWallet();
                mainWallet = BackendManager.getInstance().synchronizeWallet(mainWallet.getId());
                if (mainWallet == null)
                    return;

                WarrantixPreference.warrantixConfig.setMainWallet(mainWallet);
                WarrantixPreference.writeConfig();
            }
        }).start();
    }


    // customer signup
    public void customerSignup(final String customerName, final String mobileNumber, final String email, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //
                // register new customer
                //
                CustomerResponse registeredCustomer =  WarrantixWebService.getInstance().sendCustomerRegistrationRequest(customerName, email, password, mobileNumber);
                if (registeredCustomer == null) {
                    WarrantixApplication.getInstance().showMessage("Registration", "Failed to add new customer");
                    return;
                }
                else if (registeredCustomer.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Registration", registeredCustomer.getMessage());
                    return;
                }

                // Save main customer into preference
                WarrantixPreference.warrantixConfig.setMainCustomer(registeredCustomer);
                WarrantixPreference.writeConfig();

                //
                // call sign in
                //
                Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();
                CustomerLoginResponse customerResponse = WarrantixWebService.getInstance().sendCustomerLoginRequest(mainCustomer.getUsername(), mainCustomer.getPassword());

                if (customerResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Registration", "Failed to login with new customer");
                    return;
                }
                else if (customerResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Registration", customerResponse.getMessage());
                    return;
                }

                //
                // register new wallet
                //
                String customerId = WarrantixPreference.warrantixConfig.getMainCustomer().getId();
                WalletResponse walletResponse = WarrantixWebService.getInstance().sendWalletRegistrationRequest(customerId);
                if (walletResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Wallet", "Failed to create wallet for customer");
                    return;
                }
                else if (walletResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Wallet", walletResponse.getMessage());
                    return;
                }

                WarrantixPreference.warrantixConfig.setMainWallet(walletResponse);
                WarrantixPreference.writeConfig();


                //
                // register new device
                //
                String mobileNumber = WarrantixPreference.warrantixConfig.getMainCustomer().getContact().getTel();
                DeviceResponse deviceResponse = WarrantixWebService.getInstance().sendDeviceRegistrationRequest(mobileNumber, customerId);
                if (deviceResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Device", "Failed to create device for customer");
                    return;
                }
                else if (deviceResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Device", deviceResponse.getMessage());
                    return;
                }

                WarrantixPreference.warrantixConfig.setMainDevice(deviceResponse);
                WarrantixPreference.writeConfig();

                WarrantixApplication.getInstance().showMessage("Info", "Customer Registration is succeeded");

                BackendManager.getInstance().synchronizeDB();

            }
        }).start();
    }

    public void customerSignin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Customer mainCustomer = WarrantixPreference.warrantixConfig.getMainCustomer();
                CustomerLoginResponse customerResponse = WarrantixWebService.getInstance().sendCustomerLoginRequest(mainCustomer.getUsername(), mainCustomer.getPassword());

                if (customerResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Login", "Failed to login");
                }
                else if (customerResponse.getCode() != 0) {
                    String message = customerResponse.getMessage();
                    WarrantixApplication.getInstance().showMessage("Login", message);
                }
                else {
                    WarrantixApplication.getInstance().showMessage("Info", "Login succeeded");
                    BackendManager.getInstance().synchronizeDB();
                }

                return;
            }
        }).start();
    }

    public void registerGCMToken(final String gcmToken) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String deviceId = WarrantixPreference.warrantixConfig.getMainDevice().getId();
                DeviceResponse deviceResponse = WarrantixWebService.getInstance().sendGCMTokenRegistrationRequest(deviceId, gcmToken);

                if (deviceResponse == null) {
                    Log.v(TAG, "Register GCM token is failed");
                }
                else if (deviceResponse.getCode() != 0) {
                    Log.v(TAG, "Register GCM token is failed");
                }
                else {
                    WarrantixPreference.warrantixConfig.setMainDevice(deviceResponse);
                }
            }
        }).start();
    }

    // synchronize database from backend
    public void synchronize() {

    }

    public void addFamilyCustomer(final String familyName, final String familyNumber, final String familyEmail) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                // register new customer
                //
                CustomerResponse registeredCustomer =  WarrantixWebService.getInstance().sendCustomerRegistrationRequest(familyName, familyName, "qqqqqqqq", familyNumber);
                if (registeredCustomer == null) {
                    WarrantixApplication.getInstance().showMessage("Registration", "Failed to add family customer");
                    return;
                }
                else if (registeredCustomer.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Registration", registeredCustomer.getMessage());
                    return;
                }

                //
                // add new customer
                //
                Wallet mainWallet = WarrantixPreference.warrantixConfig.getMainWallet();
                WarrantixWebService.getInstance().sendAddFamilyToWalletRequest(mainWallet.getId(), registeredCustomer);

            }
        }).start();
    }

    public void addDeviceToCustomer(final String mobileNumber, final Customer familyCustomer) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //
                // register new device
                //
                DeviceResponse newDevice = WarrantixWebService.getInstance().sendDeviceRegistrationRequest(mobileNumber, familyCustomer.getId());
                if (newDevice == null) {
                    WarrantixApplication.getInstance().showMessage("Device", "Failed to add device to customer");
                    return;
                }
                else if (newDevice.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Device", newDevice.getMessage());
                    return;
                }

                //
                // add new device to customer
                //

                List<String> deviceIDs = familyCustomer.getDeviceID();
                if (deviceIDs == null)
                    deviceIDs = new ArrayList<String>();

                deviceIDs.add(newDevice.getId());

                familyCustomer.setDeviceID(deviceIDs);
                familyCustomer.addDevice(newDevice.getId(), newDevice);

                CustomerResponse customerResponse =  WarrantixWebService.getInstance().sendPutCustomerByIdRequest(familyCustomer.getId(), familyCustomer);
                if (customerResponse == null) {
                    WarrantixApplication.getInstance().showMessage("Device", "Failed to update customer with new device");
                    return;
                }
                else if (customerResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("Device", customerResponse.getMessage());
                    return;
                }
            }
        }).start();

    }



    // -----------------------------------------------------------------------------------------------
    //
    // event handlers
    //
    // -----------------------------------------------------------------------------------------------

//    @Subscribe
//    public void onAddFamilyCustomerSuccessEvent(AddCustomerSuccessEvent event) {
//        String walletID = event.getMainWalletID();
//        Customer familyCustomer = event.getFamilyCustomer();
//
//        WarrantixPreference.warrantixConfig.addFamilyCustomer(familyCustomer);
//        WarrantixPreference.writeConfig();
//    }

//    @Subscribe
//    public void onAddFamilycustomerFailedEvent(AddCustomerFailedEvent event) {
//        String message = event.getErrorMessage();
//        WarrantixApplication.getInstance().showMessage(message);
//    }

//    @Subscribe
//    public void onAddDeviceToFamilySuccessEvent(AddDeviceToFamilySuccessEvent event) {
//        Customer familyCustomer = event.getFamilyCustomer();
//        WarrantixPreference.warrantixConfig.updateFamilyCustomer(familyCustomer);
//        WarrantixPreference.writeConfig();
//    }

//    @Subscribe
//    public void onAddDeviceToFamilyFailedEvent(AddDeviceToFamilyFailedEvent event) {
//
//    }


    //----------------------------------------------------------------------------------------------------
    //
    // CRUD screens Handlers
    //
    //

    public void getProductsResponse(String brandID) {

        final String acBrandID = brandID;

        GetProductsResponse getProductsResponse =  WarrantixWebService.getInstance().getProducts(acBrandID);
        if (getProductsResponse == null) {
            WarrantixApplication.getInstance().showMessage("Product", "Failed to get the products");
        }
        else if (getProductsResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("product", getProductsResponse.getMessage());
        } else {
            ProductsSuccessEvent successEvent = new ProductsSuccessEvent(getProductsResponse);
            BusProvider.get().post(successEvent);
        }

    }

    public  GetUsedProductsResponse getUsedProducts( String brandID) {

        final String acBrandID = brandID;

        GetUsedProductsResponse getUsedProductsResponse =  WarrantixWebService.getInstance().getUsedProducts(acBrandID);
        if (getUsedProductsResponse == null) {
            WarrantixApplication.getInstance().showMessage("UsedProduct", "Failed to get the used products");
        }
        else if (getUsedProductsResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("UsedProduct", getUsedProductsResponse.getMessage());
        }

        return getUsedProductsResponse;
    }

    public void getRelatedProducts(String type , String brandID) {

        final String acBrandID = brandID;
        final String acType = type;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetRelatedProductsResponse getRelatedProductsResponse=  WarrantixWebService.getInstance().getRelatedProducts(acType, acBrandID);
                if (getRelatedProductsResponse == null) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", "Failed to get the related products");
                }
                else if (getRelatedProductsResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", getRelatedProductsResponse.getMessage());
                } else {

                    List<Product> products = new ArrayList<>();

                    List<RelatedProduct> relatedProducts = getRelatedProductsResponse.getRelatedProducts();
                    for (int i = 0; i < relatedProducts.size(); i++){
                        GetProductResponse getProductResponse = WarrantixWebService.getInstance().getProduct(relatedProducts.get(i).getProductID());
                        products.add(getProductResponse.getProduct());
                    }

                    RelatedProductsSuccessEvent successEvent = new RelatedProductsSuccessEvent(getRelatedProductsResponse, products);
                    BusProvider.get().post(successEvent);
                }
            }
        }).start();
    }

    public GetDealersResponse getDealersResponse() {

        GetDealersResponse getDealersResponse =  WarrantixWebService.getInstance().getDealersResponse();
        if (getDealersResponse == null) {
            WarrantixApplication.getInstance().showMessage("Dealer", "Failed to get the dealers");
        }
        else if (getDealersResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("Dealer", getDealersResponse.getMessage());
        }

        return getDealersResponse;
    }

    public GetServicesResponse getServicesResponse(String type , String brandID) {

        final String acBrandID = brandID;
        final String acType = type;

        GetServicesResponse getServicesResponse=  WarrantixWebService.getInstance().getServices(acType, acBrandID);
        if (getServicesResponse == null) {
            WarrantixApplication.getInstance().showMessage("Service", "Failed to get the services");
        }
        else if (getServicesResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("Service", getServicesResponse.getMessage());
        }

        return getServicesResponse;
    }

    public GetServiceCompaniesResponse getServiceCompaniesResponse(String type , String brandID) {

        final String acBrandID = brandID;
        final String acType = type;

        GetServiceCompaniesResponse getServiceCompaniesResponse=  WarrantixWebService.getInstance().getServiceCompanies(acType, acBrandID);
        if (getServiceCompaniesResponse == null) {
            WarrantixApplication.getInstance().showMessage("ServiceCompany", "Failed to get the service companies");
        }
        else if (getServiceCompaniesResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("ServiceCompany", getServiceCompaniesResponse.getMessage());
        }

        return getServiceCompaniesResponse;
    }


    public void getProductResponse(String productID) {

        final String acProductID = productID;

        GetProductResponse getProductResponse =  WarrantixWebService.getInstance().getProduct(acProductID);
        if (getProductResponse == null) {
            WarrantixApplication.getInstance().showMessage("Product", "Failed to get the product");
        }
        else if (getProductResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("Product", getProductResponse.getMessage());
        } else {
            ProductSuccessEvent successEvent = new ProductSuccessEvent(getProductResponse);
            BusProvider.get().post(successEvent);
        }

    }

    public void getRelatedProductResponse(String _Id) {

        final String ac_Id = _Id;

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetRelatedProductResponse getRelatedProductResponse=  WarrantixWebService.getInstance().getRelatedProduct(ac_Id);
                if (getRelatedProductResponse == null) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", "Failed to get the relatedproduct");
                }
                else if (getRelatedProductResponse.getCode() != 0) {
                    WarrantixApplication.getInstance().showMessage("RelatedProduct", getRelatedProductResponse.getMessage());
                } else {

                    RelatedProductSuccessEvent successEvent = new RelatedProductSuccessEvent(getRelatedProductResponse);
                    BusProvider.get().post(successEvent);
                }

            }
        }).start();
    }

    public GetOrdersResponse getOrdersResponse() {

        GetOrdersResponse getOrdersResponse =  WarrantixWebService.getInstance().getOrdersResponse();
        if (getOrdersResponse == null) {
            WarrantixApplication.getInstance().showMessage("Order", "Failed to get the orders");
        }
        else if (getOrdersResponse.getCode() != 0) {
            WarrantixApplication.getInstance().showMessage("Order", getOrdersResponse.getMessage());
        }

        return getOrdersResponse;
    }

}

