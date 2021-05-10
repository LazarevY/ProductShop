import {ParameterMap} from "./types";

export interface ServiceResponse{
  code: number;
  message: string;
  params: ParameterMap<any>;
}
