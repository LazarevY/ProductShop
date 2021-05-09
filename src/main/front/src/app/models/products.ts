import {ParameterMap} from "./map";

export interface ProductMetadata{
  filename: string;
}

export interface Product{
  id: number;
  metadata: ProductMetadata;
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
  product: Product;
  countOfProduct: number;
  price: number;
  stockStore: Stock | null;
}
