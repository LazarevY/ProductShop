export interface User {
  id: number;
  firstName: string;
  lastName: string;
  phone: string;
  email: string;
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
}

