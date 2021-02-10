import React, { useState, useEffect } from "react";
import axios from "axios";
import MockAxios from "axios-mock-adapter";

const UsersList = () => {
  const [users, setUsers] = useState([]);

  const mock = new MockAxios(axios);

  mock.onGet("/users").reply(200, {
    users: [
      {
        id: 1,
        name: "John",
        email: "j@trade.com",
        approvedUser: true,
        dateOfRegistration: 2020,
      },
      {
        id: 2,
        name: "Karl",
        email: "k@trade.com",
        approvedUser: false,
        dateOfRegistration: 2021,
      },
    ],
  });

  useEffect(() => {
    axios.get("/users").then((response) => {
      setUsers(response.data.users);
    });
  }, []);

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
              <tr>
                <td>{elem.name}</td>
                <td>{elem.email}</td>
                <td>{elem.approvedUser ? "Yes" : "No"}</td>
                <td>{elem.dateOfRegistration}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default UsersList;
