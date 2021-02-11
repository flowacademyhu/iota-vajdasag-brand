import { useState, useEffect } from "react";
import { getUsers } from "../communications/userApi";

const useUsers = () => {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState();

  const fetchUsers = () => {
    getUsers(setUsers);
  };

  useEffect(() => {
    try {
      fetchUsers();
    } catch (e) {
      setError(e);
      console.log(error.response.status);
    }
  }, []);

  return { users, fetchUsers };
};

export default useUsers;
