import React from "react";
import { useTranslation } from "react-i18next";

const ListElement = ({ product }) => {
  const { t } = useTranslation();

  return (
    <tr>
      <td>{product.title}</td>
      <td>{product.address}</td>
      <td>{product.city}</td>
      <td>{product.category}</td>
      {/*
      <td>
        <ApproveButton user={user} />
      </td>
      */}
    </tr>
  );
};

export default ListElement;
