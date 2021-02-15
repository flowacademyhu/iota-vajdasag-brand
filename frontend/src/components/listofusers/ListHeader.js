import React from "react";
import { useTranslation } from "react-i18next";
import { SortDown, SortUpAlt } from "react-bootstrap-icons";

const ListHeader = ({ onColumnClick, isSortAscending, sortKey }) => {
  const { t } = useTranslation();

  const SortingSign = (value) => {
    if (sortKey === value) {
      return isSortAscending ? <SortUpAlt /> : <SortDown />;
    }
  };

  return (
    <thead>
      <tr>
        <th scope="col" onClick={() => onColumnClick("name")}>
          {t("name")}
          {SortingSign("name")}
        </th>
        <th scope="col" onClick={() => onColumnClick("email")}>
          {t("email")}
          {SortingSign("email")}
        </th>
        <th scope="col" onClick={() => onColumnClick("isApproved")}>
          {t("approvedUser")}
          {SortingSign("isApproved")}
        </th>
        <th scope="col" onClick={() => onColumnClick("dateOfRegistration")}>
          {t("dateOfRegistration")}
          {SortingSign("dateOfRegistration")}
        </th>
      </tr>
    </thead>
  );
};

export default ListHeader;
