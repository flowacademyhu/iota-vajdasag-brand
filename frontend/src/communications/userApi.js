import axios from "axios";
import mockApi from "./mockForUserApi";

export const fetchUsersWithApi = () => {
  return axios.get("/users");
};
