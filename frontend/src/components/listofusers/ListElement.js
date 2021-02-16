import React from "react";
import { useTranslation } from "react-i18next";

const ListElement = ({ user }) => {
  const { t } = useTranslation();

  const ApproveButton = (user) => {
    return user.isApproved ? (
      <button type="button" class="btn btn-success" disabled>
        {t("approve")}
      </button>
    ) : (
      <button type="button" class="btn btn-success">
        {t("approve")}
      </button>
    );
  };

  return (
    <tr>
      <td>{user.name}</td>
      <td>{user.email}</td>
      <td>{user.isApproved ? t("Yes") : t("No")}</td>
      <td>{user.dateOfRegistration}</td>
      <td> {ApproveButton(user)} </td>
    </tr>
  );
};

export default ListElement;
