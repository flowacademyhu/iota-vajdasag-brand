import React, { useState } from "react";
import ListElement from "./listofusers/ListElement";
import { useTranslation } from "react-i18next";
import useUsers from "./useUsers";
import Searchbar from "./listofusers/Searchbar";

const UsersList = () => {
  const [searchKeyword, setSearchKeyword] = useState("");
  const { users } = useUsers();
  const { t } = useTranslation();

  return (
    <div className="d-flex flex-row-reverse">
      <div className="col-9">
        <Searchbar setSearchKeyword={setSearchKeyword} />
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
            {users
              ?.filter((user) =>
                user.name
                  .normalize("NFD")
                  .replace(/[\u0300-\u036f]/g, "")
                  .toUpperCase()
                  .includes(
                    searchKeyword
                      .normalize("NFD")
                      .replace(/[\u0300-\u036f]/g, "")
                      .toUpperCase()
                  )
              )
              .map((user) => (
                <ListElement user={user} key={user.id} />
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default UsersList;
