import axios from "axios";
import { fetchMockUsersForListing } from "./mockForUserApi";

export const fetchUsersWithApi = () => {
  fetchMockUsersForListing();
  return axios.get("/users");
};
