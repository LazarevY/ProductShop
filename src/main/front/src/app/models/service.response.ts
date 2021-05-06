export interface ParameterMap{
  [key: string]: any;
}

export interface ServiceResponse{
  code: number;
  message: string;
  params: ParameterMap;
}
