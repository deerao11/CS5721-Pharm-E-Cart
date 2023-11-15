import mysql.connector

mydb = mysql.connector.connect(
    host="cs5721-project-suhasmkumar66-35d6.a.aivencloud.com",
    user="avnadmin",
    password="AVNS_9ZVRUDfaIDQXFi0H9r1",
    port=27477,
    database="products",
)

# print(mydb)
cursor = mydb.cursor()


def get_all_products():
    product_list = []
    cursor = mydb.cursor()
    cursor.execute("select * from  product_list;")
    result_set = cursor.fetchall()
    for i in result_set:
        product_list.append({"id": i[0], "name": i[1]})
    print(product_list)


# cursor.execute("create table product_list (product_id varchar(20) primary key,product_name varchar(256));")
# cursor.execute(
#     "insert into product_list(product_id,product_name) values(6,'boroplus') ;"
# )
update="""
UPDATE product_list
SET
    product_description = CASE
        WHEN product_id = 1 THEN 'for headache'
        WHEN product_id = 2 THEN 'for cold or fever'
        WHEN product_id = 3 THEN 'for allergies'
        WHEN product_id = 4 THEN 'for vomiting or infections'
        WHEN product_id = 5 THEN 'for dryness of skin'
        WHEN product_id = 6 THEN 'for dryness of skin'
    END,
    price = CASE
        WHEN product_id = 1 THEN 200
        WHEN product_id = 2 THEN 240
        WHEN product_id = 3 THEN 300
        WHEN product_id = 4 THEN 350
        WHEN product_id = 5 THEN 100
        WHEN product_id = 6 THEN 150
    END,
    quantity = CASE 
        WHEN product_id = 1 THEN 20
        WHEN product_id = 2 THEN 40
        WHEN product_id = 3 THEN 30
        WHEN product_id = 4 THEN 35
        WHEN product_id = 5 THEN 10
        WHEN product_id = 6 THEN 15
    END,
    category_id = CASE
        WHEN product_id = 1 THEN 1
        WHEN product_id = 2 THEN 1
        WHEN product_id = 3 THEN 1
        WHEN product_id = 4 THEN 1
        WHEN product_id = 5 THEN 1
        WHEN product_id = 6 THEN 1
    END




WHERE product_id IN (1,2,3,4,5,6);
"""
cursor.execute(update)
mydb.commit()
cursor.execute("select * from product_list")
for i in cursor:
    print(i)

# get_all_products()

