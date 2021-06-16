import {Product} from './products';

export class User {
  id = 0;
  firstName = '';
  lastName = '';
  phone = '';
  email = '';
}

export interface RegistrationUser {
  id: number;
  firstName: string;
  lastName: string;
  phone: string;
  email: string;
  password: string;
}

export interface LoginUser {
  email: string;
  password: string;
}

export interface UserAddress{
  addressId: number;
  address: string;
}

export interface UserPayMethod{
  id: number;
  cardLastDigits: string;
  cardEndDate: string;
}

export interface UserPayMethodFull{
  id: number;
  card: string;
  cardEndDate: string;
}

export interface Gender{
  id: number;
  name: string;
}

export interface UserCalorieData{
  age: number;
  weight: number;
  growth: number;
  gender: Gender;
  currentNorm: number;
  funcEnable: boolean;
  token: string;
}

export interface UserOrderItem{
  orderId: number;
  productId: number;
  countOfProducts: number;
  product: Product;
}

export interface UserOrder{
  id: number;
  orderStatusId: number;
  storeId: number;
  commonPrice: number;
  stockPrice: number;
  date: string;
  orderProducts: Array<UserOrderItem>;
}

