import axios from "axios";
import mockApi from "./mockForUserApi";

export const getUsers = async (setUsers) => {
  await axios.get("/users").then((response) => {
    setUsers(response.data.users);
  });
};
