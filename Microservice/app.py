import json, falcon, sys
import pymysql
from datetime import datetime
from wsgiref.simple_server import make_server

conn = pymysql.connect(
    host="cs5721-project-suhasmkumar66-35d6.a.aivencloud.com",
    user="avnadmin",
    password="AVNS_9ZVRUDfaIDQXFi0H9r1",
    port=27477,
    cursorclass=pymysql.cursors.DictCursor,
    database="products",
)
cur = conn.cursor()


class RegisterClass:
    def on_post(self, req, resp):
        data = json.loads(req.stream.read())
        if (
            "first_name" in data
            and "last_name" in data
            and "username" in data
            and "password" in data
            and "PPSN" in data
            and "address" in data
            and "eir_code" in data
            and "email" in data
        ):
            sQry = "select * from `users`.customer where username = '{0}'".format(
                data["username"]
            )
            cur = conn.cursor()
            cur.execute(sQry)
            output = cur.fetchone()
            if output is not None:
                result = {"error": "username exists"}
                resp.status = falcon.HTTP_400
                resp.body = json.dumps(result)
                return
            sQry = "select * from `users`.customer where email = '{0}'".format(
                data["email"]
            )
            cur.execute(sQry)
            output = cur.fetchone()
            if output is not None:
                result = {"error": "email exists"}
                resp.status = falcon.HTTP_400
                resp.body = json.dumps(result)
                return
            now = datetime.now()
            Iqry = "INSERT INTO `users`.customer (`first_name`,`last_name`,`username`,\
				`password`,`PPSN`,`address`,`eir_code`,`email`,`role`,`created_date`) \
				values ('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}')".format(
                data["first_name"],
                data["last_name"],
                data["username"],
                data["password"],
                data["PPSN"],
                data["address"],
                data["eir_code"],
                data["email"],
                "Customer",
                now.strftime("%Y-%m-%d %H:%M:%S"),
            )
            cur.execute(Iqry)
            conn.commit()

            result = {"msg": "user registered sucessfully"}
            resp.status = falcon.HTTP_200
            resp.body = json.dumps(result)
        else:
            result = {"error": "required params missing"}
            resp.status = falcon.HTTP_400
            resp.body = json.dumps(result)


class LoginClass:
    def on_post(self, req, resp):
        data = json.loads(req.stream.read())
        print(data)
        if "username" in data and "password" in data:
            sQry = "select * from `users`.customer where username = '{0}'".format(
                data["username"]
            )
            print(sQry)
            cur = conn.cursor()
            cur.execute(sQry)
            output = cur.fetchone()
            print(output)
            if output is not None:
                if data["password"] == output["password"]:
                    result = {
                        "id": output["id"],
                        "first_name": output["first_name"],
                        "last_name": output["last_name"],
                        "PPSN": output["PPSN"],
                        "address": output["address"],
                        "eir_code": output["eir_code"],
                        "email": output["email"],
                        "role": output["role"],
                    }

                    resp.status = falcon.HTTP_200
                    resp.body = json.dumps(result)
                else:
                    result = {"error": "password is incorrect"}
                    resp.status = falcon.HTTP_200
                    resp.body = json.dumps(result)
            else:
                result = {"error": "username is incorrect"}
                resp.status = falcon.HTTP_400
                resp.body = json.dumps(result)

        else:
            result = {"error": "required params missing"}
            resp.status = falcon.HTTP_400
            resp.body = json.dumps(result)

class ProductCatalog:
    def on_post(self, req, resp):
            sQry = "select * from product_list ;"

            print(sQry)
            cur = conn.cursor()
            cur.execute(sQry)
            result_set = cur.fetchall()
            if result_set is not None:
                product_details = []
                for i in result_set:
                    product_details.append(
                        {
                            "id": i["product_id"],
                            "name": i["product_name"],
                            "product_description": i["product_description"],
                            "quantity": i["quantity"],
                        }
                    )
                resp.status = falcon.HTTP_200
                resp.body = json.dumps(product_details)
            else:
                result = {"error": "no items found in catalog."}
                resp.status = falcon.HTTP_400
                resp.body = json.dumps(result)

class ProductDetails:
    def on_post(self, req, resp):
        data = json.loads(req.stream.read())
        print(data)
        if not "product_id" in data and not "product_name" in data:
            result = {"error": "required params are missing."}
            resp.status = falcon.HTTP_400
            resp.body = json.dumps(result)
        else:
            product_id = data.get("product_id")
            product_name = data.get("product_name")
            sQry = "select * from product_list where "
            conditions = []
            if product_id:
                conditions.append(f"product_id={product_id}")
            if product_name:
                conditions.append(f"product_name='{product_name}'")
            sQry += " or ".join(conditions)

            print(sQry)
            cur = conn.cursor()
            cur.execute(sQry)
            result_set = cur.fetchall()
            if result_set is not None:
                product_details = []
                for i in result_set:
                    product_details.append(
                        {
                            "id": i["product_id"],
                            "name": i["product_name"],
                            "product_description": i["product_description"],
                            "quantity": i["quantity"],
                        }
                    )

                resp.status = falcon.HTTP_200
                resp.body = json.dumps(product_details)
            else:
                result = {"error": "no such product exists"}
                resp.status = falcon.HTTP_400
                resp.body = json.dumps(result)


api = falcon.API()
api.add_route("/register", RegisterClass())

api.add_route("/login", LoginClass())
api.add_route("/get-product-details", ProductDetails())
api.add_route("/get-product-catalog", ProductCatalog())

# remove this in prod
if __name__ == "__main__":
    with make_server("", 5000, api) as httpd:
        print("Serving on port 5000...")
        httpd.serve_forever()
