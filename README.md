# Inventory Microservice

A RESTful microservice to handle inventory, stock and stock history features

- Create inventory
- Update inventory
- Get all inventory

- Create stock
- Update stock

- Get stock history by inventory id

## Running locally

- Run spring application

## Running via executable jar

- Open cmd or terminal or bash
- mvn clean package
- Change directory to target/
- java -jar inventory-0.0.1-SNAPSHOT.jar
  - or whatever the version was generated during maven

## API Reference

#### Create inventory

```http
  POST /api/inventory/
```

| Parameter   | Type        | Description                    |
| :---------- | :---------- | :----------------------------- |
| `inventory` | `Inventory` | **Required**. Inventory object |

#### Update inventory

```http
  PUT /api/inventory/{id}/
```

| Parameter   | Type        | Description                             |
| :---------- | :---------- | :-------------------------------------- |
| `id`        | `long`      | **Required**. Id of inventory to modify |
| `inventory` | `Inventory` | **Required**. Inventory object          |

#### Get all inventory

```http
  GET /api/inventory/all/
```

| Parameter   | Type  | Description                              |
| :---------- | :---- | :--------------------------------------- |
| `page`      | `int` | **Required**. Page to retrieve           |
| `maxResult` | `int` | **Required**. Number of records per page |

#### Create stock

```http
  POST /api/stock/
```

| Parameter | Type    | Description                |
| :-------- | :------ | :------------------------- |
| `stock`   | `Stock` | **Required**. Stock object |

#### Update stock

```http
  PUT /api/inventory/{id}/
```

| Parameter | Type    | Description                         |
| :-------- | :------ | :---------------------------------- |
| `id`      | `long`  | **Required**. Id of stock to modify |
| `stock`   | `Stock` | **Required**. Stock object          |

#### Get stock history by inventory id

```http
  GET /api/stockhistory/{inventoryId}/
```

| Parameter     | Type   | Description                              |
| :------------ | :----- | :--------------------------------------- |
| `inventoryId` | `long` | **Required**. Id of inventory requested  |
| `page`        | `int`  | **Required**. Page to retrieve           |
| `maxResult`   | `int`  | **Required**. Number of records per page |
