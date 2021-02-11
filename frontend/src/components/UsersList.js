import React, { useState, useEffect } from "react";
import ListElement from "./listofusers/ListElement";
import { fetchUsersWithApi } from "../communications/userApi";
import { fetchMockUsersForListing } from "../communications/mockForUserApi";

fetchMockUsersForListing();

const UsersList = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch();
  }, []);

  const fetch = async () => {
    const response = await fetchUsersWithApi();
    setUsers(response.data.users);
  };

  return (
    <div className="d-flex flex-row-reverse">
      <div className="col-9">
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Email</th>
              <th scope="col">Accepted registration</th>
              <th scope="col">Date of registration</th>
            </tr>
          </thead>
          <tbody>
            {users?.map((elem) => (
              <ListElement elem={elem} key={elem.id} />
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default UsersList;
