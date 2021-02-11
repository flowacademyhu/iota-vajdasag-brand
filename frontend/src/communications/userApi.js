import axios from "axios";

export const login = (value) => {
  return axios.post("http://localhost:3000/api", value);
};

export const signUp = (value) => {
  return axios.post("http://localhost:3000/api", value);
};
