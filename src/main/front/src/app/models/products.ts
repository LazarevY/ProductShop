import {ParameterMap} from "./types";

export interface ProductMetadata{
  productFileName: string;
}

export interface Product{
  id: number;
  name: string;
  description: string;
  weight: number;
  calories: number;
  metadata: ProductMetadata;
}

export interface StockType{
  id: number;
  type: string;
}

export interface StockClauseItem{
  id: number;
  name: string;

}

export interface StockClause{
  id: number;
  stockClauseItem: StockClauseItem;
  clauseValue: string;
}

export interface Stock{
  id: number;
  type: StockType;
  stockClauses: Array<StockClause>;
}

export interface StoreProduct{
  product_id: number;
  store_id: number;
  product: Product;
  count: number;
  price: number;
  actualPrice: number;
  stock: Stock | null;
}

export interface ProductOrderItem{
  productId: number;
  count: number;
}

export interface  ProductOrder{
  storeId: number;
  products: Array<ProductOrderItem>;
}

export interface  ProductRegisterOrder{
  storeId: number;
  commonPrice: number;
  stockPrice: number;
  token: string;
  products: Array<ProductOrderItem>;
}


export interface ProductCategory{
  id: number;
  name: string;
}
