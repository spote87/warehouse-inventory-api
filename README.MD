# Warehouse Inventory APIs

This is web application implemented using Spring Boot. This application manages warehouse inventory.

## Functionality

This application exposes below REST endpoints to manage warehouse inventory.

**1. **POST : /warehouseinventory/v1/load****

    This endpoint accepts inventory.json and product.json files as form data input. Generally this service is used by admins to load inventory data in system.

    Input to this service will be form data with key=file and value=one of the above mentioned json files.

**2. **GET: /warehouseinventory/v1/products****

    This endpoint returns all the products with associated articles present in database.

    Response Structure looks like:

        [{"name":"Dining Chair","productId":1,"articles":[{"name":"screw","stock":17,"art_id":1 }]}]

**3. **DELETE: warehouseinventory/v1/product/{productId}****

    This endpoint sales(removes) specified product.
    If specified product id is not present or there are not enough articles to assemble product with, it will return error with proper message.

## System Design

Application is built using Spring Boot with JPA and in memory H2 database along with Java 11. Maven is used for building
the application.

Application takes _inventory.json_ and _products.json_ input. So I have created single endpoint for this and have
implemented strategy pattern as we have to chose algorithm at runtime based on file name. In future if we want to add
some other data in inventory, we can create separate strategy without touching existing code.

## How to Run Application

1. Clone this repository by executing below command

        git clone https://github.com/spote87/warehouse-inventory-api.git

2. Go to directory where you have checked out code and run below commands

        mvn clean install

        mvn spring-boot:run

   Second command will launch application. Once application is started, We need to load inventory data. So call below
   service twice with **key**=_file_ and **value**=_inventory.json/products.json_ respectively(first inventory and then
   products).

        http://localhost:8080/warehouseinventory/v1/load

   _Input_: form data

            key:file
            value: inventory.json/products.json

**Note**: inventory.json and products.json files are present in *src/main/resources/*

Once data is loaded, you can list and sale products by calling below services:

        List:
          http://localhost:8080//warehouseinventory/v1/products

        Sale:
          http://localhost:8080//warehouseinventory/v1/products/{productId}
          e.g:
            http://localhost:8080//warehouseinventory/v1/1

