import React from "react";
import { useTranslation } from "react-i18next";

const ListElement = ({ elem }) => {
  const { t } = useTranslation();

  return (
    <tr>
      <td>{elem.name}</td>
      <td>{elem.email}</td>
      <td>{elem.approvedUser ? t("Yes") : t("No")}</td>
      <td>{elem.dateOfRegistration}</td>
    </tr>
  );
};

export default ListElement;
