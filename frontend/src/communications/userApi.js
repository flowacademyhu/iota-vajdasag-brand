import axios from "axios";
import mockApi from "./mockForUserApi";

export const getUsers = async () => {
  try {
    const response = await axios.get("/users");
    return response.data.users;
  } catch (error) {
    throw new Error("Failed to get users.");
  }
};

export const login = (value) => {
   return axios.post("http://localhost:3000/api", value)
}
