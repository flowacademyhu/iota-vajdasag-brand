import React from "react";

const ListElement = ({ elem }) => {
  return (
    <tr>
      <td>{elem.name}</td>
      <td>{elem.email}</td>
      <td>{elem.approvedUser ? "Yes" : "No"}</td>
      <td>{elem.dateOfRegistration}</td>
    </tr>
  );
};

export default ListElement;
