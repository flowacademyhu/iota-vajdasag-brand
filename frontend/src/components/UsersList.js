import React from "react";
import ListElement from "./listofusers/ListElement";
import { useTranslation } from "react-i18next";
import useUsers from "./useUsers";

const UsersList = () => {
  const { users } = useUsers();
  const { t } = useTranslation();

  return (
      <table className="table table-striped">
        <thead>
          <tr>
            <th scope="col">{t("name")}</th>
            <th scope="col">{t("email")}</th>
            <th scope="col">{t("acceptedRegistration")}</th>
            <th scope="col">{t("dateOfRegistrion")}</th>
          </tr>
        </thead>
        <tbody>
          {users?.map((user) => (
            <ListElement user={user} key={user.id} />
          ))}
        </tbody>
      </table>
  );
};

export default UsersList;
