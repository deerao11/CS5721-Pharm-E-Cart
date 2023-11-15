from flask import Flask,request,jsonify
import mysql.connector


app = Flask(__name__)


def getDbConnection():
    mydb = mysql.connector.connect(
        host="cs5721-project-suhasmkumar66-35d6.a.aivencloud.com",
        user="avnadmin",
        password="AVNS_9ZVRUDfaIDQXFi0H9r1",
        port=27477,
        database="products",
    )
    return mydb


@app.route("/get-product-catalog")
def productCatalog():
    # connection to db
    # get the details from database
    # Send the data

    #  products=[{"name":"paracetamol","quantity":10,"amount":999}]
    # return products
    mydb = None
    product_list = []
    try:
        mydb = getDbConnection()
        cursor = mydb.cursor()
        cursor.execute("select * from  product_list;")
        result_set = cursor.fetchall()
        for i in result_set:
            product_list.append({"id": i[0], "name": i[1]})
    except Exception as ex:
        print("error ", ex)
    if mydb:
        mydb.close()
    return product_list


@app.route("/get-product-details")
def productDetails():
    # connection to db
    
    mydb = None
    product_details = []
    try:
        mydb = getDbConnection()
        cursor = mydb.cursor()
        # for Query parameters : 
        product_id = request.args.get("product_id")
        product_name = request.args.get("product_name")
        if not product_id and not product_name:
            raise ValueError("Please provide product id or product name")

        q = "select * from product_list where "
        conditions = []
        if product_id:
            conditions.append(f"product_id={product_id}")
        if product_name:
            conditions.append(f"product_name='{product_name}'")

        q += " or ".join(conditions)
        
        cursor.execute(q)
        
        # get the details from database corresponding to that speciifc product
        # Send the data
        result_set = cursor.fetchall()
        for i in result_set:
            product_details.append({"id": i[0], "name": i[1], "product description": i[2], "quantity": i[3] })
    except Exception as ex:
        print("Error:", ex)

    if mydb:
        mydb.close()

    # 1. get the product id and(or) name from request
    # 2. validate whether the given input in reuqtes is valid or not if not throw appropriate error
    # 3. if valid then query db and get the product detail
    # 4. if product exists send the product data
    # 5. else send appropriate message
    # 6. try/catch
    # 7. insert at keast 5 products
    # 8. modify schema of table to include amount and quantity, product description,category id(1 as over the counter 2 as prescribed)

    return jsonify(product_details)


app.run()
