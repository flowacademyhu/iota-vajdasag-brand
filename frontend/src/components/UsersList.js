import React, { useState, useEffect } from "react";
import ListElement from "./listofusers/ListElement";
import { fetchUsersWithApi } from "../communications/userApi";
import { useTranslation } from "react-i18next";

const UsersList = () => {
  const [users, setUsers] = useState([]);
  const { t } = useTranslation();

  useEffect(() => {
    try {
      fetchData();
    } catch (error) {
      console.log(error.response.status);
    }
  }, []);

  const fetchData = async () => {
    const response = await fetchUsersWithApi();
    setUsers(response.data.users);
  };

  return (
    <div className="d-flex flex-row-reverse">
      <div className="col-9">
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">{t("Name")}</th>
              <th scope="col">{t("Email")}</th>
              <th scope="col">{t("Accepted registration")}</th>
              <th scope="col">{t("Date of registration")}</th>
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
