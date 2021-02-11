import axios from "axios";
import mockApi from "./mockForUserApi";

export const getUsers = async (setUsers, setError) => {
  try {
    await axios.get("/users").then((response) => {
      setUsers(response.data.users);
    });
  } catch (error) {
    setError(error);
    console.log(error.response.status);
  }
};
