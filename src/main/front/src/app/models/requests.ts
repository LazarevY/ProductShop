export interface CatalogRequest {
  token: string;
  storeId: number;
  priceLow: number;
  priceHigh: number;
  namePattern: string;
  categories: Array<number>;
  userId: number;
}
