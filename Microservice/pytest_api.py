import requests
import json

ENDPOINT = "https://falconer2-71714182580c.herokuapp.com"

response = requests.get(ENDPOINT)
print(response)

def test_get_products():
    payload = {
        "category_id":1
    }
    response = requests.post(ENDPOINT + "/get-products", json=payload)
    assert response.status_code == 200

def test_search_products():
    payload = {
       "category_id":1,
        "search_key":"cro"
    }
    response = requests.post(ENDPOINT + "/search-products", json=payload)
    assert response.status_code == 200


def test_login():
    payload = {
       "username":"roxy",
       "password":"roxy@123"
    }
    response = requests.post(ENDPOINT + "/login", json=payload)
    assert response.status_code == 200



def test_register():
    payload = {
        "first_name":"Suhas",
        "last_name":"Mohan Kumar",
        "username":"roxy2",
        "password":"cm94eUAxMjNyb3h5QDEyMw==",
        "PPSN":"123456",
        "address":"India",
        "eir_code":"V94 PK66",
        "email":"suhasdb005@gmail.com"
    }
    response = requests.post(ENDPOINT + "/register", json=payload)
    assert response.status_code == 200

def test_addtocart():
    payload = json.dumps([
  {
    "category_id": 1,
    "product_id": 1,
    "customer_id": 1,
    "quantity": 2,
    "price": 110.1
  },
  {
    "category_id": 1,
    "product_id": 2,
    "customer_id": 1,
    "quantity": 2,
    "price": 210.1
  }
])
    response = requests.post(ENDPOINT + "/addtocart", data=payload)
    assert response.status_code == 200


def test_placeOrder():
    payload = {
       "order_number":"BB3C6ACB09",
       "delivery_type":"Priority Delivery",
       "customer_id":1
    }
    response = requests.post(ENDPOINT + "/placeOrder", json=payload)
    assert response.status_code == 200


def test_updateOderStatus():
    payload = {
       "customer_id":1,
        "order_number":"E0804D6C19",
        "order_status":"Confirmed"
    }
    response = requests.post(ENDPOINT + "/updateOderStatus", json=payload)
    assert response.status_code == 200

def test_updateInventory():
    payload = {
         "order_number":"E0804D6C19"
    }
    response = requests.post(ENDPOINT + "/updateInventory",json=payload)
    assert response.status_code == 200

def test_getCustomerOrders():
    payload = {
       "customer_id": 1
    }
    response = requests.post(ENDPOINT + "/getCustomerOrders", json=payload)
    assert response.status_code == 200


def test_getInventoryProducts():
    payload = {
         "test":"test"
    }
    response = requests.post(ENDPOINT + "/getInventoryProducts",json=payload)
    assert response.status_code == 200



def test_updateInventoryPharmacists():
    payload = {
        "pharmacist_id":14
    }
    response = requests.post(ENDPOINT + "/updateInventoryPharmacists",json=payload)
    assert response.status_code == 200

    