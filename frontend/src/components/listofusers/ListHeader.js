import React from "react";
import { useTranslation } from "react-i18next";

const ListHeader = ({onColumnClick}) => {
  const { t } = useTranslation();

  return (
    <thead>
      <tr>
        <th scope="col" onClick={() => onColumnClick("name")}>
          {t("name")}
        </th>
        <th scope="col" onClick={() => onColumnClick("email")}>
          {t("email")}
        </th>
        <th scope="col" onClick={() => onColumnClick("isApproved")}>
          {t("approvedUser")}
        </th>
        <th scope="col" onClick={() => onColumnClick("dateOfRegistration")}>
          {t("dateOfRegistration")}
        </th>
      </tr>
    </thead>
  );
};

export default ListHeader;
