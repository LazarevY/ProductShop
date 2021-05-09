import {ParameterMap} from "./map";

export interface ServiceResponse{
  code: number;
  message: string;
  params: ParameterMap<any>;
}
