import { useState, useEffect } from "react";
import { getUsers } from "../communications/userApi";

const useUsers = (searchKeyword) => {
  const [listOfAllUsers, setListOfAllUsers] = useState([]);
  const [users, setUsers] = useState([]);

  const fetchUsers = async () => {
    const fetchedUsers = await getUsers();
    setListOfAllUsers(fetchedUsers);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const makeWordComparable = (keyword) => {
    return keyword
      .normalize("NFD")
      .replace(/[\u0300-\u036f]/g, "")
      .toUpperCase();
  };

  useEffect(() => {
    setUsers(
      listOfAllUsers?.filter((user) =>
        makeWordComparable(user.name).includes(
          makeWordComparable(searchKeyword)
        )
      )
    );
  }, [listOfAllUsers, searchKeyword]);

  return { users };
};

export default useUsers;
