import React from "react";
import { useTranslation } from "react-i18next";
import { SortDown, SortUpAlt } from "react-bootstrap-icons";

const ListHeader = ({ onColumnClick, isSortAscending, sortKey }) => {
  const { t } = useTranslation();

  const ProductHeaders = ["title", "address", "city", "category"];

  const SortingSign = (value) => {
    if (sortKey === value) {
      return isSortAscending ? <SortUpAlt /> : <SortDown />;
    }
  };

  return (
    <thead>
      <tr>
        <th scope="col" onClick={() => onColumnClick("title")}>
          {t("title")}
          {SortingSign("title")}
        </th>
        <th scope="col" onClick={() => onColumnClick("address")}>
          {t("address")}
          {SortingSign("address")}
        </th>
        <th scope="col" onClick={() => onColumnClick("city")}>
          {t("city")}
          {SortingSign("city")}
        </th>
        <th scope="col" onClick={() => onColumnClick("category")}>
          {t("category")}
          {SortingSign("category")}
        </th>
      </tr>
    </thead>
  );
};

export default ListHeader;
