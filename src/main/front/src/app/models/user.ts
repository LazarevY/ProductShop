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
  cartLastDigits: number;
  cardEndDate: string;
}

export interface Gender{
  id: number;
  name: string;
}

export interface UserCalorieData{
  weight: number;
  growth: number;
  gender: Gender;
  currentNorm: number;
}

