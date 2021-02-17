import api from "./apiInstance";
import "./mockForUserApi";

export const getUsers = async () => {
  try {
    const response = await api.get("/users");
    return response.data.users;
  } catch (error) {
    throw new Error("Failed to get users.");
  }
};

export const login = (value) => {
  return api.post("/login", value);
};

export const signUp = async (value) => {
  const response = await api.post("/registration");
  api.post("/login", value);
  return response;
};

export const sendApproval = async (userId) => {
  try {
    return await api.put(`/users/${userId}/approval`);
  } catch (error) {
    throw new Error("Registration approval miscarried.");
  }
};
