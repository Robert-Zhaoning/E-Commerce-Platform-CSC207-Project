package data_access;

import use_case.add_to_cart.AddToCartUserDataAccessInterface;
import use_case.checkout.CheckoutDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.open_product.OpenProductProductDataAccessInterface;
import use_case.filter.FilterDataAccessInterface;
import use_case.manage_address.UserDataAccessInterface;
import use_case.search.SearchDataAccessInterface;
import use_case.sign_up.SignUpDataAccessInterface;
import entity.Address;
import entity.Cart;
import entity.Order;
import entity.Product;
import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;

public class DataAccessObject implements
    AddToCartUserDataAccessInterface,
    CheckoutDataAccessInterface,
    FilterDataAccessInterface,
    LoginUserDataAccessInterface,
    UserDataAccessInterface,
    OpenProductProductDataAccessInterface,
    SearchDataAccessInterface,
    SignUpDataAccessInterface {

        private final String baseUrl = "https://x8ki-letl-twmt.n7.xano.io/api:vu2PKIfe";

        /* Helper methods */

        /**
         * 
         * @param address Address object to be posted to the server
         * @return true if successful, false otherwise
         */
        public boolean postAddress(Address address) {
            OkHttpClient client = new OkHttpClient();
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("recipientName", address.getRecipientName());
                jsonBody.put("line1", address.getLine1());
                jsonBody.put("line2", address.getLine2());
                jsonBody.put("city", address.getCity());
                jsonBody.put("provinceOrState", address.getProvinceOrState());
                jsonBody.put("postalCode", address.getPostalCode());
                jsonBody.put("country", address.getCountry());
                jsonBody.put("defaultBilling", address.isDefaultBilling());
                jsonBody.put("defaultShipping", address.isDefaultShipping());
                Request request = new Request.Builder()
                    .url(baseUrl + "/address")
                    .post(okhttp3.RequestBody.create(jsonBody.toString(), okhttp3.MediaType.parse("application/json")))
                    .build();
                Response response = client.newCall(request).execute();
                return response.isSuccessful();
            }
            catch (IOException e) {
                return false;
                }
            catch (JSONException e) {
                return false;
            }
        
        }

        public HashSet<Address> getAddresses(String username) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(baseUrl + "/get_address?username=" + username)
                .build();
            try {
                Response response = client.newCall(request).execute();
                JSONArray jsonArray = new JSONArray(response.body().string());
                HashSet<Address> addresses = new HashSet<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonAddress = jsonArray.getJSONObject(i);
                    Address address = new Address(
                        jsonAddress.getString("id"),
                        jsonAddress.getString("recipientName"),
                        jsonAddress.getString("line1"),
                        jsonAddress.getString("line2"),
                        jsonAddress.getString("city"),
                        jsonAddress.getString("provinceOrState"),
                        jsonAddress.getString("postalCode"),
                        jsonAddress.getString("country"),
                        jsonAddress.getBoolean("defaultBilling"),
                        jsonAddress.getBoolean("defaultShipping")
                    );
                    addresses.add(address);
                }
                return addresses;
            }
            catch (IOException e) {
                return null;
            }
            catch (JSONException e) {
                return null;
            }
        }

        public boolean checkIfAddressExists(Address address) {
            return getAddresses(address.getRecipientName()).contains(address);
            
        }

        public Cart getCart(String username) {
            return new Cart(getUser(username));
        }

        
        /* User related methods */
    
        @Override
        public User getUser(String username) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(baseUrl + "/get_user?username=" + username)
                .build();
            try {
                Response response = client.newCall(request).execute();
                JSONObject jsonBody = new JSONObject(response.body().string());
                User user = new User(
                    jsonBody.getString("username"), 
                    jsonBody.getString("email"), 
                    jsonBody.getInt("hashedPassword"), 
                    jsonBody.getDouble("balance"), 
                    new HashSet<Address>(), 
                    new ArrayList<String>(),
                    getCart(jsonBody.getString("username"))
                );
                if (getAddresses(username) != null) {
                    for (Address address : getAddresses(username)) {
                        user.addAddress(address);
                    }
                }
                for (int i = 0; i < jsonBody.getJSONArray("previousPurchasesCategories").length(); i++) {
                    user.addCategory(jsonBody.getJSONArray("previousPurchasesCategories").getString(i));
                }

                return user;
            }
            catch (IOException e) {
                return null;
            }
            catch (JSONException e) {
                return null;
            }
        }

        @Override
        public User getUserData(String username) {
            return getUser(username);
        }

        @Override
        public void createUser(User user) {
            OkHttpClient client = new OkHttpClient();
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("username", user.getUsername());
                jsonBody.put("email", user.getEmail());
                jsonBody.put("hashedPassword", user.getHashedPassword(56734822));
                jsonBody.put("balance", user.getBalance());
                JSONArray addressesArray = new JSONArray();

                for (Address address : user.getBillingAddresses()) {
                    postAddress(address);
                    addressesArray.put(address.getId());
                }
                jsonBody.put("billingAddresses", addressesArray);
                JSONArray categoriesArray = new JSONArray();
                for (String category : user.getPreviousPurchasesCategories()) {
                    categoriesArray.put(category);
                }
                jsonBody.put("previousPurchasesCategories", categoriesArray);
                jsonBody.put("cartUUID", user.getCart().getCartUUID());

                Request request = new Request.Builder()
                    .url(baseUrl + "/user?=" + user.getUsername())
                    .post(okhttp3.RequestBody.create(jsonBody.toString(), okhttp3.MediaType.parse("application/json")))
                    .build();
                client.newCall(request).execute();
            }
            catch (IOException e) {
            }
            catch (JSONException e) {
            }
        }

        @Override
        public boolean checkUserExists(String username) {
            if (getUser(username) != null) {
                return true;
            }
            return false;
        }

        @Override
        public User get(String username) {
            return getUser(username);
        }

        @Override
        public boolean existsByName(String username) {
            return checkUserExists(username);
        }

        @Override
        public void saveUser(User user) {
            
        }

        /* Product related methods */

        @Override
        public Product getProduct(int productId) {
            return null;
        }

        @Override
        public List<Product> getAllProducts() {
            
        }

        @Override
        public void addToCart(User username, String productUUID, Integer quantity) {
            
        }

        @Override
        public void saveOrder(Order order) {
            
        }



}
