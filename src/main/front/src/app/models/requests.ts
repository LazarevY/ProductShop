export interface CatalogRequest {
  token: string;
  storeId: number;
  priceLow: number;
  priceHigh: number;
  namePattern: string;
  categories: Array<number>;
  userId: number;
}


export interface ProductDataRequest {
  storeId: number;
  productId: number;
}

export interface ProductsDataRequest {
  storeId: number;
  productIds: Array<number>;
}


export interface UserAddressesRequest {
  token: string;
}
export interface UserAddressRequest {
  id: number;
  token: string;
}
