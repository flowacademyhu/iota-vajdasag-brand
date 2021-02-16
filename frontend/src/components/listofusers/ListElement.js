import React from "react";
import { useTranslation } from "react-i18next";
import useUsers from "../useUsers";

const ListElement = ({ user }) => {
  const { t } = useTranslation();
  const { sendRegistrationApproval } = useUsers();

  const ApproveButton = (user) => {
    return user.isApproved ? (
      <button type="button" className="btn btn-success" disabled>
        {t("approveRegistration")}
      </button>
    ) : (
      <button
        type="button"
        className="btn btn-success"
        onClick={() => sendRegistrationApproval(user)}
      >
        {t("approveRegistration")}
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
