import axios from "axios";
import "./mockForUserApi";

export const getUsers = async () => {
  try {
    console.log("belemegy a tryba");
    const response = await axios.get("http://localhost:3000/api/users");
    return response.data.users;
  } catch (error) {
    console.log(error);
    //throw new Error("Failed to get users.");
  }
};

export const login = (value) => {
  return axios.post("http://localhost:3000/api/login", value);
};

export const sendApproval = async (userId) => {
  try {
    return await axios.put(`http://localhost:3000/users/${userId}/approval`);
  } catch (error) {
    throw new Error("Registration approval miscarried.");
  }
};
