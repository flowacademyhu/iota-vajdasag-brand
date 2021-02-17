import api from "./apiInstance";
import "./mockForUserApi";

export const getUsers = async () => {
  try {
    const response = await api.get("/users");
    console.log(response.data.users);
    return response.data.users;
  } catch (error) {
    throw new Error("Failed to get users.");
  }
};

export const login = (value) => {
  return api.post("/login", value);
};

export const signUp = async (value) => {
  console.log("api");
  const response = await api.post("/registration");
  console.log(response);
  return response;
};
