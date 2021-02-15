import axios from "axios";
import "./mockForUserApi";

export const getUsers = async () => {
  try {
    const response = await axios.get("http://localhost:3000/api/users");
    console.log(response.data.users);
    return response.data.users;
  } catch (error) {
    console.log(error)
    throw new Error("Failed to get users.");
  }
};

export const login = (value) => {
  return axios.post("http://localhost:3000/api/login", value);
};
