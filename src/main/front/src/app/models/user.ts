import {Product} from './products';

export class User {
  id = 0;
  firstName = '';
  lastName = '';
  phone = '';
  email = '';
}

export class UserUpdateData {
  firstName = '';
  lastName = '';
  phone = '';
  token = '';
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
  id: number;
  address: string;
}

export interface UserAddAddress{
  id: number;
  address: string;
  token: string;
}

export interface DeleteUserAddress{
  id: number;
  token: string;
}

export interface UserAddPayMethod{
  id: number;
  card: string;
  token: string;
  date: string;
}

export interface DeleteUserPayMethod{
  id: number;
  token: string;
}

export interface UserPayMethod{
  id: number;
  cardLastDigits: string;
  cardEndDate: string;
}

export interface UserPayMethodFull{
  id: number;
  cardNumber: string;
  dateEnd: string;
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

