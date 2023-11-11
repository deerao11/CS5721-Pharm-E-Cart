from flask import Flask


app = Flask(__name__)


@app.route("/get-product-catalog")
def productCatalog():
    # connection to db
    # get the details from database
    # Send the data
    products= [{"name":"paracetamol","quantity":10,"amount":999}]
    return products



@app.route("/get-product-details")
def productDetails():
    # connection to db
    # get the details from database corresponding to that speciifc product
    # Send the data
    return "this is specific product"
app.run()