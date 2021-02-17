import React from "react";
import { useTranslation } from "react-i18next";

const ListElement = ({ user }) => {
  const { t } = useTranslation();

  return (
    <tr>
      <td>{user.name}</td>
      <td>{user.email}</td>
      <td>{user.isApproved ? t("yes") : t("no")}</td>
      <td>{user.dateOfRegistration}</td>
    </tr>
  );
};

export default ListElement;
