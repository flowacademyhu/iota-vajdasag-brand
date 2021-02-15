import React from "react";
import { useTranslation } from "react-i18next";

const ListHeader = ({ setSortKey, setAscendingSort, isSortAscending }) => {
  const { t } = useTranslation();

  const handleChange = (value) => {
    setAscendingSort(!isSortAscending);
    setSortKey(value);
  };

  return (
    <thead>
      <tr>
        <th scope="col" onClick={() => handleChange("name")}>
          {t("name")}
        </th>
        <th scope="col" onClick={() => handleChange("email")}>
          {t("email")}
        </th>
        <th scope="col" onClick={() => handleChange("isApproved")}>
          {t("approvedUser")}
        </th>
        <th scope="col" onClick={() => handleChange("dateOfRegistration")}>
          {t("dateOfRegistration")}
        </th>
        <th></th>
      </tr>
    </thead>
  );
};

export default ListHeader;
