import axios from "axios";
import "./mockForUserApi";

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
  return axios.post("/login", value);
};

export const signUp = async (value) => {
  console.log("api");
  const response = await axios.post("http://localhost:3000/api/registration");
  console.log(response);
  return response;
};
