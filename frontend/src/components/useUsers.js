import { useState, useEffect } from "react";
import { getUsers } from "../communications/userApi";

const useUsers = () => {
  const [users, setUsers] = useState([]);

  const fetchUsers = async () => {
    const fetchedUsers = await getUsers();
    setUsers(fetchedUsers);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return { users };
};

export default useUsers;
