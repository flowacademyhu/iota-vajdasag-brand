import axios from "axios";
import "./mockForUserApi";

export const getUsers = async () => {
  try {
    const response = await axios.get("/users");
    return response.data.users;
  } catch (error) {
    throw new Error("Failed to get users.");
  }
};

export const login = (value) => {
  return axios.post("http://localhost:3000/api", value);
};

export const sendApproval = async (user) => {
  try {
    const response = await axios.put(`http://localhost:3000/users/${user.id}/approval`);
    console.log(response);
  } catch (error) {
    throw new Error("Registration approval miscarried.");
  }
};
