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
}

export interface StockType{
  id: number;
  type: string;
}

export interface StockClauseItem{
  id: number;
  value: string;
}

export interface StockClause{
  id: number;
  item: StockClauseItem;
}

export interface Stock{
  id: number;
  type: StockType;
  values: ParameterMap<StockClause>;
}

export interface StoreProduct{
  product_id: number;
  store_id: number;
  product: Product;
  metadata: ProductMetadata;
  count: number;
  price: number;
  stockStore: Stock | null;
}

export interface Store{
  id: number;
  address: string;
}
