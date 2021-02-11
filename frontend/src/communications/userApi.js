import axios from "axios";

export const fetchUsersWithApi = () => {
  return axios.get("/users");
};
