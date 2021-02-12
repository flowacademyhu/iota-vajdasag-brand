import axios from "axios";
import "./mockForUserApi";
import { signUpMock } from "./mockForUserApi";

export const getUsers = async () => {
  try {
    const response = await axios.get("/users");
    console.log(response.data.users);
    return response.data.users;
  } catch (error) {
    throw new Error("Failed to get users.");
  }
};

export const login = (value) => {
  return axios.post("http://localhost:3000/api/login", value);
};

export const signUp = async (value) => {
  console.log("api");
  signUpMock();
  const response = await axios.post(
    "http://localhost:3000/api/registration",
    value
  );
  console.log(response);
  return response;
};
