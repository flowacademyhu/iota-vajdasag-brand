import { useState, useEffect } from "react";
import { getUsers } from "../communications/userApi";

const useUsers = () => {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState();

  const fetchUsers = () => {
    getUsers(setUsers, setError);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return { users, fetchUsers };
};

export default useUsers;
